package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.EjercicioDTO.ActualizarEjercicioDTO;
import com.gymtrack.backend.dto.EjercicioDTO.CrearEjercicioDTO;
import com.gymtrack.backend.dto.EjercicioDTO.EjercicioDTO;
import com.gymtrack.backend.model.Ejercicio;
import com.gymtrack.backend.model.GrupoMuscular;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EjercicioMapper {

    //hago que sepa que use el metodo default de abajo
    @Mapping(source = "gruposMusculares", target = "gruposMusculares")
    EjercicioDTO toDto(Ejercicio ejercicio);

    @Mapping(target = "gruposMusculares", ignore = true)
    Ejercicio toEntity(CrearEjercicioDTO dto);

    @Mapping(target = "gruposMusculares", ignore = true)
    void updateEntity(ActualizarEjercicioDTO dto, @MappingTarget Ejercicio ejercicio);

    //los recibe de la entidad que son varios por eso un set
    default Set<String> mapGruposMusculares(Set<GrupoMuscular> grupos){

        return grupos.stream().map(GrupoMuscular::getNombre).collect(Collectors.toSet());
    }
}
