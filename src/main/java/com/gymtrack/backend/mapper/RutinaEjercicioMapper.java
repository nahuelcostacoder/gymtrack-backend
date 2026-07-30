package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.RutinaEjercicioDTO.ActualizarRutinaEjercicioDTO;
import com.gymtrack.backend.dto.RutinaEjercicioDTO.CrearRutinaEjercicioDTO;
import com.gymtrack.backend.dto.RutinaEjercicioDTO.RutinaEjercicioDTO;
import com.gymtrack.backend.model.RutinaEjercicio;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        uses = EjercicioMapper.class
)
public interface RutinaEjercicioMapper {

    RutinaEjercicioDTO toDTO(RutinaEjercicio rutinaEjercicio);

    @Mapping(target = "rutina", ignore = true)
    @Mapping(target = "ejercicio", ignore = true)
    RutinaEjercicio toEntity(CrearRutinaEjercicioDTO dto);

    @Mapping(target = "rutina", ignore = true)
    @Mapping(target = "ejercicio", ignore = true)
    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    void updateEntity(ActualizarRutinaEjercicioDTO dto, @MappingTarget RutinaEjercicio rutinaEjercicio);
}