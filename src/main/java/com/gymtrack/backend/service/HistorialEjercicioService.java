package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.EjercicioDTO.HistorialEjercicioDTO;

import java.util.List;

public interface HistorialEjercicioService {

    List<HistorialEjercicioDTO> obtenerHistorial(Long usuarioId, Long ejercicioId);
}
