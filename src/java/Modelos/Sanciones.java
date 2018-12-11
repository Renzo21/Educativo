/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author ALUMNO
 */
public class Sanciones {
    private int id_sancion;
    private String obs_sancion;
    private Tiposfaltas tipofalta;

    public Sanciones() {
    }

    public Sanciones(int id_sancion, String obs_sancion, Tiposfaltas tipofalta) {
        this.id_sancion = id_sancion;
        this.obs_sancion = obs_sancion;
        this.tipofalta = tipofalta;
    }

    public int getId_sancion() {
        return id_sancion;
    }

    public void setId_sancion(int id_sancion) {
        this.id_sancion = id_sancion;
    }

    public String getObs_sancion() {
        return obs_sancion;
    }

    public void setObs_sancion(String obs_sancion) {
        this.obs_sancion = obs_sancion;
    }

    public Tiposfaltas getTipofalta() {
        return tipofalta;
    }

    public void setTipofalta(Tiposfaltas tipofalta) {
        this.tipofalta = tipofalta;
    }
    
    
}
