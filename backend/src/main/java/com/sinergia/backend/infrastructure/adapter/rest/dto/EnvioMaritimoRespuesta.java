package com.sinergia.backend.infrastructure.adapter.rest.dto;

import com.sinergia.backend.domain.model.enums.EstadoEnvio;
import com.sinergia.backend.domain.model.enums.TipoProducto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnvioMaritimoRespuesta {
    private Long id;
    private String numeroGuia;
    private TipoProducto tipoProducto;
    private Integer cantidad;
    private LocalDate fechaRegistro;
    private LocalDate fechaEntrega;
    private BigDecimal precioOriginal;
    private BigDecimal precioFinal;
    private EstadoEnvio estado;
    private String clienteNombre;
    private String clienteEmail;
    private String numeroFlota;
    private String puertoNombre;
}
