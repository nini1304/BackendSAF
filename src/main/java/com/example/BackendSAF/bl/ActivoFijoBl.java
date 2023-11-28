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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


@Service
public class ActivoFijoBl {

    HashMap<String,Object> datos;
    //este hashmap para delete
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
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private ActivoFijoDRepository activofijodrepository;
    @Autowired
    private TiempoRepository tiemporepository;
    @Autowired
    private CiudadEmpresaRepository ciudadEmpresaRepository;
    @Autowired
    private BloqueEmpresaRepository bloqueEmpresaRepository;
    @Autowired
    private PersonalEmpresaRepository personalEmpresaRepository;
    @Autowired
    private OficinaEmpresaRepository oficinaEmpresaRepository;
    private BigDecimal auxiliar=BigDecimal.valueOf(2);
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
        UbicacionDao ubicacionRegistrada = ubicacionRepository.save(ubicacionDao);
        LOGGER.info("Ubicación registrada: {}", ubicacionDao);
        // Devuelve un UbicacionDto con el ID de la ubicación registrada
        return new UbicacionDto(
                ubicacionRegistrada.getId(),
                ubicacionRegistrada.getCalle(),
                ubicacionRegistrada.getAvenida(),
                Math.toIntExact(ubicacionRegistrada.getBloqueId()),
                Math.toIntExact(ubicacionRegistrada.getCiudadId())
        );

    }

    public ActivoFijoDto registrar(String nombre, Integer valor, String fechaCompraString, String descripcion, Integer tipoActivoId, Integer marcaId, String calle, String avenida, Long bloqueId, Long ciudadId, Integer personalId, Integer estadoId, Integer condicionId, Boolean estado, Long idEmpresa, String username) throws ParseException {
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
        act.setEmpresaId(idEmpresa);
        activofijorepository.save(act);

        // Guardar en la tabla histórica
        ActivoFijoHDao actH = new ActivoFijoHDao();
        actH.setIdActivo(act.getId());
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
        actH.setEmpresaId(idEmpresa);
        actH.setEvento("Registro");
        actH.setUsuario(username);
        activoFijoHRepository.save(actH);

        return new ActivoFijoDto(act.getId(),act.getNombre(), act.getValor(), fechaCompra, act.getDescripcion(), act.getTipoActivoId(), act.getMarcaId(), act.getUbicacionId(), act.getPersonalId(), act.getEstadoId(), act.getCondicionId(), act.getEstado());
    }

    public Date convertirADate(String fechaString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd yyyy", Locale.ENGLISH);
        return dateFormat.parse(fechaString);
    }
    // Esto son los get para todas las listas de componentes
    // getACt te envia la lista de todos los activos fijos
    public List<ActivoFijoList2Dto> getAct(String mesIngresado, Integer añoIngresado,Long idEmpresa, String userName) throws Exception {
        BigDecimal aux = BigDecimal.valueOf(1);
        BigDecimal aux2 = BigDecimal.valueOf(1);
        BigDecimal aux3 = BigDecimal.valueOf(1);
        List<ActivoFijoDao> activoFijo = activofijorepository.findAllByIdEmpresa(idEmpresa);
        List<ActivoFijoList2Dto> listAct = new ArrayList<>();
        //LOGGER.info("ActivoFijo: {}", activoFijo.get(0).getTipoActivoId());
        if(mesIngresado.equals("-")&&añoIngresado==0){
            for (ActivoFijoDao act : activoFijo) {
                if (act.getEstado()){
                    listAct.add(new ActivoFijoList2Dto(
                            act.getId(),
                            act.getNombre(),
                            act.getValor(),
                            formatearFecha(act.getFechaCompra().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()),
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
                            tipoActivoRepository.getPorcentajeDepreciacionById(Long.valueOf(act.getTipoActivoId())),
                            BigDecimal.valueOf(0),
                            BigDecimal.valueOf(0),
                            BigDecimal.valueOf(0)
                    ));
                }
            }
        }
        else{
            TiempoDao tiempo = tiemporepository.findByMesAndAnioAndEmpresaId(mesIngresado, añoIngresado.toString(),idEmpresa);
            if (tiempo != null) {
                // El registro de tiempo para el mes y año ya existe, lanzar una excepción
                throw new Exception("Ya existe un registro para el mes " + mesIngresado + " y el año " + añoIngresado);
            }
            tiempo = new TiempoDao();
            tiempo.setMes(mesIngresado);
            tiempo.setAnio(añoIngresado.toString());
            tiempo.setEmpresaId(idEmpresa);
            tiemporepository.save(tiempo);
            for (ActivoFijoDao act : activoFijo) {
                if((act.getValor().subtract(calcularDepreciacion(act.getFechaCompra(), mesIngresado, añoIngresado,act.getValor(), tipoActivoRepository.getPorcentajeDepreciacionById(Long.valueOf(act.getTipoActivoId()))))).compareTo(BigDecimal.ZERO) <= 0){
                    aux=BigDecimal.valueOf(1);
                }
                else{
                    aux=act.getValor().subtract(calcularDepreciacion(act.getFechaCompra(), mesIngresado, añoIngresado,act.getValor(), tipoActivoRepository.getPorcentajeDepreciacionById(Long.valueOf(act.getTipoActivoId()))));
                }
                if(tipoActivoRepository.getPorcentajeDepreciacionById(Long.valueOf(act.getTipoActivoId()))<0){
                    aux2=BigDecimal.valueOf(0);
                }
                else{
                    aux2=BigDecimal.valueOf(tipoActivoRepository.getPorcentajeDepreciacionById(Long.valueOf(act.getTipoActivoId())));
                }
                if(calcularDepreciacion(act.getFechaCompra(), mesIngresado, añoIngresado,act.getValor(), tipoActivoRepository.getPorcentajeDepreciacionById(Long.valueOf(act.getTipoActivoId()))).compareTo(act.getValor()) >= 0){
                    aux3=BigDecimal.valueOf(0);
                }
                else{
                    aux3=calcularDepreciacion(act.getFechaCompra(), mesIngresado, añoIngresado,act.getValor(), tipoActivoRepository.getPorcentajeDepreciacionById(Long.valueOf(act.getTipoActivoId())));
                }
                if (act.getEstado()){
                    listAct.add(new ActivoFijoList2Dto(
                            act.getId(),
                            act.getNombre(),
                            act.getValor(),
                            formatearFecha(act.getFechaCompra().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()),
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
                            Integer.valueOf(String.valueOf(aux2)),
                            aux3,
                            aux,
                            calcularMesesHastaCero(act.getValor(), act.getFechaCompra(), mesIngresado, añoIngresado, tipoActivoRepository.getPorcentajeDepreciacionById(Long.valueOf(act.getTipoActivoId())))

                    ));
                    BigDecimal mesesRestantes = calcularMesesHastaCero(act.getValor(), act.getFechaCompra(), mesIngresado, añoIngresado, tipoActivoRepository.getPorcentajeDepreciacionById(Long.valueOf(act.getTipoActivoId())));
                    LOGGER.info("ActivoFijoId:"+act.getId());
                    LOGGER.info("Meses Restantes: "+ mesesRestantes);
                    ActivoFijoDDao actD = new ActivoFijoDDao();
                    actD.setIdActivo(act.getId());
                    actD.setNombre(act.getNombre());
                    actD.setValor(act.getValor());
                    actD.setFechaCompra(act.getFechaCompra());
                    actD.setDescripcion(act.getDescripcion());
                    actD.setFechaRegistro(act.getFechaRegistro());
                    actD.setTipoActivoNombre(tipoActivoRepository.getTipoActivoNombreById(Long.valueOf(act.getTipoActivoId())));
                    actD.setMarcaNombre(marcaRepository.getMarcaNombreById(Long.valueOf(act.getMarcaId())));
                    actD.setCalle(ubicacionRepository.getUbicacionCalleById(Long.valueOf(act.getUbicacionId())));
                    actD.setAvenida(ubicacionRepository.getUbicacionAvenidaById(Long.valueOf(act.getUbicacionId())));
                    actD.setBloqueNombre(bloqueRepository.getBloqueNombreById(ubicacionRepository.getUbicacionBloqueIdById(Long.valueOf(act.getUbicacionId()))));
                    actD.setCiudadNombre(ciudadRepository.getCiudadNombreById(ubicacionRepository.getUbicacionCiudadIdById(Long.valueOf(act.getUbicacionId()))));
                    actD.setPersonalNombre(personalRepository.getPersonalNombreById(Long.valueOf(act.getPersonalId())));
                    actD.setEstadoNombre(estadoRepository.getEstadoNombreById(Long.valueOf(act.getEstadoId())));
                    actD.setCondicionNombre(fijoRepository.getCondicionNombreById(Long.valueOf(act.getCondicionId())));
                    actD.setPorcentajeDepreciacion(Integer.valueOf(String.valueOf(aux2)));
                    actD.setValorDepreciacion(aux3);
                    actD.setValorActual(aux);
                    actD.setMesesRestantes(mesesRestantes.longValue());
                    actD.setEmpresaId(idEmpresa);
                    actD.setUsuario(userName);
                    actD.setFechaD(new Date());
                    actD.setIdTiempo(tiempo.getId());
                    activofijodrepository.save(actD);

                }
            }
        }
        //fecha formato, combo boxes con empresa, campo estado
        //PDFReportGenerator.generatePDFReport2(listAct, "reporte.pdf");
        return listAct;

    }
    public BigDecimal calcularMesesHastaCero(BigDecimal valor, Date fechaCompra, String mesIngresado, int añoIngresado, Integer porcentajeDepreciacion) {
        LocalDate fechaCompraLocal = fechaCompra.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Month mesParametro = Month.valueOf(mesIngresado.toUpperCase());
        LocalDate fechaIngresada = LocalDate.of(añoIngresado, mesParametro, 1);

        BigDecimal mesesDiferencia = BigDecimal.valueOf(fechaCompraLocal.until(fechaIngresada).toTotalMonths());

        BigDecimal porcentajeDecimal = BigDecimal.valueOf(1 - porcentajeDepreciacion / 100.0);

        // Calcular meses necesarios con fórmula matemática
        BigDecimal mesesNecesarios = BigDecimal.valueOf(Math.round(Math.log(valor.doubleValue() / 100) / Math.log(porcentajeDecimal.doubleValue())));
        BigDecimal mesesRestantes= mesesDiferencia.add(mesesNecesarios);
        if(mesesRestantes.compareTo(BigDecimal.ZERO) <= 0){
            mesesRestantes=BigDecimal.valueOf(0);
        }
        mesesRestantes=mesesRestantes.subtract(auxiliar);
        auxiliar=auxiliar.add(BigDecimal.valueOf(1));
        return mesesRestantes;
    }


    public BigDecimal calcularDepreciacion(Date fechaCompra, String mesIngresado, int añoIngresado, BigDecimal valorActual, Integer porcentajeDepreciacion) {

        // Convertir la fecha de compra (java.util.Date) a LocalDate
        LocalDate fechaCompraLocal = fechaCompra.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        //LOGGER.info("Fecha de compra: {}", fechaCompraLocal);
        // Obtener el mes ingresado como parámetro (asumiendo que es el nombre del mes)
        Month mesParametro = Month.valueOf(mesIngresado.toUpperCase());
        //LOGGER.info("Mes ingresado: {}", mesParametro);

        // Crear una fecha con el mes y año ingresados
        LocalDate fechaIngresada = LocalDate.of(añoIngresado, mesParametro, 1);
        //LOGGER.info("Fecha ingresada: {}", fechaIngresada);

        // Calcular la diferencia en meses entre la fecha de compra y el mes ingresado
        long mesesDiferencia = (fechaCompraLocal.until(fechaIngresada).toTotalMonths()) + 1;

        //LOGGER.info("Meses de diferencia: {}", mesesDiferencia);

        // Calcular la depreciación
        BigDecimal depreciacion = valorActual.multiply(BigDecimal.valueOf(mesesDiferencia))
                .multiply(BigDecimal.valueOf(porcentajeDepreciacion))
                .divide(BigDecimal.valueOf(100 * 12), 2, BigDecimal.ROUND_HALF_UP);
        //LOGGER.info("Depreciacion: {}", depreciacion);
        return depreciacion;
    }
    public String formatearFecha(LocalDate fecha) {
        // Define el formato deseado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // Formatea la fecha
        String fechaFormateada = fecha.format(formatter);
        // Devuelve la fecha formateada
        return fechaFormateada;
    }
    public List<ActivoFijoListDto> getAct2(Long idEmpresa) {
        List<ActivoFijoDao> activoFijo = activofijorepository.findAllByIdEmpresa(idEmpresa);
        List<ActivoFijoListDto> listAct = new ArrayList<>();
        //LOGGER.info("ActivoFijo: {}", activoFijo.get(0).getTipoActivoId());

        for (ActivoFijoDao act : activoFijo) {
            if (act.getEstado()){
                listAct.add(new ActivoFijoListDto(
                        act.getId(),
                        act.getNombre(),
                        act.getValor(),
                        formatearFecha(act.getFechaCompra().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()),
                        act.getDescripcion(),
                        tipoActivoRepository.getTipoActivoNombreById(Long.valueOf(act.getTipoActivoId())),
                        marcaRepository.getMarcaNombreById(Long.valueOf(act.getMarcaId())),
                        ubicacionRepository.getUbicacionCalleById(Long.valueOf(act.getUbicacionId())),
                        ubicacionRepository.getUbicacionAvenidaById(Long.valueOf(act.getUbicacionId())),
                        bloqueRepository.getBloqueNombreById(ubicacionRepository.getUbicacionBloqueIdById(Long.valueOf(act.getUbicacionId()))),
                        ciudadRepository.getCiudadNombreById(ubicacionRepository.getUbicacionCiudadIdById(Long.valueOf(act.getUbicacionId()))),
                        personalRepository.getPersonalNombreById(Long.valueOf(act.getPersonalId())),
                        estadoRepository.getEstadoNombreById(Long.valueOf(act.getEstadoId())),
                        fijoRepository.getCondicionNombreById(Long.valueOf(act.getCondicionId()))
                ));
            }
        }

        return listAct;

    }
    public void generarExcel(List<ActivoFijoList2Dto> listaActivoFijo, String nombreArchivo) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("ActivoFijo");

        // Crear encabezados
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nombre");
        headerRow.createCell(2).setCellValue("Valor");
        headerRow.createCell(3).setCellValue("Fecha de compra");
        headerRow.createCell(4).setCellValue("Descripción");
        headerRow.createCell(5).setCellValue("Tipo de activo");
        headerRow.createCell(6).setCellValue("Marca");
        headerRow.createCell(7).setCellValue("Calle");
        headerRow.createCell(8).setCellValue("Avenida");
        headerRow.createCell(9).setCellValue("Bloque");
        headerRow.createCell(10).setCellValue("Ciudad");
        headerRow.createCell(11).setCellValue("Personal");
        //headerRow.createCell(12).setCellValue("Estado");
        //headerRow.createCell(13).setCellValue("Condición");
        headerRow.createCell(12).setCellValue("Porcentaje de depreciación");
        headerRow.createCell(13).setCellValue("Valor de depreciación");
        headerRow.createCell(14).setCellValue("Valor actual");
        headerRow.createCell(15).setCellValue("Meses restantes");

        // Agrega más encabezados según tus necesidades

        // Llena los datos
        for (int i = 0; i < listaActivoFijo.size(); i++) {
            ActivoFijoList2Dto activoFijo = listaActivoFijo.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(activoFijo.getId());
            row.createCell(1).setCellValue(activoFijo.getNombre());
            row.createCell(2).setCellValue(activoFijo.getValor().doubleValue());
            row.createCell(3).setCellValue(activoFijo.getFechaCompra());
            row.createCell(4).setCellValue(activoFijo.getDescripcion());
            row.createCell(5).setCellValue(activoFijo.getTipoActivoNombre());
            row.createCell(6).setCellValue(activoFijo.getMarcaNombre());
            row.createCell(7).setCellValue(activoFijo.getCalle());
            row.createCell(8).setCellValue(activoFijo.getAvenida());
            row.createCell(9).setCellValue(activoFijo.getBloqueNombre());
            row.createCell(10).setCellValue(activoFijo.getCiudadNombre());
            row.createCell(11).setCellValue(activoFijo.getPersonalNombre());
            //row.createCell(12).setCellValue(activoFijo.getEstadoNombre());
            //row.createCell(13).setCellValue(activoFijo.getCondicionNombre());
            row.createCell(12).setCellValue(activoFijo.getPorcentajeDepreciacion());
            row.createCell(13).setCellValue(activoFijo.getValorDepreciacion().doubleValue());
            row.createCell(14).setCellValue(activoFijo.getValorActual().doubleValue());
            row.createCell(15).setCellValue(activoFijo.getMesesRestantes().doubleValue());
            // Agrega más celdas según tus necesidades
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(nombreArchivo);
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
            System.out.println("Archivo Excel generado exitosamente: " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void generarExcel2(List<ActivoFDDto> listaActivoFijo, String nombreArchivo) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("ActivoFijo");

        // Crear encabezados
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nombre");
        headerRow.createCell(2).setCellValue("Valor");
        headerRow.createCell(3).setCellValue("Fecha de compra");
        headerRow.createCell(4).setCellValue("Descripción");
        headerRow.createCell(5).setCellValue("Tipo de activo");
        headerRow.createCell(6).setCellValue("Marca");
        headerRow.createCell(7).setCellValue("Calle");
        headerRow.createCell(8).setCellValue("Avenida");
        headerRow.createCell(9).setCellValue("Bloque");
        headerRow.createCell(10).setCellValue("Ciudad");
        headerRow.createCell(11).setCellValue("Personal");
        //headerRow.createCell(12).setCellValue("Estado");
        //headerRow.createCell(13).setCellValue("Condición");
        headerRow.createCell(12).setCellValue("Porcentaje de depreciación");
        headerRow.createCell(13).setCellValue("Valor de depreciación");
        headerRow.createCell(14).setCellValue("Valor actual");
        headerRow.createCell(15).setCellValue("Meses restantes");

        // Agrega más encabezados según tus necesidades

        // Llena los datos
        for (int i = 0; i < listaActivoFijo.size(); i++) {
            ActivoFDDto activoFijo = listaActivoFijo.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(activoFijo.getId());
            row.createCell(1).setCellValue(activoFijo.getNombre());
            row.createCell(2).setCellValue(activoFijo.getValor().doubleValue());
            row.createCell(3).setCellValue(activoFijo.getFechaCompra());
            row.createCell(4).setCellValue(activoFijo.getDescripcion());
            row.createCell(5).setCellValue(activoFijo.getTipoActivoNombre());
            row.createCell(6).setCellValue(activoFijo.getMarcaNombre());
            row.createCell(7).setCellValue(activoFijo.getCalle());
            row.createCell(8).setCellValue(activoFijo.getAvenida());
            row.createCell(9).setCellValue(activoFijo.getBloqueNombre());
            row.createCell(10).setCellValue(activoFijo.getCiudadNombre());
            row.createCell(11).setCellValue(activoFijo.getPersonalNombre());
            //row.createCell(12).setCellValue(activoFijo.getEstadoNombre());
            //row.createCell(13).setCellValue(activoFijo.getCondicionNombre());
            row.createCell(12).setCellValue(activoFijo.getPorcentajeDepreciacion());
            row.createCell(13).setCellValue(activoFijo.getValorDepreciacion().doubleValue());
            row.createCell(14).setCellValue(activoFijo.getValorActual().doubleValue());
            row.createCell(15).setCellValue(activoFijo.getMesesRestantes().doubleValue());
            // Agrega más celdas según tus necesidades
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(nombreArchivo);
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
            System.out.println("Archivo Excel generado exitosamente: " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<CondicionDto> getCond() {
        List<CondicionDao> condicion = fijoRepository.findAll();
        List<CondicionDto> listConds = condicion.stream()
                .map(con -> new CondicionDto(con.getId(), con.getNombre()))
                .collect(Collectors.toList());

        return listConds;
    }

    public List<EstadoDto> getEst(Long EmpresaId) {
        List<Long> estado = oficinaEmpresaRepository.findOficinasByEmpresaId(EmpresaId);

        List<EstadoDto> listEst = estado.stream()
                .map(est -> new EstadoDto(est, estadoRepository.getEstadoNombreById(est)))
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

    public List<PersonalDto> getPer(Long EmpresaId) {
        List<Long> personal = personalEmpresaRepository.findPersonalIdByEmpresaId(EmpresaId);

        List<PersonalDto> listPer = personal.stream()
                .map(per -> new PersonalDto(per, personalRepository.getPersonalNombreById(per)))
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
    public List<EmpresaDto> getEmp() {
        List<EmpresaDao> empresa = empresaRepository.findAll();

        List<EmpresaDto> listEmp = empresa.stream()
                .map(emp -> new EmpresaDto(emp.getIdEmpresa(), emp.getNombre(), emp.getLogo()))
                .collect(Collectors.toList());

        return listEmp;
    }

    public List<BloqueDto> getBloq(Long EmpresaId) {
        List<Long> bloque = bloqueEmpresaRepository.findBloquesByEmpresaId(EmpresaId);

        List<BloqueDto> listBloc = bloque.stream()
                .map(blo -> new BloqueDto(blo, bloqueRepository.getBloqueNombreById(blo)))
                .collect(Collectors.toList());

        return listBloc;
    }

    public List<CiudadDto> getCiud(Long EmpresaId) {
        List<Long> ciudad = ciudadEmpresaRepository.findCiudadesByEmpresaId(EmpresaId);

        List<CiudadDto> listCiud = ciudad.stream()
                .map(ciu -> new CiudadDto(ciu, ciudadRepository.getCiudadNombreById(ciu)))
                .collect(Collectors.toList());


        return listCiud;
    }
    public ActivoFijoDto actualizarActivoFijo(Long activoFijoId, String nombre, Integer valor, String fechaCompraString, String descripcion, Integer tipoActivoId, Integer marcaId, String calle, String avenida, Long bloqueId, Long ciudadId, Integer personalId, Integer estadoId, Integer condicionId, Boolean estado, String userName) throws ParseException {
        // Busca el activo fijo existente por su ID
        ActivoFijoDao activoExistente = activofijorepository.findById(activoFijoId).orElse(null);

        if (activoExistente == null) {
            throw new NoSuchElementException("El activo fijo con ID " + activoFijoId + " no existe");
        }

        // Registra una nueva ubicación o actualiza la existente
        UbicacionDto ubicacionDto = registrarUbicacion(calle, avenida, bloqueId, ciudadId);

        // Actualiza los datos del activo fijo existente
        activoExistente.setNombre(nombre);
        activoExistente.setValor(new BigDecimal(valor));
        activoExistente.setFechaCompra(convertirADate(fechaCompraString));
        activoExistente.setDescripcion(descripcion);
        activoExistente.setTipoActivoId(tipoActivoId);
        activoExistente.setMarcaId(marcaId);
        activoExistente.setUbicacionId(ubicacionDto.getId()); // Utiliza el ID de la ubicación registrada
        activoExistente.setPersonalId(personalId);
        activoExistente.setEstadoId(estadoId);
        activoExistente.setCondicionId(condicionId);
        activoExistente.setEstado(estado);
        //Guardar en la tabla histórica
        ActivoFijoHDao actH = new ActivoFijoHDao();
        actH.setIdActivo(activoExistente.getId());
        actH.setNombre(nombre);
        actH.setValor(new BigDecimal(valor));
        actH.setFechaCompra(convertirADate(fechaCompraString));
        actH.setDescripcion(descripcion);
        actH.setFechaRegistro(new Date());
        actH.setTipoActivoId(tipoActivoId);
        actH.setMarcaId(marcaId);
        actH.setUbicacionId(ubicacionDto.getId()); // Utiliza el ID de la ubicación registrada
        actH.setPersonalId(personalId);
        actH.setEstadoId(estadoId);
        actH.setCondicionId(condicionId);
        actH.setEstado(estado);
        actH.setEmpresaId(activoExistente.getEmpresaId());
        actH.setEvento("Actualizacion");
        actH.setUsuario(userName);
        activoFijoHRepository.save(actH);

        // Guarda los cambios en el activo fijo
        activofijorepository.save(activoExistente);

        return new ActivoFijoDto(activoExistente.getId(), activoExistente.getNombre(), activoExistente.getValor(),
                activoExistente.getFechaCompra(), activoExistente.getDescripcion(), activoExistente.getTipoActivoId(),
                activoExistente.getMarcaId(), activoExistente.getUbicacionId(), activoExistente.getPersonalId(),
                activoExistente.getEstadoId(), activoExistente.getCondicionId(), activoExistente.getEstado());
    }

    public ActivoFijoDto getActivoFijoById(Long id){
        ActivoFijoDao activoFijoDao = activofijorepository.findById(id).orElse(null);
        if(activoFijoDao == null){
            return null;
        }
        return new ActivoFijoDto(
                activoFijoDao.getId(),
                activoFijoDao.getNombre(),
                activoFijoDao.getValor(),
                activoFijoDao.getFechaCompra(),
                activoFijoDao.getDescripcion(),
                activoFijoDao.getTipoActivoId(),
                activoFijoDao.getMarcaId(),
                activoFijoDao.getUbicacionId(),
                activoFijoDao.getPersonalId(),
                activoFijoDao.getEstadoId(),
                activoFijoDao.getCondicionId(),
                activoFijoDao.getEstado()
        );
    }

    public ResponseEntity<Object> deleteActivoFijo(Long id){
        datos=new HashMap<>();
        boolean existe=this.activofijorepository.existsById(id);
        if(!existe){
            datos.put("error",true);
            datos.put("message","No existe un activo fijo con ese id para eliminar");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        activofijorepository.deleteById(id);
        datos.put("message","Activo fijo Eliminado");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED);
    }

    public ActivoFijoDto actualizarActivoFijoEstado(Long id, String userName) throws ParseException {
        // Busca el activo fijo existente por su ID
        ActivoFijoDao activoExistente = activofijorepository.findById(id).orElse(null);
        if (activoExistente == null) {
            throw new NoSuchElementException("El activo fijo con ID " + id + " no existe");
        }

        //solamente actualiza el estado
        activoExistente.setEstado(false);
        LOGGER.info("Registrando Evento....");
        //Guardar en la tabla histórica y se crea con el evento actualiza estado
        ActivoFijoHDao actH = new ActivoFijoHDao();
        actH.setIdActivo(activoExistente.getId());
        actH.setNombre(activoExistente.getNombre());
        actH.setValor(activoExistente.getValor());
        actH.setFechaCompra(activoExistente.getFechaCompra());
        actH.setDescripcion(activoExistente.getDescripcion());
        actH.setFechaRegistro(new Date());
        actH.setTipoActivoId(activoExistente.getTipoActivoId());
        actH.setMarcaId(activoExistente.getMarcaId());
        actH.setUbicacionId(activoExistente.getUbicacionId()); // Utiliza el ID de la ubicación registrada
        actH.setPersonalId(activoExistente.getPersonalId());
        actH.setEstadoId(activoExistente.getEstadoId());
        actH.setCondicionId(activoExistente.getCondicionId());
        actH.setEstado(activoExistente.getEstado());
        actH.setEmpresaId(activoExistente.getEmpresaId());
        actH.setEvento("Borrado logico");
        actH.setUsuario(userName);
        LOGGER.info("Activo Fijo historico registrado: {}", actH);
        activoFijoHRepository.save(actH);

        return new ActivoFijoDto(activoExistente.getId(), activoExistente.getNombre(), activoExistente.getValor(),
                activoExistente.getFechaCompra(), activoExistente.getDescripcion(), activoExistente.getTipoActivoId(),
                activoExistente.getMarcaId(), activoExistente.getUbicacionId(), activoExistente.getPersonalId(),
                activoExistente.getEstadoId(), activoExistente.getCondicionId(), activoExistente.getEstado());

    }

}