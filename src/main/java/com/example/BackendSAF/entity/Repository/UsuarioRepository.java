package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.UsuarioDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<UsuarioDao, Long> {
    // Función personalizada para buscar un usuario por su nombre de usuario y contraseña
    @Query("SELECT u FROM UsuarioDao u WHERE u.username = :username AND u.password = :password")
    UsuarioDao findByUserAndPassword(@Param("username") String username, @Param("password") String password);

}

