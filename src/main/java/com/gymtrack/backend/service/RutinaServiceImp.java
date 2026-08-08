package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.RutinaDTO.ActualizarRutinaDTO;
import com.gymtrack.backend.dto.RutinaDTO.CrearRutinaDTO;
import com.gymtrack.backend.dto.RutinaDTO.RutinaDTO;
import com.gymtrack.backend.exception.AccesoDenegadoException;
import com.gymtrack.backend.exception.AlreadyExistsException;
import com.gymtrack.backend.exception.NotFoundException;
import com.gymtrack.backend.mapper.RutinaMapper;
import com.gymtrack.backend.model.Rutina;
import com.gymtrack.backend.model.Usuario;
import com.gymtrack.backend.repository.RutinaRepository;
import com.gymtrack.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class RutinaServiceImp implements RutinaService {

    private final RutinaRepository rutinaRepository;
    private final UsuarioRepository usuarioRepository;
    private final RutinaMapper rutinaMapper;

    @Override
    public List<RutinaDTO> listar() {
        return rutinaRepository.findAll().
                stream().map(rutinaMapper::toDTO)
                .toList();
    }

    @Override
    public RutinaDTO buscarPorId(Long id) {

        Rutina rutina = buscarEntidadRutinaPorId(id);

        return rutinaMapper.toDTO(rutina);
    }

    @Override
    public RutinaDTO crear(Long usuarioId, CrearRutinaDTO dto) {

        Usuario usuario = buscarEntidadUsuarioPorId(usuarioId);

        validarNombreDuplicado(dto.getNombre(), usuario.getId());

        //despues agregar logica de cant max rutinas gratis verificando suscripcion

        Rutina rutina = rutinaMapper.toEntity(dto);
        rutina.setUsuario(usuario);


        return rutinaMapper.toDTO(rutinaRepository.save(rutina));

    }

    @Override
    public RutinaDTO actualizar(Long usuarioId, Long id, ActualizarRutinaDTO dto) {

        Rutina rutina = buscarEntidadRutinaPorId(id);

        if (!rutina.getUsuario().getId().equals(usuarioId)){

            throw new AccesoDenegadoException("No podes modificar una rutina que no te pertenece");
        }

        //que sucede, si lo valido de la forma basica lo que hace es ver si se repite el nombre en la totalidad de las rutinas, pero la realidad es que dos usuarios dif pueden tener el mismo nombre de rutina

        //ademas tiraria error pq veria que si esta rutina ya existia, ve el nombre y va a decir que ya existe al actualizar

       if (dto.getNombre() != null) {

           validarNombreDuplicado(dto.getNombre(), rutina.getUsuario().getId(), rutina.getId());

       }

        rutinaMapper.updateEntity(dto, rutina);


        return rutinaMapper.toDTO(rutinaRepository.save(rutina));
    }

    @Override
    public void eliminar(Long usuarioId, Long id) {

        Rutina rutina = buscarEntidadRutinaPorId(id);

        if (!rutina.getUsuario().getId().equals(usuarioId)){

            throw new AccesoDenegadoException("No podes modificar una rutina que no te pertenece");
        }

        rutinaRepository.delete(rutina);

    }

    private Rutina buscarEntidadRutinaPorId(Long id) {

        return rutinaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado una rutina con ID " + id));
    }

    private Usuario buscarEntidadUsuarioPorId(Long id) {

        return usuarioRepository.findById(id).
                orElseThrow(() -> new NotFoundException("No se ha encontrado un usuario con ID " + id));

    }

    private void validarNombreDuplicado(String nombre, Long usuarioId, Long rutinaId) {

        //esto se valida:

        /*¿Existe una rutina
                - nombre = Push
                - usuario = Nahuel
                - id != 2 ?*/

        if (rutinaRepository.existsByNombreAndUsuarioIdAndIdNot(
                nombre,
                usuarioId,
                rutinaId
        )) {
            throw new AlreadyExistsException(
                    "Ya existe otra rutina con ese nombre"
            );
        }
    }

    private void validarNombreDuplicado(String nombre, Long usuarioId){


        if (rutinaRepository.existsByNombreIgnoreCaseAndUsuarioId(nombre, usuarioId))
            throw new AlreadyExistsException("Ya existe otra rutina con ese nombre");
    }
}