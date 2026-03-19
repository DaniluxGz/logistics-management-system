package com.sinergia.backend.domain.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "land_shipments")
@PrimaryKeyJoinColumn(name = "shipment_id")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EnvioTerrestre extends Envio {

    // Vehicle plate format: ABC-123 or ABC123
    @NotBlank(message = "La placa del vehículo es obligatoria")
    @Pattern(regexp = "^[A-Z]{3}-?\\d{3}$", message = "El formato de la placa debe ser ABC-123 o ABC123")
    @Column(name = "placa_vehiculo", nullable = false)
    private String placaVehiculo;

    // Many land shipments belong to one warehouse
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bodega_id", nullable = false)
    private Bodega bodega;
}
