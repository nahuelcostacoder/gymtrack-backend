package com.gymtrack.backend.service;

import com.gymtrack.backend.dto.GrupoMuscularDTO.ActualizarGrupoMuscularDTO;
import com.gymtrack.backend.dto.GrupoMuscularDTO.CrearGrupoMuscularDTO;
import com.gymtrack.backend.dto.GrupoMuscularDTO.GrupoMuscularDTO;
import com.gymtrack.backend.model.GrupoMuscular;

import java.util.List;

public interface GrupoMuscularService {

    List<GrupoMuscularDTO> listar();
    GrupoMuscularDTO buscarPorId(Long id);
    GrupoMuscularDTO crear(CrearGrupoMuscularDTO dto);
    GrupoMuscularDTO actualizar(Long id, ActualizarGrupoMuscularDTO dto);
    void eliminar(Long id);

}
