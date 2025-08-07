package com.alura.Forohub.domain.topico;

import com.alura.Forohub.domain.curso.Curso;
import com.alura.Forohub.domain.curso.CursoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Topico crearTopico(DatosCrearTopico datos){
        var curso = cursoRepository.findById(datos.cursoId())
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));
        var topico = new Topico(datos, curso);
        return topicoRepository.save(topico);
    }

    public void actualizarTopico(Long id, DatosActualizarTopico datos) {
        Topico topico = topicoRepository.getReferenceById(id);

        if (datos.cursoId() != null){
            Curso curso = cursoRepository.getReferenceById(datos.cursoId());
            topico.setCurso(curso);
        }
        topico.actualizarInformacionTopico(datos);
    }

}
