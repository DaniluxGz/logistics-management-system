package com.sinergia.backend.infrastructure.adapter.rest;

import com.sinergia.backend.application.usecase.ClienteServicio;
import com.sinergia.backend.domain.model.entity.Cliente;
import com.sinergia.backend.infrastructure.adapter.rest.dto.ClienteRespuesta;
import com.sinergia.backend.infrastructure.adapter.rest.dto.EnvioMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClienteControlador {

    private final ClienteServicio clienteServicio;
    private final EnvioMapper envioMapper;

    // Obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<ClienteRespuesta>> obtenerTodos() {
        return ResponseEntity.ok(
                clienteServicio.obtenerTodos()
                        .stream()
                        .map(envioMapper::toClienteRespuesta)
                        .toList()
        );
    }

    // Obtener cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteRespuesta> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(envioMapper.toClienteRespuesta(clienteServicio.obtenerPorId(id)));
    }

    // Crear nuevo cliente
    @PostMapping
    public ResponseEntity<ClienteRespuesta> crear(@Valid @RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(envioMapper.toClienteRespuesta(clienteServicio.crear(cliente)));
    }

    // Actualizar cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteRespuesta> actualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        return ResponseEntity.ok(envioMapper.toClienteRespuesta(clienteServicio.actualizar(id, cliente)));
    }

    // Eliminar cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clienteServicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
