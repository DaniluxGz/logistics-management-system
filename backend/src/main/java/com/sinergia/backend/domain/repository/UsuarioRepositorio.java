package com.sinergia.backend.domain.repository;

import com.sinergia.backend.domain.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    // Buscar usuario por email (usado en autenticación)
    Optional<Usuario> findByEmail(String email);

    // Verificar si existe un usuario con ese email
    boolean existsByEmail(String email);
}
