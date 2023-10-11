package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.ActivoFijoDao;
import com.example.BackendSAF.entity.CondicionDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collections;
import java.util.List;

public interface ActivoFijoRepository extends JpaRepository<ActivoFijoDao, Long> {
    /*
    public static List<CondicionDao> findAllCondicionDao() {
        // Lógica para obtener la lista de CondicionDao
        List<CondicionDao> condicionList = obtenerLista(); // Reemplaza esto con tu lógica real

        return condicionList != null ? condicionList : Collections.emptyList();
    }

     */
}

