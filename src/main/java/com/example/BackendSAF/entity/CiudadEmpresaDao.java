package com.example.BackendSAF.entity;

import javax.persistence.*;

@Entity
@Table(name="ciudad_empresa")
public class CiudadEmpresaDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ciudad_id", insertable = false, updatable = false)
    private Long ciudadId;
    @Column(name = "empresa_id", insertable = false, updatable = false)
    private Long empresaId;

    @ManyToOne
    @JoinColumn(name = "ciudad_id")
    private CiudadDao ciudad;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private EmpresaDao empresa;

    // Getters y setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCiudadId() {
        return ciudadId;
    }
    public void setCiudadId(Long id) {
        this.ciudadId = id;
    }
    public Long getEmpresaId() {
        return empresaId;
    }
    public void setEmpresaId(Long id) {
        this.empresaId = id;
    }
    public CiudadDao getCiudad() {
        return ciudad;
    }
    public void setCiudad(CiudadDao ciudad) {
        this.ciudad = ciudad;
    }
    public EmpresaDao getEmpresa() {
        return empresa;
    }
    public void setEmpresa(EmpresaDao empresa) {
        this.empresa = empresa;
    }

}
