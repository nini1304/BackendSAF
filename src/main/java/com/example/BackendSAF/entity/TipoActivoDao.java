package com.example.BackendSAF.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipo_activo")

public class TipoActivoDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "tipoActivo")
    private List<ActivoFijoDao> activosFijos;

}
