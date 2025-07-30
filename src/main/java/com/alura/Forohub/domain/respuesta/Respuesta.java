package com.alura.Forohub.domain.respuesta;

import com.alura.Forohub.domain.topico.Topico;
import com.alura.Forohub.domain.usuarios.Usuario;

import java.time.LocalDateTime;

public class Respuesta {
    private Long id;
    private String mensaje;
    private Topico topico;
    private LocalDateTime fechaCreacion;
    private Usuario autor;
    private String Solucion;
}
