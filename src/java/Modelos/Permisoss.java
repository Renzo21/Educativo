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
public class Permisoss {
    private int id_permisoss;
    private Roles rol;
    private Formularios formulario;

    public Permisoss() {
    }

    public Permisoss(int id_permisoss, Roles rol, Formularios formulario) {
        this.id_permisoss = id_permisoss;
        this.rol = rol;
        this.formulario = formulario;
    }

    public int getId_permisoss() {
        return id_permisoss;
    }

    public void setId_permisoss(int id_permisoss) {
        this.id_permisoss = id_permisoss;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public Formularios getFormulario() {
        return formulario;
    }

    public void setFormulario(Formularios formulario) {
        this.formulario = formulario;
    }

    
    
}
