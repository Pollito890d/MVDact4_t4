package com.mvdact4_t4.aplicacion_autenticacion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mvdact4_t4.aplicacion_autenticacion.models.Telefono;

public interface TelefonoRepository extends JpaRepository<Telefono, Long> {
}
