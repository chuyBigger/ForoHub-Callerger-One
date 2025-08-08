package com.alura.Forohub.domain.topico;

import com.alura.Forohub.domain.curso.Curso;
import com.alura.Forohub.domain.respuesta.Respuesta;
import com.alura.Forohub.domain.usuarios.Usuario;
import jakarta.persistence.*;


import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Table(name = "topicos")
@Entity(name = "topico")

public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "mensaje")
    private String mensaje;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;// Valor Default

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "curso_id")
    private Curso curso;

    @Column(name = "activo")
    private boolean activo;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuesta> respuestas = new ArrayList<>();

    public Topico(@Valid DatosCrearTopico datos, Curso curso, Usuario autor) {
        this.id = null;
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fecha = LocalDateTime.now();
        this.estado = Estado.SIN_RESPUESTA;
        this.curso = curso;
        this.activo = true;
        this.autor = autor;
    }

    public void actualizarInformacionTopico(@Valid DatosActualizarTopico datos) {
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
    }

    public void eliminar() {
        this.activo = false;
    }
}
