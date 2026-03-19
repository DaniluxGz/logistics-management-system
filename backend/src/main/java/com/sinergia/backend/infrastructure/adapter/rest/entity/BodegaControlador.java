package com.sinergia.backend.infrastructure.adapter.rest.entity;

import com.sinergia.backend.domain.model.entity.Bodega;
import com.sinergia.backend.domain.repository.BodegaRepositorio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bodegas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BodegaControlador {

    private final BodegaRepositorio bodegaRepositorio;

    // Obtener todas las bodegas
    @GetMapping
    public ResponseEntity<List<Bodega>> obtenerTodas() {
        return ResponseEntity.ok(bodegaRepositorio.findAll());
    }

    // Obtener bodega por ID
    @GetMapping("/{id}")
    public ResponseEntity<Bodega> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(bodegaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Bodega no encontrada con ID: " + id)));
    }

    // Crear nueva bodega
    @PostMapping
    public ResponseEntity<Bodega> crear(@Valid @RequestBody Bodega bodega) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bodegaRepositorio.save(bodega));
    }

    // Eliminar bodega por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        bodegaRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
