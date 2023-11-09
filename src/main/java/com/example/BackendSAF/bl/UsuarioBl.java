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
                empId,
                empresa.getNombre(),
                empresa.getLogo());

    }

    public UsuarioDto registrar (String nombre, String username, String password, Long idRol, Long idEmpresa) throws ParseException{
        UsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.setNombre(nombre);
        usuarioDao.setUser(username);
        usuarioDao.setPassword(password);
        usuarioDao.setIdRol(idRol);
        usuarioRepository.save(usuarioDao);

        UsuarioEmpresaDao usuarioEmpresaDao = new UsuarioEmpresaDao();
        usuarioEmpresaDao.setUsuario(usuarioDao);

        EmpresaDao empresa = empresaRepository.findById(idEmpresa).orElse(null);
        if(empresa == null){
            throw new ParseException("No se encontro la empresa", 0);
        }else{
            usuarioDao.setIdEmpresa(idEmpresa);
            usuarioEmpresaDao.setEmpresa(empresa);
            usuarioEmpresaRepository.save(usuarioEmpresaDao);
        }
        usuarioEmpresaRepository.save(usuarioEmpresaDao);

        return new UsuarioDto(usuarioDao.getNombre(), usuarioDao.getUsername(), usuarioDao.getPassword(), usuarioDao.getIdRol(), idEmpresa);
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

    public List<UsuarioListDto> getUsuariosEmpresa(Long idEmpresa){
        List<UsuarioEmpresaDao> usuarioEmpresa = usuarioEmpresaRepository.findAllByEmpresaId(idEmpresa);
        List<UsuarioListDto> listUsu = new ArrayList<>();
        for (UsuarioEmpresaDao usu : usuarioEmpresa){
            listUsu.add(new UsuarioListDto(
                    usu.getUsuario().getIdUsuario(),
                    usu.getUsuario().getNombre(),
                    usu.getUsuario().getUsername(),
                    usu.getUsuario().getPassword(),
                    usu.getUsuario().getRol().getRol()
            ));
        }
        return listUsu;
    }
    public UsuarioDto actualizarUsuario(Long usuarioId, String nombre, String username, String password, Long idRol) throws ParseException {
        UsuarioDao usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario == null) {
            throw new ParseException("No se encontro el usuario", 0);
        }
        usuario.setNombre(nombre);
        usuario.setUser(username);
        usuario.setPassword(password);
        usuario.setIdRol(idRol);
        usuarioRepository.save(usuario);
        return new UsuarioDto(usuario.getNombre(), usuario.getUsername(), usuario.getPassword(), usuario.getIdRol(), usuario.getIdUsuario());
    }
}
