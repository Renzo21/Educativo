/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.Date;

/**
 *
 * @author Administrator
 */
public class Inscripciones {
    private int id_inscripcion;
    private Date fecha_inscripcion;
    private String estado_inscripcion;
    private Convocatorias convocatoria;
    private Alumnos alumno;

    public Inscripciones() {
    }

    public Inscripciones(int id_inscripcion, Date fecha_inscripcion, String estado_inscripcion, Convocatorias convocatoria, Alumnos alumno) {
        this.id_inscripcion = id_inscripcion;
        this.fecha_inscripcion = fecha_inscripcion;
        this.estado_inscripcion = estado_inscripcion;
        this.convocatoria = convocatoria;
        this.alumno = alumno;
    }

    public int getId_inscripcion() {
        return id_inscripcion;
    }

    public void setId_inscripcion(int id_inscripcion) {
        this.id_inscripcion = id_inscripcion;
    }

    public Date getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public void setFecha_inscripcion(Date fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }

    public String getEstado_inscripcion() {
        return estado_inscripcion;
    }

    public void setEstado_inscripcion(String estado_inscripcion) {
        this.estado_inscripcion = estado_inscripcion;
    }

    public Convocatorias getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(Convocatorias convocatoria) {
        this.convocatoria = convocatoria;
    }

    public Alumnos getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumnos alumno) {
        this.alumno = alumno;
    }
    
    
}
