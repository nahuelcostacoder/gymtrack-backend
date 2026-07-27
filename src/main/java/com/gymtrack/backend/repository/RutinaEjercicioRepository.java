package com.gymtrack.backend.repository;

import com.gymtrack.backend.model.RutinaEjercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RutinaEjercicioRepository extends JpaRepository<RutinaEjercicio, Long> {

    //Id not significa distinto al id de RutinaEjercicio
    //¿Existe otro RutinaEjercicio de esta rutina que tenga este mismo ejercicio, distinto del que estoy editando?"
    boolean existsByRutinaIdAndEjercicioIdAndIdNot(Long idRutina, Long idEjercicio, Long idRutinaEjercicio);
    boolean existsByRutinaIdAndOrdenAndIdNot(Long idRutina, Integer ordenId, Long idRutinaEjercicio);
    boolean existsByRutinaIdAndEjercicioId(Long idRutina, Long idEjercicio);
    boolean existsByRutinaIdAndOrden(Long idRutina, Integer orden);
    Optional<RutinaEjercicio> findByIdAndRutinaId(Long rutinaEjercicioId, Long rutinaId);
    List<RutinaEjercicio> findByRutinaId(Long rutinaId);
}
