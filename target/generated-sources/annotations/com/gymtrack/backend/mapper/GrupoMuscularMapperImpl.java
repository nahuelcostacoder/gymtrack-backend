package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.GrupoMuscularDTO.ActualizarGrupoMuscularDTO;
import com.gymtrack.backend.dto.GrupoMuscularDTO.CrearGrupoMuscularDTO;
import com.gymtrack.backend.dto.GrupoMuscularDTO.GrupoMuscularDTO;
import com.gymtrack.backend.model.GrupoMuscular;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-22T22:11:09-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class GrupoMuscularMapperImpl implements GrupoMuscularMapper {

    @Override
    public GrupoMuscularDTO toDto(GrupoMuscular grupoMuscular) {
        if ( grupoMuscular == null ) {
            return null;
        }

        GrupoMuscularDTO.GrupoMuscularDTOBuilder grupoMuscularDTO = GrupoMuscularDTO.builder();

        grupoMuscularDTO.id( grupoMuscular.getId() );
        grupoMuscularDTO.nombre( grupoMuscular.getNombre() );

        return grupoMuscularDTO.build();
    }

    @Override
    public GrupoMuscular toEntity(CrearGrupoMuscularDTO dto) {
        if ( dto == null ) {
            return null;
        }

        GrupoMuscular.GrupoMuscularBuilder grupoMuscular = GrupoMuscular.builder();

        grupoMuscular.nombre( dto.getNombre() );

        return grupoMuscular.build();
    }

    @Override
    public void updateEntity(ActualizarGrupoMuscularDTO dto, GrupoMuscular grupoMuscular) {
        if ( dto == null ) {
            return;
        }

        grupoMuscular.setNombre( dto.getNombre() );
    }
}
