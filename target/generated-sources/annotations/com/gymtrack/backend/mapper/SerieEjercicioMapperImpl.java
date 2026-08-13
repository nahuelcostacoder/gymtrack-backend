package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.SeriesEjercicioDTO.ActualizarSerieEjercicioDTO;
import com.gymtrack.backend.dto.SeriesEjercicioDTO.CrearSerieEjercicioDTO;
import com.gymtrack.backend.dto.SeriesEjercicioDTO.SerieEjercicioDTO;
import com.gymtrack.backend.model.SerieEjercicio;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-12T21:01:15-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class SerieEjercicioMapperImpl implements SerieEjercicioMapper {

    @Override
    public SerieEjercicioDTO toDto(SerieEjercicio serieEjercicio) {
        if ( serieEjercicio == null ) {
            return null;
        }

        SerieEjercicioDTO.SerieEjercicioDTOBuilder serieEjercicioDTO = SerieEjercicioDTO.builder();

        serieEjercicioDTO.id( serieEjercicio.getId() );
        serieEjercicioDTO.numeroSerie( serieEjercicio.getNumeroSerie() );
        serieEjercicioDTO.peso( serieEjercicio.getPeso() );
        serieEjercicioDTO.repeticiones( serieEjercicio.getRepeticiones() );
        serieEjercicioDTO.rir( serieEjercicio.getRir() );
        serieEjercicioDTO.completada( serieEjercicio.getCompletada() );
        serieEjercicioDTO.observaciones( serieEjercicio.getObservaciones() );

        return serieEjercicioDTO.build();
    }

    @Override
    public SerieEjercicio toEntity(CrearSerieEjercicioDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SerieEjercicio.SerieEjercicioBuilder serieEjercicio = SerieEjercicio.builder();

        serieEjercicio.peso( dto.getPeso() );
        serieEjercicio.repeticiones( dto.getRepeticiones() );
        serieEjercicio.rir( dto.getRir() );
        serieEjercicio.completada( dto.getCompletada() );
        serieEjercicio.observaciones( dto.getObservaciones() );

        return serieEjercicio.build();
    }

    @Override
    public void updateEntity(ActualizarSerieEjercicioDTO dto, SerieEjercicio serieEjercicio) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getNumeroSerie() != null ) {
            serieEjercicio.setNumeroSerie( dto.getNumeroSerie() );
        }
        if ( dto.getPeso() != null ) {
            serieEjercicio.setPeso( dto.getPeso() );
        }
        if ( dto.getRepeticiones() != null ) {
            serieEjercicio.setRepeticiones( dto.getRepeticiones() );
        }
        if ( dto.getRir() != null ) {
            serieEjercicio.setRir( dto.getRir() );
        }
        if ( dto.getCompletada() != null ) {
            serieEjercicio.setCompletada( dto.getCompletada() );
        }
        if ( dto.getObservaciones() != null ) {
            serieEjercicio.setObservaciones( dto.getObservaciones() );
        }
    }
}
