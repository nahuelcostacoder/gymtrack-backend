package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.SeriesEjercicioDTO.ActualizarNumeroSerieDTO;
import com.gymtrack.backend.dto.SeriesEjercicioDTO.ActualizarSerieEjercicioDTO;
import com.gymtrack.backend.dto.SeriesEjercicioDTO.CrearSerieEjercicioDTO;
import com.gymtrack.backend.dto.SeriesEjercicioDTO.SerieEjercicioDTO;

import java.util.List;

public interface SerieEjercicioService {

    List<SerieEjercicioDTO> listarPorEntrenamientoEjercicio(Long entrenamientoId,
                                   Long entrenamientoEjercicioId);

    SerieEjercicioDTO buscarPorId(Long entrenamientoId,
                                  Long entrenamientoEjercicioId,
                                  Long serieEjercicioId);

    SerieEjercicioDTO crear(Long entrenamientoId,
                            Long entrenamientoEjercicioId,
                            CrearSerieEjercicioDTO dto);

    SerieEjercicioDTO actualizar(Long entrenamientoId,
                                 Long entrenamientoEjercicioId,
                                 Long serieEjercicioId,
                                 ActualizarSerieEjercicioDTO dto);

    SerieEjercicioDTO actualizarNumeroSerie(Long entrenamientoId,
                                            Long entrenamientoEjercicioId,
                                            Long serieEjercicioId,
                                            ActualizarNumeroSerieDTO dto);

    void eliminar(Long entrenamientoId,
                  Long entrenamientoEjercicioId,
                  Long serieEjercicioId);
}
