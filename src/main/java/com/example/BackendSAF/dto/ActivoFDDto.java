package com.example.BackendSAF.dto;
import java.math.BigDecimal;
import java.util.Date;

public class ActivoFDDto {
    private Long id;
    private Long idActivo;
    private String nombre;
    private BigDecimal valor;
    private String fechaCompra;
    private String descripcion;
    private String fechaRegistro;
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
    private String fechaDepreciacion;
    private String usuario;
    private String mes;
    private String anio;
    private BigDecimal mesesRestantes;
    //Constructor
    public ActivoFDDto() {
    }
    public ActivoFDDto(Long id, Long idActivo, String nombre, BigDecimal valor, String fechaCompra, String descripcion, String fechaRegistro, String tipoActivoNombre, String marcaNombre, String calle, String avenida, String bloqueNombre, String ciudadNombre, String personalNombre, String estadoNombre, String condicionNombre, Integer porcentajeDepreciacion, BigDecimal valorDepreciacion, BigDecimal valorActual, String fechaDepreciacion, String usuario, String mes, String anio, BigDecimal mesesRestantes) {
        this.id = id;
        this.idActivo = idActivo;
        this.nombre = nombre;
        this.valor = valor;
        this.fechaCompra = fechaCompra;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
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
        this.fechaDepreciacion = fechaDepreciacion;
        this.usuario = usuario;
        this.mes = mes;
        this.anio = anio;
        this.mesesRestantes = mesesRestantes;
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

    public String getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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

    public String getFechaDepreciacion() {
        return fechaDepreciacion;
    }
    public void setFechaDepreciacion(String fechaDepreciacion) {
        this.fechaDepreciacion = fechaDepreciacion;
    }

    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMes() {
        return mes;
    }
    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }
    public void setAnio(String anio) {
        this.anio = anio;
    }

    public BigDecimal getMesesRestantes() {
        return mesesRestantes;
    }
    public void setMesesRestantes(BigDecimal mesesRestantes) {
        this.mesesRestantes = mesesRestantes;
    }

}

