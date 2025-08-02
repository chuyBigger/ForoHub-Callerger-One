package com.alura.Forohub.controller;

import com.alura.Forohub.domain.topico.DatosCrearTopico;
import com.alura.Forohub.domain.topico.Topico;
import com.alura.Forohub.domain.topico.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity crearTopico(@RequestBody @Valid DatosCrearTopico datos){
        topicoRepository.save(new Topico(datos));
        return ResponseEntity.ok(datos);
    }
}
