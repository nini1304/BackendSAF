package com.example.BackendSAF.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "activo_fijo_d")
public class ActivoFijoDDao {
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

    @Column(name = "tipo_activo_nombre")
    private String tipoActivoNombre;

    @Column(name = "marca_nombre")
    private String marcaNombre;

    @Column(name = "calle")
    private String calle;

    @Column(name = "avenida")
    private String avenida;

    @Column(name = "bloque_nombre")
    private String bloqueNombre;

    @Column(name = "ciudad_nombre")
    private String ciudadNombre;

    @Column(name = "personal_nombre")
    private String personalNombre;

    @Column(name = "estado_nombre")
    private String estadoNombre;

    @Column(name = "condicion_nombre")
    private String condicionNombre;

    @Column(name = "porcentaje_depreciacion")
    private Integer porcentajeDepreciacion;

    @Column(name = "valor_depreciacion")
    private BigDecimal valorDepreciacion;

    @Column(name = "valor_actual")
    private BigDecimal valorActual;

    @Column(name = "empresa_id")
    private Long empresaId;
    @Column(name = "fecha_depreciacion")
    private Date fechaD;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "id_tiempo")
    private Long idTiempo;
    @Column(name = "meses_restantes")
    private Long mesesRestantes;
    @ManyToOne
    @JoinColumn(name = "id_tiempo", insertable = false, updatable = false)
    private TiempoDao tiempo;

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

    public Long getEmpresaId() {
        return empresaId;
    }
    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public Date getFechaD() {
        return fechaD;
    }
    public void setFechaD(Date fechaD) {
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

    public Long getMesesRestantes() {
        return mesesRestantes;
    }
    public void setMesesRestantes(Long mesesRestantes) {
        this.mesesRestantes = mesesRestantes;
    }
}
