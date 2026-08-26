package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.EntrenamientoDTO.ActualizarEntrenamientoDTO;
import com.gymtrack.backend.dto.EntrenamientoDTO.CrearEntrenamientoDTO;
import com.gymtrack.backend.dto.EntrenamientoDTO.EntrenamientoDTO;
import com.gymtrack.backend.dto.RutinaEjercicioDTO.ActualizarRutinaEjercicioDTO;
import com.gymtrack.backend.exception.NotFoundException;
import com.gymtrack.backend.mapper.EntrenamientoMapper;
import com.gymtrack.backend.model.Entrenamiento;
import com.gymtrack.backend.model.Rutina;
import com.gymtrack.backend.model.Usuario;
import com.gymtrack.backend.repository.EntrenamientoRepository;
import com.gymtrack.backend.repository.RutinaRepository;
import com.gymtrack.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
public class EntrenamientoServiceImp implements EntrenamientoService{

    private final EntrenamientoRepository entrenamientoRepository;
    private final UsuarioRepository usuarioRepository;
    private final RutinaRepository rutinaRepository;
    private final EntrenamientoMapper entrenamientoMapper;


    @Override
    public EntrenamientoDTO buscarPorUsuarioId(Long entrenamientoId, Long usuarioId) {

        Entrenamiento entrenamiento = buscarEntidadEntrenamientoPorIdyUsuarioId(entrenamientoId, usuarioId);

        return entrenamientoMapper.toDto(entrenamiento);
    }

    @Override
    public List<EntrenamientoDTO> listarPorUsuarioId(Long usuarioId) {

        validarUsuarioExiste(usuarioId);

        return entrenamientoRepository.findByUsuarioIdOrderByFechaInicioDesc(usuarioId)
                .stream().map(entrenamientoMapper::toDto).toList();
    }

    @Override
    public EntrenamientoDTO crear(Long usuarioId, CrearEntrenamientoDTO dto) {

        Usuario usuario = buscarUsuarioPorId(usuarioId);

        Entrenamiento entrenamiento = entrenamientoMapper.toEntity(dto);

        entrenamiento.setUsuario(usuario);
        entrenamiento.setFechaInicio(LocalDateTime.now());

        //la rutina es opcional recordemos
        if (dto.getRutinaId() != null){

            Rutina rutina = buscarRutinaPorIdYUsuarioId(dto.getRutinaId(), usuarioId);
            entrenamiento.setRutina(rutina);
        }


        return entrenamientoMapper.toDto(entrenamientoRepository.save(entrenamiento));
    }

    @Override
    public EntrenamientoDTO actualizar(Long entrenamientoId, Long usuarioId, ActualizarEntrenamientoDTO dto) {

        //voy a poder actualizar la rutina por si el usuario se confundio y quiere asociar otra o las observaciones

        //fecha fin va en un metodo aparte

        Entrenamiento entrenamiento = buscarEntidadEntrenamientoPorIdyUsuarioId(entrenamientoId, usuarioId);


        entrenamientoMapper.updateEntity(dto, entrenamiento);

        if (dto.getRutinaId() != null){

            Rutina rutina = buscarRutinaPorIdYUsuarioId(dto.getRutinaId(), usuarioId);
            entrenamiento.setRutina(rutina);

        }

        return entrenamientoMapper.toDto(entrenamientoRepository.save(entrenamiento));
    }

    @Override
    public void eliminar(Long entrenamientoId, Long usuarioId) {

        Entrenamiento entrenamiento = buscarEntidadEntrenamientoPorIdyUsuarioId(entrenamientoId, usuarioId);

        entrenamientoRepository.delete(entrenamiento);
    }

    @Override
    public EntrenamientoDTO finalizar(Long entrenamientoId, Long usuarioId) {

        Entrenamiento entrenamiento = buscarEntidadEntrenamientoPorIdyUsuarioId(entrenamientoId, usuarioId);

        if (entrenamiento.getFechaFin() != null)
            throw new IllegalStateException("El entrenamiento ya fue finalizado");

        LocalDateTime fechaFin = LocalDateTime.now();

        entrenamiento.setFechaFin(fechaFin);

        int duracion = Math.toIntExact(
                Duration.between(
                        entrenamiento.getFechaInicio(),
                        fechaFin
                ).toMinutes()
        );

        entrenamiento.setDuracionMinutos(duracion);

        return entrenamientoMapper.toDto(entrenamientoRepository.save(entrenamiento));
    }



    private Entrenamiento buscarEntidadEntrenamientoPorIdyUsuarioId(Long entrenamientoId, Long usuarioId){

        return entrenamientoRepository.findByIdAndUsuarioId(entrenamientoId, usuarioId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un entrenamiento con id " + entrenamientoId));
    }

    private void validarUsuarioExiste(Long usuarioId){

        if (!usuarioRepository.existsById(usuarioId))
            throw new NotFoundException("No existe un usuario con id " + usuarioId);
    }

    private Usuario buscarUsuarioPorId(Long usuarioId){

        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un usuario con id " + usuarioId));
    }

    private Rutina buscarRutinaPorIdYUsuarioId(Long rutinaId, Long usuarioId){

        return rutinaRepository.
                findByIdAndUsuarioId(rutinaId, usuarioId).orElseThrow(() -> new NotFoundException("No se ha encontrado una rutina relacionada con ese usuario"));
    }
}
