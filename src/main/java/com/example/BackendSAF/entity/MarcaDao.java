package com.example.BackendSAF.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "marca")

public class MarcaDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "marca")
    private List<ActivoFijoDao> activosFijos;
    //Getters
    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
}
