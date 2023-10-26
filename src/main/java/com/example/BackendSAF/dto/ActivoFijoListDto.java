package com.example.BackendSAF.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ActivoFijoListDto {
    private Long id;
    private String nombre;
    private BigDecimal valor;
    private Date fechaCompra;
    private String descripcion;
    private String tipoActivoNombre;
    private String marcaNombre;
    private String calle;
    private String avenida;
    private String bloqueNombre;
    private String ciudadNombre;

    private String personalNombre;
    private String estadoNombre;
    private String condicionNombre;

    public ActivoFijoListDto() {
    }
    public ActivoFijoListDto(Long id, String nombre, BigDecimal valor, Date fechaCompra, String descripcion, String tipoActivoNombre, String marcaNombre, String calle, String avenida, String bloqueNombre, String ciudadNombre, String personalNombre, String estadoNombre, String condicionNombre) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
        this.fechaCompra = fechaCompra;
        this.descripcion = descripcion;
        this.tipoActivoNombre = tipoActivoNombre;
        this.marcaNombre = marcaNombre;
        this.calle = calle;
        this.avenida = avenida;
        this.bloqueNombre = bloqueNombre;
        this.ciudadNombre = ciudadNombre;
        this.personalNombre = personalNombre;
        this.estadoNombre = estadoNombre;
        this.condicionNombre = condicionNombre;
    }



    //Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public String getTipoActivoNombre() {
        return tipoActivoNombre;
    }
    public void setTipoActivoNombre(String tipoActivoNombre) {
        this.tipoActivoNombre = tipoActivoNombre;
    }
    public String getMarcaNombre() {
        return marcaNombre;
    }
    public void setMarcaNombre(String marcaNombre) {
        this.marcaNombre = marcaNombre;
    }
    public String getBloqueNombre() {
        return bloqueNombre;
    }
    public void setBloqueNombre(String bloqueNombre) {
        this.bloqueNombre = bloqueNombre;
    }
    public String getCiudadNombre() {
        return ciudadNombre;
    }
    public void setCiudadNombre(String ciudadNombre) {
        this.ciudadNombre = ciudadNombre;
    }
    public String getPersonalNombre() {
        return personalNombre;
    }
    public void setPersonalNombre(String personalNombre) {
        this.personalNombre = personalNombre;
    }
    public String getEstadoNombre() {
        return estadoNombre;
    }
    public void setEstadoNombre(String estadoNombre) {
        this.estadoNombre = estadoNombre;
    }
    public String getCondicionNombre() {
        return condicionNombre;
    }
    public void setCondicionNombre(String condicionNombre) {
        this.condicionNombre = condicionNombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public Date getFechaCompra() {
        return fechaCompra;
    }
    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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


}
