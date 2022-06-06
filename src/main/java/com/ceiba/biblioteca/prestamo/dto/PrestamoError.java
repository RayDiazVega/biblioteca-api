package com.ceiba.biblioteca.prestamo.dto;

public class PrestamoError {

  private String mensaje;

  public PrestamoError() {
  }

  public PrestamoError(String mensaje) {
    this.mensaje = mensaje;
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }
}
