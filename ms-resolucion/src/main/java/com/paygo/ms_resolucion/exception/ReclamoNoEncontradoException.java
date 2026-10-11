package com.paygo.ms_resolucion.exception;

public class ReclamoNoEncontradoException extends RuntimeException {
    public ReclamoNoEncontradoException(Long numreclamo) {
        super("No existe el reclamo N° " + numreclamo);
    }
}
