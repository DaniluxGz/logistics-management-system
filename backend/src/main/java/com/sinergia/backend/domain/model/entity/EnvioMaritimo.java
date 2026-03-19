package com.sinergia.backend.domain.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "sea_shipments")
@PrimaryKeyJoinColumn(name = "shipment_id")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EnvioMaritimo extends Envio {

    // Fleet number for maritime transport
    @NotBlank(message = "El número de flota es obligatorio")
    @Column(name = "numero_flota", nullable = false)
    private String numeroFlota;

    // Many sea shipments belong to one port
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "puerto_id", nullable = false)
    private Puerto puerto;
}
