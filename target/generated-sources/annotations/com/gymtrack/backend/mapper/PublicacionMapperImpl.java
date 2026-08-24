package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.EntrenamientoDTO.EntrenamientoDTO;
import com.gymtrack.backend.dto.PublicacionDTO.ActualizarPublicacionDTO;
import com.gymtrack.backend.dto.PublicacionDTO.CrearPublicacionDTO;
import com.gymtrack.backend.dto.PublicacionDTO.PublicacionDTO;
import com.gymtrack.backend.model.Entrenamiento;
import com.gymtrack.backend.model.Publicacion;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-23T21:37:42-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12.1 (Microsoft)"
)
@Component
public class PublicacionMapperImpl implements PublicacionMapper {

    @Override
    public PublicacionDTO toDTO(Publicacion publicacion) {
        if ( publicacion == null ) {
            return null;
        }

        PublicacionDTO.PublicacionDTOBuilder publicacionDTO = PublicacionDTO.builder();

        publicacionDTO.id( publicacion.getId() );
        publicacionDTO.contenido( publicacion.getContenido() );
        publicacionDTO.entrenamiento( entrenamientoToEntrenamientoDTO( publicacion.getEntrenamiento() ) );
        publicacionDTO.fechaCreacion( publicacion.getFechaCreacion() );

        return publicacionDTO.build();
    }

    @Override
    public Publicacion toEntity(CrearPublicacionDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Publicacion.PublicacionBuilder publicacion = Publicacion.builder();

        publicacion.contenido( dto.getContenido() );

        return publicacion.build();
    }

    @Override
    public void updateEntity(ActualizarPublicacionDTO dto, Publicacion publicacion) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getContenido() != null ) {
            publicacion.setContenido( dto.getContenido() );
        }
    }

    protected EntrenamientoDTO entrenamientoToEntrenamientoDTO(Entrenamiento entrenamiento) {
        if ( entrenamiento == null ) {
            return null;
        }

        EntrenamientoDTO.EntrenamientoDTOBuilder entrenamientoDTO = EntrenamientoDTO.builder();

        entrenamientoDTO.id( entrenamiento.getId() );
        entrenamientoDTO.fechaInicio( entrenamiento.getFechaInicio() );
        entrenamientoDTO.fechaFin( entrenamiento.getFechaFin() );
        if ( entrenamiento.getDuracionMinutos() != null ) {
            entrenamientoDTO.duracionMinutos( entrenamiento.getDuracionMinutos().intValue() );
        }
        entrenamientoDTO.observaciones( entrenamiento.getObservaciones() );

        return entrenamientoDTO.build();
    }
}
