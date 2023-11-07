package com.example.BackendSAF.bl;

import com.example.BackendSAF.dto.*;
import com.example.BackendSAF.entity.*;
import com.example.BackendSAF.entity.Repository.EmpresaRepository;
import com.example.BackendSAF.entity.Repository.RolRepository;
import com.example.BackendSAF.entity.Repository.UsuarioEmpresaRepository;
import com.example.BackendSAF.entity.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioBl {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private UsuarioEmpresaRepository usuarioEmpresaRepository;
    @Autowired
    private RolRepository rolRepository;

    public Object list (int page, int size){
        return usuarioRepository.findAll(PageRequest.of(page, size));
    }
    public UsuarioBl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public LoginDto login(String username, String password, Long empId) throws AuthenticationException {
        UsuarioDao usuario=usuarioRepository.findByUserAndPassword(username, password);
        if (usuario == null) {
            // Lanza una excepción si la autenticación falla
            throw new AuthenticationException("Credenciales de inicio de sesión incorrectas");
        }
        UsuarioEmpresaDao userEmp=usuarioEmpresaRepository.findAllByIds(usuario.getIdUsuario(),empId);
        if (userEmp == null) {
            // Lanza una excepción si la autenticación falla
            throw new AuthenticationException("El usuario no esta asociado a esa empresa");
        }
        EmpresaDao empresa=empresaRepository.findById(empId).orElse(null);
        assert empresa != null;
        return new LoginDto(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getIdRol(),
                usuario.getIdEmpresa(),
                empresa.getNombre(),
                empresa.getLogo());

    }

    public UsuarioDto registrar (String nombre, String username, String password, Long idRol, Long idEmpresa) throws ParseException{
        UsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.setNombre(nombre);
        usuarioDao.setUser(username);
        usuarioDao.setPassword(password);
        usuarioDao.setIdRol(idRol);
        usuarioDao.setIdEmpresa(idEmpresa);
        usuarioRepository.save(usuarioDao);

        return new UsuarioDto(usuarioDao.getNombre(), usuarioDao.getUsername(), usuarioDao.getPassword(), usuarioDao.getIdRol(), usuarioDao.getIdEmpresa());
    }

    public List<EmpresaDto> getEmpresa(){
        List<EmpresaDao> empresa = empresaRepository.findAll();
        List<EmpresaDto> listEmpresa = empresa.stream()
                .map(empre -> new EmpresaDto(empre.getIdEmpresa(), empre.getNombre(), empre.getLogo()))
                .collect(Collectors.toList());
        return listEmpresa;
    }

    public List<RolDto> getRoles(){
        List<RolDao> rol = rolRepository.findAll();
        List<RolDto> listRol = rol.stream()
                .map(roles -> new RolDto(roles.getIdRol(), roles.getRol()))
                .collect(Collectors.toList());
        return listRol;
    }

    public List<UsuarioListDto> getUsuarios(){
        List<UsuarioDao> usuario = usuarioRepository.findAll();
        List<UsuarioListDto> listUsu = new ArrayList<>();
        for (UsuarioDao act: usuario) {
            listUsu.add(new UsuarioListDto(
                    act.getIdUsuario(),
                    act.getNombre(),
                    act.getUsername(),
                    act.getPassword(),
                    empresaRepository.getEmpresaNombreById(Long.valueOf(act.getIdEmpresa())),
                    rolRepository.getRolNombreById(Long.valueOf(act.getIdRol()))
            ));
        }
        return listUsu;
    }

}
