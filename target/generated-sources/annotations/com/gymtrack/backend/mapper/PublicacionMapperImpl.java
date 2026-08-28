package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.MediaPublicacionDTO.MediaPublicacionDTO;
import com.gymtrack.backend.dto.PublicacionDTO.ActualizarPublicacionDTO;
import com.gymtrack.backend.dto.PublicacionDTO.CrearPublicacionDTO;
import com.gymtrack.backend.dto.PublicacionDTO.PublicacionDTO;
import com.gymtrack.backend.model.Entrenamiento;
import com.gymtrack.backend.model.MediaPublicacion;
import com.gymtrack.backend.model.Publicacion;
import com.gymtrack.backend.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-27T21:17:45-0300",
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

        publicacionDTO.media( mediaPublicacionListToMediaPublicacionDTOList( publicacion.getArchivos() ) );
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

    protected MediaPublicacionDTO mediaPublicacionToMediaPublicacionDTO(MediaPublicacion mediaPublicacion) {
        if ( mediaPublicacion == null ) {
            return null;
        }

        MediaPublicacionDTO.MediaPublicacionDTOBuilder mediaPublicacionDTO = MediaPublicacionDTO.builder();

        mediaPublicacionDTO.id( mediaPublicacion.getId() );
        mediaPublicacionDTO.url( mediaPublicacion.getUrl() );
        mediaPublicacionDTO.tipo( mediaPublicacion.getTipo() );

        return mediaPublicacionDTO.build();
    }

    protected List<MediaPublicacionDTO> mediaPublicacionListToMediaPublicacionDTOList(List<MediaPublicacion> list) {
        if ( list == null ) {
            return null;
        }

        List<MediaPublicacionDTO> list1 = new ArrayList<MediaPublicacionDTO>( list.size() );
        for ( MediaPublicacion mediaPublicacion : list ) {
            list1.add( mediaPublicacionToMediaPublicacionDTO( mediaPublicacion ) );
        }

        return list1;
    }

    private Usuario publicacionEntrenamientoUsuario(Publicacion publicacion) {
        Entrenamiento entrenamiento = publicacion.getEntrenamiento();
        if ( entrenamiento == null ) {
            return null;
        }
        return entrenamiento.getUsuario();
    }
}
