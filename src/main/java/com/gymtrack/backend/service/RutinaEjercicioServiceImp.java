package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.RutinaEjercicioDTO.ActualizarRutinaEjercicioDTO;
import com.gymtrack.backend.dto.RutinaEjercicioDTO.CrearRutinaEjercicioDTO;
import com.gymtrack.backend.dto.RutinaEjercicioDTO.RutinaEjercicioDTO;
import com.gymtrack.backend.exception.AlreadyExistsException;
import com.gymtrack.backend.exception.BusinessException;
import com.gymtrack.backend.exception.NotFoundException;
import com.gymtrack.backend.mapper.RutinaEjercicioMapper;
import com.gymtrack.backend.model.Ejercicio;
import com.gymtrack.backend.model.Rutina;
import com.gymtrack.backend.model.RutinaEjercicio;
import com.gymtrack.backend.repository.EjercicioRepository;
import com.gymtrack.backend.repository.RutinaEjercicioRepository;
import com.gymtrack.backend.repository.RutinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RutinaEjercicioServiceImp implements RutinaEjercicioService{

    private final RutinaEjercicioRepository rutinaEjercicioRepository;
    private final RutinaEjercicioMapper rutinaEjercicioMapper;
    private final EjercicioRepository ejercicioRepository;
    private final RutinaRepository rutinaRepository;

    @Override
    public List<RutinaEjercicioDTO> listarPorRutina(Long rutinaId) {

        buscarEntidadRutinaPorId(rutinaId); //el metodo se ejecuta igual, si encuentra la rutina, el objeto se descarta por java y sino tira la exception

       return rutinaEjercicioRepository.findByRutinaId(rutinaId)
               .stream().map(rutinaEjercicioMapper::toDTO).toList();
    }

    @Override
    public RutinaEjercicioDTO buscarPorId(Long rutinaId, Long rutinaEjercicioId) {

        RutinaEjercicio rutinaEjercicio = buscarEntidadRutinaEjercicioPorIdYRutina(rutinaId, rutinaEjercicioId);

        return rutinaEjercicioMapper.toDTO(rutinaEjercicio);
    }

    @Override
    public RutinaEjercicioDTO crear(Long rutinaId, CrearRutinaEjercicioDTO dto) {

        Rutina rutina = buscarEntidadRutinaPorId(rutinaId);
        Ejercicio ejercicio = buscarEntidadEjercicioPorId(dto.getEjercicioId());

        //validamos que no se este agregando una relacion de rutina-ejercicio que ya existe, ej rutina 2 tiene pecho

        validarEjercicioRepetido(rutina.getId(), ejercicio.getId());
        validarOrden(rutina.getId(), dto.getOrden());

        RutinaEjercicio rutinaEjercicio = rutinaEjercicioMapper.toEntity(dto);

        rutinaEjercicio.setRutina(rutina);
        rutinaEjercicio.setEjercicio(ejercicio);


        return rutinaEjercicioMapper.toDTO(rutinaEjercicioRepository.save(rutinaEjercicio));

    }

    @Override
    public RutinaEjercicioDTO actualizar(Long idRutina, Long idRutinaEjercicio, ActualizarRutinaEjercicioDTO dto) {

        RutinaEjercicio rutinaEjercicio = buscarEntidadRutinaEjercicioPorIdYRutina(idRutinaEjercicio, idRutina);

        Rutina rutina = buscarEntidadRutinaPorId(idRutina);

        if (dto.getEjercicioId() != null){

            Ejercicio ejercicio = buscarEntidadEjercicioPorId(dto.getEjercicioId());

            validarEjercicioRepetidoAlActualizar(rutina.getId(), ejercicio.getId(), rutinaEjercicio.getId());

            rutinaEjercicio.setEjercicio(ejercicio);
        }

        if (dto.getOrden() != null){

            validarOrdenAlActualizar(rutina.getId(), dto.getOrden(), rutinaEjercicio.getId());
        }

        rutinaEjercicioMapper.updateEntity(dto, rutinaEjercicio);

        return rutinaEjercicioMapper.toDTO(rutinaEjercicioRepository.save(rutinaEjercicio));
    }

    @Override
    public void eliminar(Long rutinaId, Long rutinaEjercicioId) {

        RutinaEjercicio rutinaEjercicio = buscarEntidadRutinaEjercicioPorIdYRutina(rutinaId, rutinaEjercicioId);

        rutinaEjercicioRepository.delete(rutinaEjercicio);

    }

    private RutinaEjercicio buscarEntidadRutinaEjercicioPorIdYRutina(Long idRutina, Long idRutinaEjercicio){

        return rutinaEjercicioRepository
                .findByIdAndRutinaId(idRutinaEjercicio, idRutina)
                .orElseThrow(() ->
                        new NotFoundException(
                                "No existe el ejercicio de rutina con ID "
                                        + idRutinaEjercicio
                                        + " dentro de la rutina "
                                        + idRutina
                        )
                );
    }

    private Rutina buscarEntidadRutinaPorId(Long id){

        return rutinaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado una rutina con id " + id));
    }

    private Ejercicio buscarEntidadEjercicioPorId(Long id){

        return ejercicioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado un ejercicio con id " + id));
    }

    private void validarEjercicioRepetido(Long rutinaId, Long ejercicioId){

        if (rutinaEjercicioRepository.existsByRutinaIdAndEjercicioId(rutinaId, ejercicioId)){

            throw new AlreadyExistsException("Ya existe un rutina-ejercicio con esta rutina y ejercicio");
        }
    }

    private void validarOrden(Long rutinaId, Integer orden){

        if (rutinaEjercicioRepository.existsByRutinaIdAndOrden(rutinaId, orden)){

            throw new AlreadyExistsException("Ya existe un ejercicio en ese orden en esa rutina");
        }

    }

    private void validarEjercicioRepetidoAlActualizar(Long rutinaId, Long ejercicioId, Long rutinaEjercicioId){

        if (rutinaEjercicioRepository.existsByRutinaIdAndEjercicioIdAndIdNot(rutinaId, ejercicioId, rutinaEjercicioId))

            throw new BusinessException("El ejercicio ya pertenece a esta rutina");
    }

    private void validarOrdenAlActualizar(Long rutinaId, Integer orden, Long rutinaEjercicioId){

        if (rutinaEjercicioRepository.existsByRutinaIdAndOrdenAndIdNot(rutinaId, orden, rutinaEjercicioId))

            throw new BusinessException("Ya eciste un ejercicio en esta posicion");
    }
}
