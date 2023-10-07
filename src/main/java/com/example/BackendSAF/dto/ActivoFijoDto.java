package com.example.BackendSAF.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ActivoFijoDto {

    private String nombre;
    private BigDecimal valor;
    private String fechaCompra;
    private String descripcion;
    private Integer porcentajeDepreciacion;
    private Integer tipoObjetoId;
    private Integer marcaId;
    private Integer ubicacionId;
    private Integer personalId;
    private Integer estadoId;
    private Integer condicionId;
    private Boolean estado;

    public ActivoFijoDto(String nombre, BigDecimal valor, String fechaCompra, String descripcion, Integer porcentajeDepreciacion, Integer tipoActivoId, Integer marcaId, Integer ubicacionId, Integer personalId, Integer estadoId, Integer condicionId, Boolean estado) {
    }

    // Constructores

    // Métodos setter para cada atributo


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
/*
    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
*/
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPorcentajeDepreciacion(Integer porcentajeDepreciacion) {
        this.porcentajeDepreciacion = porcentajeDepreciacion;
    }
/*
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
*/
    public void setTipoObjetoId(Integer tipoObjetoId) {
        this.tipoObjetoId = tipoObjetoId;
    }

    public void setMarcaId(Integer marcaId) {
        this.marcaId = marcaId;
    }

    public void setUbicacionId(Integer ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public void setPersonalId(Integer personalId) {
        this.personalId = personalId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }

    public void setCondicionId(Integer condicionId) {
        this.condicionId = condicionId;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    // Resto de constructores, getters y otros métodos
}


