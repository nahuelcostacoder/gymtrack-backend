package com.gymtrack.backend.service;


import com.gymtrack.backend.dto.PerfilDTO.ActualizarPerfilDTO;
import com.gymtrack.backend.dto.PerfilDTO.CrearPerfilDTO;
import com.gymtrack.backend.dto.PerfilDTO.PerfilDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PerfilService {

    List<PerfilDTO> listar();
    PerfilDTO buscarPorId(Long id);
    PerfilDTO crear(Long usuarioId, CrearPerfilDTO dto, MultipartFile archivo);
    PerfilDTO actualizar(Long usuarioId, Long id, ActualizarPerfilDTO dto);
    PerfilDTO actualizarFotoPerfil(Long usuarioId, MultipartFile archivo);
    PerfilDTO eliminarFotoPerfil(Long usuarioId);
    PerfilDTO buscarPorIdUsuario(Long id);
}
