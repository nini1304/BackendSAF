package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.ActivoFijoDDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivoFijoDRepository extends JpaRepository<ActivoFijoDDao, Long> {
    //Obtener activos fijos por idempresa e idtiempo
    @Query("SELECT a FROM ActivoFijoDDao a WHERE a.empresaId= :idEmpresa AND a.idTiempo= :idTiempo")
    List<ActivoFijoDDao> findByIdEmpresaAndIdTiempo(Long idEmpresa, Long idTiempo);


}
