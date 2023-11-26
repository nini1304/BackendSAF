package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.TiempoDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TiempoRepository extends JpaRepository<TiempoDao, Long> {
    //TiempoDao findByMesAndAnio(String mes, String anio);
    @Query("SELECT t.id FROM TiempoDao t WHERE t.mes = :mes AND t.anio = :anio")
    TiempoDao findByMesAndAnio(String mes, String anio);
    //Obtener mes y año por id
    @Query("SELECT t.mes FROM TiempoDao t WHERE t.id = :id")
    String findMesById(Long id);
    @Query("SELECT t.anio FROM TiempoDao t WHERE t.id = :id")
    String findAnioById(Long id);
    //Obtener por Lista de tiempos por idEmpresa
    @Query("SELECT t FROM TiempoDao t WHERE t.empresaId = :idEmpresa")
    List<TiempoDao> findByIdEmpresa(Long idEmpresa);
    @Query("SELECT t FROM TiempoDao t WHERE t.mes = :mes AND t.anio = :anio AND t.empresaId = :empresaId")
    TiempoDao findByMesAndAnioAndEmpresaId(String mes, String anio, Long empresaId);
}
