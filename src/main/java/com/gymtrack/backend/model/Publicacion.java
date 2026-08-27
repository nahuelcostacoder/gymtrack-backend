package com.gymtrack.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@Table(name = "publicaciones")
@Entity
public class Publicacion extends EntidadAuditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String contenido;

    @Column(length = 500)
    private String imagenUrl;

    @OneToMany(
            mappedBy = "publicacion", //la fk esta en media
            cascade =  CascadeType.ALL, //si modifico algo aca, ej borro publi, que se borre tmb mediapublicacion asociados
            orphanRemoval = true
    )
    private List<MediaPublicacion> archivos = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "entrenamiento_id", nullable = false, unique = true)
    private Entrenamiento entrenamiento;

}
