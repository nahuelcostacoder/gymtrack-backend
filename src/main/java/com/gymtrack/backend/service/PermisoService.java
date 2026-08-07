package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.PermisoDTO.ActualizarPermisoDTO;
import com.gymtrack.backend.dto.PermisoDTO.CrearPermisoDTO;
import com.gymtrack.backend.dto.PermisoDTO.PermisoDTO;

import java.util.List;

public interface PermisoService {

    List<PermisoDTO> listar();
    PermisoDTO buscarPorId(Long id);
    PermisoDTO buscarPorNombre(String nombre);
    PermisoDTO crear(CrearPermisoDTO dto);
    PermisoDTO actualizar(Long id, ActualizarPermisoDTO dto);
    void eliminar(Long id);
}
