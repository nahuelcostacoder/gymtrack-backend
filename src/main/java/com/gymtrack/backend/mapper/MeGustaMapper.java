package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.MeGustaDTO.MeGustaDTO;
import com.gymtrack.backend.model.MeGusta;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MeGustaMapper {

    MeGustaDTO toDTO(MeGusta meGusta);


    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "publicacion", ignore = true)
    MeGusta toEntity(MeGustaDTO dto);

}
