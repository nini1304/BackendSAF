package com.example.BackendSAF.entity;

import javax.persistence.*;

@Entity
@Table(name = "ciudad")
public class CiudadDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "Departamento_id")
    private DepartamentoDao departamento;

    // Constructores, getters y setters
    public CiudadDao() {
        // Constructor vacío
    }

    public CiudadDao(String nombre, DepartamentoDao departamento) {
        this.nombre = nombre;
        this.departamento = departamento;
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

    public DepartamentoDao getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoDao departamento) {
        this.departamento = departamento;
    }
}

