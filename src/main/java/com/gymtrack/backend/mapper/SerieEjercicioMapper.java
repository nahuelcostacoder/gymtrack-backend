package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.EntrenamientoEjercicioDTO.ActualizarEntrenamientoEjercicioDTO;
import com.gymtrack.backend.dto.EntrenamientoEjercicioDTO.CrearEntrenamientoEjercicioDTO;
import com.gymtrack.backend.dto.SeriesEjercicioDTO.ActualizarSerieEjercicioDTO;
import com.gymtrack.backend.dto.SeriesEjercicioDTO.CrearSerieEjercicioDTO;
import com.gymtrack.backend.dto.SeriesEjercicioDTO.SerieEjercicioDTO;
import com.gymtrack.backend.model.SerieEjercicio;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SerieEjercicioMapper {

    SerieEjercicioDTO toDto(SerieEjercicio serieEjercicio);

    SerieEjercicio toEntity(CrearSerieEjercicioDTO dto);

    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    void updateEntity(ActualizarSerieEjercicioDTO dto,
                      @MappingTarget SerieEjercicio serieEjercicio);
}
