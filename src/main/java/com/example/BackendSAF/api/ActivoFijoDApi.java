package com.example.BackendSAF.api;

import com.example.BackendSAF.bl.ActivoFijoDBl;
import com.example.BackendSAF.dto.TiempoDto;
import com.example.BackendSAF.dto.TipoActivoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/depreciados")
public class ActivoFijoDApi {
    @Autowired
    private ActivoFijoDBl activoFijoDBl;
    @GetMapping("/tiempo")
    public List<TiempoDto> obtenerListaDeTiempo() {
        return activoFijoDBl.getTie();
    }
}
