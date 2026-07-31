package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.UsuarioDTO.UsuarioResumenDTO;
import com.gymtrack.backend.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioResumenMapper {

    //para que sepa como pasar de usuario a usuarioResumenDTO
    UsuarioResumenDTO toDto(Usuario usuario);
}
