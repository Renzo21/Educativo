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
public class Convocatorias {
    private int id_convocatoria;
    private int cupo_convocatoria;
    private String estado_convocatoria;
    private Sedes sede;
    private Grados grado;
    private Especialidades especialidad;
    private Secciones seccion;
    private Turnos turno;
    private Periodos periodo;

    public Convocatorias() {
    }

    public Convocatorias(int id_convocatoria, int cupo_convocatoria, String estado_convocatoria, Sedes sede, Grados grado, Especialidades especialidad, Secciones seccion, Turnos turno, Periodos periodo) {
        this.id_convocatoria = id_convocatoria;
        this.cupo_convocatoria = cupo_convocatoria;
        this.estado_convocatoria = estado_convocatoria;
        this.sede = sede;
        this.grado = grado;
        this.especialidad = especialidad;
        this.seccion = seccion;
        this.turno = turno;
        this.periodo = periodo;
    }

    public int getId_convocatoria() {
        return id_convocatoria;
    }

    public void setId_convocatoria(int id_convocatoria) {
        this.id_convocatoria = id_convocatoria;
    }

    public int getCupo_convocatoria() {
        return cupo_convocatoria;
    }

    public void setCupo_convocatoria(int cupo_convocatoria) {
        this.cupo_convocatoria = cupo_convocatoria;
    }

    public String getEstado_convocatoria() {
        return estado_convocatoria;
    }

    public void setEstado_convocatoria(String estado_convocatoria) {
        this.estado_convocatoria = estado_convocatoria;
    }

    public Sedes getSede() {
        return sede;
    }

    public void setSede(Sedes sede) {
        this.sede = sede;
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

    public Secciones getSeccion() {
        return seccion;
    }

    public void setSeccion(Secciones seccion) {
        this.seccion = seccion;
    }

    public Turnos getTurno() {
        return turno;
    }

    public void setTurno(Turnos turno) {
        this.turno = turno;
    }

    public Periodos getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodos periodo) {
        this.periodo = periodo;
    }

    
    
}
