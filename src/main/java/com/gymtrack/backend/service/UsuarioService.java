package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.UsuarioDTO.*;
import com.gymtrack.backend.model.Usuario;

import java.rmi.AlreadyBoundException;
import java.util.List;

public interface UsuarioService {

    List<UsuarioDTO> listar();
    UsuarioDTO buscarPorId(Long id);
    UsuarioDTO crear(CrearUsuarioDTO dto);
    UsuarioDTO actualizar(Long id, ActualizarUsuarioAdminDTO dto);
    UsuarioDTO actualizarMiUsuario(Long id, ActualizarUsuarioDTO dto);
    void cambiarEmail(Long id, CambiarEmailDTO dto);
    void cambiarPassword(Long id, CambiarPasswordDTO dto);
    UsuarioDTO agregarRol(Long id, Long rolId);
    UsuarioDTO quitarRol(Long id, Long rolId);
    void eliminar(Long id);

}
