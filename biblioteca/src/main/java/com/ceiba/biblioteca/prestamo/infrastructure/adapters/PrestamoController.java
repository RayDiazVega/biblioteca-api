package com.ceiba.biblioteca.prestamo.infrastructure.adapters;

import com.ceiba.biblioteca.prestamo.application.PrestamoService;
import com.ceiba.biblioteca.prestamo.dto.Prestamo;
import com.ceiba.biblioteca.prestamo.dto.PrestamoResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/prestamo", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PrestamoController {

  @Autowired
  private PrestamoService prestamoService;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  private PrestamoResponse save(@Valid @RequestBody Prestamo prestamo) {
    return prestamoService.save(prestamo);
  }

  @GetMapping("/{id-prestamo}")
  private Prestamo findById(@PathVariable("id-prestamo") Long id) {
    return prestamoService.findById(id);
  }
}

