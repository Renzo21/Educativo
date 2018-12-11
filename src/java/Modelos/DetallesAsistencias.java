/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Administrator
 */
public class DetallesAsistencias {
    private int id_detalleasistencias;
    private String estado_asistencia;
    private Inscripciones inscripcion;
    private Asistencias asistencia;

    public DetallesAsistencias() {
    }

    public DetallesAsistencias(int id_detalleasistencias, String estado_asistencia, Inscripciones inscripcion, Asistencias asistencia) {
        this.id_detalleasistencias = id_detalleasistencias;
        this.estado_asistencia = estado_asistencia;
        this.inscripcion = inscripcion;
        this.asistencia = asistencia;
    }

    public int getId_detalleasistencias() {
        return id_detalleasistencias;
    }

    public void setId_detalleasistencias(int id_detalleasistencias) {
        this.id_detalleasistencias = id_detalleasistencias;
    }

    public String getEstado_asistencia() {
        return estado_asistencia;
    }

    public void setEstado_asistencia(String estado_asistencia) {
        this.estado_asistencia = estado_asistencia;
    }

    public Inscripciones getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripciones inscripcion) {
        this.inscripcion = inscripcion;
    }

    public Asistencias getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Asistencias asistencia) {
        this.asistencia = asistencia;
    }
    
    
}
