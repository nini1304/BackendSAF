package com.example.BackendSAF.api;

import com.example.BackendSAF.bl.ActivoFijoBl;
import com.example.BackendSAF.dto.ActivoFijoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/activos-fijos")
public class ActivoFijoApi {

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
        return activoFijoBl.list(page, size);
    }

    @PostMapping("/registrar")
    public ActivoFijoDto registrarActivoFijo(
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "valor") BigDecimal valor,
            @RequestParam(name = "fechaCompra") Date fechaCompra,
            @RequestParam(name = "descripcion") String descripcion,
            @RequestParam(name = "porcentajeDepreciacion") Integer porcentajeDepreciacion,
            @RequestParam(name = "fechaRegistro") Date fechaRegistro,
            @RequestParam(name = "tipoActivoId") Integer tipoActivoId,
            @RequestParam(name = "marcaId") Integer marcaId,
            @RequestParam(name = "ubicacionId") Integer ubicacionId,
            @RequestParam(name = "personalId") Integer personalId,
            @RequestParam(name = "estadoId") Integer estadoId,
            @RequestParam(name = "condicionId") Integer condicionId,
            @RequestParam(name = "estado") Boolean estado
    ) throws IOException {
        return activoFijoBl.registrar(
                nombre,
                valor,
                fechaCompra,
                descripcion,
                porcentajeDepreciacion,
                fechaRegistro,
                tipoActivoId,
                marcaId,
                ubicacionId,
                personalId,
                estadoId,
                condicionId,
                estado
        );
    }
}
