package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.EjercicioDTO.HistorialEjercicioDTO;
import com.gymtrack.backend.model.EntrenamientoEjercicio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = SerieEjercicioMapper.class
)
public interface HistorialEjercicioMapper {

    @Mapping(target = "entrenamientoId", source = "entrenamiento.id")
    @Mapping(target = "fechaEntrenamiento", source = "entrenamiento.fechaInicio")
    @Mapping(target = "nombreRutina", source = "entrenamiento.rutina.nombre")
    @Mapping(target = "series", source = "series")
    HistorialEjercicioDTO toDto(EntrenamientoEjercicio entrenamientoEjercicio);
}
