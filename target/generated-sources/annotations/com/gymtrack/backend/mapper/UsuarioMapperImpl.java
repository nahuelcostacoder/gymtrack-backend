package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.UsuarioDTO.ActualizarUsuarioDTO;
import com.gymtrack.backend.dto.UsuarioDTO.CrearUsuarioDTO;
import com.gymtrack.backend.dto.UsuarioDTO.UsuarioDTO;
import com.gymtrack.backend.model.Rol;
import com.gymtrack.backend.model.Usuario;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-22T13:15:12-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioDTO toDto(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioDTO.UsuarioDTOBuilder usuarioDTO = UsuarioDTO.builder();

        usuarioDTO.id( usuario.getId() );
        usuarioDTO.username( usuario.getUsername() );
        usuarioDTO.email( usuario.getEmail() );
        usuarioDTO.nombre( usuario.getNombre() );
        usuarioDTO.apellido( usuario.getApellido() );
        usuarioDTO.fotoPerfilUrl( usuario.getFotoPerfilUrl() );
        usuarioDTO.fechaNacimiento( usuario.getFechaNacimiento() );
        usuarioDTO.habilitado( usuario.isHabilitado() );
        usuarioDTO.roles( rolSetToStringSet( usuario.getRoles() ) );

        return usuarioDTO.build();
    }

    @Override
    public Usuario toEntity(CrearUsuarioDTO usuarioDto) {
        if ( usuarioDto == null ) {
            return null;
        }

        Usuario.UsuarioBuilder usuario = Usuario.builder();

        usuario.username( usuarioDto.getUsername() );
        usuario.email( usuarioDto.getEmail() );
        usuario.password( usuarioDto.getPassword() );
        usuario.nombre( usuarioDto.getNombre() );
        usuario.apellido( usuarioDto.getApellido() );
        usuario.fechaNacimiento( usuarioDto.getFechaNacimiento() );

        return usuario.build();
    }

    @Override
    public void updateEntity(ActualizarUsuarioDTO dto, Usuario usuario) {
        if ( dto == null ) {
            return;
        }

        usuario.setUsername( dto.getUsername() );
        usuario.setNombre( dto.getNombre() );
        usuario.setApellido( dto.getApellido() );
        usuario.setFotoPerfilUrl( dto.getFotoPerfilUrl() );
        usuario.setFechaNacimiento( dto.getFechaNacimiento() );
    }

    protected Set<String> rolSetToStringSet(Set<Rol> set) {
        if ( set == null ) {
            return null;
        }

        Set<String> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( Rol rol : set ) {
            set1.add( mapRolToString( rol ) );
        }

        return set1;
    }
}
