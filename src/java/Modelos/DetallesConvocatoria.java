/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Luisao
 */
public class DetallesConvocatoria {
    private int id_detalleconvocatoria;
    private Convocatorias convocatoria; 
    private Profesores profesor;
    private Asignaturas asignatura;
    private Horarios horario;
    private Frecuencias frecuencia;
    private Aulas aula;

    public DetallesConvocatoria() {
    }

    public DetallesConvocatoria(int id_detalleconvocatoria, Convocatorias convocatoria, Profesores profesor, Asignaturas asignatura, Horarios horario, Frecuencias frecuencia, Aulas aula) {
        this.id_detalleconvocatoria = id_detalleconvocatoria;
        this.convocatoria = convocatoria;
        this.profesor = profesor;
        this.asignatura = asignatura;
        this.horario = horario;
        this.frecuencia = frecuencia;
        this.aula = aula;
    }

    public int getId_detalleconvocatoria() {
        return id_detalleconvocatoria;
    }

    public void setId_detalleconvocatoria(int id_detalleconvocatoria) {
        this.id_detalleconvocatoria = id_detalleconvocatoria;
    }

    public Convocatorias getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(Convocatorias convocatoria) {
        this.convocatoria = convocatoria;
    }

    public Profesores getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesores profesor) {
        this.profesor = profesor;
    }

    public Asignaturas getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignaturas asignatura) {
        this.asignatura = asignatura;
    }

    public Horarios getHorario() {
        return horario;
    }

    public void setHorario(Horarios horario) {
        this.horario = horario;
    }

    public Frecuencias getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Frecuencias frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Aulas getAula() {
        return aula;
    }

    public void setAula(Aulas aula) {
        this.aula = aula;
    }

    
}
