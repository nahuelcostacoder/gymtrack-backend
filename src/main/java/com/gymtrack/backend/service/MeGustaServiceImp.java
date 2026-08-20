package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.MeGustaDTO.MeGustaDTO;
import com.gymtrack.backend.repository.MeGustaRepository;
import com.gymtrack.backend.repository.PublicacionRepository;
import com.gymtrack.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MeGustaServiceImp implements MeGustaService{

    private MeGustaRepository meGustaRepository;
    private UsuarioRepository usuarioRepository;
    private PublicacionRepository publicacionRepository;


    @Override
    public List<MeGustaDTO> listarPorUsuario(Long usuarioId) {
        return List.of();
    }

    @Override
    public MeGustaDTO darMeGusta(Long usuarioId, Long publicacionId) {
        return null;
    }

    @Override
    public void eliminarMeGusta(Long usuarioId, Long publicacionId) {

    }

    @Override
    public boolean dioMeGusta(Long usuarioId, Long publicacionId) {
        return false;
    }

    @Override
    public long contarPorPublicacion(Long publicacionId) {
        return 0;
    }
}
