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

    @OneToMany(mappedBy = "ubicacion")
    private List<ActivoFijoDao> activosFijos;
    //constructor sin nada
    public UbicacionDao() {
    }
    //constructor sin id
    public UbicacionDao(String nombre, String calle, String avenida, List<ActivoFijoDao> activosFijos) {
        this.nombre = nombre;
        this.calle = calle;
        this.avenida = avenida;
        this.activosFijos = activosFijos;
    }

    //constructor con id
    public UbicacionDao(Long id, String nombre, String calle, String avenida, List<ActivoFijoDao> activosFijos) {
        this.id = id;
        this.nombre = nombre;
        this.calle = calle;
        this.avenida = avenida;
        this.activosFijos = activosFijos;
    }

    //Getters setters
    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<ActivoFijoDao> getActivosFijos() {
        return activosFijos;
    }

    public void setActivosFijos(List<ActivoFijoDao> activosFijos) {
        this.activosFijos = activosFijos;
    }
}
