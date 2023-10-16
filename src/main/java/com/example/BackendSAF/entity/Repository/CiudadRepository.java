package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.CiudadDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepository extends JpaRepository<CiudadDao, Long> {
    // Puedes agregar métodos personalizados si es necesario
}

