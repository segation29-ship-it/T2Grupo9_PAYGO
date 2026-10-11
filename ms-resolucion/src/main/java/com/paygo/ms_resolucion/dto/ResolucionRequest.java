package com.paygo.ms_resolucion.dto;

// Lo que el analista de riesgos envia para resolver un reclamo
public class ResolucionRequest {

    private Integer estado;   // 2 = procedente, 3 = improcedente
    private String analista;  // nombre de quien resuelve

    public Integer getEstado() { return estado; }
    public void setEstado(Integer estado) { this.estado = estado; }

    public String getAnalista() { return analista; }
    public void setAnalista(String analista) { this.analista = analista; }
}
