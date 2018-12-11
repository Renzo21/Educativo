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
public class Periodos {
    private int id_periodo;
    private String nombre_periodo;

    public Periodos() {
    }

    public Periodos(int id_periodo, String nombre_periodo) {
        this.id_periodo = id_periodo;
        this.nombre_periodo = nombre_periodo;
    }

    public int getId_periodo() {
        return id_periodo;
    }

    public void setId_periodo(int id_periodo) {
        this.id_periodo = id_periodo;
    }

    public String getNombre_periodo() {
        return nombre_periodo;
    }

    public void setNombre_periodo(String nombre_periodo) {
        this.nombre_periodo = nombre_periodo;
    }

    
    
}
