package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.MarcaDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MarcaRepository extends JpaRepository<MarcaDao, Long> {
    // Consulta personalizada para obtener el nombre de una Marca por su ID
    @Query("SELECT m.nombre FROM MarcaDao m WHERE m.id = :id")
    String getMarcaNombreById(Long id);
}

