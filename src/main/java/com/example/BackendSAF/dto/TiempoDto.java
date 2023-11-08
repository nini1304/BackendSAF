package com.example.BackendSAF.dto;

public class TiempoDto {
    private Long id;
    private String mes;
    private String anio;
    //Constructor
    public TiempoDto() {
    }
    public TiempoDto(Long id, String mes, String anio) {
        this.id = id;
        this.mes = mes;
        this.anio = anio;
    }
    // Agrega getters y setters
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
