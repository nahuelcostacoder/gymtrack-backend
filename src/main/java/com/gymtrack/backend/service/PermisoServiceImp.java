package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.PermisoDTO.ActualizarPermisoDTO;
import com.gymtrack.backend.dto.PermisoDTO.CrearPermisoDTO;
import com.gymtrack.backend.dto.PermisoDTO.PermisoDTO;
import com.gymtrack.backend.dto.UsuarioDTO.CrearUsuarioDTO;
import com.gymtrack.backend.exception.NotFoundException;
import com.gymtrack.backend.mapper.PermisoMapper;
import com.gymtrack.backend.model.Permiso;
import com.gymtrack.backend.repository.PermisoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermisoServiceImp implements PermisoService {

    private final PermisoRepository permisoRepository;
    private final PermisoMapper permisoMapper;

    @Override
    public List<PermisoDTO> listar() {
        return permisoRepository.findAll()
                .stream()
                .map(permisoMapper::toDTO).toList();
    }

    @Override
    public PermisoDTO buscarPorId(Long id) {

        Permiso permiso = buscarEntidadPorId(id);

        return permisoMapper.toDTO(permiso);
    }

    @Override
    public PermisoDTO buscarPorNombre(String nombre){

        return permisoMapper.toDTO(permisoRepository
                .findByNombre(nombre)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un permiso con nombre " + nombre)));
    }

    @Override
    public PermisoDTO crear(CrearPermisoDTO dto) {

        Permiso permiso = permisoMapper.toEntity(dto);

        return permisoMapper.toDTO(permisoRepository.save(permiso));
    }

    @Override
    public PermisoDTO actualizar(Long id, ActualizarPermisoDTO dto) {

        Permiso permiso = buscarEntidadPorId(id);

        permisoMapper.updateEntity(dto, permiso);

        return permisoMapper.toDTO(permisoRepository.save(permiso));
    }

    @Override
    public void eliminar(Long id) {

        if (!permisoRepository.existsById(id))

            throw new NotFoundException("No existe un permiso con id " + id);

        permisoRepository.deleteById(id);
    }

    private Permiso buscarEntidadPorId(Long permisoId){

        return permisoRepository
                .findById(permisoId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un permiso con id " + permisoId));
    }
}
