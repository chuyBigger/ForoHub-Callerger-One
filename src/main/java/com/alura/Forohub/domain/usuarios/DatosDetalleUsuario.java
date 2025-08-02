package com.alura.Forohub.domain.usuarios;

import com.alura.Forohub.domain.perfiles.Perfil;

public record DatosDetalleUsuario(

        Long id,
        String nombre,
        String correoElectronico,
        Perfil perfil

) {

    public DatosDetalleUsuario(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreoElectronico(),
                usuario.getPerfil()
        );
    }
}

