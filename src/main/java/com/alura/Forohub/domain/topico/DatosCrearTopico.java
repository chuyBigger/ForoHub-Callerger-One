package com.alura.Forohub.domain.topico;

import com.alura.Forohub.domain.curso.Curso;
import jakarta.validation.constraints.NotNull;


public record DatosCrearTopico (
    Long idUsuario,
    @NotNull
    String titulo,
    @NotNull
    String mensaje,
    @NotNull
    String curso
){

}


