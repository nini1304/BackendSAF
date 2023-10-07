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
    //Getters
    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }


}
