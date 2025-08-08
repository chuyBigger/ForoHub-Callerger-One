package com.alura.Forohub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Page<Topico> findByActivoTrue(Pageable paguinacion);

    @Query("SELECT t FROM topico t WHERE (:nombre IS NULL OR t.titulo LIKE %:nombre%) AND (:anio IS NULL OR YEAR(t.fecha) = :anio)")
    Page<Topico> buscarPorNombreYAnio(@Param("nombre") String nombre, @Param("anio") Integer anio, Pageable pageable);


    boolean existsByTitulo(String titulo);

    boolean existsByMensaje(String mensaje);
}
