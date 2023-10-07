package com.example.BackendSAF.dto;

public class UbicacionDto {
    private Long id;
    private String nombre;

    public UbicacionDto() {
    }

    public UbicacionDto(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y setters (opcional, pero recomendado)

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
}
