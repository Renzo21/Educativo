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
public class DetallesCalificaciones {
    private int id_detallecalificacion;
    private int nota_final;
    private Inscripciones inscripcion;
    private Calificaciones calificacion;

    public DetallesCalificaciones() {
    }

    public DetallesCalificaciones(int id_detallecalificacion, int nota_final, Inscripciones inscripcion, Calificaciones calificacion) {
        this.id_detallecalificacion = id_detallecalificacion;
        this.nota_final = nota_final;
        this.inscripcion = inscripcion;
        this.calificacion = calificacion;
    }

    public int getId_detallecalificacion() {
        return id_detallecalificacion;
    }

    public void setId_detallecalificacion(int id_detallecalificacion) {
        this.id_detallecalificacion = id_detallecalificacion;
    }

    public int getNota_final() {
        return nota_final;
    }

    public void setNota_final(int nota_final) {
        this.nota_final = nota_final;
    }

    public Inscripciones getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripciones inscripcion) {
        this.inscripcion = inscripcion;
    }

    public Calificaciones getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Calificaciones calificacion) {
        this.calificacion = calificacion;
    }

    
}
