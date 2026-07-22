package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.PerfilDTO.ActualizarPerfilDTO;
import com.gymtrack.backend.dto.PerfilDTO.CrearPerfilDTO;
import com.gymtrack.backend.dto.PerfilDTO.PerfilDTO;
import com.gymtrack.backend.exception.NotFoundException;
import com.gymtrack.backend.mapper.PerfilMapper;
import com.gymtrack.backend.model.Perfil;
import com.gymtrack.backend.model.Usuario;
import com.gymtrack.backend.repository.PerfilRepository;
import com.gymtrack.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PerfilServiceImp implements PerfilService{

    private final PerfilRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;
    private final PerfilMapper perfilMapper;

    @Override
    public List<PerfilDTO> listar() {

        return perfilRepository.findAll().stream()
                .map(perfilMapper::toDTO).toList();
    }

    @Override
    public PerfilDTO buscarPorId(Long id) {

        Perfil perfil = buscarEntidadPorId(id);

        return perfilMapper.toDTO(perfil);

    }

    @Override
    public PerfilDTO crear(CrearPerfilDTO dto) {

        Perfil perfil = perfilMapper.toEntity(dto);

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un usuario con el ID " + dto.getUsuarioId()));
        //busco el usuario con ese id para despues guardarlo completo con su correspondiente entidad en la bd

        perfil.setUsuario(usuario);

        Perfil perfilGuardado = perfilRepository.save(perfil);

        return perfilMapper.toDTO(perfilGuardado);

    }

    @Override
    public PerfilDTO actualizar(Long id, ActualizarPerfilDTO dto) {

        Perfil perfil = buscarEntidadPorId(id);

        perfilMapper.updateEntity(dto, perfil);

        Perfil perfilActualizado = perfilRepository.save(perfil);

        return perfilMapper.toDTO(perfilActualizado);
    }

    @Override
    public void eliminar(Long id) {

        if (!perfilRepository.existsById(id))
            throw new NotFoundException("No se ha encontrado un perfil con ID " + id);

        perfilRepository.deleteById(id);
    }

    @Override
    public PerfilDTO buscarPorIdUsuario(Long id) {

        Perfil perfil = perfilRepository.findByUsuarioId(id)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un perfil para el usuario con ID " + id));

        return perfilMapper.toDTO(perfil);
    }


    private Perfil buscarEntidadPorId(Long id){

        return perfilRepository.
                findById(id)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un perfil con el ID " + id));
    }
}
