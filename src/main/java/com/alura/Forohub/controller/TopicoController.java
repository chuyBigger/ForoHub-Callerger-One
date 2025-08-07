package com.alura.Forohub.controller;

import com.alura.Forohub.domain.curso.CursoRepository;
import com.alura.Forohub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity registrar(@RequestBody @Valid DatosCrearTopico datos, UriComponentsBuilder uriComponentsBuilder) {
        Topico crearTopico = topicoService.crearTopico(datos);
        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(crearTopico.getId()).toUri();
        DatosDetalleTopico detalleTopico = new DatosDetalleTopico(crearTopico);
        return ResponseEntity.created(uri).body(detalleTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopicos>> listaTopicos(@PageableDefault(size = 10, sort = "titulo") Pageable paguinacion) {
        var page = topicoRepository.findByActivoTrue(paguinacion).map(DatosListaTopicos::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping("/buscar")
    public ResponseEntity<Page<DatosListaTopicos>> listaTopicosBusqueda(@RequestBody DatosBusquedaTopicos datos, @PageableDefault(size = 10, sort = "titulo") Pageable paguinacion) {
        var page = topicoRepository.buscarPorNombreYAnio(datos.titulo(), datos.anio(), paguinacion).map(DatosListaTopicos::new);
        if (page.isEmpty()){
            return ResponseEntity.noContent().build(); // para retornar 204
        }
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalleTopico(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosListaTopicos(topico));

    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datos) {
        topicoService.actualizarTopico(id, datos);
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        var buscarTopico = topicoRepository.findById(id);
        if (buscarTopico.isPresent()) {
            var topico = buscarTopico.get();
            topico.eliminar();
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
