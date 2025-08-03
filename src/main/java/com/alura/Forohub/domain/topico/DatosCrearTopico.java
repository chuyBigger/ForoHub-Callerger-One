package com.alura.Forohub.domain.topico;

import com.alura.Forohub.domain.curso.Curso;
import jakarta.validation.constraints.NotNull;


public record DatosCrearTopico (

    @NotNull
    String titulo,
    @NotNull
    String mensaje,
    @NotNull
    Long cursoId
){

}


