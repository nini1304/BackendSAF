package com.example.BackendSAF.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ActivoFijoDto {

    private Long id;
    private String nombre;
    private BigDecimal valor;
    private Date fechaCompra;
    private String descripcion;
    private Integer porcentajeDepreciacion;
    private Integer tipoObjetoId;
    private Integer marcaId;
    private Integer ubicacionId;
    private Integer personalId;
    private Integer estadoId;
    private Integer condicionId;
    private Boolean estado;



    // Constructores
        //constructor sin el id
    public ActivoFijoDto(String nombre, BigDecimal valor, Date fechaCompra, String descripcion,
                         Integer porcentajeDepreciacion, Integer tipoActivoId, Integer marcaId,
                         Integer ubicacionId, Integer personalId, Integer estadoId, Integer condicionId,
                         Boolean estado) {
    }
        //constructor con el id

    // Métodos setter para cada atributo

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

    public Integer getPorcentajeDepreciacion() {
        return porcentajeDepreciacion;
    }

    public void setPorcentajeDepreciacion(Integer porcentajeDepreciacion) {
        this.porcentajeDepreciacion = porcentajeDepreciacion;
    }

    public Integer getTipoObjetoId() {
        return tipoObjetoId;
    }

    public void setTipoObjetoId(Integer tipoObjetoId) {
        this.tipoObjetoId = tipoObjetoId;
    }

    public Integer getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Integer marcaId) {
        this.marcaId = marcaId;
    }

    public Integer getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(Integer ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public Integer getPersonalId() {
        return personalId;
    }

    public void setPersonalId(Integer personalId) {
        this.personalId = personalId;
    }

    public Integer getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }

    public Integer getCondicionId() {
        return condicionId;
    }

    public void setCondicionId(Integer condicionId) {
        this.condicionId = condicionId;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


    // Resto de constructores, getters y otros métodos
}


