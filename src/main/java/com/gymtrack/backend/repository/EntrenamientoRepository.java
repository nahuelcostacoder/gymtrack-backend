package com.gymtrack.backend.repository;

import com.gymtrack.backend.dto.EntrenamientoDTO.EntrenamientoDTO;
import com.gymtrack.backend.model.Entrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Long> {

    Optional<Entrenamiento> findByUsuarioIdOrderByFechaInicioDesc(Long usuarioId);
    Optional<Entrenamiento> findByIdAndUsuarioId(Long entrenamientoId, Long usuarioId);
}
