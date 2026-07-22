package com.gymtrack.backend.service;


import com.gymtrack.backend.dto.PerfilDTO.ActualizarPerfilDTO;
import com.gymtrack.backend.dto.PerfilDTO.CrearPerfilDTO;
import com.gymtrack.backend.dto.PerfilDTO.PerfilDTO;

import java.util.List;

public interface PerfilService {

    List<PerfilDTO> listar();
    PerfilDTO buscarPorId(Long id);
    PerfilDTO crear(CrearPerfilDTO dto);
    PerfilDTO actualizar(Long id, ActualizarPerfilDTO dto);
    void eliminar(Long id);
    PerfilDTO buscarPorIdUsuario(Long id);
}
