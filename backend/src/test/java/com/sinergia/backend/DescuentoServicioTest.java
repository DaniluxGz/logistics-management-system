package com.sinergia.backend;

import com.sinergia.backend.domain.service.DescuentoServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas del servicio de descuentos")
class DescuentoServicioTest {

    private DescuentoServicio descuentoServicio;

    @BeforeEach
    void setUp() {
        descuentoServicio = new DescuentoServicio();
    }

    @Test
    @DisplayName("Envío terrestre con más de 10 unidades aplica 5% de descuento")
    void calcularPrecioTerrestre_CantidadMayorA10_AplicaDescuento() {
        BigDecimal precioOriginal = new BigDecimal("100.00");
        BigDecimal resultado = descuentoServicio.calcularPrecioTerrestre(precioOriginal, 11);
        assertEquals(new BigDecimal("95.00"), resultado);
    }

    @Test
    @DisplayName("Envío terrestre con 10 o menos unidades no aplica descuento")
    void calcularPrecioTerrestre_CantidadMenorOIgualA10_SinDescuento() {
        BigDecimal precioOriginal = new BigDecimal("100.00");
        BigDecimal resultado = descuentoServicio.calcularPrecioTerrestre(precioOriginal, 10);
        assertEquals(new BigDecimal("100.00"), resultado);
    }

    @Test
    @DisplayName("Envío marítimo con más de 10 unidades aplica 3% de descuento")
    void calcularPrecioMaritimo_CantidadMayorA10_AplicaDescuento() {
        BigDecimal precioOriginal = new BigDecimal("100.00");
        BigDecimal resultado = descuentoServicio.calcularPrecioMaritimo(precioOriginal, 15);
        assertEquals(new BigDecimal("97.00"), resultado);
    }

    @Test
    @DisplayName("Envío marítimo con 10 o menos unidades no aplica descuento")
    void calcularPrecioMaritimo_CantidadMenorOIgualA10_SinDescuento() {
        BigDecimal precioOriginal = new BigDecimal("100.00");
        BigDecimal resultado = descuentoServicio.calcularPrecioMaritimo(precioOriginal, 5);
        assertEquals(new BigDecimal("100.00"), resultado);
    }

    @Test
    @DisplayName("Calcula correctamente el valor del descuento aplicado")
    void calcularValorDescuento_RetornaLaDiferencia() {
        BigDecimal precioOriginal = new BigDecimal("100.00");
        BigDecimal precioFinal = new BigDecimal("95.00");
        BigDecimal resultado = descuentoServicio.calcularValorDescuento(precioOriginal, precioFinal);
        assertEquals(new BigDecimal("5.00"), resultado);
    }
}
