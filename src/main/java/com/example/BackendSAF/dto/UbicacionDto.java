package com.example.BackendSAF.dto;
public class UbicacionDto {
    private Long id;
    private String calle;
    private String avenida;
    private Integer bloqueId;
    private Integer ciudadId;

    public UbicacionDto() {
    }

    public UbicacionDto(Long id, String calle, String avenida, Integer bloqueId, Integer ciudadId) {
        this.id = id;
        this.calle = calle;
        this.avenida = avenida;
        this.bloqueId = bloqueId;
        this.ciudadId = ciudadId;
    }

    // Getters y setters (opcional, pero recomendado)

    public Integer getId() {
        return Math.toIntExact(id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAvenida() {
        return avenida;
    }

    public void setAvenida(String avenida) {
        this.avenida = avenida;
    }

    public Integer getBloqueId() {
        return bloqueId;
    }

    public void setBloqueId(Integer bloqueId) {
        this.bloqueId = bloqueId;
    }

    public Integer getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(Integer ciudadId) {
        this.ciudadId = ciudadId;
    }
}
