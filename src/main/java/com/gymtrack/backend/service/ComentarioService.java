package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.ComentarioDTO.ComentarioDTO;
import com.gymtrack.backend.dto.ComentarioDTO.CrearComentarioDTO;
import com.gymtrack.backend.dto.ComentarioDTO.EditarComentarioDTO;
import com.gymtrack.backend.model.Comentario;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface ComentarioService {

    //para historial
    Page<ComentarioDTO> listarPorUsuario(Long usuarioId, Pageable pageable);
    Page<ComentarioDTO> listarPorPublicacion(Long publicacionId, Pageable pageable);
    ComentarioDTO crearComentario(Long usuarioId, Long publicacionId, CrearComentarioDTO dto);
    ComentarioDTO editarComentario(Long comentarioId, Long usuarioId, EditarComentarioDTO dto);
    void eliminarComentario(Long comentarioId, Long usuarioId);
}
