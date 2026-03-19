package com.sinergia.backend.domain.repository;

import com.sinergia.backend.domain.model.entity.EnvioMaritimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnvioMaritimoRepositorio extends JpaRepository<EnvioMaritimo, Long> {

    // Buscar envíos marítimos por número de flota
    List<EnvioMaritimo> findByNumeroFlota(String numeroFlota);
}
