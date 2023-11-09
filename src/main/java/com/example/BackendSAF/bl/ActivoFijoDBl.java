package com.example.BackendSAF.bl;

import com.example.BackendSAF.dto.ActivoFDDto;
import com.example.BackendSAF.dto.EmpresaDto;
import com.example.BackendSAF.dto.TiempoDto;
import com.example.BackendSAF.entity.ActivoFijoDDao;
import com.example.BackendSAF.entity.EmpresaDao;
import com.example.BackendSAF.entity.Repository.ActivoFijoDRepository;
import com.example.BackendSAF.entity.Repository.TiempoRepository;
import com.example.BackendSAF.entity.TiempoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivoFijoDBl {
    @Autowired
    private ActivoFijoDRepository activofijodrepository;
    @Autowired
    private TiempoRepository tiemporepository;

    public List<ActivoFDDto> getActD(Long idEmpresa, Long idTiempo) {
        List<ActivoFijoDDao> activoD = activofijodrepository.findByIdEmpresaAndIdTiempo(idEmpresa, idTiempo);
        String mes = tiemporepository.findMesById(idTiempo);
        String anio = tiemporepository.findAnioById(idTiempo);
        List<ActivoFDDto> listAD = activoD.stream()
                .map(acD -> new ActivoFDDto(
                        acD.getId(),
                        acD.getIdActivo(),
                        acD.getNombre(),
                        acD.getValor(),
                        acD.getFechaCompra(),
                        acD.getDescripcion(),
                        acD.getFechaRegistro(),
                        acD.getTipoActivoNombre(),
                        acD.getMarcaNombre(),
                        acD.getCalle(),
                        acD.getAvenida(),
                        acD.getBloqueNombre(),
                        acD.getCiudadNombre(),
                        acD.getPersonalNombre(),
                        acD.getEstadoNombre(),
                        acD.getCondicionNombre(),
                        acD.getPorcentajeDepreciacion(),
                        acD.getValorDepreciacion(),
                        acD.getValorActual(),
                        acD.getFechaD(),
                        acD.getUsuario(),
                        mes,
                        anio

                )).collect(Collectors.toList());

        return listAD;
    }
    public List<TiempoDto> getTie(){
        List<TiempoDao> tiempo= tiemporepository.findAll();
        List<TiempoDto> listT= tiempo.stream()
                .map(tie-> new TiempoDto(
                        tie.getId(),
                        tie.getMes(),
                        tie.getAnio()
                )).collect(Collectors.toList());
        return listT;
    }
}
