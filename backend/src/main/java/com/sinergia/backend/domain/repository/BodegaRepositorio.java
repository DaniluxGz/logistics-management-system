package com.sinergia.backend.domain.repository;

import com.sinergia.backend.domain.model.entity.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodegaRepositorio extends JpaRepository<Bodega, Long> {
}
