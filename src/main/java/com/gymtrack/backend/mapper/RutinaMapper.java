package com.gymtrack.backend.mapper;


import com.gymtrack.backend.dto.RutinaDTO.ActualizarRutinaDTO;
import com.gymtrack.backend.dto.RutinaDTO.CrearRutinaDTO;
import com.gymtrack.backend.dto.RutinaDTO.RutinaDTO;
import com.gymtrack.backend.model.Rutina;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",
        uses = EjercicioMapper.class
)
public interface RutinaMapper {

    @Mapping(source = "usuario.id", target = "usuarioId")
    RutinaDTO toDTO(Rutina rutina);

    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "ejercicios", ignore = true)
    Rutina toEntity(CrearRutinaDTO dto);

    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "ejercicios", ignore = true)
    void updateEntity(ActualizarRutinaDTO dto, @MappingTarget Rutina rutina);
}
