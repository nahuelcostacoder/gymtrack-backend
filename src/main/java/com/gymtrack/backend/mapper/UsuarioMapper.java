package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.UsuarioDTO.ActualizarUsuarioDTO;
import com.gymtrack.backend.dto.UsuarioDTO.CrearUsuarioDTO;
import com.gymtrack.backend.dto.UsuarioDTO.UsuarioDTO;
import com.gymtrack.backend.model.Rol;
import com.gymtrack.backend.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO toDto(Usuario usuario);

    @Mapping(target = "roles", ignore = true) //que no ponga los nombres, pq eso lo hace el service y no lo que me traen del front
    Usuario toEntity(CrearUsuarioDTO usuarioDto);

    @Mapping(target = "roles", ignore = true) //lo mismo aca
    void updateEntity(
            ActualizarUsuarioDTO dto,
            @MappingTarget Usuario usuario //mapping target es para decirle a mapStruct que modifique el ya exitente (el que le pasamos por parametro)
    );


    default String mapRolToString(Rol rol){

        return rol != null ? rol.getNombre(): null;
    }
}
