package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.UsuarioDTO.*;
import com.gymtrack.backend.mapper.UsuarioMapper;
import com.gymtrack.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImp implements UsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;


    @Override
    public List<UsuarioDTO> listar() {
        return List.of();
    }

    @Override
    public UsuarioDTO buscarPorId(Long id) {
        return null;
    }

    @Override
    public UsuarioDTO crear(CrearUsuarioDTO dto) {
        return null;
    }

    @Override
    public UsuarioDTO actualizar(Long id, ActualizarUsuarioDTO dto) {
        return null;
    }

    @Override
    public void cambiarEmail(Long id, CambiarEmailDTO dto) {

    }

    @Override
    public void cambiarPassword(Long id, CambiarPasswordDTO dto) {

    }

    @Override
    public void eliminar(Long id) {

    }
}
