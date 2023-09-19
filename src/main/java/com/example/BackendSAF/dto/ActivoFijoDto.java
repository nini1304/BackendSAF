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
    private Date fechaRegistro;
    private Integer tipoObjetoId;
    private Integer marcaId;
    private Integer ubicacionId;
    private Integer personalId;
    private Integer estadoId;
    private Integer condicionId;
    private Boolean estado;

    // Constructores, getters y setters

    public ActivoFijoDto() {
    }

    public ActivoFijoDto(Long id, String nombre, BigDecimal valor, Date fechaCompra, String descripcion, Integer porcentajeDepreciacion, Date fechaRegistro, Integer tipoObjetoId, Integer marcaId, Integer ubicacionId, Integer personalId, Integer estadoId, Integer condicionId, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
        this.fechaCompra = fechaCompra;
        this.descripcion = descripcion;
        this.porcentajeDepreciacion = porcentajeDepreciacion;
        this.fechaRegistro = fechaRegistro;
        this.tipoObjetoId = tipoObjetoId;
        this.marcaId = marcaId;
        this.ubicacionId = ubicacionId;
        this.personalId = personalId;
        this.estadoId = estadoId;
        this.condicionId = condicionId;
        this.estado = estado;
    }

    // Getters y setters para cada propiedad

    // Resto de los getters y setters

    // Métodos getters y setters para cada propiedad
}

