package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.ActivoFijoDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActivoFijoRepository extends JpaRepository<ActivoFijoDao, Long> {
    // Consultas personalizadas para obtener nombres de entidades relacionadas
    @Query("SELECT t.nombre FROM TipoActivoDao t WHERE t.id = :tipoActivoId")
    String getTipoActivoNombreById(Integer tipoActivoId);

    @Query("SELECT m.nombre FROM MarcaDao m WHERE m.id = :marcaId")
    String getMarcaNombreById(Integer marcaId);

    @Query("SELECT u.calle FROM UbicacionDao u WHERE u.id = :ubicacionId")
    String getUbicacionCalleById(Integer ubicacionId);

    @Query("SELECT u.avenida FROM UbicacionDao u WHERE u.id = :ubicacionId")
    String getUbicacionAvenidaById(Integer ubicacionId);

    @Query("SELECT b.nombre FROM BloqueDao b JOIN UbicacionDao u ON b.id = u.bloque.id WHERE u.id = :ubicacionId")
    String getUbicacionBloqueNombreById(Integer ubicacionId);

    @Query("SELECT c.nombre FROM CiudadDao c JOIN UbicacionDao u ON c.id = u.ciudad.id WHERE u.id = :ubicacionId")
    String getUbicacionCiudadNombreById(Integer ubicacionId);

    @Query("SELECT p.nombre FROM PersonalDao p WHERE p.id = :personalId")
    String getPersonalNombreById(Integer personalId);

    @Query("SELECT e.nombre FROM EstadoDao e WHERE e.id = :estadoId")
    String getEstadoNombreById(Integer estadoId);

    @Query("SELECT c.nombre FROM CondicionDao c WHERE c.id = :condicionId")
    String getCondicionNombreById(Integer condicionId);
}


