package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.ActivoFijoDao;
import com.example.BackendSAF.entity.ActivoFijoHDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivoFijoHRepository extends JpaRepository<ActivoFijoHDao, Long> {
    @Query("Select a.evento from ActivoFijoHDao a where a.id= :activoFijoHId")
    String getEventoNombreById(Long activoFijoHId);
    @Query("Select a.usuario from ActivoFijoHDao a where a.id= :activoFijoHId")
    String getUsuarioNombreById(Long activoFijoHId);

    @Query("SELECT af FROM ActivoFijoHDao af WHERE af.empresaId = :idEmpresa")
    List<ActivoFijoHDao> findAllByIdEmpresa(Long idEmpresa);
}

