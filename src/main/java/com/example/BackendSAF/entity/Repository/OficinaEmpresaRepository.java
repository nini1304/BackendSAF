package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.OficinaEmpresaDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OficinaEmpresaRepository extends JpaRepository<OficinaEmpresaDao, Long> {
    @Query("SELECT be.oficinaId FROM OficinaEmpresaDao be WHERE be.empresaId = :empresaId")
    List<Long> findOficinasByEmpresaId(Long empresaId);
}
