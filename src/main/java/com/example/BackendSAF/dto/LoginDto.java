package com.example.BackendSAF.dto;
public class LoginDto {
    private Long idUsuario;
    private String nombre;
    private Long idRol;
    private Long idEmpresa;
    private String nombreEmpresa;
    private String logo;

    public LoginDto(Long idUsuario,String nombre, Long idRol, Long idEmpresa, String nombreEmpresa, String logo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.idRol = idRol;
        this.idEmpresa = idEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.logo = logo;
    }

    // Agrega getters y setters según sea necesario
    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }
    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}

