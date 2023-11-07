package com.example.BackendSAF.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "activo_fijo")
public class ActivoFijoDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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

    @Column(name = "Tipo_Activo_id")
    private Integer tipoActivoId;

    @Column(name = "Marca_id")
    private Integer marcaId;

    @Column(name = "Ubicacion_id")
    private Integer ubicacionId;

    @Column(name = "Personal_id")
    private Integer personalId;

    @Column(name = "Estado_id")
    private Integer estadoId;

    @Column(name = "Condicion_id")
    private Integer condicionId;

    @Column(name = "estado")
    private Boolean estado;
    @Column(name = "empresa_id")
    private Long empresaId;

    @ManyToOne
    @JoinColumn(name = "Tipo_Activo_id", insertable = false, updatable = false)
    private TipoActivoDao tipoActivo;

    @ManyToOne
    @JoinColumn(name = "Marca_id", insertable = false, updatable = false)
    private MarcaDao marca;

    @ManyToOne
    @JoinColumn(name = "Ubicacion_id", insertable = false, updatable = false)
    private UbicacionDao ubicacion;

    @ManyToOne
    @JoinColumn(name = "Personal_id", insertable = false, updatable = false)
    private PersonalDao personal;

    @ManyToOne
    @JoinColumn(name = "Estado_id", insertable = false, updatable = false)
    private EstadoDao estado_id;

    @ManyToOne
    @JoinColumn(name = "Condicion_id", insertable = false, updatable = false)
    private CondicionDao condicion;
    @ManyToOne
    @JoinColumn(name = "empresa_id", insertable = false, updatable = false)
    private EmpresaDao empresa;


    // Getters y Setters


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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getTipoActivoId() {
        return tipoActivoId;
    }

    public void setTipoActivoId(Integer tipoObjetoId) {
        this.tipoActivoId = tipoObjetoId;
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
    public Long getEmpresaId() {
        return empresaId;
    }
    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }
}
