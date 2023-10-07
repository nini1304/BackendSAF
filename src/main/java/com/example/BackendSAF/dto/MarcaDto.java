package com.example.BackendSAF.dto;

public class MarcaDto {
    private Long id;
    private String nombre;

    // Constructor vacío (por si es necesario)
    public MarcaDto() {
    }

    // Constructor con parámetros
    public MarcaDto(Long id, String nombre) {
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
