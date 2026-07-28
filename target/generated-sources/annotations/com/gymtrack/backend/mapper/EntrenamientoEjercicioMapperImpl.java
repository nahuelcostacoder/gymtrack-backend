package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.EntrenamientoEjercicioDTO.ActualizarEntrenamientoEjercicioDTO;
import com.gymtrack.backend.dto.EntrenamientoEjercicioDTO.CrearEntrenamientoEjercicioDTO;
import com.gymtrack.backend.dto.EntrenamientoEjercicioDTO.EntrenamientoEjercicioDTO;
import com.gymtrack.backend.model.EntrenamientoEjercicio;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-28T13:40:02-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class EntrenamientoEjercicioMapperImpl implements EntrenamientoEjercicioMapper {

    @Autowired
    private EjercicioMapper ejercicioMapper;

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
}
