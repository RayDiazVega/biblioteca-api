package com.ceiba.biblioteca.prestamo.infrastructure.ports;

import com.ceiba.biblioteca.prestamo.dto.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

}
