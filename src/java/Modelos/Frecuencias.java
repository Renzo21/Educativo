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
public class Frecuencias {
    private int id_frecuencia;
    private String nombre_frecuencia;

    public Frecuencias() {
    }

    public Frecuencias(int id_frecuencia, String nombre_frecuencia) {
        this.id_frecuencia = id_frecuencia;
        this.nombre_frecuencia = nombre_frecuencia;
    }

    public int getId_frecuencia() {
        return id_frecuencia;
    }

    public void setId_frecuencia(int id_frecuencia) {
        this.id_frecuencia = id_frecuencia;
    }

    public String getNombre_frecuencia() {
        return nombre_frecuencia;
    }

    public void setNombre_frecuencia(String nombre_frecuencia) {
        this.nombre_frecuencia = nombre_frecuencia;
    }

    
    
}
