package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.RolDTO.ActualizarRolDTO;
import com.gymtrack.backend.dto.RolDTO.CrearRolDTO;
import com.gymtrack.backend.dto.RolDTO.RolDTO;
import com.gymtrack.backend.model.Rol;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-07T20:54:15-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class RolMapperImpl implements RolMapper {

    @Override
    public RolDTO toDTO(Rol rol) {
        if ( rol == null ) {
            return null;
        }

        RolDTO.RolDTOBuilder rolDTO = RolDTO.builder();

        rolDTO.id( rol.getId() );
        rolDTO.nombre( rol.getNombre() );
        rolDTO.permisos( mapPermiso( rol.getPermisos() ) );

        return rolDTO.build();
    }

    @Override
    public Rol toEntity(CrearRolDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Rol.RolBuilder rol = Rol.builder();

        rol.nombre( dto.getNombre() );

        return rol.build();
    }

    @Override
    public void updateEntity(ActualizarRolDTO dto, Rol rol) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getNombre() != null ) {
            rol.setNombre( dto.getNombre() );
        }
    }
}
