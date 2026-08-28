package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.UsuarioDTO.ActualizarUsuarioAdminDTO;
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
    date = "2026-08-27T21:17:45-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12.1 (Microsoft)"
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

        if ( dto.getUsername() != null ) {
            usuario.setUsername( dto.getUsername() );
        }
        if ( dto.getNombre() != null ) {
            usuario.setNombre( dto.getNombre() );
        }
        if ( dto.getApellido() != null ) {
            usuario.setApellido( dto.getApellido() );
        }
        if ( dto.getFechaNacimiento() != null ) {
            usuario.setFechaNacimiento( dto.getFechaNacimiento() );
        }
    }

    @Override
    public void updateEntityAdmin(ActualizarUsuarioAdminDTO dto, Usuario usuario) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getUsername() != null ) {
            usuario.setUsername( dto.getUsername() );
        }
        if ( dto.getNombre() != null ) {
            usuario.setNombre( dto.getNombre() );
        }
        if ( dto.getApellido() != null ) {
            usuario.setApellido( dto.getApellido() );
        }
        if ( dto.getFechaNacimiento() != null ) {
            usuario.setFechaNacimiento( dto.getFechaNacimiento() );
        }
        usuario.setHabilitado( dto.isHabilitado() );
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
