package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.PermisoDTO.ActualizarPermisoDTO;
import com.gymtrack.backend.dto.PermisoDTO.CrearPermisoDTO;
import com.gymtrack.backend.dto.PermisoDTO.PermisoDTO;
import com.gymtrack.backend.model.Permiso;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-26T20:57:26-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12.1 (Microsoft)"
)
@Component
public class PermisoMapperImpl implements PermisoMapper {

    @Override
    public PermisoDTO toDTO(Permiso permiso) {
        if ( permiso == null ) {
            return null;
        }

        PermisoDTO.PermisoDTOBuilder permisoDTO = PermisoDTO.builder();

        permisoDTO.id( permiso.getId() );
        permisoDTO.nombre( permiso.getNombre() );

        return permisoDTO.build();
    }

    @Override
    public Permiso toEntity(CrearPermisoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Permiso permiso = new Permiso();

        permiso.setNombre( dto.getNombre() );

        return permiso;
    }

    @Override
    public void updateEntity(ActualizarPermisoDTO dto, Permiso permiso) {
        if ( dto == null ) {
            return;
        }

        permiso.setNombre( dto.getNombre() );
    }
}
