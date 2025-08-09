package com.alura.Forohub.controller;

import com.alura.Forohub.domain.respuesta.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/respuestas")
@RestController
public class RespuestasController {

    @Autowired
    private RespuestaRepository respuestaRepositoryç;

    @Autowired
    private RespuestaService respuestaService;

    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosCrearRespuesta datos, UriComponentsBuilder uriComponentsBuilder) {
        var nuevaRespuesta = respuestaService.crearRespuesta(datos);
        if (nuevaRespuesta == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("⚠️ No se pudo crear la respuesta");
        }
        var uri = uriComponentsBuilder.path("/topicos/{id_topicos}/respuestas/{id_respuestas}").buildAndExpand(nuevaRespuesta.getTopico().getId(), nuevaRespuesta.getId()).toUri();
        return ResponseEntity.created(uri).body("✅ Usuario registrado Exitosamente:"+ new DatosDetallesRespuesta(nuevaRespuesta));
    }


}
