package com.alura.Forohub.domain.topico;

import com.alura.Forohub.domain.curso.Curso;
import com.alura.Forohub.domain.curso.CursoRepository;
import com.alura.Forohub.domain.topico.validaciones.ValidadorDeTopicos;
import com.alura.Forohub.domain.usuarios.Usuario;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ValidadorDeTopicos validadorDeTopicos;

    public Topico crearTopico(DatosCrearTopico datos){

        DatosDetalleTopico detalle = new DatosDetalleTopico(null, datos.titulo(), datos.mensaje(), null,null);
        validadorDeTopicos.validar(detalle);
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
