package com.sinergia.backend.infrastructure.adapter.rest.dto;

import com.sinergia.backend.domain.model.entity.Cliente;
import com.sinergia.backend.domain.model.entity.EnvioMaritimo;
import com.sinergia.backend.domain.model.entity.EnvioTerrestre;
import org.springframework.stereotype.Component;

@Component
public class EnvioMapper {

    // Convertir EnvioTerrestre a DTO de respuesta
    public EnvioTerrestreRespuesta toTerrestreRespuesta(EnvioTerrestre envio) {
        return EnvioTerrestreRespuesta.builder()
                .id(envio.getId())
                .numeroGuia(envio.getNumeroGuia())
                .tipoProducto(envio.getTipoProducto())
                .cantidad(envio.getCantidad())
                .fechaRegistro(envio.getFechaRegistro())
                .fechaEntrega(envio.getFechaEntrega())
                .precioOriginal(envio.getPrecioOriginal())
                .precioFinal(envio.getPrecioFinal())
                .estado(envio.getEstado())
                .clienteNombre(envio.getCliente() != null ? envio.getCliente().getName() : null)
                .clienteEmail(envio.getCliente() != null ? envio.getCliente().getEmail() : null)
                .placaVehiculo(envio.getPlacaVehiculo())
                .bodegaNombre(envio.getBodega() != null ? envio.getBodega().getNombre() : null)
                .build();
    }

    // Convertir EnvioMaritimo a DTO de respuesta
    public EnvioMaritimoRespuesta toMaritimoRespuesta(EnvioMaritimo envio) {
        return EnvioMaritimoRespuesta.builder()
                .id(envio.getId())
                .numeroGuia(envio.getNumeroGuia())
                .tipoProducto(envio.getTipoProducto())
                .cantidad(envio.getCantidad())
                .fechaRegistro(envio.getFechaRegistro())
                .fechaEntrega(envio.getFechaEntrega())
                .precioOriginal(envio.getPrecioOriginal())
                .precioFinal(envio.getPrecioFinal())
                .estado(envio.getEstado())
                .clienteNombre(envio.getCliente() != null ? envio.getCliente().getName() : null)
                .clienteEmail(envio.getCliente() != null ? envio.getCliente().getEmail() : null)
                .numeroFlota(envio.getNumeroFlota())
                .puertoNombre(envio.getPuerto() != null ? envio.getPuerto().getNombre() : null)
                .build();
    }

    // Convertir Cliente a DTO de respuesta
    public ClienteRespuesta toClienteRespuesta(Cliente cliente) {
        return ClienteRespuesta.builder()
                .id(cliente.getId())
                .name(cliente.getName())
                .email(cliente.getEmail())
                .phone(cliente.getPhone())
                .build();
    }
}
