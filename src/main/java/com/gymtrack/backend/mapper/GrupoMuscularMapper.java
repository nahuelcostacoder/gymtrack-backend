package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.GrupoMuscularDTO.ActualizarGrupoMuscularDTO;
import com.gymtrack.backend.dto.GrupoMuscularDTO.CrearGrupoMuscularDTO;
import com.gymtrack.backend.dto.GrupoMuscularDTO.GrupoMuscularDTO;
import com.gymtrack.backend.model.GrupoMuscular;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring")
public interface GrupoMuscularMapper {

    GrupoMuscularDTO toDto(GrupoMuscular grupoMuscular);

    GrupoMuscular toEntity(CrearGrupoMuscularDTO dto);

    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    void updateEntity(ActualizarGrupoMuscularDTO dto, @MappingTarget GrupoMuscular grupoMuscular);
}
