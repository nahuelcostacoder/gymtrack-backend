package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.UsuarioDTO.*;
import com.gymtrack.backend.model.Usuario;

import java.rmi.AlreadyBoundException;
import java.util.List;

public interface UsuarioService {

    List<UsuarioDTO> listar();
    UsuarioDTO buscarPorId(Long id);
    UsuarioDTO crear(CrearUsuarioDTO dto);
    UsuarioDTO actualizar(Long id, ActualizarUsuarioDTO dto);
    void cambiarEmail(Long id, CambiarEmailDTO dto) throws AlreadyBoundException;
    void cambiarPassword(Long id, CambiarPasswordDTO dto);
    void eliminar(Long id);
}
