package com.gymtrack.backend.mapper;

import com.gymtrack.backend.dto.RutinaDTO.ActualizarRutinaDTO;
import com.gymtrack.backend.dto.RutinaDTO.CrearRutinaDTO;
import com.gymtrack.backend.dto.RutinaDTO.RutinaDTO;
import com.gymtrack.backend.dto.RutinaEjercicioDTO.RutinaEjercicioDTO;
import com.gymtrack.backend.model.Rutina;
import com.gymtrack.backend.model.RutinaEjercicio;
import com.gymtrack.backend.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-30T15:04:52-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class RutinaMapperImpl implements RutinaMapper {

    @Autowired
    private EjercicioMapper ejercicioMapper;

    @Override
    public RutinaDTO toDTO(Rutina rutina) {
        if ( rutina == null ) {
            return null;
        }

        RutinaDTO.RutinaDTOBuilder rutinaDTO = RutinaDTO.builder();

        rutinaDTO.usuarioId( rutinaUsuarioId( rutina ) );
        rutinaDTO.id( rutina.getId() );
        rutinaDTO.nombre( rutina.getNombre() );
        rutinaDTO.descripcion( rutina.getDescripcion() );
        rutinaDTO.publica( rutina.isPublica() );
        rutinaDTO.ejercicios( rutinaEjercicioListToRutinaEjercicioDTOList( rutina.getEjercicios() ) );

        return rutinaDTO.build();
    }

    @Override
    public Rutina toEntity(CrearRutinaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Rutina.RutinaBuilder rutina = Rutina.builder();

        rutina.nombre( dto.getNombre() );
        rutina.descripcion( dto.getDescripcion() );
        rutina.publica( dto.isPublica() );

        return rutina.build();
    }

    @Override
    public void updateEntity(ActualizarRutinaDTO dto, Rutina rutina) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getNombre() != null ) {
            rutina.setNombre( dto.getNombre() );
        }
        if ( dto.getDescripcion() != null ) {
            rutina.setDescripcion( dto.getDescripcion() );
        }
        rutina.setPublica( dto.isPublica() );
    }

    private Long rutinaUsuarioId(Rutina rutina) {
        Usuario usuario = rutina.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        return usuario.getId();
    }

    protected RutinaEjercicioDTO rutinaEjercicioToRutinaEjercicioDTO(RutinaEjercicio rutinaEjercicio) {
        if ( rutinaEjercicio == null ) {
            return null;
        }

        RutinaEjercicioDTO.RutinaEjercicioDTOBuilder rutinaEjercicioDTO = RutinaEjercicioDTO.builder();

        rutinaEjercicioDTO.id( rutinaEjercicio.getId() );
        rutinaEjercicioDTO.orden( rutinaEjercicio.getOrden() );
        rutinaEjercicioDTO.seriesObjetivo( rutinaEjercicio.getSeriesObjetivo() );
        rutinaEjercicioDTO.repeticionesObjetivo( rutinaEjercicio.getRepeticionesObjetivo() );
        rutinaEjercicioDTO.pesoObjetivo( rutinaEjercicio.getPesoObjetivo() );
        rutinaEjercicioDTO.descansoSegundos( rutinaEjercicio.getDescansoSegundos() );
        rutinaEjercicioDTO.observaciones( rutinaEjercicio.getObservaciones() );
        rutinaEjercicioDTO.ejercicio( ejercicioMapper.toDto( rutinaEjercicio.getEjercicio() ) );

        return rutinaEjercicioDTO.build();
    }

    protected List<RutinaEjercicioDTO> rutinaEjercicioListToRutinaEjercicioDTOList(List<RutinaEjercicio> list) {
        if ( list == null ) {
            return null;
        }

        List<RutinaEjercicioDTO> list1 = new ArrayList<RutinaEjercicioDTO>( list.size() );
        for ( RutinaEjercicio rutinaEjercicio : list ) {
            list1.add( rutinaEjercicioToRutinaEjercicioDTO( rutinaEjercicio ) );
        }

        return list1;
    }
}
