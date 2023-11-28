package com.example.BackendSAF.entity;

import javax.persistence.*;

@Entity
@Table(name = "personal_empresa")
public class PersonalEmpresaDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "personal_id", insertable = false, updatable = false)
    private Long personalId;
    @Column(name = "empresa_id", insertable = false, updatable = false)
    private Long empresaId;

    @ManyToOne
    @JoinColumn(name = "personal_id")
    private PersonalDao personal;

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

    public Long getPersonalId() {
        return personalId;
    }
    public void setPersonalId(Long id) {
        this.personalId = id;
    }

    public Long getEmpresaId() {
        return empresaId;
    }
    public void setEmpresaId(Long id) {
        this.empresaId = id;
    }

    public PersonalDao getPersonal() {
        return personal;
    }
    public void setPersonal(PersonalDao personal) {
        this.personal = personal;
    }

    public EmpresaDao getEmpresa() {
        return empresa;
    }
    public void setEmpresa(EmpresaDao empresa) {
        this.empresa = empresa;
    }

}
