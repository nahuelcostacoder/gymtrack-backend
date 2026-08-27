package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.PublicacionDTO.ActualizarPublicacionDTO;
import com.gymtrack.backend.dto.PublicacionDTO.CrearPublicacionDTO;
import com.gymtrack.backend.dto.PublicacionDTO.PublicacionDTO;
import com.gymtrack.backend.dto.RolDTO.ActualizarRolDTO;
import com.gymtrack.backend.model.MediaPublicacion;
import com.gymtrack.backend.model.Publicacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PublicacionService {

    Page<PublicacionDTO> listarFeed(Long usuarioId, Pageable pageable);
    Page<PublicacionDTO > listarPorUsuario(Long usuarioId, Long usuarioAutenticadoId, Pageable pageable);
    PublicacionDTO buscarPorId(Long usuarioId, Long publicacionId);
    PublicacionDTO crear(Long entrenamientoId, CrearPublicacionDTO dto, List<MultipartFile> archivos);
    PublicacionDTO actualizar(Long usuarioId,
                              Long publicacionId,
                              ActualizarPublicacionDTO dto);
    PublicacionDTO agregarMedia(Long usuarioId,Long publicacionId, List<MultipartFile> archivos);
    PublicacionDTO eliminarMedia(Long usuarioId, Long publicacionId, Long mediaId);
    void eliminar(Long usuarioId, Long publicacionId);
}
