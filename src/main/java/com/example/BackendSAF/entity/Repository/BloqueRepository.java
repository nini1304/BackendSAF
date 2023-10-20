package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.BloqueDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BloqueRepository extends JpaRepository<BloqueDao, Long> {
    // Consulta personalizada para obtener el nombre del bloque de una Ubicación por su ID
    @Query("SELECT b.nombre FROM BloqueDao b WHERE b.id = :id")
    String getBloqueNombreById(Long id);
}
