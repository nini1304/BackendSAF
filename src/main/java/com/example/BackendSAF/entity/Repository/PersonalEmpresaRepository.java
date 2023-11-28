package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.PersonalDao;
import com.example.BackendSAF.entity.PersonalEmpresaDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonalEmpresaRepository extends JpaRepository<PersonalEmpresaDao,Long> {
    @Query("SELECT pe.personalId FROM PersonalEmpresaDao pe WHERE pe.empresaId = :empresaId")
    List<Long> findPersonalIdByEmpresaId(Long empresaId);
}
