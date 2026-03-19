package com.sinergia.backend.infrastructure.adapter.rest.entity;

import com.sinergia.backend.application.usecase.EnvioServicio;
import com.sinergia.backend.domain.model.entity.EnvioMaritimo;
import com.sinergia.backend.domain.model.entity.EnvioTerrestre;
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

    // Obtener todos los envíos terrestres
    @GetMapping("/terrestres")
    public ResponseEntity<List<EnvioTerrestre>> obtenerTodosLosTerrestres() {
        return ResponseEntity.ok(envioServicio.obtenerTodosLosEnviosTerrestres());
    }

    // Obtener todos los envíos marítimos
    @GetMapping("/maritimos")
    public ResponseEntity<List<EnvioMaritimo>> obtenerTodosLosMaritimos() {
        return ResponseEntity.ok(envioServicio.obtenerTodosLosEnviosMaritimos());
    }

    // Obtener envío terrestre por ID
    @GetMapping("/terrestres/{id}")
    public ResponseEntity<EnvioTerrestre> obtenerTerrestrePorId(@PathVariable Long id) {
        return ResponseEntity.ok(envioServicio.obtenerEnvioTerrestrePorId(id));
    }

    // Obtener envío marítimo por ID
    @GetMapping("/maritimos/{id}")
    public ResponseEntity<EnvioMaritimo> obtenerMaritimoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(envioServicio.obtenerEnvioMaritimoPorId(id));
    }

    // Crear envío terrestre con calculo de descuento
    @PostMapping("/terrestres")
    public ResponseEntity<EnvioTerrestre> crearTerrestre(@Valid @RequestBody EnvioTerrestre envio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(envioServicio.crearEnvioTerrestre(envio));
    }

    // Crear envío marítimo con calculo de descuento
    @PostMapping("/maritimos")
    public ResponseEntity<EnvioMaritimo> crearMaritimo(@Valid @RequestBody EnvioMaritimo envio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(envioServicio.crearEnvioMaritimo(envio));
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
