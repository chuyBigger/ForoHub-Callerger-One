package com.alura.Forohub.domain.perfiles;

import jakarta.persistence.*;

@Table(name = "perfiles")
@Entity(name = "perfil")

public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;
}
