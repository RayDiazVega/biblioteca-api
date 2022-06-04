package com.ceiba.biblioteca.prestamo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDate;

public class PrestamoResponse {

  private Long id;

  @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
  private LocalDate fechaMaximaDevolucion;

  public PrestamoResponse() {
  }

  public PrestamoResponse(Long id, LocalDate fechaMaximaDevolucion) {
    this.id = id;
    this.fechaMaximaDevolucion = fechaMaximaDevolucion;
  }

  public Long getId() {
    return id;
  }

  public LocalDate getFechaMaximaDevolucion() {
    return fechaMaximaDevolucion;
  }
}
