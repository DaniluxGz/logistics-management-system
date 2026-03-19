package com.sinergia.backend.domain.repository;

import com.sinergia.backend.domain.model.entity.Puerto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuertoRepositorio extends JpaRepository<Puerto, Long> {
}
