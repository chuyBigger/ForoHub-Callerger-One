package com.alura.Forohub.controller;

import com.alura.Forohub.domain.perfiles.PerfilRepository;
import com.alura.Forohub.domain.usuarios.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroUsuario datos, UriComponentsBuilder uriComponentsBuilder) {
        Usuario nuevoUsuario = usuarioService.registarUsuario(datos);
        var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(nuevoUsuario.getId()).toUri();
        return ResponseEntity.created(uri).body("✅ Usuario registrado Exitosamente:" + new DatosDetalleUsuario(nuevoUsuario));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaUsuarios>> listarUsuarios(@PageableDefault(size = 10, sort = "nombre")Pageable pageable){
        var page = usuarioRepository.findAllByActivoTrue(pageable).map(DatosListaUsuarios::new);
        return ResponseEntity.ok(page);
    }


    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        System.out.println("vamos a eliminar !!!!!");
        var usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("⚠️ Usuario No encontrado¡");
        }
        var usuarioEliminado = usuario.get();
        usuarioEliminado.eliminar();
        return ResponseEntity.ok("✅ Usuario eliminado con éxito");
    }
}
