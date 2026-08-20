package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.PublicacionDTO.ActualizarPublicacionDTO;
import com.gymtrack.backend.dto.PublicacionDTO.CrearPublicacionDTO;
import com.gymtrack.backend.dto.PublicacionDTO.PublicacionDTO;
import com.gymtrack.backend.dto.RolDTO.ActualizarRolDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PublicacionService {

    Page<PublicacionDTO> listarFeed(Pageable pageable);
    Page<PublicacionDTO > listarPorUsuario(Long usuarioId, Pageable pageable);
    PublicacionDTO buscarPorId(Long publicacionId);
    PublicacionDTO crear(Long entrenamientoId, CrearPublicacionDTO dto);
    PublicacionDTO actualizar(Long usuarioId,
                              Long publicacionId,
                              ActualizarPublicacionDTO dto);
    void eliminar(Long usuarioId, Long publicacionId);
}
