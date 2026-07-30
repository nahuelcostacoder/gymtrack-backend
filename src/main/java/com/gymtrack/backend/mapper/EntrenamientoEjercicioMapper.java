package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.EntrenamientoEjercicioDTO.ActualizarEntrenamientoEjercicioDTO;
import com.gymtrack.backend.dto.EntrenamientoEjercicioDTO.CrearEntrenamientoEjercicioDTO;
import com.gymtrack.backend.dto.EntrenamientoEjercicioDTO.EntrenamientoEjercicioDTO;
import com.gymtrack.backend.model.EntrenamientoEjercicio;
import org.mapstruct.Mapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        uses = {
                EjercicioMapper.class,
                SerieEjercicioMapper.class
        }
)

public interface EntrenamientoEjercicioMapper {

    EntrenamientoEjercicioDTO toDto(EntrenamientoEjercicio entrenamientoEjercicio);

    @Mapping(target = "entrenamiento", ignore = true)
    @Mapping(target = "ejercicio", ignore = true)
    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    EntrenamientoEjercicio toEntity(CrearEntrenamientoEjercicioDTO dto);

    @Mapping(target = "entrenamiento", ignore = true)
    @Mapping(target = "ejercicio", ignore = true)
    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    void updateEntity(ActualizarEntrenamientoEjercicioDTO dto,
                      @MappingTarget EntrenamientoEjercicio entrenamientoEjercicio);
}
