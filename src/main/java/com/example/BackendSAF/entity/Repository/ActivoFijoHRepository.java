package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.ActivoFijoHDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivoFijoHRepository extends JpaRepository<ActivoFijoHDao, Long> {
    @Query("Select a.evento from ActivoFijoHDao a where a.id= :activoFijoHId")
    String getEventoNombreById(Long activoFijoHId);
    @Query("Select a.usuario from ActivoFijoHDao a where a.id= :activoFijoHId")
    String getUsuarioNombreById(Long activoFijoHId);
    // Puedes agregar métodos personalizados si es necesario
}

