package com.gymtrack.backend.service;


import com.gymtrack.backend.dto.RolDTO.ActualizarRolDTO;
import com.gymtrack.backend.dto.RolDTO.CrearRolDTO;
import com.gymtrack.backend.dto.RolDTO.RolDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface RolService {

    List<RolDTO> listar();
    RolDTO buscarPorId(Long id);
    RolDTO buscarPorNombre(String nombre);
    RolDTO crear(CrearRolDTO dto);
    RolDTO actualizar(Long id, ActualizarRolDTO dto);
    RolDTO agregarPermiso(Long id, Long permisoId);
    RolDTO quitarPermiso(Long id, Long permisoId);
    void eliminar(Long id);

}
