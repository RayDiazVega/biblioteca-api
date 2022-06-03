package com.ceiba.biblioteca.prestamo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

  @NotNull(message = "isbn is required")
  @Pattern(regexp = "[a-zA-Z\\d]{1,10}", message = "isbn must be a valid value")
  @Column(nullable = false)
  private String isbn;

  @NotNull(message = "identificaciónUsuario is required")
  @Pattern(regexp = "[a-zA-Z\\d]{1,10}", message = "identificaciónUsuario must be a valid value")
  @Column(nullable = false)
  private String identificacionUsuario;

  @NotNull(message = "tipoUsuario is required")
  @Pattern(regexp = "[123]", message = "Tipo de usuario no permitido en la biblioteca")
  @Column(nullable = false)
  private String tipoUsuario;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
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

  public String getTipoUsuario() {
    return tipoUsuario;
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
