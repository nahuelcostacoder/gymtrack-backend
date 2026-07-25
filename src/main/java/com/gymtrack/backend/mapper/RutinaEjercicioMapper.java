package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.RutinaEjercicioDTO.CrearRutinaEjercicioDTO;
import com.gymtrack.backend.dto.RutinaEjercicioDTO.RutinaEjercicioDTO;
import com.gymtrack.backend.model.RutinaEjercicio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = EjercicioMapper.class
)
public interface RutinaEjercicioMapper {

    RutinaEjercicioDTO toDTO(RutinaEjercicio rutinaEjercicio);

    @Mapping(target = "rutina", ignore = true)
    @Mapping(target = "ejercicio", ignore = true)
    RutinaEjercicio toEntity(CrearRutinaEjercicioDTO dto);
}