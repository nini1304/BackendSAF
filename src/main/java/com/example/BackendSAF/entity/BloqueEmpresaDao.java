package com.example.BackendSAF.entity;

import javax.persistence.*;

@Entity
@Table(name="bloque_empresa")
public class BloqueEmpresaDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bloque_id", insertable = false, updatable = false)
    private Long bloqueId;
    @Column(name = "empresa_id", insertable = false, updatable = false)
    private Long empresaId;
    @ManyToOne
    @JoinColumn(name = "bloque_id")
    private BloqueDao bloque;

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

    public Long getEmpresaId() {
        return empresaId;
    }
    public void setEmpresaId(Long id) {
        this.empresaId = id;
    }
    public Long getBloqueId() {
        return bloqueId;
    }
    public void setBloqueId(Long id) {
        this.bloqueId = id;
    }
    public BloqueDao getBloque() {
        return bloque;
    }
    public void setBloque(BloqueDao bloque) {
        this.bloque = bloque;
    }
    public EmpresaDao getEmpresa() {
        return empresa;
    }
    public void setEmpresa(EmpresaDao empresa) {
        this.empresa = empresa;
    }

}
