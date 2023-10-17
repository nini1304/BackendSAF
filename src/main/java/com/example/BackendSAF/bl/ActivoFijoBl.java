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
    @Autowired
    private BloqueRepository bloqueRepository;
    @Autowired
    private CiudadRepository ciudadRepository;

    public Object list(int page, int size) {
        return activofijorepository.findAll(PageRequest.of(page, size));
    }

    public UbicacionDto registrarUbicacion(String calle, String avenida, Long bloqueId, Long ciudadId) {
        //Log de la ubicación
        LOGGER.info("Registrando ubicación...");
        // Crea una instancia de UbicacionDao con los datos de ubicación
        UbicacionDao ubicacionDao = new UbicacionDao();
        ubicacionDao.setCalle(calle);
        ubicacionDao.setAvenida(avenida);

        // Aquí debes obtener los objetos BloqueDao y CiudadDao a partir de sus IDs, supongo que tienes métodos en tus repositorios para hacerlo
        //BloqueDao bloque = bloqueRepository.findById(bloqueId).orElse(null);
        //Log que muestra el bloque
        //LOGGER.info("Bloque registrado: {}", bloque);
        //CiudadDao ciudad = ciudadRepository.findById(ciudadId).orElse(null);
        //Log que muestra la ciudad
        //LOGGER.info("Ciudad registrada: {}", ciudad);

        ubicacionDao.setBloqueId(Math.toIntExact(bloqueId));
        ubicacionDao.setCiudadId(Math.toIntExact(ciudadId));

        // Guarda la ubicación en la base de datos
        //Log que muestra lo que se va a guardar
        LOGGER.info("Ubicación registrada: {}", ubicacionDao);
        UbicacionDao ubicacionRegistrada = ubicacionRepository.save(ubicacionDao);

        // Devuelve un UbicacionDto con el ID de la ubicación registrada
        return new UbicacionDto(
                ubicacionRegistrada.getId(),
                ubicacionRegistrada.getCalle(),
                ubicacionRegistrada.getAvenida(),
                Math.toIntExact(ubicacionRegistrada.getBloqueId()),
                Math.toIntExact(ubicacionRegistrada.getCiudadId())
        );

    }

    public ActivoFijoDto registrar(String nombre, Integer valor, String fechaCompraString, String descripcion, Integer tipoActivoId, Integer marcaId, String calle, String avenida, Long bloqueId, Long ciudadId, Integer personalId, Integer estadoId, Integer condicionId, Boolean estado) throws ParseException {
        // Registra la ubicación primero
        UbicacionDto ubicacionDto = registrarUbicacion(calle, avenida, bloqueId, ciudadId);
        Date fechaCompra = convertirADate(fechaCompraString);
        // Luego, crea un objeto ActivoFijoDao con los datos del formulario
        ActivoFijoDao act = new ActivoFijoDao();
        act.setNombre(nombre);
        act.setValor(new BigDecimal(valor));
        act.setFechaCompra(fechaCompra);
        act.setDescripcion(descripcion);
        act.setFechaRegistro(new Date());
        act.setTipoActivoId(tipoActivoId);
        act.setMarcaId(marcaId);
        act.setUbicacionId(ubicacionDto.getId()); // Utiliza el ID de la ubicación registrada
        act.setPersonalId(personalId);
        act.setEstadoId(estadoId);
        act.setCondicionId(condicionId);
        act.setEstado(estado);
        activofijorepository.save(act);

        // Guardar en la tabla histórica
        ActivoFijoHDao actH = new ActivoFijoHDao();
        actH.setNombre(nombre);
        actH.setValor(new BigDecimal(valor));
        actH.setFechaCompra(fechaCompra);
        actH.setDescripcion(descripcion);
        actH.setFechaRegistro(new Date());
        actH.setTipoActivoId(tipoActivoId);
        actH.setMarcaId(marcaId);
        actH.setUbicacionId(ubicacionDto.getId()); // Utiliza el ID de la ubicación registrada
        actH.setPersonalId(personalId);
        actH.setEstadoId(estadoId);
        actH.setCondicionId(condicionId);
        actH.setEstado(estado);
        activoFijoHRepository.save(actH);

        return new ActivoFijoDto(act.getNombre(), act.getValor(), fechaCompra, act.getDescripcion(), act.getTipoActivoId(), act.getMarcaId(), act.getUbicacionId(), act.getPersonalId(), act.getEstadoId(), act.getCondicionId(), act.getEstado());
    }


    public Date convertirStringADate(String fechaString) throws ParseException {
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.parse(fechaString);
    }
    public Date convertirADate(String fechaString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd yyyy", Locale.ENGLISH);
        return dateFormat.parse(fechaString);
    }




    // Esto son los get para todas las listas de componentes
    //getACt te envia la lista de todos los activos fijos
    public List<ActivoFijoDto> getAct() {
        List<ActivoFijoDao> activofijo = activofijorepository.findAll();

        List<ActivoFijoDto> listAct = activofijo.stream().
                map(act -> new ActivoFijoDto(act.getNombre(), act.getValor()
                        , act.getFechaCompra(), act.getDescripcion(), act.getTipoActivoId()
                        , act.getMarcaId(), act.getUbicacionId(), act.getPersonalId(), act.getEstadoId(), act.getCondicionId()
                        , act.getEstado()))
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
/*
    public List<UbicacionDto> getUbi() {
        List<UbicacionDao> ubicacion = ubicacionRepository.findAll();

        List<UbicacionDto> listUbi = ubicacion.stream()
                .map(ubi -> new UbicacionDto(ubi.getId(), ubi.getNombre()))
                .collect(Collectors.toList());

        return listUbi;
    }
*/
    public List<BloqueDto> getBloq() {
        List<BloqueDao> bloque = bloqueRepository.findAll();

        List<BloqueDto> listBloc = bloque.stream()
                .map(blo -> new BloqueDto(blo.getId(), blo.getNombre()))
                .collect(Collectors.toList());

        return listBloc;
    }

    public List<CiudadDto> getCiud() {
        List<CiudadDao> ciudad = ciudadRepository.findAll();

        List<CiudadDto> listCiud = ciudad.stream()
                .map(ciu -> new CiudadDto(ciu.getId(), ciu.getNombre()))
                .collect(Collectors.toList());

        return listCiud;
    }
    public ActivoFijoDto actualizarActivo(Integer id, String nombre, String valor, String fechaCompraString, String descripcion, Integer tipoActivoId, Integer marcaId, Integer ubicacionId, Integer personalId, Integer estadoId, Integer condicionId, Boolean estado) throws ParseException {
        Date fechaCompra = convertirStringADate(fechaCompraString);
        Optional<ActivoFijoDao> optionalActivoFijoDao = activofijorepository.findById(Long.valueOf(id));
        if (optionalActivoFijoDao.isPresent()) {
            ActivoFijoDao activoFijoDto = optionalActivoFijoDao.get();

            activoFijoDto.setNombre(nombre);
            activoFijoDto.setValor(new BigDecimal(valor));
            activoFijoDto.setFechaCompra(fechaCompra);
            activoFijoDto.setDescripcion(descripcion);
            activoFijoDto.setTipoActivoId(tipoActivoId);
            activoFijoDto.setMarcaId(marcaId);
            activoFijoDto.setUbicacionId(ubicacionId);
            activoFijoDto.setPersonalId(personalId);
            activoFijoDto.setEstadoId(estadoId);
            activoFijoDto.setCondicionId(condicionId);
            activoFijoDto.setEstado(estado);

            activofijorepository.save(activoFijoDto);

            ActivoFijoHDao acth = new ActivoFijoHDao();
            acth.setNombre(nombre);
            acth.setValor(new BigDecimal(valor));
            acth.setFechaCompra(fechaCompra);
            acth.setDescripcion(descripcion);
            acth.setTipoActivoId(tipoActivoId);
            acth.setMarcaId(marcaId);
            acth.setUbicacionId(ubicacionId);
            acth.setPersonalId(personalId);
            acth.setEstadoId(estadoId);
            acth.setCondicionId(condicionId);
            acth.setEstado(estado);
            activoFijoHRepository.save(acth);

            return new ActivoFijoDto(activoFijoDto.getNombre(), activoFijoDto.getValor(), activoFijoDto.getFechaCompra(), activoFijoDto.getDescripcion(), activoFijoDto.getTipoActivoId(), activoFijoDto.getMarcaId(), activoFijoDto.getUbicacionId(), activoFijoDto.getPersonalId(), activoFijoDto.getEstadoId(), activoFijoDto.getCondicionId(), activoFijoDto.getEstado());
        } else {
            try {
                throw new IllegalAccessException("No existe el activo fijo con el id: " + id);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

