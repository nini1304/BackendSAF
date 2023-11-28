package com.example.BackendSAF.entity;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuario")
public class UsuarioDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Long idUsuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "idRol")
    private Long idRol;
    @Column(name = "status")
    private Boolean status=true;

    @ManyToOne
    @JoinColumn(name = "idRol", insertable = false, updatable = false)
    private RolDao rol;

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioEmpresaDao> empresas;

    // Getters y setters
    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }
    public void setUser(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Long getIdRol() {
        return idRol;
    }
    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public RolDao getRol() {
        return rol;
    }
    public void setRol(RolDao rol) {
        this.rol = rol;
    }

    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }



}
