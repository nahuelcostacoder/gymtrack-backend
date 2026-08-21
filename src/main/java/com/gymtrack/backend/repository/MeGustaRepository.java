package com.gymtrack.backend.repository;

import com.gymtrack.backend.model.MeGusta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeGustaRepository extends JpaRepository<MeGusta, Long> {

    List<MeGusta> findByUsuarioId(Long usuarioId);
    boolean existsByUsuarioIdAndPublicacionId(Long usuarioId,
                                              Long publicacionId);

    Optional<MeGusta> findByUsuarioIdAndPublicacionId(Long usuarioId,
                                                      Long publicacionId);
    Long countByPublicacionId(Long publicacionId);
    Page<MeGusta> findByPublicacionId(Long publicacionId, Pageable pageable);

}
