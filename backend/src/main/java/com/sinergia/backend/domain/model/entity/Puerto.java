package com.sinergia.backend.domain.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "puertos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Puerto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del puerto es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @Column(name = "ubicacion")
    private String ubicacion;
}
