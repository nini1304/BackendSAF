package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.EmpresaDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmpresaRepository extends JpaRepository<EmpresaDao, Long> {
    //Consulta personalizada para obtener el nombre de una empresa por su ID
    @Query("SELECT e.nombre FROM EmpresaDao e WHERE e.idEmpresa = :id")
    String getEmpresaNombreById(Long id);
    //Obtener el logo de una empresa por su ID
    @Query("SELECT e.logo FROM EmpresaDao e WHERE e.idEmpresa = :id")
    String getEmpresaLogoById(Long id);


}
