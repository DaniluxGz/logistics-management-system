package com.sinergia.backend.domain.repository;


import com.sinergia.backend.domain.model.entity.EnvioTerrestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnvioTerrestreRepositorio extends JpaRepository<EnvioTerrestre, Long> {

    // Buscar todos los envíos terrestres con cliente y bodega cargados
    @Query("SELECT e FROM EnvioTerrestre e JOIN FETCH e.cliente JOIN FETCH e.bodega")
    List<EnvioTerrestre> findAll();

    // Buscar envío terrestre por ID con cliente y bodega cargados
    @Query("SELECT e FROM EnvioTerrestre e JOIN FETCH e.cliente JOIN FETCH e.bodega WHERE e.id = :id")
    Optional<EnvioTerrestre> findById(Long id);

    // Buscar envíos terrestres por placa de vehículo
    List<EnvioTerrestre> findByPlacaVehiculo(String placaVehiculo);

    // Verificar si existe un envío con ese número de guía
    boolean existsByNumeroGuia(String numeroGuia);
}