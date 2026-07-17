package com.gymtrack.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Builder
@Table(name = "perfiles")
public class Perfil extends EntidadAuditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String biografia;

    @Column(nullable = false)
    private BigDecimal peso;

    @Column(nullable = false)
    private BigDecimal altura;

    @Enumerated(EnumType.STRING) //para que no los guarde como numeros ordinales y si como string
    @Column(nullable = false)
    private Objetivo objetivo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NivelEntrenamiento nivelEntrenamiento;   //vendria a ser si es bajo, moderado o alto

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true) //aca le digo a JPA, quiero en perfiles una columna llamada usuario_id que sea una foranea de usuarios
    private Usuario usuario;
}
