package com.alura.Forohub.controller;

import com.alura.Forohub.domain.curso.Curso;
import com.alura.Forohub.domain.curso.CursoRepository;
import com.alura.Forohub.domain.topico.DatosCrearTopico;
import com.alura.Forohub.domain.topico.Topico;
import com.alura.Forohub.domain.topico.TopicoRepository;
import com.alura.Forohub.domain.topico.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosCrearTopico datos, UriComponentsBuilder uriComponentsBuilder){
        Topico crearTopico = topicoService.crearTopico(datos);
        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(crearTopico.getId()).toUri();
        return ResponseEntity.created(uri).body(crearTopico);
    }
}
