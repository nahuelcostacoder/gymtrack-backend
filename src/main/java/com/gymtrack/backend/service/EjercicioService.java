package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.EjercicioDTO.ActualizarEjercicioDTO;
import com.gymtrack.backend.dto.EjercicioDTO.CrearEjercicioDTO;
import com.gymtrack.backend.dto.EjercicioDTO.EjercicioDTO;

import java.util.List;

public interface EjercicioService {

    List<EjercicioDTO> listar();
    EjercicioDTO buscarPorId(Long id);
    EjercicioDTO crear(CrearEjercicioDTO dto);
    EjercicioDTO actualizar(Long id, ActualizarEjercicioDTO dto);
    void eliminar(Long id);
    List<EjercicioDTO> buscarPorGrupoMuscular(String nombre);
}
