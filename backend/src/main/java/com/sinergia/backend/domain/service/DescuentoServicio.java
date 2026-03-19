package com.sinergia.backend.domain.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class DescuentoServicio {

    // Cantidad mínima para aplicar el descuento
    private static final int CANTIDAD_MINIMA_DESCUENTO = 10;

    // Porcentaje de descuento para envío terrestre que es de (5%)
    private static final BigDecimal DESCUENTO_TERRESTRE = new BigDecimal("0.05");

    // Porcentaje de descuento para envío marítimo que es de (3%)
    private static final BigDecimal DESCUENTO_MARITIMO = new BigDecimal("0.03");

    // Calcula el precio final para el envío terrestre
    public BigDecimal calcularPrecioTerrestre(BigDecimal precioOriginal, int cantidad) {
        if (cantidad > CANTIDAD_MINIMA_DESCUENTO) {
            return aplicarDescuento(precioOriginal, DESCUENTO_TERRESTRE);
        }
        return precioOriginal;
    }

    // Calcula el precio final para el envío marítimo
    public BigDecimal calcularPrecioMaritimo(BigDecimal precioOriginal, int cantidad) {
        if (cantidad > CANTIDAD_MINIMA_DESCUENTO) {
            return aplicarDescuento(precioOriginal, DESCUENTO_MARITIMO);
        }
        return precioOriginal;
    }

    // Aplica el porcentaje de descuento al precio original
    private BigDecimal aplicarDescuento(BigDecimal precio, BigDecimal porcentaje) {
        BigDecimal descuento = precio.multiply(porcentaje).setScale(2, RoundingMode.HALF_UP);
        return precio.subtract(descuento).setScale(2, RoundingMode.HALF_UP);
    }

    // Calcula el valor del descuento aplicado
    public BigDecimal calcularValorDescuento(BigDecimal precioOriginal, BigDecimal precioFinal) {
        return precioOriginal.subtract(precioFinal).setScale(2, RoundingMode.HALF_UP);
    }
}
