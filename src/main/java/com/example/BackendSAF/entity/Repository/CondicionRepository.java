package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.CondicionDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CondicionRepository extends JpaRepository<CondicionDao, Long> {
    // Consulta personalizada para obtener el nombre de una Condición por su ID
    @Query("SELECT c.nombre FROM CondicionDao c WHERE c.id = :id")
    String getCondicionNombreById(Long id);
    // Agrega otras consultas personalizadas según sea necesario
}

