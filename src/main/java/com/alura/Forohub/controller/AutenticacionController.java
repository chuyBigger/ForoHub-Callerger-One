package com.alura.Forohub.controller;

import com.alura.Forohub.domain.usuarios.DatosAutenticacion;
import com.alura.Forohub.domain.usuarios.Usuario;
import com.alura.Forohub.infra.security.DatosTokenJWT;
import com.alura.Forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datos){
        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.correo(), datos.contrasena());
        var autenticacion = manager.authenticate(authenticationToken);
        var tokeJwt = tokenService.generarToken((Usuario) autenticacion.getPrincipal());
        return ResponseEntity.ok(new DatosTokenJWT(tokeJwt));
    }

}
