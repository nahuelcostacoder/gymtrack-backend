package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.EntrenamientoEjercicioDTO.ActualizarEntrenamientoEjercicioDTO;
import com.gymtrack.backend.dto.EntrenamientoEjercicioDTO.CrearEntrenamientoEjercicioDTO;
import com.gymtrack.backend.dto.EntrenamientoEjercicioDTO.EntrenamientoEjercicioDTO;
import com.gymtrack.backend.dto.SeriesEjercicioDTO.SerieEjercicioDTO;
import com.gymtrack.backend.model.EntrenamientoEjercicio;
import com.gymtrack.backend.model.SerieEjercicio;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-26T20:57:26-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12.1 (Microsoft)"
)
@Component
public class EntrenamientoEjercicioMapperImpl implements EntrenamientoEjercicioMapper {

    @Autowired
    private EjercicioMapper ejercicioMapper;
    @Autowired
    private SerieEjercicioMapper serieEjercicioMapper;

    @Override
    public EntrenamientoEjercicioDTO toDto(EntrenamientoEjercicio entrenamientoEjercicio) {
        if ( entrenamientoEjercicio == null ) {
            return null;
        }

        EntrenamientoEjercicioDTO.EntrenamientoEjercicioDTOBuilder entrenamientoEjercicioDTO = EntrenamientoEjercicioDTO.builder();

        entrenamientoEjercicioDTO.id( entrenamientoEjercicio.getId() );
        entrenamientoEjercicioDTO.ejercicio( ejercicioMapper.toDto( entrenamientoEjercicio.getEjercicio() ) );
        entrenamientoEjercicioDTO.orden( entrenamientoEjercicio.getOrden() );
        entrenamientoEjercicioDTO.observaciones( entrenamientoEjercicio.getObservaciones() );
        entrenamientoEjercicioDTO.series( serieEjercicioListToSerieEjercicioDTOList( entrenamientoEjercicio.getSeries() ) );

        return entrenamientoEjercicioDTO.build();
    }

    @Override
    public EntrenamientoEjercicio toEntity(CrearEntrenamientoEjercicioDTO dto) {
        if ( dto == null ) {
            return null;
        }

        EntrenamientoEjercicio.EntrenamientoEjercicioBuilder entrenamientoEjercicio = EntrenamientoEjercicio.builder();

        entrenamientoEjercicio.orden( dto.getOrden() );
        entrenamientoEjercicio.observaciones( dto.getObservaciones() );

        return entrenamientoEjercicio.build();
    }

    @Override
    public void updateEntity(ActualizarEntrenamientoEjercicioDTO dto, EntrenamientoEjercicio entrenamientoEjercicio) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getOrden() != null ) {
            entrenamientoEjercicio.setOrden( dto.getOrden() );
        }
        if ( dto.getObservaciones() != null ) {
            entrenamientoEjercicio.setObservaciones( dto.getObservaciones() );
        }
    }

    protected List<SerieEjercicioDTO> serieEjercicioListToSerieEjercicioDTOList(List<SerieEjercicio> list) {
        if ( list == null ) {
            return null;
        }

        List<SerieEjercicioDTO> list1 = new ArrayList<SerieEjercicioDTO>( list.size() );
        for ( SerieEjercicio serieEjercicio : list ) {
            list1.add( serieEjercicioMapper.toDto( serieEjercicio ) );
        }

        return list1;
    }
}
