package com.example.BackendSAF.bl;

import com.example.BackendSAF.dto.ACtivoFijoHListDto;
import com.example.BackendSAF.dto.ActivoFijoListDto;
import com.example.BackendSAF.entity.ActivoFijoDao;
import com.example.BackendSAF.entity.ActivoFijoHDao;
import com.example.BackendSAF.entity.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ActivoFijoHBl {
    HashMap<String,Object> datos;
    @Autowired
    private ActivoFijoRepository activofijorepository;
    @Autowired
    private CondicionRepository fijoRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private MarcaRepository marcaRepository;
    @Autowired
    private PersonalRepository personalRepository;
    @Autowired
    private TipoActivoRepository tipoActivoRepository;
    @Autowired
    private UbicacionRepository ubicacionRepository;
    @Autowired
    private ActivoFijoHRepository activoFijoHRepository; // Repositorio para la tabla histórica
    @Autowired
    private BloqueRepository bloqueRepository;
    @Autowired
    private CiudadRepository ciudadRepository;

    public List<ACtivoFijoHListDto> getAct() {
        List<ActivoFijoHDao> activoFijoH = activoFijoHRepository.findAll();
        List<ACtivoFijoHListDto> listAct = new ArrayList<>();
        //LOGGER.info("ActivoFijo: {}", activoFijo.get(0).getTipoActivoId());

        for (ActivoFijoHDao act : activoFijoH) {
            if (act.getEstado()){
                listAct.add(new ACtivoFijoHListDto(
                        act.getId(),
                        act.getIdActivo(),
                        act.getNombre(),
                        act.getValor(),
                        act.getFechaCompra(),
                        act.getDescripcion(),
                        tipoActivoRepository.getTipoActivoNombreById(Long.valueOf(act.getTipoActivoId())),
                        marcaRepository.getMarcaNombreById(Long.valueOf(act.getMarcaId())),
                        ubicacionRepository.getUbicacionCalleById(Long.valueOf(act.getUbicacionId())),
                        ubicacionRepository.getUbicacionAvenidaById(Long.valueOf(act.getUbicacionId())),
                        bloqueRepository.getBloqueNombreById(ubicacionRepository.getUbicacionBloqueIdById(Long.valueOf(act.getUbicacionId()))),
                        ciudadRepository.getCiudadNombreById(ubicacionRepository.getUbicacionCiudadIdById(Long.valueOf(act.getUbicacionId()))),
                        personalRepository.getPersonalNombreById(Long.valueOf(act.getPersonalId())),
                        estadoRepository.getEstadoNombreById(Long.valueOf(act.getEstadoId())),
                        fijoRepository.getCondicionNombreById(Long.valueOf(act.getCondicionId())),
                        activoFijoHRepository.getEventoNombreById(Long.valueOf(act.getId())),
                        activoFijoHRepository.getUsuarioNombreById(Long.valueOf(act.getId()))
                ));
            }
        }

        return listAct;
    }
}
