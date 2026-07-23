package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.UsuarioDTO.*;
import com.gymtrack.backend.exception.AlreadyExistsException;
import com.gymtrack.backend.exception.NotFoundException;
import com.gymtrack.backend.mapper.UsuarioMapper;
import com.gymtrack.backend.model.Usuario;
import com.gymtrack.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Map;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImp implements UsuarioService{

    private final UsuarioRepository usuarioRepository;
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

    @Override
    public UsuarioDTO actualizar(Long id, ActualizarUsuarioDTO dto) {

        validarNombreUsuarioDisponible(dto.getNombre());
        validarEmailDisponible(dto.getEmail());

        Usuario usuario = buscarEntidadPorId(id);

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
    public void eliminar(Long id) {

        usuarioRepository.delete(buscarEntidadPorId(id));

    }

    private Usuario buscarEntidadPorId(Long id){

        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontro con usuario con ID " + id));
    }


    private void validarNombreUsuarioDisponible(String nombreUsuario){

        if (usuarioRepository.existsByUsername(nombreUsuario))
            throw new AlreadyExistsException("Ya existe un usuario con nombre " + nombreUsuario);
    }

    private void validarEmailDisponible(String email){

        if (usuarioRepository.existsByEmail(email)) {

            throw new AlreadyExistsException("Ya existe un usuario con email " + email);
        }
    }
}

