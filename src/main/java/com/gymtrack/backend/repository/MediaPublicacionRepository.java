package com.gymtrack.backend.repository;

import com.gymtrack.backend.model.MediaPublicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.attribute.standard.Media;
import java.util.Optional;

@Repository
public interface MediaPublicacionRepository extends JpaRepository<MediaPublicacion, Long> {

    Optional<MediaPublicacion> findByIdAndPublicacionId(Long id,
                                                        Long publicacionId);

}
