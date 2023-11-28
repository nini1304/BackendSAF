package com.example.BackendSAF.api;

import com.example.BackendSAF.bl.ActivoFijoBl;
import com.example.BackendSAF.bl.ActivoFijoDBl;
import com.example.BackendSAF.bl.PDFReportGenerator;
import com.example.BackendSAF.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/depreciados")
public class ActivoFijoDApi {
    @Autowired
    private ActivoFijoBl activoFijoBl;
    @Autowired
    private ActivoFijoDBl activoFijoDBl;
    @Autowired
    private PDFReportGenerator pdfReportGenerator;

    @GetMapping("/actD")
    public List<ActivoFDDto> obtenerListaActivosFijosD(@RequestParam(name="idEmp")Long idEmp, @RequestParam(name="idTie")Long idTie) {
        return  activoFijoDBl.getActD(idEmp,idTie);
    }
    @GetMapping("/tiempo")
    public List<TiempoDto> obtenerListaDeTiempo(@RequestParam(name="idEmp")Long idEmp) {
        return activoFijoDBl.getTie(idEmp);
    }
    @PostMapping("/pdf")
    public ResponseEntity<String> generarPDFReport(@RequestParam String nombreArchivo, @RequestParam String imageUrl, @RequestParam String username, @RequestParam String empresa, @RequestBody List<ActivoFDDto> activos) {
        pdfReportGenerator.generatePDFReportD(activos, nombreArchivo, imageUrl, username, empresa);
        return ResponseEntity.ok("PDF generado correctamente");
    }
    @PostMapping("/excel")
    public ResponseEntity<String> generarExcel(@RequestParam String nombreArchivo, @RequestBody List<ActivoFDDto> activos) {
        activoFijoBl.generarExcel2(activos, nombreArchivo);
        return ResponseEntity.ok("Excel generado correctamente");
    }
}
