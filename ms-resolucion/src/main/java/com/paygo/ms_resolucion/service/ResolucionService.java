package com.paygo.ms_resolucion.service;

import com.paygo.ms_resolucion.client.ReclamoClient;
import com.paygo.ms_resolucion.dto.ReclamoDTO;
import com.paygo.ms_resolucion.dto.ResolucionRequest;
import com.paygo.ms_resolucion.exception.ReclamoNoEncontradoException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResolucionService {

    @Autowired
    private ReclamoClient reclamoClient;

    public ReclamoDTO resolverReclamo(Long numreclamo, ResolucionRequest request) {

        // 1. Validar que el reclamo existe, llamando SINCRONICAMENTE via OpenFeign
        ReclamoDTO reclamo;
        try {
            reclamo = reclamoClient.obtenerReclamo(numreclamo);
        } catch (FeignException.NotFound e) {
            throw new ReclamoNoEncontradoException(numreclamo);
        }

        // 2. Validar que el estado enviado sea válido
        if (request.getEstado() == null ||
                (request.getEstado() != 2 && request.getEstado() != 3)) {
            throw new IllegalArgumentException(
                    "El estado debe ser 2 (procedente) o 3 (improcedente)");
        }

        // 3. Actualizar los campos segun el resultado de la resolucion
        reclamo.setEstado(request.getEstado());
        reclamo.setAnalista(request.getAnalista());

        if (request.getEstado() == 2) {
            reclamo.setObservacion(
                    "Reclamo procedente. Se gestionara la reversion del consumo no reconocido.");
        } else {
            reclamo.setObservacion(
                    "Reclamo improcedente. No se encontro evidencia de un consumo no autorizado.");
        }

        // 4. Enviar la actualizacion de vuelta al servicio de reclamos
        return reclamoClient.actualizarReclamo(numreclamo, reclamo);
    }
}
