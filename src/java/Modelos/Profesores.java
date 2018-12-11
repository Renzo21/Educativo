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
public class Profesores {
    private int id_profesor;
    private String nombre_profesor;
    private String apellido_profesor;
    private String ci_profesor;
    private Generos genero;
    private String direccion_profesor;
    private String telefono_profesor;
    private Nacionalidades nacionalidad;
    private Ciudades ciudad;

    public Profesores() {
    }

    public Profesores(int id_profesor, String nombre_profesor, String apellido_profesor, String ci_profesor, Generos genero, String direccion_profesor, String telefono_profesor, Nacionalidades nacionalidad, Ciudades ciudad) {
        this.id_profesor = id_profesor;
        this.nombre_profesor = nombre_profesor;
        this.apellido_profesor = apellido_profesor;
        this.ci_profesor = ci_profesor;
        this.genero = genero;
        this.direccion_profesor = direccion_profesor;
        this.telefono_profesor = telefono_profesor;
        this.nacionalidad = nacionalidad;
        this.ciudad = ciudad;
    }

    public int getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(int id_profesor) {
        this.id_profesor = id_profesor;
    }

    public String getNombre_profesor() {
        return nombre_profesor;
    }

    public void setNombre_profesor(String nombre_profesor) {
        this.nombre_profesor = nombre_profesor;
    }

    public String getApellido_profesor() {
        return apellido_profesor;
    }

    public void setApellido_profesor(String apellido_profesor) {
        this.apellido_profesor = apellido_profesor;
    }

    public String getCi_profesor() {
        return ci_profesor;
    }

    public void setCi_profesor(String ci_profesor) {
        this.ci_profesor = ci_profesor;
    }

    public Generos getGenero() {
        return genero;
    }

    public void setGenero(Generos genero) {
        this.genero = genero;
    }

    public String getDireccion_profesor() {
        return direccion_profesor;
    }

    public void setDireccion_profesor(String direccion_profesor) {
        this.direccion_profesor = direccion_profesor;
    }

    public String getTelefono_profesor() {
        return telefono_profesor;
    }

    public void setTelefono_profesor(String telefono_profesor) {
        this.telefono_profesor = telefono_profesor;
    }

    public Nacionalidades getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Nacionalidades nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Ciudades getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }

    
}
