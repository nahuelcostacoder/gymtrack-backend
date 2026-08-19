package com.gymtrack.backend.repository;

import com.gymtrack.backend.model.Publicacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {

    List<Publicacion> findByEntrenamientoUsuarioId(Long usuarioId);
    boolean existsByEntrenamientoId(Long entrenamientoId);
    Page<Publicacion> findAllByOrderByFechaCreacionDesc(Pageable pageable);
    Page<Publicacion> findByEntrenamientoUsuarioId(Long usuarioId, Pageable pageable);

}
