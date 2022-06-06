package com.ceiba.biblioteca.prestamo.application;

import com.ceiba.biblioteca.prestamo.dto.Prestamo;
import com.ceiba.biblioteca.prestamo.dto.PrestamoResponse;
import com.ceiba.biblioteca.prestamo.infrastructure.ports.PrestamoRepository;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrestamoService {

  private static final int USUARIO_AFILIADO = 1;
  private static final int USUARIO_EMPLEADO = 2;
  private static final int USUARIO_INVITADO = 3;
  private static final int DIAS_USUARIO_AFILIADO = 10;
  private static final int DIAS_USUARIO_EMPLEADO = 8;
  private static final int DIAS_USUARIO_INVITADO = 7;

  @Autowired
  private PrestamoRepository prestamoRepository;

  public PrestamoResponse save(Prestamo prestamo) {
    validarUsuarioInvitado(prestamo);
    prestamo.setId(0L);
    prestamo.setFechaMaximaDevolucion(calcularFechaMaximaDevolucion(prestamo.getTipoUsuario()));
    prestamo = prestamoRepository.save(prestamo);
    return new PrestamoResponse(prestamo.getId(), prestamo.getFechaMaximaDevolucion());
  }

  private void validarUsuarioInvitado(Prestamo prestamo) {
    if (prestamo.getTipoUsuario() == USUARIO_INVITADO) {
      if (prestamoRepository.existsByIdentificacionUsuario(prestamo.getIdentificacionUsuario())) {
        throw new IllegalArgumentException(
            "El usuario con identificación " + prestamo.getIdentificacionUsuario()
                + " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo");
      }
    }
  }

  private LocalDate calcularFechaMaximaDevolucion(Long tipoUsuario) {
    int days = tipoUsuario == USUARIO_AFILIADO ? DIAS_USUARIO_AFILIADO
        : tipoUsuario == USUARIO_EMPLEADO ? DIAS_USUARIO_EMPLEADO : DIAS_USUARIO_INVITADO;
    LocalDate fechaMaximaDevolucion = LocalDate.now();
    for (int i = 0; i < days; i++) {
      fechaMaximaDevolucion = fechaMaximaDevolucion.plusDays(1);
      if (fechaMaximaDevolucion.getDayOfWeek() == DayOfWeek.SATURDAY ||
          fechaMaximaDevolucion.getDayOfWeek() == DayOfWeek.SUNDAY) {
        i--;
      }
    }
    return fechaMaximaDevolucion;
  }

  public Prestamo findById(Long id) {
    return prestamoRepository.findById(id)
        .orElseThrow(() -> new NoSuchElementException("No se encontro prestamo con id " + id));
  }
}
