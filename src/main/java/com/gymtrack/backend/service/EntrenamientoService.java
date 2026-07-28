package com.gymtrack.backend.service;


import com.gymtrack.backend.dto.EntrenamientoDTO.ActualizarEntrenamientoDTO;
import com.gymtrack.backend.dto.EntrenamientoDTO.CrearEntrenamientoDTO;
import com.gymtrack.backend.dto.EntrenamientoDTO.EntrenamientoDTO;
import com.gymtrack.backend.dto.RutinaEjercicioDTO.ActualizarRutinaEjercicioDTO;
import com.gymtrack.backend.model.Entrenamiento;

import java.util.List;

public interface EntrenamientoService {

    List<EntrenamientoDTO> listarPorUsuarioId(Long usuarioId);
    EntrenamientoDTO buscarPorUsuarioId(Long entrenamientoId, Long usuarioId);
    EntrenamientoDTO crear(Long usuarioId, CrearEntrenamientoDTO dto);
    EntrenamientoDTO actualizar(Long entrenamientoId,
                                Long usuarioId,
                                ActualizarEntrenamientoDTO dto);
    void eliminar(Long entrenamientoId, Long usuarioId);
    EntrenamientoDTO finalizar(Long entrenamientoId, Long usuarioId);

}
