package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.EntrenamientoDTO.ActualizarEntrenamientoDTO;
import com.gymtrack.backend.dto.EntrenamientoDTO.CrearEntrenamientoDTO;
import com.gymtrack.backend.dto.EntrenamientoDTO.EntrenamientoDTO;
import com.gymtrack.backend.dto.RutinaEjercicioDTO.CrearRutinaEjercicioDTO;
import com.gymtrack.backend.model.Entrenamiento;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EntrenamientoMapper {

    @Mapping(target = "rutinaId", source = "rutina.id")
    @Mapping(target = "rutinaNombre", source = "rutina.nombre")
    EntrenamientoDTO toDto(Entrenamiento entrenamiento);

    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "rutina", ignore = true)
    Entrenamiento toEntity(CrearEntrenamientoDTO dto);

    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "rutina", ignore = true)
    @Mapping(target = "fechaInicio", ignore = true)
    @Mapping(target = "fechaFin", ignore = true)
    @Mapping(target = "duracionMinutos", ignore = true)
    void updateEntity(ActualizarEntrenamientoDTO dto,
                      @MappingTarget Entrenamiento entrenamiento);
}
