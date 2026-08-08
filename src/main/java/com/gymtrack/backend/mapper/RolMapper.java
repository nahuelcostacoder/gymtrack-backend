package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.RolDTO.ActualizarRolDTO;
import com.gymtrack.backend.dto.RolDTO.CrearRolDTO;
import com.gymtrack.backend.dto.RolDTO.RolDTO;
import com.gymtrack.backend.model.Permiso;
import com.gymtrack.backend.model.Rol;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RolMapper {

    RolDTO toDTO(Rol rol);

    @Mapping(target = "permisos", ignore = true)
    Rol toEntity(CrearRolDTO dto);

    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    void updateEntity(ActualizarRolDTO dto, @MappingTarget Rol rol);

    default Set<String> mapPermiso(Set<Permiso> permiso){

        return permiso.stream().map(Permiso::getNombre).collect(Collectors.toSet());
    }
}
