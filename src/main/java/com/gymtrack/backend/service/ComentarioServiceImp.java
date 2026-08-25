package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.ComentarioDTO.ComentarioDTO;
import com.gymtrack.backend.dto.ComentarioDTO.CrearComentarioDTO;
import com.gymtrack.backend.dto.ComentarioDTO.EditarComentarioDTO;
import com.gymtrack.backend.exception.AccesoDenegadoException;
import com.gymtrack.backend.exception.NotFoundException;
import com.gymtrack.backend.mapper.ComentarioMapper;
import com.gymtrack.backend.model.Comentario;
import com.gymtrack.backend.model.Publicacion;
import com.gymtrack.backend.model.Usuario;
import com.gymtrack.backend.repository.ComentarioRepository;
import com.gymtrack.backend.repository.PublicacionRepository;
import com.gymtrack.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



@RequiredArgsConstructor
@Service
public class ComentarioServiceImp implements ComentarioService{

    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final PublicacionRepository publicacionRepository;
    private final ComentarioMapper comentarioMapper;

    @Override
    public Page<ComentarioDTO> listarPorUsuario(Long usuarioId, Pageable pageable) {

        return comentarioRepository
                .findByUsuarioId(usuarioId, pageable)
                .map(comentarioMapper::toDTO);
    }

    @Override
    public Page<ComentarioDTO> listarPorPublicacion(Long publicacionId, Pageable pageable) {


        return comentarioRepository
                .findByPublicacionId(publicacionId, pageable)
                .map(comentarioMapper::toDTO);
    }


    @Override
    public ComentarioDTO crear(Long usuarioId, Long publicacionId, CrearComentarioDTO dto) {

        Usuario usuario = buscarEntidadUsuarioPorId(usuarioId);

        Publicacion publicacion = buscarEntidadPublicacionPorId(publicacionId);


        Comentario comentario = comentarioMapper.toEntity(dto);

        comentario.setUsuario(usuario);
        comentario.setPublicacion(publicacion);

        return comentarioMapper.toDTO(comentarioRepository.save(comentario));
    }

    @Override
    public ComentarioDTO editar(Long comentarioId, Long usuarioId, EditarComentarioDTO dto) {

        Comentario comentario = buscarEntidadComentarioPorId(comentarioId);

        if (!comentario.getUsuario().getId().equals(usuarioId))

            throw new AccesoDenegadoException("No podés editar un comentario que no te pertenece");

        comentarioMapper.updateEntity(dto, comentario);

        return comentarioMapper.toDTO(comentarioRepository.save(comentario));
    }

    @Override
    public void eliminar(Long comentarioId, Long usuarioId) {

        Comentario comentario = buscarEntidadComentarioPorId(comentarioId);

        if (!comentario.getUsuario().getId().equals(usuarioId))

            throw new AccesoDenegadoException("No podés eliminar un comentario que no te pertenece");

        comentarioRepository.delete(comentario);

    }

    private Comentario buscarEntidadComentarioPorId(Long comentarioId){

        return comentarioRepository
                .findById(comentarioId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un comentario con id " + comentarioId));
    }

    private Usuario buscarEntidadUsuarioPorId(Long usuarioId){

        return usuarioRepository
                .findById(usuarioId)
                .orElseThrow(() -> new NotFoundException( "No se ha encontrado un usuario con id " + usuarioId));
    }


    private Publicacion buscarEntidadPublicacionPorId(Long publicacionId){

        return publicacionRepository
                .findById(publicacionId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado una publicacion con id " + publicacionId));
    }
}
