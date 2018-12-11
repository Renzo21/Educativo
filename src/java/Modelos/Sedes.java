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
public class Sedes {
    private int id_sede;
    private String nombre_sede;
    private String direccion_sede;
    private String telefono_sede;
    private String email_sede;
    private String estado_sede;

    public Sedes() {
    }

    public Sedes(int id_sede, String nombre_sede, String direccion_sede, String telefono_sede, String email_sede, String estado_sede) {
        this.id_sede = id_sede;
        this.nombre_sede = nombre_sede;
        this.direccion_sede = direccion_sede;
        this.telefono_sede = telefono_sede;
        this.email_sede = email_sede;
        this.estado_sede = estado_sede;
    }

    public int getId_sede() {
        return id_sede;
    }

    public void setId_sede(int id_sede) {
        this.id_sede = id_sede;
    }

    public String getNombre_sede() {
        return nombre_sede;
    }

    public void setNombre_sede(String nombre_sede) {
        this.nombre_sede = nombre_sede;
    }

    public String getDireccion_sede() {
        return direccion_sede;
    }

    public void setDireccion_sede(String direccion_sede) {
        this.direccion_sede = direccion_sede;
    }

    public String getTelefono_sede() {
        return telefono_sede;
    }

    public void setTelefono_sede(String telefono_sede) {
        this.telefono_sede = telefono_sede;
    }

    public String getEmail_sede() {
        return email_sede;
    }

    public void setEmail_sede(String email_sede) {
        this.email_sede = email_sede;
    }

    public String getEstado_sede() {
        return estado_sede;
    }

    public void setEstado_sede(String estado_sede) {
        this.estado_sede = estado_sede;
    }

    
    
}
