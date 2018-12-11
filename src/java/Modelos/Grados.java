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
public class Grados {
    private int id_grado;
    private String nombre_grado;

    public Grados() {
    }

    public Grados(int id_grado, String nombre_grado) {
        this.id_grado = id_grado;
        this.nombre_grado = nombre_grado;
    }

    public int getId_grado() {
        return id_grado;
    }

    public void setId_grado(int id_grado) {
        this.id_grado = id_grado;
    }

    public String getNombre_grado() {
        return nombre_grado;
    }

    public void setNombre_grado(String nombre_grado) {
        this.nombre_grado = nombre_grado;
    }

    
    
}
