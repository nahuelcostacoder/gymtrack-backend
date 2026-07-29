package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.SeriesEjercicioDTO.ActualizarNumeroSerieDTO;
import com.gymtrack.backend.dto.SeriesEjercicioDTO.ActualizarSerieEjercicioDTO;
import com.gymtrack.backend.dto.SeriesEjercicioDTO.CrearSerieEjercicioDTO;
import com.gymtrack.backend.dto.SeriesEjercicioDTO.SerieEjercicioDTO;
import com.gymtrack.backend.exception.NotFoundException;
import com.gymtrack.backend.mapper.SerieEjercicioMapper;
import com.gymtrack.backend.model.EntrenamientoEjercicio;
import com.gymtrack.backend.model.SerieEjercicio;
import com.gymtrack.backend.repository.EntrenamientoEjercicioRepository;
import com.gymtrack.backend.repository.SerieEjercicioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SerieEjercicioServiceImp implements SerieEjercicioService{

    private final SerieEjercicioRepository serieEjercicioRepository;
    private final EntrenamientoEjercicioRepository entrenamientoEjercicioRepository;
    private final SerieEjercicioMapper serieEjercicioMapper;

    @Override
    public List<SerieEjercicioDTO> listarPorEntrenamientoEjercicio(Long entrenamientoId, Long entrenamientoEjercicioId) {
        return serieEjercicioRepository.findByEntrenamientoEjercicioEntrenamientoIdAndEntrenamientoEjercicioId(entrenamientoId, entrenamientoEjercicioId)
                .stream().map(serieEjercicioMapper::toDto).toList();
    }

    @Override
    public SerieEjercicioDTO buscarPorId(Long entrenamientoId, Long entrenamientoEjercicioId, Long serieEjercicioId) {

        SerieEjercicio serieEjercicio = buscarEntidadSerieEjercicioPorId(entrenamientoId, entrenamientoEjercicioId, serieEjercicioId);

        return serieEjercicioMapper.toDto(serieEjercicio);
    }

    @Override
    public SerieEjercicioDTO crear(Long entrenamientoId, Long entrenamientoEjercicioId, CrearSerieEjercicioDTO dto) {

        EntrenamientoEjercicio entrenamientoEjercicio = buscarEntidadEntrenamientoEjercicioPorId(entrenamientoId, entrenamientoEjercicioId);

        SerieEjercicio serieEjercicio = serieEjercicioMapper.toEntity(dto);

        int siguienteNumero = serieEjercicioRepository.findTopByEntrenamientoEjercicioIdOrderByNumeroSerieDesc(entrenamientoEjercicioId)
                .map(serie -> serie.getNumeroSerie() + 1).orElse(1);

        serieEjercicio.setEntrenamientoEjercicio(entrenamientoEjercicio);
        serieEjercicio.setNumeroSerie(siguienteNumero);


        return serieEjercicioMapper.toDto(serieEjercicioRepository.save(serieEjercicio));
    }

    @Override
    public SerieEjercicioDTO actualizar(Long entrenamientoId, Long entrenamientoEjercicioId, Long serieEjercicioId, ActualizarSerieEjercicioDTO dto) {

        SerieEjercicio serieEjercicio = buscarEntidadSerieEjercicioPorId(
                entrenamientoId, entrenamientoEjercicioId, serieEjercicioId);

        serieEjercicioMapper.updateEntity(dto, serieEjercicio);

        return serieEjercicioMapper.toDto(serieEjercicioRepository.save(serieEjercicio));
    }

    @Transactional
    @Override
    public SerieEjercicioDTO actualizarNumeroSerie(
            Long entrenamientoId,
            Long entrenamientoEjercicioId,
            Long serieEjercicioId,
            ActualizarNumeroSerieDTO dto) {

        SerieEjercicio serieEjercicio =
                buscarEntidadSerieEjercicioPorId(
                        entrenamientoId,
                        entrenamientoEjercicioId,
                        serieEjercicioId
                );

        int numeroViejo = serieEjercicio.getNumeroSerie();
        int numeroNuevo = dto.getNumeroSerie();

        if (numeroNuevo == numeroViejo) {
            return serieEjercicioMapper.toDto(serieEjercicio);
        }

        serieEjercicio.setNumeroSerie(0);
        serieEjercicioRepository.saveAndFlush(serieEjercicio);

        if (numeroNuevo < numeroViejo) {

            List<SerieEjercicio> series =
                    serieEjercicioRepository
                            .findByEntrenamientoEjercicioIdAndNumeroSerieGreaterThanEqualAndNumeroSerieLessThanOrderByNumeroSerieDesc(
                                    entrenamientoEjercicioId,
                                    numeroNuevo,
                                    numeroViejo
                            );

            for (SerieEjercicio serie : series) {
                serie.setNumeroSerie(serie.getNumeroSerie() + 1);
                serieEjercicioRepository.saveAndFlush(serie);
            }

        } else {

            List<SerieEjercicio> series =
                    serieEjercicioRepository
                            .findByEntrenamientoEjercicioIdAndNumeroSerieGreaterThanAndNumeroSerieLessThanEqualOrderByNumeroSerieAsc(
                                    entrenamientoEjercicioId,
                                    numeroViejo,
                                    numeroNuevo
                            );

            for (SerieEjercicio serie : series) {
                serie.setNumeroSerie(serie.getNumeroSerie() - 1);
                serieEjercicioRepository.saveAndFlush(serie);
            }
        }

        serieEjercicio.setNumeroSerie(numeroNuevo);

        return serieEjercicioMapper.toDto(
                serieEjercicioRepository.save(serieEjercicio)
        );
    }

    @Override
    public void eliminar(Long entrenamientoId, Long entrenamientoEjercicioId, Long serieEjercicioId) {

        SerieEjercicio serieEjercicio = buscarEntidadSerieEjercicioPorId(entrenamientoId, entrenamientoEjercicioId, serieEjercicioId);

        serieEjercicioRepository.delete(serieEjercicio);
    }

    private SerieEjercicio buscarEntidadSerieEjercicioPorId(Long entrenamientoId, Long entrenamientoEjercicioId, Long serieEjercicioId){

        return serieEjercicioRepository.findByEntrenamientoEjercicioEntrenamientoIdAndEntrenamientoEjercicioIdAndId(entrenamientoId, entrenamientoEjercicioId, serieEjercicioId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado una serie del entrenamiento de ese ejercicio"));
    }

    private EntrenamientoEjercicio buscarEntidadEntrenamientoEjercicioPorId(Long entrenamientoId, Long entrenamientoEjercicioId){

        return entrenamientoEjercicioRepository.findByEntrenamientoIdAndId(entrenamientoId, entrenamientoEjercicioId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un EntrenamientoEjercicio con id " + entrenamientoEjercicioId));
    }

}