package com.sinergia.backend.domain.repository;

import com.sinergia.backend.domain.model.entity.EnvioMaritimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnvioMaritimoRepositorio extends JpaRepository<EnvioMaritimo, Long> {

    // Buscar todos los envíos marítimos con cliente y puerto cargados
    @Query("SELECT e FROM EnvioMaritimo e JOIN FETCH e.cliente JOIN FETCH e.puerto")
    List<EnvioMaritimo> findAll();

    // Buscar envío marítimo por ID con cliente y puerto cargados
    @Query("SELECT e FROM EnvioMaritimo e JOIN FETCH e.cliente JOIN FETCH e.puerto WHERE e.id = :id")
    Optional<EnvioMaritimo> findById(Long id);

    // Buscar envíos marítimos por número de flota
    List<EnvioMaritimo> findByNumeroFlota(String numeroFlota);

    // Verificar si existe un envío con ese número de guía
    boolean existsByNumeroGuia(String numeroGuia);
}