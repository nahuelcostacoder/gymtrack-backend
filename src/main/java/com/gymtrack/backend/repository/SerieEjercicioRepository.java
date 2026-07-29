package com.gymtrack.backend.repository;

import com.gymtrack.backend.dto.SeriesEjercicioDTO.SerieEjercicioDTO;
import com.gymtrack.backend.model.SerieEjercicio;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SerieEjercicioRepository extends JpaRepository<SerieEjercicio, Long> {

    List<SerieEjercicio> findByEntrenamientoEjercicioEntrenamientoIdAndEntrenamientoEjercicioId(Long entrenamientoId,
                                                                          Long entrenamientoEjercicioId);

    Optional<SerieEjercicio> findByEntrenamientoEjercicioEntrenamientoIdAndEntrenamientoEjercicioIdAndId(Long entrenmaientoId,
                                                                                  Long entrenamientoEjercicioId,
                                                                                  Long id);

    Optional<SerieEjercicio> findTopByEntrenamientoEjercicioIdOrderByNumeroSerieDesc(
            Long entrenamientoEjercicioId
    );


    List<SerieEjercicio>
    findByEntrenamientoEjercicioIdAndNumeroSerieGreaterThanEqualAndNumeroSerieLessThanOrderByNumeroSerieDesc(
            Long entrenamientoEjercicioId,
            Integer numeroNuevo,
            Integer numeroViejo
    );

    List<SerieEjercicio>
    findByEntrenamientoEjercicioIdAndNumeroSerieGreaterThanAndNumeroSerieLessThanEqualOrderByNumeroSerieAsc(
            Long entrenamientoEjercicioId,
            Integer numeroViejo,
            Integer numeroNuevo
    );

}
