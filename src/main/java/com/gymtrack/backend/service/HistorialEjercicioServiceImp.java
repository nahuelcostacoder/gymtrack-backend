package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.EjercicioDTO.HistorialEjercicioDTO;
import com.gymtrack.backend.mapper.EntrenamientoEjercicioMapper;
import com.gymtrack.backend.mapper.HistorialEjercicioMapper;
import com.gymtrack.backend.model.EntrenamientoEjercicio;
import com.gymtrack.backend.repository.EntrenamientoEjercicioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HistorialEjercicioServiceImp implements HistorialEjercicioService{

    private final EntrenamientoEjercicioRepository entrenamientoEjercicioRepository;
    private final HistorialEjercicioMapper historialEjercicioMapper;


    @Override
    public List<HistorialEjercicioDTO> obtenerHistorial(Long usuarioId, Long ejercicioId) {
        return entrenamientoEjercicioRepository.findByEntrenamientoUsuarioIdAndEjercicioIdOrderByEntrenamientoFechaInicioDesc(usuarioId, ejercicioId)
                .stream().map(historialEjercicioMapper::toDto).toList();
    }
}
