package com.gymtrack.backend.repository;

import com.gymtrack.backend.model.MeGusta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MeGustaRepository extends JpaRepository<MeGusta, Long> {

    List<MeGusta> findByUsuarioId(Long usuarioId);
    boolean existsByUsuarioIdAndPublicacionId(Long usuarioId,
                                              Long publicacionId);

    Optional<MeGusta> findByUsuarioIdAndPublicacionId(Long usuarioId,
                                                      Long publicacionId);
    Long countByPublicacionId(Long publicacionId);
    Page<MeGusta> findByPublicacionId(Long publicacionId, Pageable pageable);

    @Query("""
        SELECT m.publicacion.id, COUNT(m)
        FROM MeGusta m
        WHERE m.publicacion.id IN :publicacionIds
        GROUP BY m.publicacion.id
    """)
    List<Object[]> contarLikesPorPublicaciones(@Param("publicacionIds") List<Long> publicacionIds);
    //Estoy devolviendo dos valores por cada fila, ID publicacion y la cant de likes
    //por eso devuelvo un object, devuelvo un array de objetos.

    //param como ya vimos era para indicar que de esa lista de publicacionIds es la que uso en el where.

    @Query("""
        SELECT m.publicacion.id
        FROM MeGusta m
        WHERE m.usuario.id = :usuarioId
        AND m.publicacion.id IN :publicacionIds
    """)
    Set<Long> buscarPublicacionesConLikeDelUsuario(@Param("usuarioId") Long usuarioId,
                                                   @Param("publicacionIds") List<Long> puiblicacionIds);
    //me devuelve una lista de a cuales publicaciones de esa pagina el usuario le dio like

}
