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
public class Unidades {
    private int id_unidad;
    private String descripcion_unidad;
    Asignaturas asignatura;

    public Unidades() {
    }

    public Unidades(int id_unidad, String descripcion_unidad, Asignaturas asignatura) {
        this.id_unidad = id_unidad;
        this.descripcion_unidad = descripcion_unidad;
        this.asignatura = asignatura;
    }

    public int getId_unidad() {
        return id_unidad;
    }

    public void setId_unidad(int id_unidad) {
        this.id_unidad = id_unidad;
    }

    public String getDescripcion_unidad() {
        return descripcion_unidad;
    }

    public void setDescripcion_unidad(String descripcion_unidad) {
        this.descripcion_unidad = descripcion_unidad;
    }

    public Asignaturas getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignaturas asignatura) {
        this.asignatura = asignatura;
    }
    
    
}
