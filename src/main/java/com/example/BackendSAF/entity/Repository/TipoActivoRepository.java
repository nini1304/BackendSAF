package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.TipoActivoDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TipoActivoRepository extends JpaRepository<TipoActivoDao, Long> {
       // Consulta personalizada para obtener el nombre de un TipoActivo por su ID
        @Query("SELECT t.nombre FROM TipoActivoDao t WHERE t.id = :id")
        String getTipoActivoNombreById(Long id);
        @Query("SELECT t.porcentajeDepreciacion FROM TipoActivoDao t WHERE t.id = :id")
        int getPorcentajeDepreciacionById(Long id);
        @Query("SELECT t.vidaUtil FROM TipoActivoDao t WHERE t.id = :id")
        int getVidaUtilById(Long id);

}
