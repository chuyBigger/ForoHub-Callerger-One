package com.alura.Forohub.domain.topico;

import com.alura.Forohub.domain.respuesta.Respuesta;
import com.alura.Forohub.domain.usuarios.Usuario;
import jakarta.persistence.*;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private LocalDateTime fecha;
    private Estado estado;// Valor Default

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    private String curso; // todo temporalmente String se va cambiar conforme se desarrollo entidades JPA

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuesta> respuestas = new ArrayList<>();

    public Topico(@Valid DatosCrearTopico datos) {
        this.id = null;
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fecha = LocalDateTime.now();
        this.estado = Estado.SIN_RESPUESTA; ;
        this.curso = datos.curso();
        // this.autor = autor; todo esta asignacion se corregira al implementar los modelos de login
    }
}
