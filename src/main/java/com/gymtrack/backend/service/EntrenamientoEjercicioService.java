package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.EntrenamientoDTO.ActualizarEntrenamientoDTO;
import com.gymtrack.backend.dto.EntrenamientoDTO.CrearEntrenamientoDTO;
import com.gymtrack.backend.dto.EntrenamientoDTO.EntrenamientoDTO;
import com.gymtrack.backend.dto.EntrenamientoEjercicioDTO.ActualizarEntrenamientoEjercicioDTO;
import com.gymtrack.backend.dto.EntrenamientoEjercicioDTO.CrearEntrenamientoEjercicioDTO;
import com.gymtrack.backend.dto.EntrenamientoEjercicioDTO.EntrenamientoEjercicioDTO;
import com.gymtrack.backend.model.EntrenamientoEjercicio;

import java.util.List;

public interface EntrenamientoEjercicioService {
    List<EntrenamientoEjercicioDTO> listarPorEntrenamientoId(Long entrenamientoId);
    EntrenamientoEjercicioDTO buscarPorId(Long entrenamientoId, Long entrenamientoEjercicioId);
    EntrenamientoEjercicioDTO crear(Long entrenamientoId, CrearEntrenamientoEjercicioDTO dto);
    EntrenamientoEjercicioDTO actualizar(Long entrenamientoId, Long entrenamientoEjercicioId, ActualizarEntrenamientoEjercicioDTO dto);
    void eliminar(Long entrenamientoId, Long entrenamientoEjercicioId);
}
