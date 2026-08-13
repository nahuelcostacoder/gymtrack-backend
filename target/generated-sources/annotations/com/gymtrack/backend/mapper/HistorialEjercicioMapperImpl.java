package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.EjercicioDTO.HistorialEjercicioDTO;
import com.gymtrack.backend.dto.SeriesEjercicioDTO.SerieEjercicioDTO;
import com.gymtrack.backend.model.Entrenamiento;
import com.gymtrack.backend.model.EntrenamientoEjercicio;
import com.gymtrack.backend.model.Rutina;
import com.gymtrack.backend.model.SerieEjercicio;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-12T21:01:15-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class HistorialEjercicioMapperImpl implements HistorialEjercicioMapper {

    @Autowired
    private SerieEjercicioMapper serieEjercicioMapper;

    @Override
    public HistorialEjercicioDTO toDto(EntrenamientoEjercicio entrenamientoEjercicio) {
        if ( entrenamientoEjercicio == null ) {
            return null;
        }

        HistorialEjercicioDTO.HistorialEjercicioDTOBuilder historialEjercicioDTO = HistorialEjercicioDTO.builder();

        historialEjercicioDTO.entrenamientoId( entrenamientoEjercicioEntrenamientoId( entrenamientoEjercicio ) );
        historialEjercicioDTO.fechaEntrenamiento( entrenamientoEjercicioEntrenamientoFechaInicio( entrenamientoEjercicio ) );
        historialEjercicioDTO.nombreRutina( entrenamientoEjercicioEntrenamientoRutinaNombre( entrenamientoEjercicio ) );
        historialEjercicioDTO.series( serieEjercicioListToSerieEjercicioDTOList( entrenamientoEjercicio.getSeries() ) );

        return historialEjercicioDTO.build();
    }

    private Long entrenamientoEjercicioEntrenamientoId(EntrenamientoEjercicio entrenamientoEjercicio) {
        Entrenamiento entrenamiento = entrenamientoEjercicio.getEntrenamiento();
        if ( entrenamiento == null ) {
            return null;
        }
        return entrenamiento.getId();
    }

    private LocalDateTime entrenamientoEjercicioEntrenamientoFechaInicio(EntrenamientoEjercicio entrenamientoEjercicio) {
        Entrenamiento entrenamiento = entrenamientoEjercicio.getEntrenamiento();
        if ( entrenamiento == null ) {
            return null;
        }
        return entrenamiento.getFechaInicio();
    }

    private String entrenamientoEjercicioEntrenamientoRutinaNombre(EntrenamientoEjercicio entrenamientoEjercicio) {
        Entrenamiento entrenamiento = entrenamientoEjercicio.getEntrenamiento();
        if ( entrenamiento == null ) {
            return null;
        }
        Rutina rutina = entrenamiento.getRutina();
        if ( rutina == null ) {
            return null;
        }
        return rutina.getNombre();
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
