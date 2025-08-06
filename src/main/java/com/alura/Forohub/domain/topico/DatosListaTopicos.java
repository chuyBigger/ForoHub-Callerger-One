package com.alura.Forohub.domain.topico;

import com.alura.Forohub.domain.usuarios.Usuario;

import java.time.LocalDateTime;

public record DatosListaTopicos(
    Long id,
    String titulo,
    String mensaje,
    LocalDateTime fecha,
    Estado estado,
    Usuario autor,
    String curso
) {
    public DatosListaTopicos(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha(), topico.getEstado(), topico.getAutor(), topico.getCurso().getNombre());
    }
}
