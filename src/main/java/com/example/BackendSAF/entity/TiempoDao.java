package com.example.BackendSAF.entity;

import javax.persistence.*;

@Entity
@Table(name = "tiempo")
public class TiempoDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "mes")
    private String mes;

    @Column(name = "anio")
    private String anio;
    //Getters y setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getMes() {
        return mes;
    }
    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }
    public void setAnio(String anio) {
        this.anio = anio;
    }

}

