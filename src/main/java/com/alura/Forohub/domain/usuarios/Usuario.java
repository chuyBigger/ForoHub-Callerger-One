package com.alura.Forohub.domain.usuarios;

import com.alura.Forohub.domain.perfiles.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.PasswordAuthentication;

@Table(name = "usuarios")
@Entity(name = "usuario")

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correoElectronico;
    private String constrasena;
    @Transient
    private Perfil prefiles;
}
