/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author ALUMNO
 */
public class DetallesAlumnos {
    private int id_detallealumnos;
    private String categoria_responsable;
    private Alumnos alumno;
    private Responsables responsable;

    public DetallesAlumnos() {
    }

    public DetallesAlumnos(int id_detallealumnos, String categoria_responsable, Alumnos alumno, Responsables responsable) {
        this.id_detallealumnos = id_detallealumnos;
        this.categoria_responsable = categoria_responsable;
        this.alumno = alumno;
        this.responsable = responsable;
    }

    public int getId_detallealumnos() {
        return id_detallealumnos;
    }

    public void setId_detallealumnos(int id_detallealumnos) {
        this.id_detallealumnos = id_detallealumnos;
    }

    public String getCategoria_responsable() {
        return categoria_responsable;
    }

    public void setCategoria_responsable(String categoria_responsable) {
        this.categoria_responsable = categoria_responsable;
    }

    public Alumnos getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumnos alumno) {
        this.alumno = alumno;
    }

    public Responsables getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsables responsable) {
        this.responsable = responsable;
    }
    
    
}
