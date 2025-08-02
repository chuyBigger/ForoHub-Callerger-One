package com.alura.Forohub.controller;

import com.alura.Forohub.domain.usuarios.DatosDetalleUsuario;
import com.alura.Forohub.domain.usuarios.DatosRegistroUsuario;
import com.alura.Forohub.domain.usuarios.Usuario;
import com.alura.Forohub.domain.usuarios.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroUsuario datos, UriComponentsBuilder uriComponentsBuilder) {

        // validar correo
        if (usuarioRepository.existsByCorreoElectronico(datos.correoElectronico())) {
            return ResponseEntity
                    .badRequest()
                    .body("⚠️ Error el Correo Electronico ya se encuentra registrado. ");
        } // Guardar Nuevo Usuario
        Usuario nuevoUsuario = usuarioRepository.save(new Usuario(datos));
        var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(nuevoUsuario.getId()).toUri();
        return ResponseEntity.created(uri).body("✅ Usuario registrado Exitosamente:" + new DatosDetalleUsuario(nuevoUsuario));

    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        var usuarioEliminado = usuarioRepository.findById(id);
        if (usuarioEliminado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("⚠️ Usuario No encontrado¡");
        }
        var usuario = usuarioEliminado.get();
        usuario.eliminar();
        return ResponseEntity.ok("✅ Usuario eliminado con éxito");
    }
}
