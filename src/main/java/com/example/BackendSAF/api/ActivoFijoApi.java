package com.example.BackendSAF.api;

import com.example.BackendSAF.bl.ActivoFijoBl;
import com.example.BackendSAF.bl.PDFReportGenerator;
import com.example.BackendSAF.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/activos-fijos")
public class ActivoFijoApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivoFijoApi.class);

    private final ActivoFijoBl activoFijoBl;
    private PDFReportGenerator pdfReportGenerator;

    @Autowired

    public ActivoFijoApi(ActivoFijoBl activoFijoBl) {
        this.activoFijoBl = activoFijoBl;
    }

    @GetMapping("/list")
    public Object listarActivosFijos(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        LOGGER.info("Ejecutando listarActivosFijos...");
        return activoFijoBl.list(page, size);
    }

    @PostMapping("/registrar")
    public ResponseEntity<ActivoFijoDto> registrarActivoFijo(
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "valor") Integer valor,
            @RequestParam(name = "fechaCompra") String fechaCompra,
            @RequestParam(name = "descripcion") String descripcion,
            @RequestParam(name = "tipoActivoId") Integer tipoActivoId,
            @RequestParam(name = "marcaId") Integer marcaId,
            @RequestParam(name = "calle") String calle,
            @RequestParam(name = "avenida") String avenida,
            @RequestParam(name = "bloqueId") Long bloqueId,
            @RequestParam(name = "ciudadId") Long ciudadId,
            @RequestParam(name = "personalId") Integer personalId,
            @RequestParam(name = "estadoId") Integer estadoId,
            @RequestParam(name = "condicionId") Integer condicionId,
            @RequestParam(name = "estado") Boolean estado,
            @RequestParam(name="idEmp")Long idEmp,
            @RequestParam(name="username")String username
    ) throws ParseException {
        ActivoFijoDto activoFijoDto = activoFijoBl.registrar(nombre, valor, fechaCompra, descripcion, tipoActivoId, marcaId, calle, avenida, bloqueId, ciudadId, personalId, estadoId, condicionId, estado,idEmp,username);
        return ResponseEntity.ok(activoFijoDto);
    }
    @GetMapping("/actF")
    public List<ActivoFijoList2Dto> obtenerListaActivosFijos(@RequestParam(name = "mes")String mesIngresado, @RequestParam(name = "anio")int anio,@RequestParam(name="idEmp")Long idEmp, @RequestParam(name="username") String username) throws Exception {
        return  activoFijoBl.getAct(mesIngresado,anio,idEmp,username);
    }
    @GetMapping("/actF2")
    public List<ActivoFijoListDto> obtenerListaActivosFijosUser(@RequestParam(name="idEmp")Long idEmp) throws ParseException {
        return  activoFijoBl.getAct2(idEmp);
    }
    @PostMapping("/excel")
    public ResponseEntity<String> generarExcel(@RequestParam String nombreArchivo, @RequestBody List<ActivoFijoList2Dto> activos) {
        activoFijoBl.generarExcel(activos, nombreArchivo);
        return ResponseEntity.ok("Excel generado correctamente");
    }

    @PostMapping("/pdf")
    public ResponseEntity<String> generarPDFReport2(@RequestParam String nombreArchivo,@RequestParam String imageUrl,@RequestParam String username,@RequestParam String empresa, @RequestBody List<ActivoFijoList2Dto> activos) {
        pdfReportGenerator.generatePDFReport2(activos, nombreArchivo, imageUrl, username, empresa);
        return ResponseEntity.ok("PDF generado correctamente");
    }

    @GetMapping("/cond")
    public List<CondicionDto> obtenerListaDeCondicionDto() {
        return activoFijoBl.getCond();
    }
    @GetMapping("estado")
    public List<EstadoDto> obtenerListaDeEstadoDto(@RequestParam(name="idEmp")Long idEmp) {
        return activoFijoBl.getEst(idEmp);
    }
    @GetMapping("/marca")
    public List<MarcaDto> obtenerListaDeMarcaDto() {
        return activoFijoBl.getMar();
    }
    @GetMapping("/personal")
    public List<PersonalDto> obtenerListaDePersonalDto(@RequestParam(name="idEmp")Long idEmp) {
        return activoFijoBl.getPer(idEmp);
    }
    @GetMapping("/tipo")
    public List<TipoActivoDto> obtenerListaDeTipoActivoDto() {
        return activoFijoBl.getTip();
    }
    @GetMapping("/ciudad")
    public List<CiudadDto> obtenerListaDeCiudadDto(@RequestParam(name="idEmp")Long idEmp) {return activoFijoBl.getCiud(idEmp);}
    @GetMapping("/bloque")
    public List<BloqueDto> obtenerListaDeBloqueDto(@RequestParam(name="idEmp")Long idEmp) {return activoFijoBl.getBloq(idEmp);}
    @GetMapping("/empresas")
    public List<EmpresaDto> obtenerListaDeEmpresaDto() {return activoFijoBl.getEmp();}
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ActivoFijoDto> actualizarActivoFijo(
            @PathVariable Long id,
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "valor") Integer valor,
            @RequestParam(name = "fechaCompra") String fechaCompra,
            @RequestParam(name = "descripcion") String descripcion,
            @RequestParam(name = "tipoActivoId") Integer tipoActivoId,
            @RequestParam(name = "marcaId") Integer marcaId,
            @RequestParam(name = "calle") String calle,
            @RequestParam(name = "avenida") String avenida,
            @RequestParam(name = "bloqueId") Long bloqueId,
            @RequestParam(name = "ciudadId") Long ciudadId,
            @RequestParam(name = "personalId") Integer personalId,
            @RequestParam(name = "estadoId") Integer estadoId,
            @RequestParam(name = "condicionId") Integer condicionId,
            @RequestParam(name = "estado") Boolean estado,
            @RequestParam (name = "username")String username
    )throws ParseException {
        ActivoFijoDto activoFijoDto = activoFijoBl.actualizarActivoFijo(id, nombre, valor, fechaCompra, descripcion, tipoActivoId, marcaId, calle, avenida, bloqueId, ciudadId, personalId, estadoId, condicionId, estado, username);
        return ResponseEntity.ok(activoFijoDto);
    }
    @GetMapping("/listar/{id}")
    public ResponseEntity<ActivoFijoDto> obtenerActivoFijoId(
            @PathVariable Long id
    ){
        ActivoFijoDto activoFijo = activoFijoBl.getActivoFijoById(id);
       if (id != null){
           return ResponseEntity.ok(activoFijo);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //actualizar pero solo para el estado
    @PutMapping("/disable")
    public ResponseEntity<ActivoFijoDto> actualizarActivoFijoEstado(
            @RequestParam Long id, @RequestParam String username
    )throws ParseException {
        ActivoFijoDto activoFijoDto = activoFijoBl.actualizarActivoFijoEstado(id, username);
        return ResponseEntity.ok(activoFijoDto);
    }

    //metodo eliminar id
    @DeleteMapping(path = "/eliminar/{id}")
    public ResponseEntity<Object> eliminarActivoFijo(@PathVariable("id") Long id){
        return this.activoFijoBl.deleteActivoFijo(id);
    }


}
