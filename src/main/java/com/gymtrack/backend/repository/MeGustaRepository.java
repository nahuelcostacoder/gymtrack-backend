package com.gymtrack.backend.repository;

import com.gymtrack.backend.model.MeGusta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeGustaRepository extends JpaRepository<MeGusta, Long> {
}
