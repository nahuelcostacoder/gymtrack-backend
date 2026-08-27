package com.gymtrack.backend.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
@Table(name = "media_publicaciones")
@Entity
public class MediaPublicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publicacion_id", nullable = false)
    private Publicacion publicacion;

    @Column(nullable = false, length = 500)
    private String url;

    @Column(nullable = false)
    private String publicId;

    @Enumerated(EnumType.STRING)
    private TipoMedia tipo;

}
