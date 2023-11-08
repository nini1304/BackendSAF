package com.example.BackendSAF.api;

import com.example.BackendSAF.bl.ActivoFijoHBl;
import com.example.BackendSAF.dto.ACtivoFijoHListDto;
import com.example.BackendSAF.dto.ActivoFijoListDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/activos-fijos-H")
public class ActivoFIjoHApi {
    private final ActivoFijoHBl activoFijoHBl;


    public ActivoFIjoHApi(ActivoFijoHBl activoFijoHBl) {
        this.activoFijoHBl=activoFijoHBl;
    }
    @GetMapping("/actFH/")
    public List<ACtivoFijoHListDto> obtenerListaActivosFijosHistoricos() throws ParseException {
        return  activoFijoHBl.getAct();
    }
    @GetMapping("/actFHEmpresa/")
    public List<ACtivoFijoHListDto> obtenerListaActivosFijosHistoricoEmpresa(@RequestParam(name="idEmp")Long idEmp) throws ParseException {
        return  activoFijoHBl.getActByEmpresaid(idEmp);
    }
}
