package com.example.BackendSAF.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "activo_fijo_h")
public class ActivoFijoHDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_activo")
    private Long idActivo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "fecha_compra")
    private Date fechaCompra;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "tipo_activo_id")
    private Integer tipoActivoId;

    @Column(name = "marca_id")
    private Integer marcaId;

    @Column(name = "ubicacion_id")
    private Integer ubicacionId;

    @Column(name = "personal_id")
    private Integer personalId;

    @Column(name = "estado_id")
    private Integer estadoId;

    @Column(name = "condicion_id")
    private Integer condicionId;

    @Column(name = "estado")
    private Boolean estado;
    @Column(name = "evento")
    private String evento;
    @Column(name = "usuario")
    private String usuario;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdActivo() {
        return idActivo;
    }

    public void setIdActivo(Long idActivo) {
        this.idActivo = idActivo;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public Integer getTipoActivoId() {
        return tipoActivoId;
    }

    public Integer getMarcaId() {
        return marcaId;
    }

    public Integer getUbicacionId() {
        return ubicacionId;
    }

    public Integer getPersonalId() {
        return personalId;
    }

    public Integer getEstadoId() {
        return estadoId;
    }

    public Integer getCondicionId() {
        return condicionId;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setTipoActivoId(Integer tipoActivoId) {
        this.tipoActivoId = tipoActivoId;
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

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}

