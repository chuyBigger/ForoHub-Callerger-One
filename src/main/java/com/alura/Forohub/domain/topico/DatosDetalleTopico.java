package com.alura.Forohub.domain.topico;


public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        String nombreCurso
) {
    public DatosDetalleTopico(Topico datos){
        this(datos.getId(), datos.getTitulo(), datos.getMensaje(), datos.getCurso().getNombre());
    }
}
