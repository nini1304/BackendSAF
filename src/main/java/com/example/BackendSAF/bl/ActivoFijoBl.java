package com.example.BackendSAF.bl;

import com.example.BackendSAF.dto.ActivoFijoDto;
import com.example.BackendSAF.entity.ActivoFijoDao;
import com.example.BackendSAF.entity.Repository.ActivoFijoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;


@Service
public class ActivoFijoBl {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivoFijoBl.class);

    @Value("${currency.url}")
    private String url;

    @Value("${currency.key}")
    private String key;

    @Autowired
    private ActivoFijoRepository activofijorepository;

    public Object list(int page, int size) {
        return activofijorepository.findAll(PageRequest.of(page, size));
    }

    public ActivoFijoDto registrar(String nombre,
                                   BigDecimal valor,
                                    Date fechaCompra,
                                    String descripcion,
                                    Integer porcentajeDepreciacion,
                                    Date fechaRegistro,
                                    Integer tipoObjetoId,
                                    Integer marcaId,
                                    Integer ubicacionId,
                                    Integer personalId,
                                    Integer estadoId,
                                    Integer condicionId,
                                    Boolean estado) throws IOException {
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be greater than or equal to 0");
        }

        OkHttpClient client = new OkHttpClient.Builder().build();
        Request req = new Request.Builder()
                .url(url + "?nombre=" + nombre + "&valor=" + valor + "&fechaCompra=" + fechaCompra + "&descripcion=" + descripcion + "&porcentajeDepreciacion=" + porcentajeDepreciacion + "&fechaRegistro=" + fechaRegistro + "&tipoObjetoId=" + tipoObjetoId + "&marcaId=" + marcaId + "&ubicacionId=" + ubicacionId + "&personalId=" + personalId + "&estadoId=" + estadoId + "&condicionId=" + condicionId + "&estado=" + estado )
                .addHeader("apikey", key)
                .build();

        Response response = client.newCall(req).execute();
        String json = response.body().string();

        if (response.isSuccessful()) {
            ActivoFijoDto activoDto = objectMapper.readValue(json, ActivoFijoDto.class);
            ActivoFijoDao activo = new ActivoFijoDao();
            activo.setNombre(nombre);
            activo.setValor(valor);
            activo.setFechaCompra(fechaCompra);
            activo.setDescripcion(descripcion);
            activo.setPorcentajeDepreciacion(porcentajeDepreciacion);
            activo.setFechaRegistro(fechaRegistro);
            activo.setTipoObjetoId(tipoObjetoId);
            activo.setMarcaId(marcaId);
            activo.setUbicacionId(ubicacionId);
            activo.setPersonalId(personalId);
            activo.setEstadoId(estadoId);
            activo.setCondicionId(condicionId);
            activo.setEstado(estado);
            activofijorepository.save(activo);
            LOGGER.info("Conversion result: " + json);
        }

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, ActivoFijoDto.class);
    }
}

