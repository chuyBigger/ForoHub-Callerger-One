package com.alura.Forohub.domain.topico;

public record DatosActualizarTopico(
        Long id,
        String titulo,
        String mensaje,
        Long cursoId
) {
}
