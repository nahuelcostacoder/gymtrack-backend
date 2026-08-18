package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.PublicacionDTO.ActualizarPublicacionDTO;
import com.gymtrack.backend.dto.PublicacionDTO.CrearPublicacionDTO;
import com.gymtrack.backend.dto.PublicacionDTO.PublicacionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PublicacionServiceImp implements PublicacionService{


    @Override
    public List<PublicacionDTO> listarPublicacionesUsuario(Long usuarioId) {
        return List.of();
    }

    @Override
    public PublicacionDTO buscarPorId(Long publicacionId) {
        return null;
    }

    @Override
    public PublicacionDTO crear(CrearPublicacionDTO dto) {
        return null;
    }

    @Override
    public PublicacionDTO actualizar(Long publicacionId, ActualizarPublicacionDTO dto) {
        return null;
    }

    @Override
    public void eliminar(Long publicacionId) {

    }
}
