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
public class Aulas {
    private int id_aula;
    private String nombre_aula;

    public Aulas() {
    }

    public Aulas(int id_aula, String nombre_aula) {
        this.id_aula = id_aula;
        this.nombre_aula = nombre_aula;
    }

    public int getId_aula() {
        return id_aula;
    }

    public void setId_aula(int id_aula) {
        this.id_aula = id_aula;
    }

    public String getNombre_aula() {
        return nombre_aula;
    }

    public void setNombre_aula(String nombre_aula) {
        this.nombre_aula = nombre_aula;
    }

    
    
}
