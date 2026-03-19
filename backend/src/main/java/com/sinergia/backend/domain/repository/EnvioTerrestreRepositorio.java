package com.sinergia.backend.domain.repository;

import com.sinergia.backend.domain.model.entity.EnvioTerrestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnvioTerrestreRepositorio extends JpaRepository<EnvioTerrestre, Long> {

    // Buscar envíos terrestres por placa de vehículo
    List<EnvioTerrestre> findByPlacaVehiculo(String placaVehiculo);

    // Verificar si existe un envío con ese número de guía
    boolean existsByNumeroGuia(String numeroGuia);
}
