/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.Date;

/**
 *
 * @author Luisao
 */
public class Controlclases {
    private int id_controlclase;
    private Date fecha_clase;
    private String progreso_clase;
    private Grados grado;
    private Especialidades especialidad;
    private Contenidos contenido;
    private Profesores profesor;

    public Controlclases() {
    }

    public Controlclases(int id_controlclase, Date fecha_clase, String progreso_clase, Grados grado, Especialidades especialidad, Contenidos contenido, Profesores profesor) {
        this.id_controlclase = id_controlclase;
        this.fecha_clase = fecha_clase;
        this.progreso_clase = progreso_clase;
        this.grado = grado;
        this.especialidad = especialidad;
        this.contenido = contenido;
        this.profesor = profesor;
    }

    public int getId_controlclase() {
        return id_controlclase;
    }

    public void setId_controlclase(int id_controlclase) {
        this.id_controlclase = id_controlclase;
    }

    public Date getFecha_clase() {
        return fecha_clase;
    }

    public void setFecha_clase(Date fecha_clase) {
        this.fecha_clase = fecha_clase;
    }

    public String getProgreso_clase() {
        return progreso_clase;
    }

    public void setProgreso_clase(String progreso_clase) {
        this.progreso_clase = progreso_clase;
    }

    public Grados getGrado() {
        return grado;
    }

    public void setGrado(Grados grado) {
        this.grado = grado;
    }

    public Especialidades getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidades especialidad) {
        this.especialidad = especialidad;
    }

    public Contenidos getContenido() {
        return contenido;
    }

    public void setContenido(Contenidos contenido) {
        this.contenido = contenido;
    }

    public Profesores getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesores profesor) {
        this.profesor = profesor;
    }
    
    
}
