package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.RutinaDTO.ActualizarRutinaDTO;
import com.gymtrack.backend.dto.RutinaDTO.CrearRutinaDTO;
import com.gymtrack.backend.dto.RutinaDTO.RutinaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RutinaService {

    List<RutinaDTO> listar();
    RutinaDTO buscarPorId(Long id);
    RutinaDTO crear(CrearRutinaDTO dto);
    RutinaDTO actualizar(Long id, ActualizarRutinaDTO dto);
    void eliminar(Long id);

}
