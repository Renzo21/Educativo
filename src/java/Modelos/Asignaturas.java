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
public class Asignaturas {
    private int id_asignatura;
    private String nombre_asignatura;

    public Asignaturas() {
    }

    public Asignaturas(int id_asignatura, String nombre_asignatura) {
        this.id_asignatura = id_asignatura;
        this.nombre_asignatura = nombre_asignatura;
    }

    public int getId_asignatura() {
        return id_asignatura;
    }

    public void setId_asignatura(int id_asignatura) {
        this.id_asignatura = id_asignatura;
    }

    public String getNombre_asignatura() {
        return nombre_asignatura;
    }

    public void setNombre_asignatura(String nombre_asignatura) {
        this.nombre_asignatura = nombre_asignatura;
    }

    
    
    
}
