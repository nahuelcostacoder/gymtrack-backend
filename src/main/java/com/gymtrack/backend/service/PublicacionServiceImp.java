package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.PublicacionDTO.ActualizarPublicacionDTO;
import com.gymtrack.backend.dto.PublicacionDTO.CrearPublicacionDTO;
import com.gymtrack.backend.dto.PublicacionDTO.PublicacionDTO;
import com.gymtrack.backend.exception.AccesoDenegadoException;
import com.gymtrack.backend.exception.EstadoInvalidoException;
import com.gymtrack.backend.exception.NotFoundException;
import com.gymtrack.backend.mapper.PublicacionMapper;
import com.gymtrack.backend.model.Entrenamiento;
import com.gymtrack.backend.model.Publicacion;
import com.gymtrack.backend.repository.EntrenamientoRepository;
import com.gymtrack.backend.repository.PublicacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PublicacionServiceImp implements PublicacionService{

    private PublicacionRepository publicacionRepository;
    private EntrenamientoRepository entrenamientoRepository;
    private PublicacionMapper publicacionMapper;

    @Override
    public List<PublicacionDTO> listarPublicacionesUsuario(Long usuarioId) {

        return publicacionRepository.findByEntrenamientoUsuarioId(usuarioId)
                .stream().map(publicacionMapper::toDTO).toList();
    }


    @Override
    public Page<PublicacionDTO> listarFeed(Pageable pageable){

        return publicacionRepository.findAllByOrderByFechaCreacionDesc(pageable).map(publicacionMapper::toDTO);
    }

    @Override
    public Page<PublicacionDTO> listarPorUsuario(Long usuarioId, Pageable pageable){

        return publicacionRepository
                .findByEntrenamientoUsuarioId(usuarioId, pageable)
                .map(publicacionMapper::toDTO);
    }

    @Override
    public PublicacionDTO buscarPorId(Long publicacionId) {

        Publicacion publicacion = buscarEntidadPorId(publicacionId);

        return publicacionMapper.toDTO(publicacion);
    }

    @Override
    public PublicacionDTO crear(Long usuarioId, CrearPublicacionDTO dto) {

        Entrenamiento entrenamiento = buscarEntrenamientoPorId(usuarioId, dto.getEntrenamientoId());

        //ahora debemos revisar que ya no haya una publicacion con ese entrenamiento

        if (publicacionRepository.existsByEntrenamientoId(entrenamiento.getId()))

            throw new EstadoInvalidoException("Ese entrenamiento ya fue publicado");


        Publicacion publicacion = publicacionMapper.toEntity(dto);

        publicacion.setEntrenamiento(entrenamiento);

        return publicacionMapper.toDTO(publicacionRepository.save(publicacion));
    }

    @Override
    public PublicacionDTO actualizar(Long usuarioId,
                                     Long publicacionId,
                                     ActualizarPublicacionDTO dto) {

        Publicacion publicacion = buscarEntidadPorId(publicacionId);

        Long propietario = publicacion.getEntrenamiento()
                .getUsuario()
                .getId();

        //vamos a verificar que el usuario que pide actualizar es dueño de la publicacion
        if (!propietario.equals(usuarioId)){

            throw new AccesoDenegadoException("No podés actualziar una publicación que no te pertenece");
        }

        publicacionMapper.updateEntity(dto, publicacion);

        return publicacionMapper.toDTO(publicacionRepository.save(publicacion));
    }

    @Override
    public void eliminar(Long usuarioId, Long publicacionId) {

        Publicacion publicacion = buscarEntidadPorId(publicacionId);

        Long propietario = publicacion.getEntrenamiento()
                .getUsuario()
                .getId();

        //vamos a verificar que el usuario que pide eliminar es dueño de la publicacion
        if (!propietario.equals(usuarioId)){

            throw new AccesoDenegadoException("No podés eliminar una publicación que no te pertenece");
        }

        publicacionRepository.delete(publicacion);
    }

    private Publicacion buscarEntidadPorId(Long publicacionId){

        return publicacionRepository
                .findById(publicacionId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado una publicacion con id " + publicacionId));
    }

    private Entrenamiento buscarEntrenamientoPorId(Long usuarioId, Long entrenamientoId){

        return entrenamientoRepository
                .findByIdAndUsuarioId(usuarioId, entrenamientoId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado el entrenamiento o no pertenece al usuario"));
    }
}
