package com.example.BackendSAF.entity;

import javax.persistence.*;

@Entity
@Table(name = "bloque")
public class BloqueDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "nro_piso")
    private int nroPiso;

    // Constructores, getters y setters
    public BloqueDao() {
        // Constructor vacío
    }

    public BloqueDao(String nombre, int nroPiso) {
        this.nombre = nombre;
        this.nroPiso = nroPiso;
    }

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

    public int getNroPiso() {
        return nroPiso;
    }

    public void setNroPiso(int nroPiso) {
        this.nroPiso = nroPiso;
    }
}

