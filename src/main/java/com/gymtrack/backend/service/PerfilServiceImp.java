package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.ArchivoDTO.ArchivoDTO;
import com.gymtrack.backend.dto.PerfilDTO.ActualizarPerfilDTO;
import com.gymtrack.backend.dto.PerfilDTO.CrearPerfilDTO;
import com.gymtrack.backend.dto.PerfilDTO.PerfilDTO;
import com.gymtrack.backend.exception.AccesoDenegadoException;
import com.gymtrack.backend.exception.EstadoInvalidoException;
import com.gymtrack.backend.exception.NotFoundException;
import com.gymtrack.backend.mapper.PerfilMapper;
import com.gymtrack.backend.model.Perfil;
import com.gymtrack.backend.model.TipoMedia;
import com.gymtrack.backend.model.Usuario;
import com.gymtrack.backend.repository.PerfilRepository;
import com.gymtrack.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PerfilServiceImp implements PerfilService{

    private final PerfilRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;
    private final PerfilMapper perfilMapper;
    private final CloudinaryImagenServiceImp cloudinaryImagenServiceImp;

    @Override
    public List<PerfilDTO> listar() {

        return perfilRepository.findAll().stream()
                .map(perfilMapper::toDTO).toList();
    }

    @Override
    public PerfilDTO buscarPorId(Long id) {

        Perfil perfil = buscarEntidadPorId(id);

        return perfilMapper.toDTO(perfil);

    }

    @Override
    public PerfilDTO crear(Long usuarioId, CrearPerfilDTO dto, MultipartFile archivo) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un usuario con el ID " + usuarioId));
        //busco el usuario con ese id para despues guardarlo completo con su correspondiente entidad en la bd


        if (perfilRepository.existsByUsuarioId(usuarioId)) {
            throw new EstadoInvalidoException(
                    "El usuario ya tiene un perfil"
            );
        }

        Perfil perfil = perfilMapper.toEntity(dto);

        perfil.setUsuario(usuario);

        cargarImagen(perfil, archivo);

        Perfil perfilGuardado = perfilRepository.save(perfil);

        return perfilMapper.toDTO(perfilGuardado);

    }

    @Override
    public PerfilDTO actualizar(Long usuarioId, Long id, ActualizarPerfilDTO dto) {

        Perfil perfil = buscarEntidadPorId(id);

        if (!perfil.getUsuario().getId().equals(usuarioId)){

            throw new AccesoDenegadoException("No podes actualizar un perfil que no es tuyo");
        }

        perfilMapper.updateEntity(dto, perfil);

        Perfil perfilActualizado = perfilRepository.save(perfil);

        return perfilMapper.toDTO(perfilActualizado);
    }

    @Override
    public PerfilDTO actualizarFotoPerfil(Long usuarioId, MultipartFile archivo){

        Perfil perfil = buscarEntidadPorIdUsuario(usuarioId);

        String publicIdAnterior = perfil.getFotoPerfilPublicId();

        //subimos la nueva imagen
        cargarImagen(perfil, archivo);

        perfilRepository.save(perfil);

        //so tpdp salio bien, para eliminar la imagen vieja de cloudinary si ya habia una antes
        if (publicIdAnterior != null) {
            cloudinaryImagenServiceImp.eliminarArchivo(publicIdAnterior, TipoMedia.IMAGEN);
        }

        return perfilMapper.toDTO(perfil);
    }

    @Override //aca es eliminar sin reemplazar
    public PerfilDTO eliminarFotoPerfil(Long usuarioId){

        Perfil perfil = buscarEntidadPorIdUsuario(usuarioId);

        if (perfil.getFotoPerfilPublicId() != null){

            cloudinaryImagenServiceImp.eliminarArchivo(perfil.getFotoPerfilPublicId(), TipoMedia.IMAGEN);
        }

        perfil.setFotoPerfilUrl(null);
        perfil.setFotoPerfilPublicId(null);

        return perfilMapper.toDTO(perfil);
    }


    @Override
    public PerfilDTO buscarPorIdUsuario(Long id) {

        Perfil perfil = perfilRepository.findByUsuarioId(id)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un perfil para el usuario con ID " + id));

        return perfilMapper.toDTO(perfil);
    }

    private void cargarImagen(Perfil perfil, MultipartFile archivo){

        //subimos la nueva imagen

        if (archivo == null || archivo.isEmpty()) {
            return;
        }

        String contentType = archivo.getContentType();

        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("El archivo debe ser una imagen");
        }

        ArchivoDTO imagen = cloudinaryImagenServiceImp.subirArchivo(archivo);

        //guardamos url
        perfil.setFotoPerfilUrl(imagen.getUrl());
        perfil.setFotoPerfilPublicId(imagen.getPublicId());

    }

    private Perfil buscarEntidadPorIdUsuario(Long usuarioId){

        return perfilRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un perfil para el usuario con ID " + usuarioId));

    }

    private Perfil buscarEntidadPorId(Long id){

        return perfilRepository.
                findById(id)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un perfil con el ID " + id));
    }
}
