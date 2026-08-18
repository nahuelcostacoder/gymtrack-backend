package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.PublicacionDTO.ActualizarPublicacionDTO;
import com.gymtrack.backend.dto.PublicacionDTO.CrearPublicacionDTO;
import com.gymtrack.backend.dto.PublicacionDTO.PublicacionDTO;
import com.gymtrack.backend.dto.RolDTO.ActualizarRolDTO;

import java.util.List;

public interface PublicacionService {

    List<PublicacionDTO> listarPublicacionesUsuario(Long usuarioId);
    PublicacionDTO buscarPorId(Long publicacionId);
    PublicacionDTO crear(CrearPublicacionDTO dto);
    PublicacionDTO actualizar(Long publicacionId, ActualizarPublicacionDTO dto);
    void eliminar(Long publicacionId);
}
