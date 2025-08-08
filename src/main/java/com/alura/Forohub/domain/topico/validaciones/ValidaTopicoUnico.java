package com.alura.Forohub.domain.topico.validaciones;


import com.alura.Forohub.domain.topico.DatosDetalleTopico;
import com.alura.Forohub.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaTopicoUnico implements ValidadorDeTopicos {

    @Autowired
    private TopicoRepository topicoRepository;

    public void validar(DatosDetalleTopico datos){
        if (topicoRepository.existsByTitulo(datos.titulo())){
            throw new ValidationException(" ⚠️ ya existe un topico con este titulo!");
        }
        if (topicoRepository.existsByMensaje(datos.mensaje())){
            throw new ValidationException("⚠️ Ya existe un topico con este mensaje!");
        }
    }


}
