package com.ceiba.biblioteca.prestamo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "prestamos")
public class Prestamo {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "isbn es obligatorio")
  @Pattern(regexp = "[a-zA-Z\\d]{1,10}", message = "isbn debe ser un valor valido")
  @Column(nullable = false)
  private String isbn;

  @NotNull(message = "identificaciónUsuario es obligatorio")
  @Pattern(regexp = "[a-zA-Z\\d]{1,10}", message = "identificaciónUsuario debe ser un valor valido")
  @Column(nullable = false)
  private String identificacionUsuario;

  @NotNull(message = "tipoUsuario es obligatorio")
  @Pattern(regexp = "[123]", message = "Tipo de usuario no permitido en la biblioteca")
  @JsonFormat(shape = Shape.NUMBER)
  @Column(nullable = false)
  private String tipoUsuario;

  @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
  @Column(nullable = false)
  private LocalDate fechaMaximaDevolucion;

  public Prestamo() {
  }

  public Long getId() {
    return id;
  }

  public String getIsbn() {
    return isbn;
  }

  public String getIdentificacionUsuario() {
    return identificacionUsuario;
  }

  public Long getTipoUsuario() {
    return Long.valueOf(tipoUsuario);
  }

  public LocalDate getFechaMaximaDevolucion() {
    return fechaMaximaDevolucion;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public void setIdentificacionUsuario(String identificacionUsuario) {
    this.identificacionUsuario = identificacionUsuario;
  }

  public void setTipoUsuario(String tipoUsuario) {
    this.tipoUsuario = tipoUsuario;
  }

  public void setFechaMaximaDevolucion(LocalDate fechaMaximaDevolucion) {
    this.fechaMaximaDevolucion = fechaMaximaDevolucion;
  }
}
