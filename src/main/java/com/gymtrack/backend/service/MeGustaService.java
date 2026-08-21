package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.MeGustaDTO.MeGustaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MeGustaService {

    List<MeGustaDTO> listarPorUsuario(Long usuarioId);
    MeGustaDTO darMeGusta(Long usuarioId, Long publicacionId);
    void eliminarMeGusta(Long usuarioId, Long publicacionId);
    boolean dioMeGusta(Long usuarioId, Long publicacionId);
    long contarPorPublicacion(Long publicacionId);
    Page<MeGustaDTO> listarPorPublicacion(Long publicacionId,
                                          Pageable pageable);
}
