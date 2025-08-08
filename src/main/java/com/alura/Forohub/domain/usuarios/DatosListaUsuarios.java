package com.alura.Forohub.domain.usuarios;

public record DatosListaUsuarios (
        Long id,
        String nombre,
        String correo

) {
    public DatosListaUsuarios (Usuario usuario){

        this(usuario.getId(), usuario.getNombre(), usuario.getCorreoElectronico());

    }

}
