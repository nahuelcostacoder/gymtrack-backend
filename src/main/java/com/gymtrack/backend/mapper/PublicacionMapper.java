package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.PublicacionDTO.ActualizarPublicacionDTO;
import com.gymtrack.backend.dto.PublicacionDTO.CrearPublicacionDTO;
import com.gymtrack.backend.dto.PublicacionDTO.PublicacionDTO;
import com.gymtrack.backend.model.Publicacion;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PublicacionMapper {

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
