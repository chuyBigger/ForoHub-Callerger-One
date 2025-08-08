package com.alura.Forohub.domain.topico;

import com.alura.Forohub.domain.curso.Curso;
import com.alura.Forohub.domain.curso.CursoRepository;
import com.alura.Forohub.domain.usuarios.Usuario;
import com.alura.Forohub.infra.security.SecurityFilter;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Topico crearTopico(DatosCrearTopico datos){
        var autor = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var curso = cursoRepository.findById(datos.cursoId())
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));
        var topico = new Topico(datos, curso, autor);
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


    public boolean eliminarTopico(Long id){

            var buscarTopico = topicoRepository.findById(id);
            if (buscarTopico.isPresent()) {
                var topico = buscarTopico.get();
                topico.eliminar();
                return true;
            }
            return false;

    }

}
