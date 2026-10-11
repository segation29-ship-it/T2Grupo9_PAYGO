package com.paygo.ms_resolucion.client;

import com.paygo.ms_resolucion.dto.ReclamoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "servicio-reclamos")
public interface ReclamoClient {

    @GetMapping("/api/reclamos/{id}")
    ReclamoDTO obtenerReclamo(@PathVariable("id") Long id);

    @PutMapping("/api/reclamos/{id}")
    ReclamoDTO actualizarReclamo(@PathVariable("id") Long id, @RequestBody ReclamoDTO reclamo);

}
