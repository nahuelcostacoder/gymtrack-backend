package com.gymtrack.backend.repository;

import com.gymtrack.backend.model.Comentario;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.awt.print.Pageable;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    Page<Comentario> findByUsuarioId(Long usuarioId, Pageable pageable);
    Page<Comentario> findByPublicacionId(Long publicacionId, Pageable pageable);
}
