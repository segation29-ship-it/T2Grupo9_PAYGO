package com.paygo.servicio_reclamos.controller;

import com.paygo.servicio_reclamos.entity.Reclamo;
import com.paygo.servicio_reclamos.repository.ReclamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reclamos")
public class ReclamoController {

    @Autowired
    private ReclamoRepository repository;

    @GetMapping
    public List<Reclamo> listarReclamos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reclamo> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Reclamo registrarReclamo(@RequestBody Reclamo reclamo) {
        if (reclamo.getEstado() == null) reclamo.setEstado(1);
        if (reclamo.getAnalista() == null) reclamo.setAnalista("No asignado");
        if (reclamo.getObservacion() == null) reclamo.setObservacion("por evaluar");
        return repository.save(reclamo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reclamo> actualizarReclamo(@PathVariable Long id, @RequestBody Reclamo reclamoDetalles) {
        return repository.findById(id).map(reclamo -> {
            reclamo.setEstado(reclamoDetalles.getEstado());
            reclamo.setAnalista(reclamoDetalles.getAnalista());
            reclamo.setObservacion(reclamoDetalles.getObservacion());
            Reclamo actualizado = repository.save(reclamo);
            return ResponseEntity.ok(actualizado);
        }).orElse(ResponseEntity.notFound().build());
    }
}