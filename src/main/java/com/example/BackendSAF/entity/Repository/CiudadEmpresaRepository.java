package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.CiudadDao;
import com.example.BackendSAF.entity.CiudadEmpresaDao;
import com.example.BackendSAF.entity.EmpresaDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CiudadEmpresaRepository extends JpaRepository<CiudadEmpresaDao,Long> {
    @Query("SELECT ce.ciudadId FROM CiudadEmpresaDao ce WHERE ce.empresaId = :empresaId")
    List<Long> findCiudadesByEmpresaId(Long empresaId);
}
