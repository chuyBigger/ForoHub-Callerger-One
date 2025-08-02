package com.alura.Forohub.domain.usuarios;

import com.alura.Forohub.domain.perfiles.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario(

        @NotBlank
        String nombre,
        @NotBlank @Email
        String correoElectronico,
        @NotBlank
        String contrasena,
        @NotBlank
        Perfil perfil
) {
}
