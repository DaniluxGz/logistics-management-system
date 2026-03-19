package com.sinergia.backend.infrastructure.adapter.rest;

import com.sinergia.backend.application.usecase.EnvioServicio;
import com.sinergia.backend.domain.model.entity.EnvioMaritimo;
import com.sinergia.backend.domain.model.entity.EnvioTerrestre;
import com.sinergia.backend.infrastructure.adapter.rest.dto.EnvioMapper;
import com.sinergia.backend.infrastructure.adapter.rest.dto.EnvioMaritimoRespuesta;
import com.sinergia.backend.infrastructure.adapter.rest.dto.EnvioTerrestreRespuesta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EnvioControlador {

    private final EnvioServicio envioServicio;
    private final EnvioMapper envioMapper;

    // Obtener todos los envíos terrestres
    @GetMapping("/terrestres")
    public ResponseEntity<List<EnvioTerrestreRespuesta>> obtenerTodosLosTerrestres() {
        return ResponseEntity.ok(
                envioServicio.obtenerTodosLosEnviosTerrestres()
                        .stream()
                        .map(envioMapper::toTerrestreRespuesta)
                        .toList()
        );
    }

    // Obtener todos los envíos marítimos
    @GetMapping("/maritimos")
    public ResponseEntity<List<EnvioMaritimoRespuesta>> obtenerTodosLosMaritimos() {
        return ResponseEntity.ok(
                envioServicio.obtenerTodosLosEnviosMaritimos()
                        .stream()
                        .map(envioMapper::toMaritimoRespuesta)
                        .toList()
        );
    }

    // Obtener envío terrestre por ID
    @GetMapping("/terrestres/{id}")
    public ResponseEntity<EnvioTerrestreRespuesta> obtenerTerrestrePorId(@PathVariable Long id) {
        return ResponseEntity.ok(envioMapper.toTerrestreRespuesta(envioServicio.obtenerEnvioTerrestrePorId(id)));
    }

    // Obtener envío marítimo por ID
    @GetMapping("/maritimos/{id}")
    public ResponseEntity<EnvioMaritimoRespuesta> obtenerMaritimoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(envioMapper.toMaritimoRespuesta(envioServicio.obtenerEnvioMaritimoPorId(id)));
    }

    // Crear envío terrestre con calculo de descuento
    @PostMapping("/terrestres")
    public ResponseEntity<EnvioTerrestreRespuesta> crearTerrestre(@Valid @RequestBody EnvioTerrestre envio) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(envioMapper.toTerrestreRespuesta(envioServicio.crearEnvioTerrestre(envio)));
    }

    // Crear envío marítimo con calculo de descuento
    @PostMapping("/maritimos")
    public ResponseEntity<EnvioMaritimoRespuesta> crearMaritimo(@Valid @RequestBody EnvioMaritimo envio) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(envioMapper.toMaritimoRespuesta(envioServicio.crearEnvioMaritimo(envio)));
    }

    // Eliminar envío terrestre por ID
    @DeleteMapping("/terrestres/{id}")
    public ResponseEntity<Void> eliminarTerrestre(@PathVariable Long id) {
        envioServicio.eliminarEnvioTerrestre(id);
        return ResponseEntity.noContent().build();
    }

    // Eliminar envío marítimo por ID
    @DeleteMapping("/maritimos/{id}")
    public ResponseEntity<Void> eliminarMaritimo(@PathVariable Long id) {
        envioServicio.eliminarEnvioMaritimo(id);
        return ResponseEntity.noContent().build();
    }
}
