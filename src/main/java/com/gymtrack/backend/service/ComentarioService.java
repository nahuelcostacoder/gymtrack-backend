package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.ComentarioDTO.ComentarioDTO;
import com.gymtrack.backend.dto.ComentarioDTO.CrearComentarioDTO;
import com.gymtrack.backend.dto.ComentarioDTO.EditarComentarioDTO;
import com.gymtrack.backend.model.Comentario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ComentarioService {

    //para historial
    Page<ComentarioDTO> listarPorUsuario(Long usuarioId, Pageable pageable);
    Page<ComentarioDTO> listarPorPublicacion(Long publicacionId, Pageable pageable);
    ComentarioDTO crear(Long usuarioId, Long publicacionId, CrearComentarioDTO dto);
    ComentarioDTO editar(Long comentarioId, Long usuarioId, EditarComentarioDTO dto);
    void eliminar(Long comentarioId, Long usuarioId);
}
