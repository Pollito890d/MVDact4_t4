package com.mvdact4_t4.aplicacion_autenticacion.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mvdact4_t4.aplicacion_autenticacion.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}
