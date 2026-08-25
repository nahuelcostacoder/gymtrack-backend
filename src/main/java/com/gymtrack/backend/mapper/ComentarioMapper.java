package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.ComentarioDTO.ComentarioDTO;
import com.gymtrack.backend.dto.ComentarioDTO.CrearComentarioDTO;
import com.gymtrack.backend.dto.ComentarioDTO.EditarComentarioDTO;
import com.gymtrack.backend.model.Comentario;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {

    ComentarioDTO toDTO(Comentario comentario);

    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "publicacion", ignore = true)
    Comentario toEntity(CrearComentarioDTO dto);

    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    void updateEntity(EditarComentarioDTO dto, @MappingTarget Comentario comentario);
}
