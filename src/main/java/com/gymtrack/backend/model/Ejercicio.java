package com.gymtrack.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Builder
@Table(name = "ejercicios")
public class Ejercicio extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Column(length = 500)
    private String videoUrl;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ejercicios_grupos_musculares",
            joinColumns = @JoinColumn(name = "ejercicio_id"),
            inverseJoinColumns = @JoinColumn(name = "grupos_musculares_id")
    )
    @Builder.Default
    private Set<GrupoMuscular> gruposMusculares = new HashSet<>();
}
