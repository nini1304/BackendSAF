package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.PersonalDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonalRepository extends JpaRepository<PersonalDao, Long> {
    // Consulta personalizada para obtener el nombre de un Personal por su ID
    @Query("SELECT p.nombre FROM PersonalDao p WHERE p.id = :id")
    String getPersonalNombreById(Long id);
    // Agrega otras consultas personalizadas según sea necesario
}

