package com.example.BackendSAF.api;

import com.example.BackendSAF.bl.ActivoFijoBl;
import com.example.BackendSAF.dto.ActivoFijoDto;
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
            @RequestParam(name = "valor") BigDecimal valor,
            //@RequestParam(name = "fechaCompra") String fechaCompra,
            @RequestParam(name = "descripcion") String descripcion,
            @RequestParam(name = "porcentajeDepreciacion") Integer porcentajeDepreciacion,
            @RequestParam(name = "tipoActivoId") Integer tipoActivoId,
            @RequestParam(name = "marcaId") Integer marcaId,
            @RequestParam(name = "ubicacionId") Integer ubicacionId,
            @RequestParam(name = "personalId") Integer personalId,
            @RequestParam(name = "estadoId") Integer estadoId,
            @RequestParam(name = "condicionId") Integer condicionId,
            @RequestParam(name = "estado") Boolean estado
    ){
        LOGGER.info("Ejecutando registrarActivosFijos...");
        ActivoFijoDto activoFijoDto = activoFijoBl.registrar(nombre, valor, descripcion, porcentajeDepreciacion, tipoActivoId, marcaId, ubicacionId, personalId, estadoId, condicionId, estado);
        return ResponseEntity.ok(activoFijoDto);
    }

}
