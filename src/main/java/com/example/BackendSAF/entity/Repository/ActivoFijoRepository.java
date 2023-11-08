package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.ActivoFijoDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivoFijoRepository extends JpaRepository<ActivoFijoDao, Long> {
    // Obtener todos por idEmpresa
    @Query("SELECT af FROM ActivoFijoDao af WHERE af.empresaId = :idEmpresa")
    List<ActivoFijoDao> findAllByIdEmpresa(Long idEmpresa);
}


