package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.UbicacionDao;
import org.jetbrains.annotations.TestOnly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UbicacionRepository extends JpaRepository<UbicacionDao, Long> {
    // Consulta personalizada para obtener el nombre de una Ubicación por su ID
    @Query("SELECT u.calle FROM UbicacionDao u WHERE u.id = :id")
    String getUbicacionCalleById(Long id);

    @Query("SELECT u.avenida FROM UbicacionDao u WHERE u.id = :id")
    String getUbicacionAvenidaById(Long id);

    // Consulta personalizada para obtener el id del bloque de una Ubicación por su ID
    @Query("SELECT u.bloque.id FROM UbicacionDao u WHERE u.id = :id")
    Long getUbicacionBloqueIdById(Long id);

    // Consulta personalizada para obtener el id de la ciudad de una Ubicación por su ID
    @Query("SELECT u.ciudad.id FROM UbicacionDao u WHERE u.id = :id")
    Long getUbicacionCiudadIdById(Long id);
}

