package com.example.BackendSAF.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class ActivoFijoList2Dto {
    private Long id;
    private String nombre;
    private BigDecimal valor;
    private String fechaCompra;
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
    private Integer porcentajeDepreciacion;
    private BigDecimal valorDepreciacion;
    private BigDecimal valorActual;
    private BigDecimal mesesRestantes;

    public ActivoFijoList2Dto() {
    }
    public ActivoFijoList2Dto(Long id, String nombre, BigDecimal valor, String fechaCompra, String descripcion, String tipoActivoNombre, String marcaNombre, String calle, String avenida, String bloqueNombre, String ciudadNombre, String personalNombre, String estadoNombre, String condicionNombre, Integer porcentajeDepreciacion, BigDecimal valorDepreciacion, BigDecimal valorActual, BigDecimal mesesRestantes) {
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
        this.porcentajeDepreciacion = porcentajeDepreciacion;
        this.valorDepreciacion = valorDepreciacion;
        this.valorActual = valorActual;
        this.mesesRestantes = mesesRestantes;
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
    public String getFechaCompra() {
        return fechaCompra;
    }
    public void setFechaCompra(String fechaCompra) {
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
    public Integer getPorcentajeDepreciacion() {
        return porcentajeDepreciacion;
    }
    public void setPorcentajeDepreciacion(Integer porcentajeDepreciacion) {
        this.porcentajeDepreciacion = porcentajeDepreciacion;
    }
    public BigDecimal getValorDepreciacion() {
        return valorDepreciacion;
    }
    public void setValorDepreciacion(BigDecimal valorDepreciacion) {
        this.valorDepreciacion = valorDepreciacion;
    }
    public BigDecimal getValorActual() {
        return valorActual;
    }
    public void setValorActual(BigDecimal valorActual) {
        this.valorActual = valorActual;
    }
    public BigDecimal getMesesRestantes() {
        return mesesRestantes;
    }
    public void setMesesRestantes(BigDecimal mesesRestantes) {
        this.mesesRestantes = mesesRestantes;
    }



}
