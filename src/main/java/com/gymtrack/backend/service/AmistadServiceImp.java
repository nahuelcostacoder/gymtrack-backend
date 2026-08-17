package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.AmistadDTO.AmistadDTO;
import com.gymtrack.backend.dto.AmistadDTO.EnviarSolicitudDTO;
import com.gymtrack.backend.exception.AccesoDenegadoException;
import com.gymtrack.backend.exception.EstadoInvalidoException;
import com.gymtrack.backend.exception.NotFoundException;
import com.gymtrack.backend.mapper.AmistadMapper;
import com.gymtrack.backend.model.Amistad;
import com.gymtrack.backend.model.EstadoAmistad;
import com.gymtrack.backend.model.Usuario;
import com.gymtrack.backend.repository.AmistadRepository;
import com.gymtrack.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AmistadServiceImp implements AmistadService{

    private final AmistadRepository amistadRepository;
    private final AmistadMapper amistadMapper;
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<AmistadDTO> listarAmigos(Long usuarioId) {
        return amistadRepository.listarAmigos(usuarioId)
                .stream().map(amistadMapper::toDto).toList();
    }

    @Override
    public List<AmistadDTO> listarSolicitudesRecibidas(Long usuarioId) {
        return amistadRepository.listarSolicitudesRecibidas(usuarioId)
                .stream().map(amistadMapper::toDto).toList();
    }

    @Override
    public List<AmistadDTO> listarSolicitudesEnviadas(Long usuarioId) {
        return amistadRepository.listarSolicitudesEnviadas(usuarioId)
                .stream().map(amistadMapper::toDto).toList();
    }

    @Override
    public AmistadDTO enviarAmistad(Long emisorId, EnviarSolicitudDTO dto) {

        Usuario emisor = buscarPorUsuarioId(emisorId);
        Usuario receptor = buscarPorUsuarioId(dto.getReceptorId());

        //ahora como los demas datos el mapper no los puede pasar, lo hacemos manual con patron builder

        Amistad amistad = Amistad.builder()
                .emisorSolicitud(emisor)
                .receptorSolicitud(receptor)
                .estado(EstadoAmistad.PENDIENTE)
                .fechaSolicitud(LocalDateTime.now())
                .build();

        return amistadMapper.toDto(amistadRepository.save(amistad));
    }

    @Override
    public AmistadDTO aceptarSolicitud(Long usuarioId, Long amistadId) {

        Amistad amistad = buscarEntidadPorId(amistadId);

        if (!amistad.getReceptorSolicitud().getId().equals(usuarioId))

            throw new AccesoDenegadoException("Ese usuario no es el receptor de la solicitud");

        amistad.setEstado(EstadoAmistad.ACEPTADA);

        return amistadMapper.toDto(amistadRepository.save(amistad));
    }

    @Override
    public void rechazarSolicitud(Long usuarioId, Long amistadId) {

        Amistad amistad = buscarEntidadPorId(amistadId);

        if (!amistad.getReceptorSolicitud().getId().equals(usuarioId))

            throw new AccesoDenegadoException("No podés rechazar una solicitud de amistad que no fue enviada a tu usuario");

        if (amistad.getEstado() != EstadoAmistad.PENDIENTE)

            throw new EstadoInvalidoException("La solicitud no está pendiente");


        amistadRepository.delete(amistad); //no voy a usar un estado de rechaza, directamente la elimino
        //esto es porque es innecesario salvo que quiera tener un registro de rechazados.

    }

    @Override
    public void eliminarAmistad(Long usuarioId, Long amistadId) {

        Amistad amistad = buscarEntidadPorId(amistadId);

        //aca como puede ser tanto emisor como receptor quien la elimine, debemos verificar ambos
        boolean esEmisor = amistad.getEmisorSolicitud().getId().equals(usuarioId);

        boolean esReceptor = amistad.getReceptorSolicitud().getId().equals(usuarioId);

        if (!esEmisor && !esReceptor){

            throw new AccesoDenegadoException("No podes eliminar una amistad de la que no formás parte");
        }


        if (amistad.getEstado() != EstadoAmistad.ACEPTADA)

            throw new EstadoInvalidoException("La amistad no se encuentra aceptada");

        amistadRepository.delete(amistad);
    }

    @Override
    public void cancelarSolicitud(Long usuarioId, Long amistadId) {

        Amistad amistad = buscarEntidadPorId(amistadId);

        if (!amistad.getEmisorSolicitud().getId().equals(usuarioId)){

            throw new AccesoDenegadoException("No podés cancelar una solicitud de amistad que no enviaste");
        }

        if (amistad.getEstado() != EstadoAmistad.PENDIENTE)

            throw new EstadoInvalidoException("La amistad no se encuentra aceptada");

        amistadRepository.delete(amistad);
    }


    private Usuario buscarPorUsuarioId(Long usuarioId){

        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un usuario con id " + usuarioId));
    }

    private Amistad buscarEntidadPorId(Long amistadId){

       return amistadRepository.findById(amistadId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado una amistad con id " + amistadId));
    }
}
