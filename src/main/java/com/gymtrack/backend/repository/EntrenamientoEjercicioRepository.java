package com.gymtrack.backend.repository;

import com.gymtrack.backend.dto.EntrenamientoEjercicioDTO.EntrenamientoEjercicioDTO;
import com.gymtrack.backend.model.EntrenamientoEjercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntrenamientoEjercicioRepository extends JpaRepository<EntrenamientoEjercicio, Long> {

    List<EntrenamientoEjercicio> findByEntrenamientoId(Long entrenamientoId);
    Optional<EntrenamientoEjercicio> findByEntrenamientoIdAndId(Long entrenamientoId, Long entrenamientoEjercicioId);
    boolean existsByEntrenamientoIdAndEjercicioId(Long entrenamientoId, Long ejercicioId);
    boolean existsByEntrenamientoIdAndOrden(Long entrenamientoId, Integer orden);
    boolean existsByEntrenamientoIdAndEjercicioIdAndIdNot(Long entrenamientoId, Long ejercicioId, Long id);
    boolean existsByEntrenamientoIdAndOrdenAndIdNot(Long entrenamientoId, Integer orden, Long id);
    List<EntrenamientoEjercicio> findByEntrenamientoUsuarioIdAndEjercicioIdOrderByEntrenamientoFechaInicioDesc(Long usuarioId, Long ejercicioId);

}

