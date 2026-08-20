package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.MeGustaDTO.MeGustaDTO;

import java.util.List;

public interface MeGustaService {

    List<MeGustaDTO> listarPorUsuario(Long usuarioId);
    MeGustaDTO darMeGusta(Long usuarioId, Long publicacionId);
    void eliminarMeGusta(Long usuarioId, Long publicacionId);
    boolean dioMeGusta(Long usuarioId, Long publicacionId);
    long contarPorPublicacion(Long publicacionId);
}
