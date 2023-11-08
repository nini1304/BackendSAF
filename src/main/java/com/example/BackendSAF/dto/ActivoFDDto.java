package com.example.BackendSAF.dto;
import java.math.BigDecimal;
import java.util.Date;

public class ActivoFDDto {
    private Long id;
    private Long idActivo;
    private String nombre;
    private BigDecimal valor;
    private Date fechaCompra;
    private String descripcion;
    private Date fechaRegistro;
    private Integer tipoActivoId;
    private Integer marcaId;
    private Integer ubicacionId;
    private Integer personalId;
    private Integer estadoId;
    private Integer condicionId;
    private Boolean estado;
    private Long empresaId;
    private String fechaD;
    private String usuario;
    private Long idTiempo;
    //Constructor
    public ActivoFDDto() {
    }
    public ActivoFDDto(Long id, Long idActivo, String nombre, BigDecimal valor, Date fechaCompra, String descripcion, Date fechaRegistro, Integer tipoActivoId, Integer marcaId, Integer ubicacionId, Integer personalId, Integer estadoId, Integer condicionId, Boolean estado, Long empresaId, String fechaD, String usuario, Long idTiempo) {
        this.id = id;
        this.idActivo = idActivo;
        this.nombre = nombre;
        this.valor = valor;
        this.fechaCompra = fechaCompra;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
        this.tipoActivoId = tipoActivoId;
        this.marcaId = marcaId;
        this.ubicacionId = ubicacionId;
        this.personalId = personalId;
        this.estadoId = estadoId;
        this.condicionId = condicionId;
        this.estado = estado;
        this.empresaId = empresaId;
        this.fechaD = fechaD;
        this.usuario = usuario;
        this.idTiempo = idTiempo;
    }

    // Agrega getters y setters para cada atributo
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
    public void setTipoActivoId(Integer tipoActivoId) {
        this.tipoActivoId = tipoActivoId;
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

    public String getFechaD() {
        return fechaD;
    }
    public void setFechaD(String fechaD) {
        this.fechaD = fechaD;
    }

    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Long getIdTiempo() {
        return idTiempo;
    }
    public void setIdTiempo(Long idTiempo) {
        this.idTiempo = idTiempo;
    }
}

