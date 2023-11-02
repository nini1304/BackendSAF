package com.example.BackendSAF.dto;

public class RolDto {
    private Long idRol;
    private String rol;

    public RolDto() {
    }

    public RolDto(Long idRol, String rol) {
        this.idRol = idRol;
        this.rol = rol;
    }

    // Getters y setters (opcional, pero recomendado)

    public Long getIdRol() {
        return idRol;
    }
    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
}
