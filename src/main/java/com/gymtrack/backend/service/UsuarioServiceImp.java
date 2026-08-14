package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.UsuarioDTO.*;
import com.gymtrack.backend.exception.AlreadyExistsException;
import com.gymtrack.backend.exception.EstadoInvalidoException;
import com.gymtrack.backend.exception.NotFoundException;
import com.gymtrack.backend.mapper.UsuarioMapper;
import com.gymtrack.backend.model.Rol;
import com.gymtrack.backend.model.Usuario;
import com.gymtrack.backend.repository.RolRepository;
import com.gymtrack.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImp implements UsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final UsuarioMapper usuarioMapper;


    @Override
    public List<UsuarioDTO> listar() {

        return usuarioRepository.findAll()
                .stream().map(usuarioMapper::toDto).toList();
    }

    @Override
    public UsuarioDTO buscarPorId(Long id) {

        Usuario usuario = buscarEntidadPorId(id);

        return usuarioMapper.toDto(usuario);
    }

    @Override
    public UsuarioDTO crear(CrearUsuarioDTO dto) {

        validarNombreUsuarioDisponible(dto.getUsername());
        validarNombreUsuarioDisponible(dto.getEmail());

        Usuario usuario = usuarioMapper.toEntity(dto);

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return usuarioMapper.toDto(usuarioGuardado);

    }

    @PreAuthorize("hasAuthority()")
    @Override
    public UsuarioDTO actualizar(Long id, ActualizarUsuarioAdminDTO dto){

        Usuario usuario = buscarEntidadPorId(id);

        if (dto.getUsername() != null){

            validarNombreUsuarioDisponibleActualizar(dto.getUsername(), usuario);
        }

        usuarioMapper.updateEntityAdmin(dto, usuario);

        Usuario usuarioActualizado = usuarioRepository.save(usuario);

        return usuarioMapper.toDto(usuarioActualizado);
    }

    @Override
    public UsuarioDTO actualizarMiUsuario(Long id, ActualizarUsuarioDTO dto) {

        Usuario usuario = buscarEntidadPorId(id);

        if (dto.getUsername() != null){

            validarNombreUsuarioDisponibleActualizar(dto.getUsername(), usuario);
        }

        usuarioMapper.updateEntity(dto, usuario);

        Usuario usuarioActualizado = usuarioRepository.save(usuario);

        return usuarioMapper.toDto(usuarioActualizado);
    }

    @Override
    public void cambiarEmail(Long id, CambiarEmailDTO dto)  {


        Usuario usuario = buscarEntidadPorId(id);

        if (!usuario.getEmail().equals(dto.getEmail())
                && usuarioRepository.existsByEmail(dto.getEmail())) {

            throw new AlreadyExistsException("Ya existe un usuario con email " + dto.getEmail());
        }

        usuario.setEmail(dto.getEmail());

        usuarioRepository.save(usuario);
    }

    @Override
    public void cambiarPassword(Long id, CambiarPasswordDTO dto) {

        Usuario usuario = buscarEntidadPorId(id);

        if (usuario.getPassword().equals(dto.getPassword())){

            throw new AlreadyExistsException("La contraseña es la misma que la que ya tiene");
        }

        usuario.setPassword(dto.getPassword());

        usuarioRepository.save(usuario);

    }

    @Override
    public UsuarioDTO agregarRol(Long id, Long rolId){

        Usuario usuario = buscarEntidadPorId(id);

        Rol rol = buscarEntidadRolPorId(rolId);

        boolean yaAsignado = usuario.getRoles()
                .stream()
                .anyMatch(r -> r.getId().equals(rolId));

        //anymatch busca algun rol que cumpla esa condicion

        if (yaAsignado){

            throw new AlreadyExistsException("El usuario ya tiene asignado ese rol");
        }

        usuario.getRoles().add(rol);

        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDTO quitarRol(Long id, Long rolId){

        Usuario usuario = buscarEntidadPorId(id);

        Rol rol = buscarEntidadRolPorId(rolId);


        //remove if ademas de eliminar si encuentra, devuelve un booleano confirmandolo
        boolean eliminado = usuario.getRoles()
                .removeIf(r -> r.getId().equals(rolId));


        if (!eliminado)

            throw new NotFoundException("No se encuentro un rol con ese nombre para ese usuario");

        return usuarioMapper.toDto(usuarioRepository.save(usuario));

    }

    @Override
    public void eliminar(Long id) {

        usuarioRepository.delete(buscarEntidadPorId(id));

    }


    private Usuario buscarEntidadPorId(Long id){

        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontro con usuario con ID " + id));
    }


    private void validarNombreUsuarioDisponible(String nombreUsuario){

        if (usuarioRepository.existsByUsername(nombreUsuario)) {

            throw new AlreadyExistsException("Ya existe un usuario con nombre " + nombreUsuario);
        }
    }

    private void validarNombreUsuarioDisponibleActualizar(String nuevoNombre, Usuario usuario) {

        if (usuario.getNombre().equalsIgnoreCase(nuevoNombre)) {
            return;
        }

        if (usuarioRepository.existsByUsername(nuevoNombre));
    }

    private Rol buscarEntidadRolPorId(Long rolId){

        return rolRepository.findById(rolId).orElseThrow(() ->
                new NotFoundException("No se ha encontrado un rol con ese id" + rolId));

    }

}

