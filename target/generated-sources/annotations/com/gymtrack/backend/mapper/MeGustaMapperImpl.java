package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.MeGustaDTO.MeGustaDTO;
import com.gymtrack.backend.dto.UsuarioDTO.UsuarioResumenDTO;
import com.gymtrack.backend.model.MeGusta;
import com.gymtrack.backend.model.Usuario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-20T18:36:51-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.4 (Microsoft)"
)
@Component
public class MeGustaMapperImpl implements MeGustaMapper {

    @Override
    public MeGustaDTO toDTO(MeGusta meGusta) {
        if ( meGusta == null ) {
            return null;
        }

        MeGustaDTO.MeGustaDTOBuilder meGustaDTO = MeGustaDTO.builder();

        meGustaDTO.id( meGusta.getId() );
        meGustaDTO.usuario( usuarioToUsuarioResumenDTO( meGusta.getUsuario() ) );

        return meGustaDTO.build();
    }

    @Override
    public MeGusta toEntity(MeGustaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MeGusta.MeGustaBuilder meGusta = MeGusta.builder();

        meGusta.id( dto.getId() );

        return meGusta.build();
    }

    protected UsuarioResumenDTO usuarioToUsuarioResumenDTO(Usuario usuario) {
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
