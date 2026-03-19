package com.sinergia.backend.infrastructure.adapter.rest;

import com.sinergia.backend.domain.model.entity.Usuario;
import com.sinergia.backend.domain.model.enums.RolUsuario;
import com.sinergia.backend.domain.repository.UsuarioRepositorio;
import com.sinergia.backend.infrastructure.adapter.rest.dto.LoginRequest;
import com.sinergia.backend.infrastructure.adapter.rest.dto.LoginResponse;
import com.sinergia.backend.infrastructure.adapter.rest.dto.RegistroRequest;
import com.sinergia.backend.infrastructure.security.JwtServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AutenticacionControlador {

    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;
    private final JwtServicio jwtServicio;
    private final AuthenticationManager authenticationManager;

    // Registrar nuevo usuario
    @PostMapping("/registro")
    public ResponseEntity<LoginResponse> registro(@Valid @RequestBody RegistroRequest request) {
        if (usuarioRepositorio.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().build();
        }

        // Crear y guardar el usuario con password encriptado
        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(RolUsuario.USER)
                .build();

        usuarioRepositorio.save(usuario);

        // Generar token JWT para el nuevo usuario
        String token = jwtServicio.generarToken(usuario);

        return ResponseEntity.ok(LoginResponse.builder()
                .token(token)
                .email(usuario.getEmail())
                .nombre(usuario.getNombre())
                .rol(usuario.getRol().name())
                .build());
    }

    // Iniciar sesion con email y password
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        Usuario usuario = usuarioRepositorio.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Generar token JWT para el usuario autenticado
        String token = jwtServicio.generarToken(usuario);

        return ResponseEntity.ok(LoginResponse.builder()
                .token(token)
                .email(usuario.getEmail())
                .nombre(usuario.getNombre())
                .rol(usuario.getRol().name())
                .build());
    }
}
