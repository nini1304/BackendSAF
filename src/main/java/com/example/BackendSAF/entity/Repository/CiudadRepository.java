package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.CiudadDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepository extends JpaRepository<CiudadDao, Long> {
    // Consulta personalizada para obtener el nombre de la ciudad de una Ubicación por su ID
    @Query("SELECT c.nombre FROM CiudadDao c WHERE c.id = :id")
    String getCiudadNombreById(Long id);
}

