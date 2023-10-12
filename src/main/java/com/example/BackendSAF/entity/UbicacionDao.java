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

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "calle")
    private String calle;

    @Column(name = "avenida")
    private String avenida;

    @ManyToOne
    @JoinColumn(name = "bloque_id")
    private BloqueDao bloque;

    @ManyToOne
    @JoinColumn(name = "ciudad_id")
    private CiudadDao ciudad;

    @OneToMany(mappedBy = "ubicacion")
    private List<ActivoFijoDao> activosFijos;

    // Constructor sin nada
    public UbicacionDao() {
    }

    // Constructor sin id
    public UbicacionDao(String nombre, String calle, String avenida, BloqueDao bloque, CiudadDao ciudad, List<ActivoFijoDao> activosFijos) {
        this.nombre = nombre;
        this.calle = calle;
        this.avenida = avenida;
        this.bloque = bloque;
        this.ciudad = ciudad;
        this.activosFijos = activosFijos;
    }

    // Constructor con id
    public UbicacionDao(Long id, String nombre, String calle, String avenida, BloqueDao bloque, CiudadDao ciudad, List<ActivoFijoDao> activosFijos) {
        this.id = id;
        this.nombre = nombre;
        this.calle = calle;
        this.avenida = avenida;
        this.bloque = bloque;
        this.ciudad = ciudad;
        this.activosFijos = activosFijos;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public void setBloque(BloqueDao bloque) {
        this.bloque = bloque;
    }

    public CiudadDao getCiudad() {
        return ciudad;
    }

    public void setCiudad(CiudadDao ciudad) {
        this.ciudad = ciudad;
    }

    public List<ActivoFijoDao> getActivosFijos() {
        return activosFijos;
    }

    public void setActivosFijos(List<ActivoFijoDao> activosFijos) {
        this.activosFijos = activosFijos;
    }
}
