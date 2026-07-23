package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.GrupoMuscularDTO.ActualizarGrupoMuscularDTO;
import com.gymtrack.backend.dto.GrupoMuscularDTO.CrearGrupoMuscularDTO;
import com.gymtrack.backend.dto.GrupoMuscularDTO.GrupoMuscularDTO;
import com.gymtrack.backend.exception.AlreadyExistsException;
import com.gymtrack.backend.exception.NotFoundException;
import com.gymtrack.backend.mapper.GrupoMuscularMapper;
import com.gymtrack.backend.model.GrupoMuscular;
import com.gymtrack.backend.repository.GrupoMuscularRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GrupoMuscularServiceImp implements GrupoMuscularService{

    private final GrupoMuscularRepository grupoMuscularRepository;
    private final GrupoMuscularMapper grupoMuscularMapper;

    @Override
    public List<GrupoMuscularDTO> listar() {
        return grupoMuscularRepository.findAll().stream().map(grupoMuscularMapper::toDto).toList();
    }

    @Override
    public GrupoMuscularDTO buscarPorId(Long id) {

        GrupoMuscular grupoMuscular = buscarEntidadPorId(id);

        return grupoMuscularMapper.toDto(grupoMuscular);
    }

    @Override
    public GrupoMuscularDTO crear(CrearGrupoMuscularDTO dto) {

        validarNombreDisponible(dto.getNombre());

        //si es un nombre que no esta en la bd

        GrupoMuscular grupoMuscular = grupoMuscularMapper.toEntity(dto);

        GrupoMuscular grupoMuscularActualizado = grupoMuscularRepository.save(grupoMuscular);

        return grupoMuscularMapper.toDto(grupoMuscularActualizado);
    }

    @Override
    public GrupoMuscularDTO actualizar(Long id, ActualizarGrupoMuscularDTO dto) {

        validarNombreDisponible(dto.getNombre());

        GrupoMuscular grupoMuscular = buscarEntidadPorId(id);

        grupoMuscularMapper.updateEntity(dto, grupoMuscular);

        GrupoMuscular grupoMuscularActualizado = grupoMuscularRepository.save(grupoMuscular);

        return grupoMuscularMapper.toDto(grupoMuscularActualizado);
    }

    @Override
    public void eliminar(Long id) {

        if (!grupoMuscularRepository.existsById(id))
            throw new NotFoundException("No existe un grupo muscular con ID " + id);

        grupoMuscularRepository.deleteById(id);

    }

    private GrupoMuscular buscarEntidadPorId(Long id){

        return grupoMuscularRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un grupo muscular con ID " + id));
    }

    private void validarNombreDisponible(String nombre) {
        if (grupoMuscularRepository.existsByNombre(nombre)) {
            throw new AlreadyExistsException(
                    "Ya existe un grupo muscular con nombre " + nombre
            );
        }
    }
}
