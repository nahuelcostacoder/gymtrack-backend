package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.ComentarioDTO.ComentarioDTO;
import com.gymtrack.backend.dto.ComentarioDTO.CrearComentarioDTO;
import com.gymtrack.backend.dto.ComentarioDTO.EditarComentarioDTO;
import com.gymtrack.backend.dto.UsuarioDTO.UsuarioResumenDTO;
import com.gymtrack.backend.model.Comentario;
import com.gymtrack.backend.model.Usuario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-27T18:59:51-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12.1 (Microsoft)"
)
@Component
public class ComentarioMapperImpl implements ComentarioMapper {

    @Override
    public ComentarioDTO toDTO(Comentario comentario) {
        if ( comentario == null ) {
            return null;
        }

        ComentarioDTO.ComentarioDTOBuilder comentarioDTO = ComentarioDTO.builder();

        comentarioDTO.id( comentario.getId() );
        comentarioDTO.contenido( comentario.getContenido() );
        comentarioDTO.usuario( usuarioToUsuarioResumenDTO( comentario.getUsuario() ) );
        comentarioDTO.fechaCreacion( comentario.getFechaCreacion() );
        comentarioDTO.fechaActualizacion( comentario.getFechaActualizacion() );

        return comentarioDTO.build();
    }

    @Override
    public Comentario toEntity(CrearComentarioDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Comentario.ComentarioBuilder comentario = Comentario.builder();

        comentario.contenido( dto.getContenido() );

        return comentario.build();
    }

    @Override
    public void updateEntity(EditarComentarioDTO dto, Comentario comentario) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getContenido() != null ) {
            comentario.setContenido( dto.getContenido() );
        }
    }

    protected UsuarioResumenDTO usuarioToUsuarioResumenDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioResumenDTO.UsuarioResumenDTOBuilder usuarioResumenDTO = UsuarioResumenDTO.builder();

        usuarioResumenDTO.id( usuario.getId() );
        usuarioResumenDTO.username( usuario.getUsername() );
        usuarioResumenDTO.nombre( usuario.getNombre() );

        return usuarioResumenDTO.build();
    }
}
