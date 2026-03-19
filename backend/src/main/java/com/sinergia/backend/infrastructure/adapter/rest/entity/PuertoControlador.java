package com.sinergia.backend.infrastructure.adapter.rest.entity;

import com.sinergia.backend.domain.model.entity.Puerto;
import com.sinergia.backend.domain.repository.PuertoRepositorio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/puertos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PuertoControlador {

    private final PuertoRepositorio puertoRepositorio;

    // Obtener todos los puertos
    @GetMapping
    public ResponseEntity<List<Puerto>> obtenerTodos() {
        return ResponseEntity.ok(puertoRepositorio.findAll());
    }

    // Obtener puerto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Puerto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(puertoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Puerto no encontrado con ID: " + id)));
    }

    // Crear nuevo puerto
    @PostMapping
    public ResponseEntity<Puerto> crear(@Valid @RequestBody Puerto puerto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(puertoRepositorio.save(puerto));
    }

    // Eliminar puerto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        puertoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
