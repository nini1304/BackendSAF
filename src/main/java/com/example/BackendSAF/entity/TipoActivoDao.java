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

    @Column(name = "porcentaje_depreciacion")
    private int porcentajeDepreciacion;

    @OneToMany(mappedBy = "tipoActivo")
    private List<ActivoFijoDao> activosFijos;

    // Getters y setters (incluyendo para el nuevo campo)
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPorcentajeDepreciacion() {
        return porcentajeDepreciacion;
    }

    public void setPorcentajeDepreciacion(int porcentajeDepreciacion) {
        this.porcentajeDepreciacion = porcentajeDepreciacion;
    }
}
