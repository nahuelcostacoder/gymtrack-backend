package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.AmistadDTO.AmistadDTO;
import com.gymtrack.backend.dto.AmistadDTO.EnviarSolicitudDTO;
import com.gymtrack.backend.model.Amistad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = UsuarioResumenMapper.class

)
public interface AmistadMapper {

    @Mapping(source = "emisorSolicitud", target = "emisorSolicitud")
    @Mapping(source = "receptorSolicitud", target = "receptorSolicitud")
    AmistadDTO toDto(Amistad amistad); //asi sabe como mapear de usuario a usuario resumen
}
