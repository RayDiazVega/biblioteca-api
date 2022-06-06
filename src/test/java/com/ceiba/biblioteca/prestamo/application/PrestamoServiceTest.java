package com.ceiba.biblioteca.prestamo.application;

import com.ceiba.biblioteca.prestamo.dto.Prestamo;
import com.ceiba.biblioteca.prestamo.dto.PrestamoResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest
class PrestamoServiceTest {

  public static final String USUARIO_AFILIADO = "1";
  public static final String USUARIO_EMPLEADO = "2";
  public static final String USUARIO_INVITADO = "3";

  @SpyBean
  private PrestamoService prestamoService;

  @Test
  void prestamoLibroUsuarioAfiliadoDeberiaAlmacenarCorrectamenteYCalcularFechaDeDevolucion() {
    PrestamoResponse prestamoResponse = prestamoService.save(
        new Prestamo("ASDA7884", "974148", USUARIO_AFILIADO));

    Assertions.assertNotNull(prestamoResponse);

    Prestamo prestamo = prestamoService.findById(prestamoResponse.getId());

    Assertions.assertEquals(prestamoResponse.getFechaMaximaDevolucion(), prestamo.getFechaMaximaDevolucion());
  }

  @Test
  void prestamoLibroUsuarioEmpleadoDeberiaAlmacenarCorrectamenteYCalcularFechaDeDevolucion() {
    PrestamoResponse prestamoResponse = prestamoService.save(
        new Prestamo("ASDA7884", "974148", USUARIO_EMPLEADO));

    Assertions.assertNotNull(prestamoResponse);

    Prestamo prestamo = prestamoService.findById(prestamoResponse.getId());

    Assertions.assertEquals(prestamoResponse.getFechaMaximaDevolucion(), prestamo.getFechaMaximaDevolucion());
  }

  @Test
  void prestamoLibroUsuarioInvitadoDeberiaAlmacenarCorrectamenteYCalcularFechaDeDevolucion() {
    PrestamoResponse prestamoResponse = prestamoService.save(
        new Prestamo("ASDA7884", "974148", USUARIO_INVITADO));

    Assertions.assertNotNull(prestamoResponse);

    Prestamo prestamo = prestamoService.findById(prestamoResponse.getId());

    Assertions.assertEquals(prestamoResponse.getFechaMaximaDevolucion(), prestamo.getFechaMaximaDevolucion());
  }

  @Test
  void usuarioNoInvitadoTratandoDePrestarUnSegundoLibroDeberiaPrestarloCorrectamente() {
    PrestamoResponse prestamoResponse = prestamoService.save(
        new Prestamo("ASDA7884", "974148", USUARIO_EMPLEADO));

    Assertions.assertNotNull(prestamoResponse);

    PrestamoResponse prestamoResponse2 = prestamoService.save(
        new Prestamo("ASDA7884", "974148", USUARIO_EMPLEADO));

    Assertions.assertNotNull(prestamoResponse2);
  }
}