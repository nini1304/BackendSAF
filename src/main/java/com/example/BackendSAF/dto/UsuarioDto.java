package com.example.BackendSAF.dto;

public class UsuarioDto {
    private String nombre;
    private String username;
    private String password;
    private Long idRol;
    private Long idEmpresa;

    public UsuarioDto(String nombre, String username, String password, Long idRol, Long idEmpresa) {
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.idRol = idRol;
        this.idEmpresa = idEmpresa;
    }

    public UsuarioDto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
}
