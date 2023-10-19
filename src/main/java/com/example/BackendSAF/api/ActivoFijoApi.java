package com.example.BackendSAF.api;

import com.example.BackendSAF.bl.ActivoFijoBl;
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
            @RequestParam(name = "estado") Boolean estado
    ) throws ParseException {
        ActivoFijoDto activoFijoDto = activoFijoBl.registrar(nombre, valor, fechaCompra, descripcion, tipoActivoId, marcaId, calle, avenida, bloqueId, ciudadId, personalId, estadoId, condicionId, estado);
        return ResponseEntity.ok(activoFijoDto);
    }
    @GetMapping("/actF")
    public List<ActivoFijoDto> obtenerListaActivosFijos() throws ParseException {
        return  activoFijoBl.getAct();
    }
    @GetMapping("/cond")
    public List<CondicionDto> obtenerListaDeCondicionDto() {
        return activoFijoBl.getCond();
    }
    @GetMapping("estado")
    public List<EstadoDto> obtenerListaDeEstadoDto() {
        return activoFijoBl.getEst();
    }
    @GetMapping("/marca")
    public List<MarcaDto> obtenerListaDeMarcaDto() {
        return activoFijoBl.getMar();
    }
    @GetMapping("/personal")
    public List<PersonalDto> obtenerListaDePersonalDto() {
        return activoFijoBl.getPer();
    }
    @GetMapping("/tipo")
    public List<TipoActivoDto> obtenerListaDeTipoActivoDto() {
        return activoFijoBl.getTip();
    }
    @GetMapping("/ciudad")
    public List<CiudadDto> obtenerListaDeCiudadDto() {return activoFijoBl.getCiud();}
    @GetMapping("/bloque")
    public List<BloqueDto> obtenerListaDeBloqueDto() {return activoFijoBl.getBloq();}
    /*
    @GetMapping("/ubicacion")
    public List<UbicacionDto> obtenerListaDeUbicacionDto() {
        return activoFijoBl.getUbi();
    }
     */
    @PutMapping("actualizar/{id}")
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
            @RequestParam(name = "estado") Boolean estado
    ) throws ParseException {
        ActivoFijoDto activoFijoDto = activoFijoBl.actualizarActivoFijo(id, nombre, valor, fechaCompra, descripcion, tipoActivoId, marcaId, calle, avenida, bloqueId, ciudadId, personalId, estadoId, condicionId, estado);
        return ResponseEntity.ok(activoFijoDto);
    }
}
