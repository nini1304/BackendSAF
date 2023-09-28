package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.ActivoFijoDao;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ActivoFijoRepository extends PagingAndSortingRepository<ActivoFijoDao, Long> {
    //boolean existsById(Long id);
    // Puedes agregar métodos personalizados de consulta aquí si es necesario
}

