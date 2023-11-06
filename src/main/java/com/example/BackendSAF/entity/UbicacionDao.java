package com.example.BackendSAF.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ubicacion")
public class UbicacionDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "calle")
    private String calle;

    @Column(name = "avenida")
    private String avenida;

    @Column(name = "bloque_id")
    private Integer bloqueId;

    @Column(name = "ciudad_id")
    private Integer ciudadId;

    @ManyToOne
    @JoinColumn(name = "bloque_id", insertable = false, updatable = false)
    private BloqueDao bloque;

    @ManyToOne
    @JoinColumn(name = "ciudad_id", insertable = false, updatable = false)
    private CiudadDao ciudad;


    // Constructor sin nada
    public UbicacionDao() {
    }

    // Constructor sin id
    public UbicacionDao(String calle, String avenida, Integer bloqueId, Integer ciudadId) {
        this.calle = calle;
        this.avenida = avenida;
        this.bloqueId = bloqueId;
        this.ciudadId = ciudadId;
    }

    // Constructor con id
    public UbicacionDao(Long id, String calle, String avenida, Integer bloqueId, Integer ciudadId, List<ActivoFijoDao> activosFijos) {
        this.id = id;
        this.calle = calle;
        this.avenida = avenida;
        this.bloqueId = bloqueId;
        this.ciudadId = ciudadId;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAvenida() {
        return avenida;
    }

    public void setAvenida(String avenida) {
        this.avenida = avenida;
    }

    public BloqueDao getBloque() {
        return bloque;
    }
    public Integer getBloqueId() {
        return bloqueId;
    }

    public void setBloqueId(Integer bloqueId) {
        this.bloqueId = bloqueId;
    }

    public void setBloque(BloqueDao bloque) {
        this.bloque = bloque;
    }

    public CiudadDao getCiudad() {
        return ciudad;
    }
    public Integer getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(Integer ciudadId) {
        this.ciudadId = ciudadId;
    }

    public void setCiudad(CiudadDao ciudad) {
        this.ciudad = ciudad;
    }

//    public List<ActivoFijoDao> getActivosFijos() {
//        return activosFijos;
//    }
//
//    public void setActivosFijos(List<ActivoFijoDao> activosFijos) {
//        this.activosFijos = activosFijos;
//    }
}
