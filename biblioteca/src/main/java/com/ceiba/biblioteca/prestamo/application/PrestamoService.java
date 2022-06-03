package com.ceiba.biblioteca.prestamo.application;

import com.ceiba.biblioteca.prestamo.dto.Prestamo;
import com.ceiba.biblioteca.prestamo.dto.PrestamoResponse;
import com.ceiba.biblioteca.prestamo.infrastructure.ports.PrestamoRepository;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrestamoService {

  @Autowired
  private PrestamoRepository prestamoRepository;

  public PrestamoResponse save(Prestamo prestamo) {
    prestamo.setId(0L);
    prestamo.setFechaMaximaDevolucion(calcularFechaMaximaDevolucion(prestamo.getTipoUsuario()));
    prestamo = prestamoRepository.save(prestamo);
    return new PrestamoResponse(prestamo.getId(), prestamo.getFechaMaximaDevolucion().format(
        DateTimeFormatter.ofPattern("dd/MM/yyyy")));
  }

  private LocalDate calcularFechaMaximaDevolucion(Long tipoUsuario) {
    int days = tipoUsuario == 1 ? 10 : tipoUsuario == 2 ? 8 : 7;
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
    return prestamoRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
  }
}
