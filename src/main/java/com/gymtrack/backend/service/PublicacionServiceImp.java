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
import com.gymtrack.backend.repository.ComentarioRepository;
import com.gymtrack.backend.repository.EntrenamientoRepository;
import com.gymtrack.backend.repository.MeGustaRepository;
import com.gymtrack.backend.repository.PublicacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PublicacionServiceImp implements PublicacionService{

    private final PublicacionRepository publicacionRepository;
    private final EntrenamientoRepository entrenamientoRepository;
    private final MeGustaRepository meGustaRepository;
    private final ComentarioRepository comentarioRepository;
    private final PublicacionMapper publicacionMapper;


    @Override
    public Page<PublicacionDTO> listarFeed(Long usuarioId, Pageable pageable){

        //1. Traemos la pagina de publicaciones
        Page<Publicacion> pagina = publicacionRepository
                .findAllByOrderByFechaCreacionDesc(pageable);

        return completarPublicaciones(pagina, usuarioId);

    }

    @Override
    public Page<PublicacionDTO> listarPorUsuario(Long usuarioId, Long usuarioAutenticadoId, Pageable pageable){

        Page<Publicacion> pagina = publicacionRepository
                .findByEntrenamientoUsuarioId(usuarioId, pageable);

        return completarPublicaciones(pagina, usuarioAutenticadoId); //cargamos si dio like en base a el autenticado.
    }

    @Override
    public PublicacionDTO buscarPorId(Long usuarioId, Long publicacionId) {

        Publicacion publicacion = buscarEntidadPorId(publicacionId);

        PublicacionDTO dto =  publicacionMapper.toDTO(publicacion);

        return completarPublicacion(publicacion, usuarioId);

        //aca no es necesario hacer lo otro, porque no es N+1 porque no traigo paginas completas.
    }

    @Override
    public PublicacionDTO crear(Long usuarioId, CrearPublicacionDTO dto) {

        Entrenamiento entrenamiento = buscarEntrenamientoPorId(usuarioId, dto.getEntrenamientoId());

        //ahora debemos revisar que ya no haya una publicacion con ese entrenamiento

        if (publicacionRepository.existsByEntrenamientoId(entrenamiento.getId()))

            throw new EstadoInvalidoException("Ese entrenamiento ya fue publicado");


        Publicacion publicacion = publicacionMapper.toEntity(dto);

        publicacion.setEntrenamiento(entrenamiento);

        Publicacion guardada = publicacionRepository.save(publicacion);

        PublicacionDTO respuesta = publicacionMapper.toDTO(guardada);

        respuesta.setCantidadLikes(0L);
        respuesta.setCantidadComentarios(0L);
        respuesta.setDioLike(false);


        return respuesta;
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

        Publicacion actualizada = publicacionRepository.save(publicacion);

        return completarPublicacion(actualizada, usuarioId);
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
                .findByIdAndUsuarioId(entrenamientoId, usuarioId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado el entrenamiento o no pertenece al usuario"));
    }

    private Page<PublicacionDTO> completarPublicaciones(Page<Publicacion> pagina,
                                                  Long usuarioId){


        //2. Obtenemos los IDs de las publicaciones a esta pagina
        List<Long> publicacionesIds = pagina.getContent()
                .stream()
                .map(Publicacion::getId)
                .toList(); //acordemosnos que es una pagina no una lista normal

        //3. Obtengo resultados
        List<Object[]> resultadosLikes = meGustaRepository
                .contarLikesPorPublicaciones(publicacionesIds);

        //4. Transformo la list object en una estructura map para tener los dos valores, 0 es id y 1 es cantlikes
        Map<Long, Long> likesPorPublicacion = resultadosLikes.stream()
                .collect(Collectors.toMap(fila -> (Long) fila[0], //id publi
                        fila -> (Long) fila[1] //cant likes
                ));

        //5. Obtengo resultados comentarios
        List<Object[]> resultadosComentarios = comentarioRepository
                .contarComentariosPorPublicaciones(publicacionesIds);

        //6. Transformo la list object en una estructura map nuevamente

        Map<Long, Long > comentariosPorPublicacion = resultadosComentarios.stream()
                .collect(Collectors.toMap(fila -> (Long) fila [0],
                        fila -> (Long) fila[1]));


        //7. publicaciones con like
        Set<Long> publicacionesConLike = meGustaRepository
                .buscarPublicacionesConLikeDelUsuario(usuarioId, publicacionesIds);

        //mapea todas las publis de la pag
        return pagina.map(publicacion -> {


            PublicacionDTO dto = publicacionMapper.toDTO(publicacion);

            dto.setCantidadLikes(
                    likesPorPublicacion.getOrDefault(publicacion.getId(), 0L)
            );

            dto.setCantidadComentarios(comentariosPorPublicacion
                    .getOrDefault(publicacion.getId(), 0L));

            dto.setDioLike(publicacionesConLike.contains(publicacion.getId()));


            return dto;
        });
    }

    private PublicacionDTO completarPublicacion(
            Publicacion publicacion,
            Long usuarioId) {

        PublicacionDTO dto = publicacionMapper.toDTO(publicacion);

        dto.setCantidadLikes(
                meGustaRepository.countByPublicacionId(publicacion.getId())
        );

        dto.setCantidadComentarios(
                comentarioRepository.countByPublicacionId(publicacion.getId())
        );

        dto.setDioLike(
                meGustaRepository.existsByUsuarioIdAndPublicacionId(
                        usuarioId,
                        publicacion.getId()
                )
        );

        return dto;
    }
}
