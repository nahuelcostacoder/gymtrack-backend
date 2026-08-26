package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.MeGustaDTO.MeGustaDTO;
import com.gymtrack.backend.exception.EstadoInvalidoException;
import com.gymtrack.backend.exception.NotFoundException;
import com.gymtrack.backend.mapper.MeGustaMapper;
import com.gymtrack.backend.model.MeGusta;
import com.gymtrack.backend.model.Publicacion;
import com.gymtrack.backend.model.Usuario;
import com.gymtrack.backend.repository.MeGustaRepository;
import com.gymtrack.backend.repository.PublicacionRepository;
import com.gymtrack.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MeGustaServiceImp implements MeGustaService{

    private final MeGustaRepository meGustaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PublicacionRepository publicacionRepository;
    private final MeGustaMapper meGustaMapper;


    @Override
    public List<MeGustaDTO> listarPorUsuario(Long usuarioId) {

        return meGustaRepository
                .findByUsuarioId(usuarioId)
                .stream()
                .map(meGustaMapper::toDTO)
                .toList();
    }

    @Override
    public MeGustaDTO darMeGusta(Long usuarioId, Long publicacionId) {

        Usuario usuario = buscarEntidadUsuarioPorId(usuarioId);

        Publicacion publicacion = buscarEntidadPublicacionPorId(publicacionId);

        if (meGustaRepository.existsByUsuarioIdAndPublicacionId(usuarioId, publicacionId))

            throw new EstadoInvalidoException("El usuario ya indicó que le gusta esta publicación");

        MeGusta meGusta = MeGusta.builder()
                .usuario(usuario)
                .publicacion(publicacion)
                .build();

        meGustaRepository.save(meGusta);

        //el meGusta.toDTO puede leer el fecha creacion de la superclase de entidad auditable
        //y lo saca de ahi. Mapea tmb
        return meGustaMapper.toDTO(meGusta);
    }

    @Override
    public void eliminarMeGusta(Long usuarioId, Long publicacionId) {

        if (!meGustaRepository.existsByUsuarioIdAndPublicacionId(usuarioId, publicacionId))

            throw new NotFoundException("No existe un me gusta de ese usuario para esa publicacion");

        MeGusta meGusta = meGustaRepository
                .findByUsuarioIdAndPublicacionId(usuarioId, publicacionId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un me gusta de ese usuario y de esa publicacion"));


        meGustaRepository.delete(meGusta);

    }

    @Override
    public boolean dioMeGusta(Long usuarioId, Long publicacionId) {

        return meGustaRepository.existsByUsuarioIdAndPublicacionId(usuarioId, publicacionId);
    }

    @Override
    public long contarPorPublicacion(Long publicacionId) {

        if(!publicacionRepository.existsById(publicacionId))

            throw new NotFoundException("No se ha encontrado una publicación con id " + publicacionId);

        return meGustaRepository
                .countByPublicacionId(publicacionId);
    }

    public Page<MeGustaDTO> listarPorPublicacion(Long publicacionId, Pageable pageable){

        return meGustaRepository
                .findByPublicacionId(publicacionId, pageable)
                .map(meGustaMapper::toDTO);
    }


    private Usuario buscarEntidadUsuarioPorId(Long usuarioId){

        return usuarioRepository.
                findById(usuarioId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un usuario con id " + usuarioId));
    }

    private Publicacion buscarEntidadPublicacionPorId(Long publicacionId){

        return publicacionRepository
                .findById(publicacionId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado una publicacion con id " + publicacionId));
    }
}
