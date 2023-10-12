package com.example.BackendSAF.bl;

import com.example.BackendSAF.dto.*;
import com.example.BackendSAF.entity.*;
import com.example.BackendSAF.entity.Repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ActivoFijoBl {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivoFijoBl.class);

    @Value("http://localhost:1234/api/v1/activos-fijos/registrar")
    private String url;

    @Value("pass123")
    private String key;

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

    public Object list(int page, int size) {
        return activofijorepository.findAll(PageRequest.of(page, size));
    }

    public ActivoFijoDto registrar(String nombre,
                                   String valor,
                                   Date fechaCompra,
                                   String descripcion,
                                   Integer tipoActivoId,
                                   Integer marcaId,
                                   Integer ubicacionId,
                                   Integer personalId,
                                   Integer estadoId,
                                   Integer condicionId,
                                   Boolean estado) throws ParseException {
        // Si el rol ya existe, no se puede agregar


        ActivoFijoDao act = new ActivoFijoDao();
        act.setNombre(nombre);
        act.setValor(new BigDecimal(valor));
        act.setFechaCompra(new Date());
        //act.setFechaCompra(new Date());
        act.setDescripcion(descripcion);
        act.setFechaRegistro(new Date());
        act.setTipoActivoId(tipoActivoId);
        act.setMarcaId(marcaId);
        act.setUbicacionId(ubicacionId);
        act.setPersonalId(personalId);
        act.setEstadoId(estadoId);
        act.setCondicionId(condicionId);
        act.setEstado(estado);
        activofijorepository.save(act);
        // Guardar en la tabla histórica
        ActivoFijoHDao actH = new ActivoFijoHDao();
        actH.setNombre(nombre);
        actH.setValor(new BigDecimal(valor));
        actH.setFechaCompra(new Date());
        actH.setDescripcion(descripcion);
        actH.setFechaRegistro(new Date());
        actH.setTipoActivoId(tipoActivoId);
        actH.setMarcaId(marcaId);
        actH.setUbicacionId(ubicacionId);
        actH.setPersonalId(personalId);
        actH.setEstadoId(estadoId);
        actH.setCondicionId(condicionId);
        actH.setEstado(estado);
        activoFijoHRepository.save(actH); // Guardar en la tabla histórica

        return new ActivoFijoDto(act.getNombre(), act.getValor(), fechaCompra ,act.getDescripcion(), act.getTipoActivoId(), act.getMarcaId(), act.getUbicacionId(), act.getPersonalId(), act.getEstadoId(), act.getCondicionId(), act.getEstado());
    }
    public Date convertirStringADate(String fechaString) throws ParseException {
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.parse(fechaString);
    }
    // Esto son los get para todas las listas de componentes
    //getACt te envia la lista de todos los activos fijos
    public List<ActivoFijoDto> getAct() {
        List<ActivoFijoDao> activofijo=activofijorepository.findAll();

        List<ActivoFijoDto> listAct=activofijo.stream().
                map(act-> new ActivoFijoDto(act.getNombre(),act.getValor()
                        ,act.getFechaCompra(),act.getDescripcion(),act.getTipoActivoId()
                        ,act.getMarcaId(),act.getUbicacionId(),act.getPersonalId(),act.getEstadoId(),act.getCondicionId()
                        ,act.getEstado()))
                .collect(Collectors.toList());
        return listAct;

    }
    public List<CondicionDto> getCond() {
        List<CondicionDao> condicion = fijoRepository.findAll();

        List<CondicionDto> listConds = condicion.stream()
                .map(con -> new CondicionDto(con.getId(), con.getNombre()))
                .collect(Collectors.toList());

        return listConds;
    }
    public List<EstadoDto> getEst() {
        List<EstadoDao> estado = estadoRepository.findAll();

        List<EstadoDto> listEst = estado.stream()
                .map(est -> new EstadoDto(est.getId(), est.getNombre()))
                .collect(Collectors.toList());

        return listEst;
    }
    public List<MarcaDto> getMar() {
        List<MarcaDao> marca = marcaRepository.findAll();

        List<MarcaDto> listMar = marca.stream()
                .map(mar -> new MarcaDto(mar.getId(), mar.getNombre()))
                .collect(Collectors.toList());

        return listMar;
    }
    public List<PersonalDto> getPer() {
        List<PersonalDao> personal = personalRepository.findAll();

        List<PersonalDto> listPer = personal.stream()
                .map(per -> new PersonalDto(per.getId(), per.getNombre()))
                .collect(Collectors.toList());

        return listPer;
    }
    public List<TipoActivoDto> getTip() {
        List<TipoActivoDao> tipoActivo = tipoActivoRepository.findAll();

        List<TipoActivoDto> listTip = tipoActivo.stream()
                .map(tip -> new TipoActivoDto(tip.getId(), tip.getNombre()))
                .collect(Collectors.toList());

        return listTip;
    }
    public List<UbicacionDto> getUbi() {
        List<UbicacionDao> ubicacion = ubicacionRepository.findAll();

        List<UbicacionDto> listUbi = ubicacion.stream()
                .map(ubi -> new UbicacionDto(ubi.getId(), ubi.getNombre()))
                .collect(Collectors.toList());

        return listUbi;
    }



}

