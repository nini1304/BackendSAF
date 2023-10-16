package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.BloqueDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloqueRepository extends JpaRepository<BloqueDao, Long> {
    // Puedes agregar métodos personalizados si es necesario
}
