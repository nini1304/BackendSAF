package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.RolDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RolRepository extends JpaRepository<RolDao, Long> {
    // Consulta personalizada para obtener el nombre de un Rol por su ID
    @Query ("SELECT r.rol FROM RolDao r WHERE r.idRol = :id")
    String getRolNombreById(Long id);
}
