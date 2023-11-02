package com.example.BackendSAF.bl;

import com.example.BackendSAF.dto.EmpresaDto;
import com.example.BackendSAF.dto.LoginDto;
import com.example.BackendSAF.dto.RolDto;
import com.example.BackendSAF.entity.ActivoFijoDao;
import com.example.BackendSAF.entity.EmpresaDao;
import com.example.BackendSAF.entity.Repository.EmpresaRepository;
import com.example.BackendSAF.entity.Repository.RolRepository;
import com.example.BackendSAF.entity.Repository.UsuarioRepository;
import com.example.BackendSAF.entity.RolDao;
import com.example.BackendSAF.entity.UsuarioDao;
import com.example.BackendSAF.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioBl {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private RolRepository rolRepository;


    public UsuarioBl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public LoginDto login(String username, String password) throws AuthenticationException {
        UsuarioDao usuario=usuarioRepository.findByUserAndPassword(username, password);
        if (usuario == null) {
            // Lanza una excepción si la autenticación falla
            throw new AuthenticationException("Credenciales de inicio de sesión incorrectas");
        }
        EmpresaDao empresa=empresaRepository.findById(usuario.getIdEmpresa()).orElse(null);
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

}
