package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.EjercicioDTO.ActualizarEjercicioDTO;
import com.gymtrack.backend.dto.EjercicioDTO.CrearEjercicioDTO;
import com.gymtrack.backend.dto.EjercicioDTO.EjercicioDTO;
import com.gymtrack.backend.dto.ArchivoDTO.ArchivoDTO;
import com.gymtrack.backend.exception.AccesoDenegadoException;
import com.gymtrack.backend.exception.AlreadyExistsException;
import com.gymtrack.backend.exception.NotFoundException;
import com.gymtrack.backend.exception.ResourceNotFoundException;
import com.gymtrack.backend.mapper.EjercicioMapper;
import com.gymtrack.backend.model.Ejercicio;
import com.gymtrack.backend.model.GrupoMuscular;
import com.gymtrack.backend.model.TipoMedia;
import com.gymtrack.backend.repository.EjercicioRepository;
import com.gymtrack.backend.repository.GrupoMuscularRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class EjercicioServiceImp implements EjercicioService{

    private final EjercicioRepository ejercicioRepository;
    private final GrupoMuscularRepository grupoMuscularRepository;
    private final EjercicioMapper ejercicioMapper;
    private final CloudinaryImagenServiceImp cloudinaryImagenServiceImp;

    @Override
    public List<EjercicioDTO> listar() {

        return ejercicioRepository.findAll()
                .stream().map(ejercicioMapper::toDto).toList();
    }

    @Override
    public EjercicioDTO buscarPorId(Long id) {

        Ejercicio ejercicio = buscarEntidadPorId(id);

        return ejercicioMapper.toDto(ejercicio);
    }

    @Override
    public EjercicioDTO crear(CrearEjercicioDTO dto, MultipartFile archivo) {

        validarNombreEjercicio(dto.getNombre());

        //paso a entidad

        Ejercicio ejercicio = ejercicioMapper.toEntity(dto);

        //busco grupos musculares

        //el findAllById hace un in en SQL
        Set<GrupoMuscular> gruposMusculares = new HashSet<>(grupoMuscularRepository
                .findAllById(dto.getGruposMuscularesIds()));

        //por eso me conviene verificar si los 3 ids ej que habia en el set realmente existian al hacer el in

        if (gruposMusculares.size() != dto.getGruposMuscularesIds().size()) {
            throw new ResourceNotFoundException(
                    "Uno o más grupos musculares no existen"
            );
        }
        //agrego al ejercicio

        ejercicio.setGruposMusculares(gruposMusculares);

        crearVideo(ejercicio, archivo);

        return ejercicioMapper.toDto(ejercicioRepository.save(ejercicio));

    }

    @Override
    public EjercicioDTO actualizar(Long id, ActualizarEjercicioDTO dto) {

        validarNombreEjercicio(dto.getNombre());

        Ejercicio ejercicio = buscarEntidadPorId(id);

        ejercicioMapper.updateEntity(dto, ejercicio);

        //ahora me quedan los grupos musculares

        if (dto.getGruposMuscularesIds() != null){

            Set<GrupoMuscular> gruposMusculares = new HashSet<>(grupoMuscularRepository.findAllById(dto.getGruposMuscularesIds()));
            ejercicio.setGruposMusculares(gruposMusculares);
        }


        return ejercicioMapper.toDto(ejercicioRepository.save(ejercicio));
    }

    @Override
    public EjercicioDTO actualizarVideo(Long id, MultipartFile archivo){

        Ejercicio ejercicio = buscarEntidadPorId(id);

        String publicIdAnterior = ejercicio.getVideoPublicId();

        crearVideo(ejercicio, archivo);

        Ejercicio guardado = ejercicioRepository.save(ejercicio);

        if (publicIdAnterior != null)
            cloudinaryImagenServiceImp.eliminarArchivo(publicIdAnterior, TipoMedia.VIDEO);


        return ejercicioMapper.toDto(guardado);

    }

    public EjercicioDTO eliminarVideo(Long id){


        Ejercicio ejercicio = buscarEntidadPorId(id);

        if (ejercicio.getVideoPublicId() != null){

            cloudinaryImagenServiceImp.eliminarArchivo(ejercicio.getVideoPublicId(), TipoMedia.VIDEO);
        }

        ejercicio.setVideoUrl(null);
        ejercicio.setVideoPublicId(null);

        return ejercicioMapper.toDto(ejercicioRepository.save(ejercicio));
    }


    @Override
    public void eliminar(Long id) {

        if (!ejercicioRepository.existsById(id))
            throw new NotFoundException("No existe un ejercicio con ID " + id);

        ejercicioRepository.deleteById(id);
    }

    @Override
    public List<EjercicioDTO> buscarPorGrupoMuscular(String nombre){

        return ejercicioRepository.findByGruposMuscularesNombre(nombre)
                .stream().map(ejercicioMapper::toDto)
                .toList();
    }

    private Ejercicio buscarEntidadPorId(Long id){

        return ejercicioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un ejercicio con ID " + id));
    }

    private void validarNombreEjercicio(String nombre){

        if (ejercicioRepository.existsByNombre(nombre))

            throw new AlreadyExistsException("Ya existe un ejercicio con nombre " + nombre);
    }


    private void crearVideo(Ejercicio ejercicio, MultipartFile archivo){

        if (archivo != null && !archivo.isEmpty()){

            String contentType = archivo.getContentType();

            if (contentType == null || archivo.isEmpty())

                throw new AccesoDenegadoException("El archivo debe ser un  video");


            ArchivoDTO archivoSubido = cloudinaryImagenServiceImp.subirArchivo(archivo);

            ejercicio.setVideoUrl(archivoSubido.getUrl());
            ejercicio.setVideoPublicId(archivoSubido.getPublicId());
        }

    }

     
}
