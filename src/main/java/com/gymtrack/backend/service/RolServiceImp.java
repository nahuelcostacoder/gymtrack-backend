package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.RolDTO.ActualizarRolDTO;
import com.gymtrack.backend.dto.RolDTO.CrearRolDTO;
import com.gymtrack.backend.dto.RolDTO.RolDTO;
import com.gymtrack.backend.exception.EstadoInvalidoException;
import com.gymtrack.backend.exception.NotFoundException;
import com.gymtrack.backend.mapper.RolMapper;
import com.gymtrack.backend.model.Permiso;
import com.gymtrack.backend.model.Rol;
import com.gymtrack.backend.repository.PermisoRepository;
import com.gymtrack.backend.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RolServiceImp implements RolService{

    private final RolRepository rolRepository;
    private final PermisoRepository permisoRepository;
    private final RolMapper rolMapper;

    @Override
    public List<RolDTO> listar() {
        return rolRepository
                .findAll()
                .stream()
                .map(rolMapper::toDTO)
                .toList();
    }

    @Override
    public RolDTO buscarPorId(Long id) {

        Rol rol = buscarEntidadPorId(id);

        return rolMapper.toDTO(rol);
    }

    @Override
    public RolDTO buscarPorNombre(String nombre){

        Rol rol = rolRepository
                .findByNombre(nombre)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un rol con nombre: " + nombre));

        return rolMapper.toDTO(rol);
    }

    @Override
    public RolDTO crear(CrearRolDTO dto) {

        Rol rol = rolMapper.toEntity(dto);

        Set<Permiso> permisos = new HashSet<>(permisoRepository.findAllById(dto.getPermisosIds()));

        rol.setPermisos(permisos);


        return rolMapper.toDTO(rolRepository.save(rol));
    }

    @Override //actualizar solamente el nombre
    public RolDTO actualizar(Long id, ActualizarRolDTO dto) {

        Rol rol = buscarEntidadPorId(id);

        rolMapper.updateEntity(dto, rol);

        return rolMapper.toDTO(rolRepository.save(rol));
    }

    @Override
    public RolDTO agregarPermiso(Long id, Long permisoId){

        Rol rol = buscarEntidadPorId(id);

        Permiso permiso = buscarPermisoPorId(permisoId);

        if (rol.getPermisos().contains(permiso)) {
            throw new EstadoInvalidoException(
                    "El rol ya tiene asignado ese permiso"
            );
        }

        rol.getPermisos().add(permiso);

        return rolMapper.toDTO(rolRepository.save(rol));
    }

    @Override
    public RolDTO quitarPermiso(Long id, Long permisoId){

        Rol rol = buscarEntidadPorId(id);

        Permiso permiso = buscarPermisoPorId(permisoId);

        if (!rol.getPermisos().contains(permiso)){

            throw new EstadoInvalidoException("El rol no tiene asignado ese permiso");
        }

        rol.getPermisos().remove(permiso);

        return rolMapper.toDTO(rolRepository.save(rol));
    }

    @Override
    public void eliminar(Long id) {

        if (!rolRepository.existsById(id))

            throw new NotFoundException("No se ha encontrado un rol con id " + id);

        rolRepository.deleteById(id);

    }

    private Rol buscarEntidadPorId(Long rolId){

        return rolRepository
                .findById(rolId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un rol con id" + rolId));
    }

    private Permiso buscarPermisoPorId(Long permisoId){

        return permisoRepository
                .findById(permisoId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un permiso con id" + permisoId));
    }
}
