package com.example.BackendSAF.dto;

public class BloqueDto {
    private Long id;
    private String nombre;

    public BloqueDto() {
    }

    public BloqueDto(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y setters (opcional, pero recomendado)

    public Integer getId() {
        return Math.toIntExact(id);
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
