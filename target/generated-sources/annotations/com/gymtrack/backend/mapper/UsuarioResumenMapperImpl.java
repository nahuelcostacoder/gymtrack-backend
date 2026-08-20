package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.UsuarioDTO.UsuarioResumenDTO;
import com.gymtrack.backend.model.Usuario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-19T22:06:19-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.4 (Microsoft)"
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
