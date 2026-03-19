package com.sinergia.backend.application.usecase;

import com.sinergia.backend.domain.model.entity.*;
import com.sinergia.backend.domain.repository.*;
import com.sinergia.backend.domain.service.DescuentoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnvioServicio {

    private final EnvioTerrestreRepositorio envioTerrestreRepositorio;
    private final EnvioMaritimoRepositorio envioMaritimoRepositorio;
    private final ClienteRepositorio clienteRepositorio;
    private final BodegaRepositorio bodegaRepositorio;
    private final PuertoRepositorio puertoRepositorio;
    private final DescuentoServicio descuentoServicio;

    // Obtener todos los envíos terrestres
    @Transactional(readOnly = true)
    public List<EnvioTerrestre> obtenerTodosLosEnviosTerrestres() {
        return envioTerrestreRepositorio.findAll();
    }

    // Obtener todos los envíos marítimos
    @Transactional(readOnly = true)
    public List<EnvioMaritimo> obtenerTodosLosEnviosMaritimos() {
        return envioMaritimoRepositorio.findAll();
    }

    // Obtener envío terrestre por ID
    @Transactional(readOnly = true)
    public EnvioTerrestre obtenerEnvioTerrestrePorId(Long id) {
        return envioTerrestreRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Envío terrestre no encontrado con ID: " + id));
    }

    // Obtener envío marítimo por ID
    @Transactional(readOnly = true)
    public EnvioMaritimo obtenerEnvioMaritimoPorId(Long id) {
        return envioMaritimoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Envío marítimo no encontrado con ID: " + id));
    }

    // Crear envío terrestre aplicando descuento si aplica
    @Transactional
    public EnvioTerrestre crearEnvioTerrestre(EnvioTerrestre envio) {
        validarNumeroGuia(envio.getNumeroGuia());

        // Cargar entidades completas desde la base de datos
        Cliente cliente = clienteRepositorio.findById(envio.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        envio.setCliente(cliente);

        Bodega bodega = bodegaRepositorio.findById(envio.getBodega().getId())
                .orElseThrow(() -> new RuntimeException("Bodega no encontrada"));
        envio.setBodega(bodega);

        // Calcular precio final con posible descuento del 5%
        envio.setPrecioFinal(
                descuentoServicio.calcularPrecioTerrestre(envio.getPrecioOriginal(), envio.getCantidad())
        );

        return envioTerrestreRepositorio.save(envio);
    }

    // Crear envío marítimo aplicando descuento si aplica
    @Transactional
    public EnvioMaritimo crearEnvioMaritimo(EnvioMaritimo envio) {
        validarNumeroGuia(envio.getNumeroGuia());

        // Cargar entidades completas desde la base de datos
        Cliente cliente = clienteRepositorio.findById(envio.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        envio.setCliente(cliente);

        Puerto puerto = puertoRepositorio.findById(envio.getPuerto().getId())
                .orElseThrow(() -> new RuntimeException("Puerto no encontrado"));
        envio.setPuerto(puerto);

        // Calcular precio final con posible descuento del 3%
        envio.setPrecioFinal(
                descuentoServicio.calcularPrecioMaritimo(envio.getPrecioOriginal(), envio.getCantidad())
        );

        return envioMaritimoRepositorio.save(envio);
    }

    // Eliminar envío terrestre por ID
    @Transactional
    public void eliminarEnvioTerrestre(Long id) {
        obtenerEnvioTerrestrePorId(id);
        envioTerrestreRepositorio.deleteById(id);
    }

    // Eliminar envío marítimo por ID
    @Transactional
    public void eliminarEnvioMaritimo(Long id) {
        obtenerEnvioMaritimoPorId(id);
        envioMaritimoRepositorio.deleteById(id);
    }

    // Validar que el número de guía no exista
    private void validarNumeroGuia(String numeroGuia) {
        if (envioTerrestreRepositorio.existsByNumeroGuia(numeroGuia) ||
                envioMaritimoRepositorio.existsByNumeroGuia(numeroGuia)) {
            throw new RuntimeException("Ya existe un envío con el número de guía: " + numeroGuia);
        }
    }
}
