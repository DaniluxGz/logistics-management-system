package com.sinergia.backend.domain.repository;

import com.sinergia.backend.domain.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    // Buscar cliente por el email
    Optional<Cliente> findByEmail(String email);

    // Verificar si existe un cliente con ese email
    boolean existsByEmail(String email);
}
