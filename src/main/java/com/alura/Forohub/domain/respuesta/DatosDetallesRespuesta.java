package com.alura.Forohub.domain.respuesta;

import java.time.LocalDateTime;

public record DatosDetallesRespuesta(
        Long id,
        String mensaje,
        String topico,
        LocalDateTime fechaCreacion,
        String autor,
        boolean solucion
) {
    public DatosDetallesRespuesta(Respuesta datos){

        this(datos.getId(), datos.getMensaje(), datos.getTopico().getTitulo(), datos.getFechaCreacion(), datos.getAutor().getNombre(), datos.isSolucion());

    }
}
