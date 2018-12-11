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
public class Etapas {
    private int id_etapa;
    private String nombre_etapa;

    public Etapas() {
    }

    public Etapas(int id_etapa, String nombre_etapa) {
        this.id_etapa = id_etapa;
        this.nombre_etapa = nombre_etapa;
    }

    public int getId_etapa() {
        return id_etapa;
    }

    public void setId_etapa(int id_etapa) {
        this.id_etapa = id_etapa;
    }

    public String getNombre_etapa() {
        return nombre_etapa;
    }

    public void setNombre_etapa(String nombre_etapa) {
        this.nombre_etapa = nombre_etapa;
    }

    
    
}
