package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.EjercicioDTO.ActualizarEjercicioDTO;
import com.gymtrack.backend.dto.EjercicioDTO.CrearEjercicioDTO;
import com.gymtrack.backend.dto.EjercicioDTO.EjercicioDTO;
import com.gymtrack.backend.model.Ejercicio;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-24T17:35:08-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class EjercicioMapperImpl implements EjercicioMapper {

    @Override
    public EjercicioDTO toDto(Ejercicio ejercicio) {
        if ( ejercicio == null ) {
            return null;
        }

        EjercicioDTO.EjercicioDTOBuilder ejercicioDTO = EjercicioDTO.builder();

        ejercicioDTO.gruposMusculares( mapGruposMusculares( ejercicio.getGruposMusculares() ) );
        ejercicioDTO.id( ejercicio.getId() );
        ejercicioDTO.nombre( ejercicio.getNombre() );
        ejercicioDTO.descripcion( ejercicio.getDescripcion() );
        ejercicioDTO.videoUrl( ejercicio.getVideoUrl() );

        return ejercicioDTO.build();
    }

    @Override
    public Ejercicio toEntity(CrearEjercicioDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Ejercicio.EjercicioBuilder ejercicio = Ejercicio.builder();

        ejercicio.nombre( dto.getNombre() );
        ejercicio.descripcion( dto.getDescripcion() );
        ejercicio.videoUrl( dto.getVideoUrl() );

        return ejercicio.build();
    }

    @Override
    public void updateEntity(ActualizarEjercicioDTO dto, Ejercicio ejercicio) {
        if ( dto == null ) {
            return;
        }

        ejercicio.setNombre( dto.getNombre() );
        ejercicio.setDescripcion( dto.getDescripcion() );
        ejercicio.setVideoUrl( dto.getVideoUrl() );
    }
}
