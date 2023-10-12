package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.ActivoFijoHDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivoFijoHRepository extends JpaRepository<ActivoFijoHDao, Long> {
    // Puedes agregar métodos personalizados si es necesario
}

