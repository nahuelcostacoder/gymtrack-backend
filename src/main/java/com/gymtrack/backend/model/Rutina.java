package com.gymtrack.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@Entity
@Table(name = "rutina")
public class Rutina extends EntidadAuditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false)
    private boolean publica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "rutina", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orden ASC")
    @Builder.Default
    private List<RutinaEjercicio> ejercicios = new ArrayList<>();

    //mappedBy rutina lo que hace es en vez de usar un joincolumn y poner el atributo, le decimos que usamos el
    //rutina que esta dentro de RutinaEjercicio. Por eso no se crea otra FK.
    //Y lo de orphanRemoval es que elimina al huerfano, es decir, si se elimina una rutina se elimina RutinaEjercicio ya que no tendria sentido que siga existiendo.


}
