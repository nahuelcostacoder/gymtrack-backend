package com.gymtrack.backend.repository;

import com.gymtrack.backend.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    Page<Comentario> findByUsuarioId(Long usuarioId, Pageable pageable);
    Page<Comentario> findByPublicacionId(Long publicacionId, Pageable pageable);
    Long countByPublicacionId(Long publicacionId);

    @Query("""
            SELECT c.publicacion.id, COUNT (c)
            FROM Comentario c
            WHERE c.publicacion.id IN :publicacionIds
            GROUP BY c.publicacion.id
            """)
    List<Object[]> contarComentariosPorPublicaciones(@Param("publicacionIds") List<Long> publicacionIds);
}
