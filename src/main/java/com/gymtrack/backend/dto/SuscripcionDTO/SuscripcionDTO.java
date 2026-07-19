package com.gymtrack.backend.dto.SuscripcionDTO;

import com.gymtrack.backend.model.EstadoSuscripcion;
import com.gymtrack.backend.model.ProveedorPago;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class SuscripcionDTO {

    private Long id;
    private EstadoSuscripcion estado;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaProximoCobro;
    private LocalDateTime fechaFin;
    private ProveedorPago proveedor;
    private boolean renovacionAutomatica;

}
