package com.example.BackendSAF.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "estadod")

public class EstadoDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "estado")
    private List<ActivoFijoDao> activosFijos;
    //Getters
    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

}
