package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.EjercicioDTO.ActualizarEjercicioDTO;
import com.gymtrack.backend.dto.EjercicioDTO.CrearEjercicioDTO;
import com.gymtrack.backend.dto.EjercicioDTO.EjercicioDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EjercicioService {

    List<EjercicioDTO> listar();
    EjercicioDTO buscarPorId(Long id);
    EjercicioDTO crear(CrearEjercicioDTO dto, MultipartFile archivo);
    EjercicioDTO actualizar(Long id, ActualizarEjercicioDTO dto);
    EjercicioDTO actualizarVideo(Long id, MultipartFile multipartFile);
    EjercicioDTO eliminarVideo(Long id);
    void eliminar(Long id);
    List<EjercicioDTO> buscarPorGrupoMuscular(String nombre);
}
