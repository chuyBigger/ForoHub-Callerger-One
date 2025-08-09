package com.alura.Forohub.domain.topico;


import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        String nombreCurso,
        LocalDateTime fecha
) {
    public DatosDetalleTopico(Topico datos) {
        this(datos.getId(), datos.getTitulo(), datos.getMensaje(), datos.getCurso().getNombre(), datos.getFecha());
    }
}
