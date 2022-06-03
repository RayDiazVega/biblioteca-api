package com.ceiba.biblioteca.prestamo.application;

import com.ceiba.biblioteca.prestamo.dto.Prestamo;
import com.ceiba.biblioteca.prestamo.infrastructure.ports.PrestamoRepository;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrestamoService {

  @Autowired
  private PrestamoRepository prestamoRepository;

  public Map<String, Object> save(Prestamo prestamo) {
    prestamo.setId(0L);
    prestamo.setFechaMaximaDevolucion(calcularFechaMaximaDevolucion(prestamo.getTipoUsuario()));
    prestamo = prestamoRepository.save(prestamo);
    Map<String, Object> response = new HashMap<>();
    response.put("id", prestamo.getId());
    response.put("fechaMaximaDevolucion", prestamo.getFechaMaximaDevolucion().format(
        DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    return response;
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
