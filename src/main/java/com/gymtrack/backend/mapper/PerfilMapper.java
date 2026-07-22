package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.PerfilDTO.ActualizarPerfilDTO;
import com.gymtrack.backend.dto.PerfilDTO.PerfilDTO;
import com.gymtrack.backend.model.Perfil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PerfilMapper {

    PerfilDTO toDTO(Perfil perfil);

    @Mapping(target = "usuario", ignore = true)
    Perfil toEntity(PerfilDTO dto);

    @Mapping(target = "usuario", ignore = true)
    void updateEntity(ActualizarPerfilDTO dto, @MappingTarget Perfil perfil);
}
