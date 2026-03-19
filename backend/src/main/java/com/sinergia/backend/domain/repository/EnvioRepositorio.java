package com.sinergia.backend.domain.repository;

import com.sinergia.backend.domain.model.entity.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnvioRepositorio extends JpaRepository<Envio, Long> {

    // Buscar envío por número de guía
    Optional<Envio> findByNumeroGuia(String numeroGuia);

    // Listar todos los envíos de un cliente
    List<Envio> findByClienteId(Long clienteId);

    // Verificar si existe un envío con ese número de guía
    boolean existsByNumeroGuia(String numeroGuia);
}
