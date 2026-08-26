package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.PublicacionDTO.ActualizarPublicacionDTO;
import com.gymtrack.backend.dto.PublicacionDTO.CrearPublicacionDTO;
import com.gymtrack.backend.dto.PublicacionDTO.PublicacionDTO;
import com.gymtrack.backend.model.Publicacion;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        uses = {
            UsuarioResumenMapper.class,
                EntrenamientoMapper.class
        }
)
public interface PublicacionMapper {

    //le decimos a mapstruct que vaya a entrenamiento para obtener el usuario y lo guarde en la publi
    @Mapping(source = "entrenamiento.usuario", target = "usuario")
    PublicacionDTO toDTO(Publicacion publicacion);

    @Mapping(target = "entrenamiento", ignore = true)
    Publicacion toEntity(CrearPublicacionDTO dto);

    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    void updateEntity(ActualizarPublicacionDTO dto,
                      @MappingTarget Publicacion publicacion);

}
