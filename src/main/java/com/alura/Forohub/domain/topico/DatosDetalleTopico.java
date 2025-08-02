package com.alura.Forohub.domain.topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        Long idUsuario,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        String curso
) {
}
