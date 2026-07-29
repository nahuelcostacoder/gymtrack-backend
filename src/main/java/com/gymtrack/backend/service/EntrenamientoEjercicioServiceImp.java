package com.gymtrack.backend.service;


import com.gymtrack.backend.dto.EntrenamientoDTO.EntrenamientoDTO;
import com.gymtrack.backend.dto.EntrenamientoEjercicioDTO.ActualizarEntrenamientoEjercicioDTO;
import com.gymtrack.backend.dto.EntrenamientoEjercicioDTO.CrearEntrenamientoEjercicioDTO;
import com.gymtrack.backend.dto.EntrenamientoEjercicioDTO.EntrenamientoEjercicioDTO;
import com.gymtrack.backend.exception.AlreadyExistsException;
import com.gymtrack.backend.exception.NotFoundException;
import com.gymtrack.backend.mapper.EntrenamientoEjercicioMapper;
import com.gymtrack.backend.model.Ejercicio;
import com.gymtrack.backend.model.Entrenamiento;
import com.gymtrack.backend.model.EntrenamientoEjercicio;
import com.gymtrack.backend.repository.EjercicioRepository;
import com.gymtrack.backend.repository.EntrenamientoEjercicioRepository;
import com.gymtrack.backend.repository.EntrenamientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EntrenamientoEjercicioServiceImp implements EntrenamientoEjercicioService{

    private final EntrenamientoEjercicioRepository entrenamientoEjercicioRepository;
    private final EntrenamientoRepository entrenamientoRepository;
    private final EjercicioRepository ejercicioRepository;
    private final EntrenamientoEjercicioMapper entrenamientoEjercicioMapper;

    @Override
    public List<EntrenamientoEjercicioDTO> listarPorEntrenamientoId(Long entrenamientoId) {

        return entrenamientoEjercicioRepository.findByEntrenamientoId(entrenamientoId)
                .stream().map(entrenamientoEjercicioMapper::toDto).toList();
    }

    @Override
    public EntrenamientoEjercicioDTO buscarPorId(Long entrenamientoId, Long entrenamientoEjercicioId) {

        EntrenamientoEjercicio entrenamientoEjercicio = buscarEntidadPorIds(entrenamientoId, entrenamientoEjercicioId);

        return entrenamientoEjercicioMapper.toDto(entrenamientoEjercicio);
    }

    @Override
    public EntrenamientoEjercicioDTO crear(Long entrenamientoId, CrearEntrenamientoEjercicioDTO dto) {

        Entrenamiento entrenamiento = buscarEntidadEntrenamientoPorId(entrenamientoId);
        Ejercicio ejercicio = buscarEntidadEjercicioPorId(dto.getEjercicioId());

        validarEjercicioRepetido(entrenamientoId, dto.getEjercicioId());
        validarOrden(entrenamientoId, dto.getOrden());

        EntrenamientoEjercicio entrenamientoEjercicio = entrenamientoEjercicioMapper.toEntity(dto);

        entrenamientoEjercicio.setEntrenamiento(entrenamiento);
        entrenamientoEjercicio.setEjercicio(ejercicio);

        return entrenamientoEjercicioMapper.toDto(entrenamientoEjercicioRepository.save(entrenamientoEjercicio));
    }

    @Override
    public EntrenamientoEjercicioDTO actualizar(Long entrenamientoId, Long entrenamientoEjercicioId, ActualizarEntrenamientoEjercicioDTO dto) {

        EntrenamientoEjercicio entrenamientoEjercicio = buscarEntidadPorIds(entrenamientoId, entrenamientoEjercicioId);

        if (dto.getEjercicioId() != null){ //si no es null, es que quiere editar el ejercicio

            Ejercicio ejercicio = buscarEntidadEjercicioPorId(dto.getEjercicioId());

            validarEjercicioRepetidoAlActualizar(entrenamientoId, ejercicio.getId(), entrenamientoEjercicioId);

            entrenamientoEjercicio.setEjercicio(ejercicio);
        }

        if (dto.getOrden() != null){

            validarOrdenAlActualizar(entrenamientoId, dto.getOrden(), entrenamientoEjercicioId);
        }

        //orden y observaciones las actualizo si no son null con el mapper
        entrenamientoEjercicioMapper.updateEntity(dto, entrenamientoEjercicio);

        return entrenamientoEjercicioMapper.toDto(entrenamientoEjercicioRepository.save(entrenamientoEjercicio));
    }

    @Override
    public void eliminar(Long entrenamientoId, Long entrenamientoEjercicioId) {

        EntrenamientoEjercicio entrenamientoEjercicio = buscarEntidadPorIds(entrenamientoId, entrenamientoEjercicioId);

        entrenamientoEjercicioRepository.delete(entrenamientoEjercicio);
    }

    private EntrenamientoEjercicio buscarEntidadPorIds(Long entrenamientoId, Long entrenamientoEjercicioId){

        return entrenamientoEjercicioRepository.findByEntrenamientoIdAndId(entrenamientoEjercicioId, entrenamientoId)
                .orElseThrow(() -> new NotFoundException("No existe un EntrenamientoEjercicio vinculado a ese id de entrenamiento ejercicio y ese entrenamientoEjercicio"));

    }

    private Entrenamiento buscarEntidadEntrenamientoPorId(Long entrenamientoId){

        return entrenamientoRepository.findById(entrenamientoId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un entrenamiento con id " + entrenamientoId));
    }

    private Ejercicio buscarEntidadEjercicioPorId(Long ejercicioId){

        return ejercicioRepository.findById(ejercicioId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un ejercicio con id " + ejercicioId));
    }

    private void validarEjercicioRepetido(Long entrenamientoId, Long ejercicioId){

        if (entrenamientoEjercicioRepository.existsByEntrenamientoIdAndEjercicioId(entrenamientoId, ejercicioId))

            throw new AlreadyExistsException("Ya existe el ejercicio con id " + ejercicioId + "dentro del entrenamiento con id " + entrenamientoId);
    }

    private void validarOrden(Long entrenamientoId, Integer orden){

        if (entrenamientoEjercicioRepository.existsByEntrenamientoIdAndOrden(entrenamientoId, orden))

            throw new AlreadyExistsException("Ya existe un ejercicio en el orden " + orden);
    }

    private void validarEjercicioRepetidoAlActualizar(Long entrenamientoId, Long ejercicioId, Long entrenamientoEjercicioId) {

        if (entrenamientoEjercicioRepository.existsByEntrenamientoIdAndEjercicioIdAndIdNot(entrenamientoId, ejercicioId, entrenamientoEjercicioId))

            throw new AlreadyExistsException("Ya existe en el entrenamiento un ejercicio con ese id");
    }

    private void validarOrdenAlActualizar(Long entrenamientoId, Integer orden, Long entrenamientoEjercicioId){

        if (entrenamientoEjercicioRepository.existsByEntrenamientoIdAndOrdenAndIdNot(entrenamientoId, orden, entrenamientoEjercicioId))

            throw new AlreadyExistsException("Ya existe en el entrenamiento un ejercicio en el orden " + orden);
    }
}
