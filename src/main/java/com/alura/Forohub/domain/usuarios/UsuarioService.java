package com.alura.Forohub.domain.usuarios;

import com.alura.Forohub.domain.perfiles.Perfil;
import com.alura.Forohub.domain.perfiles.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registarUsuario(DatosRegistroUsuario datos){

        if (!usuarioRepository.existsByCorreoElectronico(datos.correoElectronico())){
            var contrasenaEncode = passwordEncoder.encode(datos.contrasena());
            Perfil perfilUsuario = perfilRepository.findById(3L)
                    .orElseThrow(() -> new RuntimeException("Perfil por defecto no encontrado"));
            Usuario nuevoUsuario = new Usuario(datos, perfilUsuario, contrasenaEncode);
            // Guardar Nuevo Usuario
            usuarioRepository.save(nuevoUsuario);
            return nuevoUsuario;

        } else {
            throw new RuntimeException("⚠️ Error el Correo Electronico ya se encuentra registrado. ");
        }
    }


}
