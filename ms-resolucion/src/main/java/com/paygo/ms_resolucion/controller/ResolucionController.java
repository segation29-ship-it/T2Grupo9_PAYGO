package com.paygo.ms_resolucion.controller;

import com.paygo.ms_resolucion.dto.ReclamoDTO;
import com.paygo.ms_resolucion.dto.ResolucionRequest;
import com.paygo.ms_resolucion.exception.ReclamoNoEncontradoException;
import com.paygo.ms_resolucion.service.ResolucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/resolucion")
public class ResolucionController {

    @Autowired
    private ResolucionService resolucionService;

    @PutMapping("/{numreclamo}")
    public ResponseEntity<Map<String, Object>> resolver(
            @PathVariable Long numreclamo,
            @RequestBody ResolucionRequest request) {

        Map<String, Object> respuesta = new HashMap<>();
        try {
            ReclamoDTO actualizado = resolucionService.resolverReclamo(numreclamo, request);
            respuesta.put("mensaje", "Reclamo resuelto correctamente");
            respuesta.put("reclamo", actualizado);
            return ResponseEntity.ok(respuesta);

        } catch (ReclamoNoEncontradoException e) {
            respuesta.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);

        } catch (IllegalArgumentException e) {
            respuesta.put("mensaje", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }
    }
}
