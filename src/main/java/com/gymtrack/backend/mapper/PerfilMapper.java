package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.PerfilDTO.ActualizarPerfilDTO;
import com.gymtrack.backend.dto.PerfilDTO.CrearPerfilDTO;
import com.gymtrack.backend.dto.PerfilDTO.PerfilDTO;
import com.gymtrack.backend.model.Perfil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PerfilMapper {

    //busca en los atributos de la entidad usuario_id (en este caso busca en la entidad de usuario el long id) y lo pone en usuarioId en el dto
    //es la manera de que mapstruct encuentre y sepa la relacion
    @Mapping(source = "usuario.id", target = "usuarioId")
    PerfilDTO toDTO(Perfil perfil);

    @Mapping(target = "usuario", ignore = true)
    Perfil toEntity(CrearPerfilDTO dto);

    @Mapping(target = "usuario", ignore = true)
    void updateEntity(ActualizarPerfilDTO dto, @MappingTarget Perfil perfil);
}
