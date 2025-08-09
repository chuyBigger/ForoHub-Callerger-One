package com.alura.Forohub.domain.respuesta;

import com.alura.Forohub.domain.topico.TopicoRepository;
import com.alura.Forohub.domain.usuarios.Usuario;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public Respuesta crearRespuesta(DatosCrearRespuesta datos){
        var topico = topicoRepository.findById(datos.topico_id())
                .orElseThrow(()-> new EntityNotFoundException("⚠️ Topico no encontrado!"));
        var autor = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var respuesta = new Respuesta(datos,topico,autor);
        respuestaRepository.save(respuesta);
        return respuesta;
    }

}
