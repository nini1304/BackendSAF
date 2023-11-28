package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.BloqueDao;
import com.example.BackendSAF.entity.BloqueEmpresaDao;
import com.example.BackendSAF.entity.CiudadDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BloqueEmpresaRepository extends JpaRepository<BloqueEmpresaDao,Long> {
    @Query("SELECT be.bloqueId FROM BloqueEmpresaDao be WHERE be.empresaId = :empresaId")
    List<Long> findBloquesByEmpresaId(Long empresaId);
}
