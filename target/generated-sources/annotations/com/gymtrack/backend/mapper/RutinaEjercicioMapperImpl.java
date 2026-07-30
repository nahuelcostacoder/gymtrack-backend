package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.RutinaEjercicioDTO.ActualizarRutinaEjercicioDTO;
import com.gymtrack.backend.dto.RutinaEjercicioDTO.CrearRutinaEjercicioDTO;
import com.gymtrack.backend.dto.RutinaEjercicioDTO.RutinaEjercicioDTO;
import com.gymtrack.backend.model.RutinaEjercicio;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-30T15:04:52-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class RutinaEjercicioMapperImpl implements RutinaEjercicioMapper {

    @Autowired
    private EjercicioMapper ejercicioMapper;

    @Override
    public RutinaEjercicioDTO toDTO(RutinaEjercicio rutinaEjercicio) {
        if ( rutinaEjercicio == null ) {
            return null;
        }

        RutinaEjercicioDTO.RutinaEjercicioDTOBuilder rutinaEjercicioDTO = RutinaEjercicioDTO.builder();

        rutinaEjercicioDTO.id( rutinaEjercicio.getId() );
        rutinaEjercicioDTO.orden( rutinaEjercicio.getOrden() );
        rutinaEjercicioDTO.seriesObjetivo( rutinaEjercicio.getSeriesObjetivo() );
        rutinaEjercicioDTO.repeticionesObjetivo( rutinaEjercicio.getRepeticionesObjetivo() );
        rutinaEjercicioDTO.pesoObjetivo( rutinaEjercicio.getPesoObjetivo() );
        rutinaEjercicioDTO.descansoSegundos( rutinaEjercicio.getDescansoSegundos() );
        rutinaEjercicioDTO.observaciones( rutinaEjercicio.getObservaciones() );
        rutinaEjercicioDTO.ejercicio( ejercicioMapper.toDto( rutinaEjercicio.getEjercicio() ) );

        return rutinaEjercicioDTO.build();
    }

    @Override
    public RutinaEjercicio toEntity(CrearRutinaEjercicioDTO dto) {
        if ( dto == null ) {
            return null;
        }

        RutinaEjercicio.RutinaEjercicioBuilder rutinaEjercicio = RutinaEjercicio.builder();

        rutinaEjercicio.orden( dto.getOrden() );
        rutinaEjercicio.seriesObjetivo( dto.getSeriesObjetivo() );
        rutinaEjercicio.repeticionesObjetivo( dto.getRepeticionesObjetivo() );
        rutinaEjercicio.pesoObjetivo( dto.getPesoObjetivo() );
        rutinaEjercicio.descansoSegundos( dto.getDescansoSegundos() );
        rutinaEjercicio.observaciones( dto.getObservaciones() );

        return rutinaEjercicio.build();
    }

    @Override
    public void updateEntity(ActualizarRutinaEjercicioDTO dto, RutinaEjercicio rutinaEjercicio) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getOrden() != null ) {
            rutinaEjercicio.setOrden( dto.getOrden() );
        }
        if ( dto.getSeriesObjetivo() != null ) {
            rutinaEjercicio.setSeriesObjetivo( dto.getSeriesObjetivo() );
        }
        if ( dto.getRepeticionesObjetivo() != null ) {
            rutinaEjercicio.setRepeticionesObjetivo( dto.getRepeticionesObjetivo() );
        }
        if ( dto.getPesoObjetivo() != null ) {
            rutinaEjercicio.setPesoObjetivo( dto.getPesoObjetivo() );
        }
        if ( dto.getDescansoSegundos() != null ) {
            rutinaEjercicio.setDescansoSegundos( dto.getDescansoSegundos() );
        }
        if ( dto.getObservaciones() != null ) {
            rutinaEjercicio.setObservaciones( dto.getObservaciones() );
        }
    }
}
