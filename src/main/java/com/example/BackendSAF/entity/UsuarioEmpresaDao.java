package com.example.BackendSAF.entity;
import javax.persistence.*;
@Entity
@Table(name = "usuario_empresa")
public class UsuarioEmpresaDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioDao usuario;

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

    public UsuarioDao getUsuario() {
        return usuario;
    }
    public void setUsuario(UsuarioDao usuario) {
        this.usuario = usuario;
    }

    public EmpresaDao getEmpresa() {
        return empresa;
    }
    public void setEmpresa(EmpresaDao empresa) {
        this.empresa = empresa;
    }

}

