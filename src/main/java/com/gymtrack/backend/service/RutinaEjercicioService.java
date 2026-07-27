package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.RutinaDTO.CrearRutinaDTO;
import com.gymtrack.backend.dto.RutinaEjercicioDTO.ActualizarRutinaEjercicioDTO;
import com.gymtrack.backend.dto.RutinaEjercicioDTO.CrearRutinaEjercicioDTO;
import com.gymtrack.backend.dto.RutinaEjercicioDTO.RutinaEjercicioDTO;

import java.util.List;

public interface RutinaEjercicioService {

    List<RutinaEjercicioDTO> listarPorRutina(Long rutinaId);
    RutinaEjercicioDTO buscarPorId(Long rutinaId, Long rutinaEjercicioId);
    RutinaEjercicioDTO crear(Long rutinaId, CrearRutinaEjercicioDTO dto);
    RutinaEjercicioDTO actualizar(Long idRutina, Long idRutinaEjercicio, ActualizarRutinaEjercicioDTO dto);
    void eliminar(Long rutinaId, Long rutinaEjercicioId);
}
