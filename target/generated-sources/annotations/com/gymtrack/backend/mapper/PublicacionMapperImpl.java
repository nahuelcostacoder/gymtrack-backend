package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.PublicacionDTO.ActualizarPublicacionDTO;
import com.gymtrack.backend.dto.PublicacionDTO.CrearPublicacionDTO;
import com.gymtrack.backend.dto.PublicacionDTO.PublicacionDTO;
import com.gymtrack.backend.model.Entrenamiento;
import com.gymtrack.backend.model.Publicacion;
import com.gymtrack.backend.model.Usuario;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-26T20:57:26-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12.1 (Microsoft)"
)
@Component
public class PublicacionMapperImpl implements PublicacionMapper {

    @Autowired
    private UsuarioResumenMapper usuarioResumenMapper;
    @Autowired
    private EntrenamientoMapper entrenamientoMapper;

    @Override
    public PublicacionDTO toDTO(Publicacion publicacion) {
        if ( publicacion == null ) {
            return null;
        }

        PublicacionDTO.PublicacionDTOBuilder publicacionDTO = PublicacionDTO.builder();

        publicacionDTO.usuario( usuarioResumenMapper.toDto( publicacionEntrenamientoUsuario( publicacion ) ) );
        publicacionDTO.id( publicacion.getId() );
        publicacionDTO.contenido( publicacion.getContenido() );
        publicacionDTO.entrenamiento( entrenamientoMapper.toDto( publicacion.getEntrenamiento() ) );
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

    private Usuario publicacionEntrenamientoUsuario(Publicacion publicacion) {
        Entrenamiento entrenamiento = publicacion.getEntrenamiento();
        if ( entrenamiento == null ) {
            return null;
        }
        return entrenamiento.getUsuario();
    }
}
