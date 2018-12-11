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
public class Tiposfaltas {
    private int id_tipofalta;
    private String descripcion_tipofalta;

    public Tiposfaltas() {
    }

    public Tiposfaltas(int id_tipofalta, String descripcion_tipofalta) {
        this.id_tipofalta = id_tipofalta;
        this.descripcion_tipofalta = descripcion_tipofalta;
    }

    public int getId_tipofalta() {
        return id_tipofalta;
    }

    public void setId_tipofalta(int id_tipofalta) {
        this.id_tipofalta = id_tipofalta;
    }

    public String getDescripcion_tipofalta() {
        return descripcion_tipofalta;
    }

    public void setDescripcion_tipofalta(String descripcion_tipofalta) {
        this.descripcion_tipofalta = descripcion_tipofalta;
    }

     
    
}
