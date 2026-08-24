package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.UsuarioDTO.UsuarioResumenDTO;
import com.gymtrack.backend.model.Usuario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-23T21:37:42-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12.1 (Microsoft)"
)
@Component
public class UsuarioResumenMapperImpl implements UsuarioResumenMapper {

    @Override
    public UsuarioResumenDTO toDto(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioResumenDTO.UsuarioResumenDTOBuilder usuarioResumenDTO = UsuarioResumenDTO.builder();

        usuarioResumenDTO.id( usuario.getId() );
        usuarioResumenDTO.username( usuario.getUsername() );
        usuarioResumenDTO.nombre( usuario.getNombre() );
        usuarioResumenDTO.fotoPerfilUrl( usuario.getFotoPerfilUrl() );

        return usuarioResumenDTO.build();
    }
}
