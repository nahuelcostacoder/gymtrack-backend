package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.AmistadDTO.AmistadDTO;
import com.gymtrack.backend.dto.AmistadDTO.EnviarSolicitudDTO;
import com.gymtrack.backend.model.Amistad;
import com.gymtrack.backend.model.Usuario;

import java.util.List;

public interface AmistadService {

    List<AmistadDTO> listarAmigos(Long usuarioId); //listar todas las amistades de un usuario
    List<AmistadDTO> listarSolicitudesRecibidas(Long usuarioId);
    List<AmistadDTO> listarSolicitudesEnviadas(Long usuarioId);
    AmistadDTO enviarAmistad(Long emisorId, EnviarSolicitudDTO dto);
    AmistadDTO aceptarSolicitud(Long usuarioId, Long amistadId);
    void rechazarSolicitud(Long usuarioId, Long amistadId);
    void eliminarAmistad(Long usuarioId, Long amistadId);
    void cancelarSolicitud(Long usuarioId, Long amistadId);

}
