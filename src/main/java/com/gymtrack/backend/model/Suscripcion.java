package com.gymtrack.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@Table(name = "suscripciones")
@Entity
public class Suscripcion extends EntidadAuditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", unique = true, nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoSuscripcion estado;

    @Column(nullable = false)
    private LocalDateTime fechaInicio;

    @Column(nullable = false)
    private LocalDateTime fechaProximoCobro;

    @Enumerated(EnumType.STRING) //pasamos de ordinal a string
    @Column(nullable = false, length = 50)
    private ProveedorPago proveedor;// MERCADO_PAGO

    @Column(unique = true, nullable = false)
    private String suscripcionExternaId;

    @Builder.Default
    @Column(nullable = false)
    private boolean renovacionAutomatica = true;

    private LocalDateTime fechaFin; // si no quiere renovacion automatica
}
