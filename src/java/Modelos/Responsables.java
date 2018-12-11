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
public class Responsables {
    private int id_responsable;
    private String nombre_responsable;
    private String apellido_responsable;
    private String ci_responsable;
    private String direccion_responsable;
    private String telefono_responsable;
    private Ciudades ciudad;

    public Responsables() {
    }

    public Responsables(int id_responsable, String nombre_responsable, String apellido_responsable, String ci_responsable, String direccion_responsable, String telefono_responsable, Ciudades ciudad) {
        this.id_responsable = id_responsable;
        this.nombre_responsable = nombre_responsable;
        this.apellido_responsable = apellido_responsable;
        this.ci_responsable = ci_responsable;
        this.direccion_responsable = direccion_responsable;
        this.telefono_responsable = telefono_responsable;
        this.ciudad = ciudad;
    }

    public int getId_responsable() {
        return id_responsable;
    }

    public void setId_responsable(int id_responsable) {
        this.id_responsable = id_responsable;
    }

    public String getNombre_responsable() {
        return nombre_responsable;
    }

    public void setNombre_responsable(String nombre_responsable) {
        this.nombre_responsable = nombre_responsable;
    }

    public String getApellido_responsable() {
        return apellido_responsable;
    }

    public void setApellido_responsable(String apellido_responsable) {
        this.apellido_responsable = apellido_responsable;
    }

    public String getCi_responsable() {
        return ci_responsable;
    }

    public void setCi_responsable(String ci_responsable) {
        this.ci_responsable = ci_responsable;
    }

    public String getDireccion_responsable() {
        return direccion_responsable;
    }

    public void setDireccion_responsable(String direccion_responsable) {
        this.direccion_responsable = direccion_responsable;
    }

    public String getTelefono_responsable() {
        return telefono_responsable;
    }

    public void setTelefono_responsable(String telefono_responsable) {
        this.telefono_responsable = telefono_responsable;
    }

    public Ciudades getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }

    
}
