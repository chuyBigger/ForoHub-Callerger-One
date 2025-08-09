package com.alura.Forohub.domain.respuesta;

import com.alura.Forohub.domain.topico.Topico;
import com.alura.Forohub.domain.usuarios.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "respuesta")

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;

    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean solucion;


    public Respuesta(@Valid DatosCrearRespuesta datos, Topico topico, Usuario autor) {
        this.id=null;
        this.mensaje=datos.mensaje();
        this.topico = topico;
        this.fechaCreacion = LocalDateTime.now();
        this.autor = autor;
        this.solucion = false;
    }
}
