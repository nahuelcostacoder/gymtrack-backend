package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.PermisoDTO.ActualizarPermisoDTO;
import com.gymtrack.backend.dto.PermisoDTO.CrearPermisoDTO;
import com.gymtrack.backend.dto.PermisoDTO.PermisoDTO;
import com.gymtrack.backend.model.Permiso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermisoMapper {

    PermisoDTO toDTO(Permiso permiso);

    Permiso toEntity(CrearPermisoDTO dto);

    void updateEntity(ActualizarPermisoDTO dto, @MappingTarget Permiso permiso);
}
