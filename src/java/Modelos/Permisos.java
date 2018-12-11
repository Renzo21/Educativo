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
public class Permisos {
    private int id_permiso;
    private String motivo_permiso;

    public Permisos() {
    }

    public Permisos(int id_permiso, String motivo_permiso) {
        this.id_permiso = id_permiso;
        this.motivo_permiso = motivo_permiso;
    }

    public int getId_permiso() {
        return id_permiso;
    }

    public void setId_permiso(int id_permiso) {
        this.id_permiso = id_permiso;
    }

    public String getMotivo_permiso() {
        return motivo_permiso;
    }

    public void setMotivo_permiso(String motivo_permiso) {
        this.motivo_permiso = motivo_permiso;
    }
    
    
}
