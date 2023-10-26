package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.EstadoDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EstadoRepository extends JpaRepository<EstadoDao, Long> {
    // Consulta personalizada para obtener el nombre de un Estado por su ID
    @Query("SELECT e.nombre FROM EstadoDao e WHERE e.id = :id")
    String getEstadoNombreById(Long id);
    // Agrega otras consultas personalizadas según sea necesario
}

