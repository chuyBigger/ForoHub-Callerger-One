package com.alura.Forohub.domain.topico;

import com.alura.Forohub.domain.curso.Curso;
import com.alura.Forohub.domain.respuesta.Respuesta;
import com.alura.Forohub.domain.usuarios.Usuario;
import jakarta.persistence.*;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Table(name = "topicos")
@Entity(name = "topico")

public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean status;
    @Transient
    private Usuario autor;
    @Transient
    private Curso curso;
    @Transient
    private Respuesta respuestas;

    public Topico(@Valid DatosRegistroTopico datos) {
        this.id = null;
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = false;
    }
}
