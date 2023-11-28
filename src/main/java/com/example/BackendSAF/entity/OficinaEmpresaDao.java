package com.example.BackendSAF.entity;

import javax.persistence.*;

@Entity
@Table(name = "oficina_empresa")
public class OficinaEmpresaDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "oficina_id", insertable = false, updatable = false)
    private Long oficinaId;
    @Column(name = "empresa_id", insertable = false, updatable = false)
    private Long empresaId;
    @ManyToOne
    @JoinColumn(name = "oficina_id")
    private EstadoDao oficina;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private EmpresaDao empresa;
    //Getters y setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpresaId() {
        return empresaId;
    }
    public void setEmpresaId(Long id) {
        this.empresaId = id;
    }

    public Long getOficinaId() {
        return oficinaId;
    }
    public void setOficinaId(Long id) {
        this.oficinaId = id;
    }

    public EstadoDao getOficina() {
        return oficina;
    }
    public void setOficina(EstadoDao oficina) {
        this.oficina = oficina;
    }

    public EmpresaDao getEmpresa() {
        return empresa;
    }
    public void setEmpresa(EmpresaDao empresa) {
        this.empresa = empresa;
    }
}
