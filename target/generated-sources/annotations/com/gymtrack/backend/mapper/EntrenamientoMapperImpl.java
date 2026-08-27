package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.EntrenamientoDTO.ActualizarEntrenamientoDTO;
import com.gymtrack.backend.dto.EntrenamientoDTO.CrearEntrenamientoDTO;
import com.gymtrack.backend.dto.EntrenamientoDTO.EntrenamientoDTO;
import com.gymtrack.backend.model.Entrenamiento;
import com.gymtrack.backend.model.Rutina;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-26T20:57:26-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12.1 (Microsoft)"
)
@Component
public class EntrenamientoMapperImpl implements EntrenamientoMapper {

    @Override
    public EntrenamientoDTO toDto(Entrenamiento entrenamiento) {
        if ( entrenamiento == null ) {
            return null;
        }

        EntrenamientoDTO.EntrenamientoDTOBuilder entrenamientoDTO = EntrenamientoDTO.builder();

        entrenamientoDTO.rutinaId( entrenamientoRutinaId( entrenamiento ) );
        entrenamientoDTO.rutinaNombre( entrenamientoRutinaNombre( entrenamiento ) );
        entrenamientoDTO.id( entrenamiento.getId() );
        entrenamientoDTO.fechaInicio( entrenamiento.getFechaInicio() );
        entrenamientoDTO.fechaFin( entrenamiento.getFechaFin() );
        if ( entrenamiento.getDuracionMinutos() != null ) {
            entrenamientoDTO.duracionMinutos( entrenamiento.getDuracionMinutos().intValue() );
        }
        entrenamientoDTO.observaciones( entrenamiento.getObservaciones() );

        return entrenamientoDTO.build();
    }

    @Override
    public Entrenamiento toEntity(CrearEntrenamientoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Entrenamiento.EntrenamientoBuilder entrenamiento = Entrenamiento.builder();

        entrenamiento.observaciones( dto.getObservaciones() );

        return entrenamiento.build();
    }

    @Override
    public void updateEntity(ActualizarEntrenamientoDTO dto, Entrenamiento entrenamiento) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getObservaciones() != null ) {
            entrenamiento.setObservaciones( dto.getObservaciones() );
        }
    }

    private Long entrenamientoRutinaId(Entrenamiento entrenamiento) {
        Rutina rutina = entrenamiento.getRutina();
        if ( rutina == null ) {
            return null;
        }
        return rutina.getId();
    }

    private String entrenamientoRutinaNombre(Entrenamiento entrenamiento) {
        Rutina rutina = entrenamiento.getRutina();
        if ( rutina == null ) {
            return null;
        }
        return rutina.getNombre();
    }
}
