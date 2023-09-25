package com.example.BackendSAF.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "personal")

public class PersonalDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ci")
    private String ci;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "celular")
    private Long celular;

    @OneToMany(mappedBy = "personal")
    private List<ActivoFijoDao> activosFijos;
}
