package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.GrupoMuscularDTO.ActualizarGrupoMuscularDTO;
import com.gymtrack.backend.dto.GrupoMuscularDTO.CrearGrupoMuscularDTO;
import com.gymtrack.backend.dto.GrupoMuscularDTO.GrupoMuscularDTO;
import com.gymtrack.backend.model.GrupoMuscular;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface GrupoMuscularMapper {

    GrupoMuscularDTO toDto(GrupoMuscular grupoMuscular);

    GrupoMuscular toEntity(CrearGrupoMuscularDTO dto);

    void updateEntity(ActualizarGrupoMuscularDTO dto, @MappingTarget GrupoMuscular grupoMuscular);
}
