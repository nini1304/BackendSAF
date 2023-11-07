package com.example.BackendSAF.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "empresa")
public class EmpresaDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmpresa")
    private Long idEmpresa;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "logo")
    private String logo;

    @OneToMany(mappedBy = "empresa")
    private List<UsuarioEmpresaDao> usuarios;

    //Getters y setters
    public Long getIdEmpresa() {
        return idEmpresa;
    }
    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLogo() {
        return logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<UsuarioEmpresaDao> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(List<UsuarioEmpresaDao> usuarios) {
        this.usuarios = usuarios;
    }

}

