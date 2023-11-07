package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.UsuarioEmpresaDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioEmpresaRepository extends JpaRepository<UsuarioEmpresaDao, Long> {
    //Encontrar todos por id de usuario e id de empresa
    @Query("SELECT ue FROM UsuarioEmpresaDao ue WHERE ue.usuario.idUsuario = :idUsuario AND ue.empresa.idEmpresa = :idEmpresa")
    UsuarioEmpresaDao findAllByIds(Long idUsuario, Long idEmpresa);
}
