package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.PerfilDTO.ActualizarPerfilDTO;
import com.gymtrack.backend.dto.PerfilDTO.CrearPerfilDTO;
import com.gymtrack.backend.dto.PerfilDTO.PerfilDTO;
import com.gymtrack.backend.mapper.PerfilMapper;
import com.gymtrack.backend.repository.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PerfilServiceImp implements PerfilService{

    private final PerfilRepository perfilRepository;
    private final PerfilMapper perfilMapper;

    @Override
    public List<PerfilDTO> listar() {
        return List.of();
    }

    @Override
    public PerfilDTO buscarPorId(Long id) {
        return null;
    }

    @Override
    public PerfilDTO crear(CrearPerfilDTO dto) {
        return null;
    }

    @Override
    public PerfilDTO actualizar(Long id, ActualizarPerfilDTO dto) {
        return null;
    }

    @Override
    public void eliminar(Long id) {

    }
}
