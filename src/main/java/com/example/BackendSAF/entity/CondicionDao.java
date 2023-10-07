package com.example.BackendSAF.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "condicion")

public class CondicionDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "condicion")
    private List<ActivoFijoDao> activosFijos;

    //Getters y setters
    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
}
