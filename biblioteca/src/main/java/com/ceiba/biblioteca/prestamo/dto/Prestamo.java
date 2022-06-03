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
@Table(name = "users")
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
  @Pattern(regexp = "[123]", message = "only values 1, 2 and 3 are allowed for tipoUsuario")
  @Column(nullable = false)
  private Integer tipoUsuario;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  @Column(nullable = false)
  private LocalDate fechaMaximaDevolucion;
}
