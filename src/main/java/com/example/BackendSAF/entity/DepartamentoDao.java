package com.example.BackendSAF.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "departamento")
public class DepartamentoDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    // Puedes añadir la relación OneToMany si es necesario
    // @OneToMany(mappedBy = "departamento")
    // private List<ActivoFijoDao> activosFijos;

    // Constructores, getters y setters
    public DepartamentoDao() {
        // Constructor vacío
    }

    public DepartamentoDao(String nombre) {
        this.nombre = nombre;
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

    // Puedes agregar getters y setters para la relación OneToMany si es necesario
}

