package com.sinergia.backend.application.usecase;

import com.sinergia.backend.domain.model.entity.Cliente;
import com.sinergia.backend.domain.repository.ClienteRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServicio {

    private final ClienteRepositorio clienteRepositorio;

    // Obtener todos los clientes
    @Transactional(readOnly = true)
    public List<Cliente> obtenerTodos() {
        return clienteRepositorio.findAll();
    }

    // Obtener cliente por ID
    @Transactional(readOnly = true)
    public Cliente obtenerPorId(Long id) {
        return clienteRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    // Crear nuevo cliente
    @Transactional
    public Cliente crear(Cliente cliente) {
        if (clienteRepositorio.existsByEmail(cliente.getEmail())) {
            throw new RuntimeException("Ya existe un cliente con el email: " + cliente.getEmail());
        }
        return clienteRepositorio.save(cliente);
    }

    // Actualizar cliente existente
    @Transactional
    public Cliente actualizar(Long id, Cliente clienteActualizado) {
        Cliente clienteExistente = obtenerPorId(id);
        clienteExistente.setName(clienteActualizado.getName());
        clienteExistente.setEmail(clienteActualizado.getEmail());
        clienteExistente.setPhone(clienteActualizado.getPhone());
        return clienteRepositorio.save(clienteExistente);
    }

    // Eliminar cliente por ID
    @Transactional
    public void eliminar(Long id) {
        obtenerPorId(id);
        clienteRepositorio.deleteById(id);
    }
}