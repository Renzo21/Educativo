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
public class Alumnos {
    private int id_alumno;
    private String nombre_alumno;
    private String apellido_alumno;
    private String ci_alumno;
    private Generos genero;
    private Nacionalidades nacionalidad;
    private Ciudades ciudad;
    private String telefono_alumno;
    private String obs_alumno;

    public Alumnos() {
    }

    public Alumnos(int id_alumno, String nombre_alumno, String apellido_alumno, String ci_alumno, Generos genero, Nacionalidades nacionalidad, Ciudades ciudad, String telefono_alumno, String obs_alumno) {
        this.id_alumno = id_alumno;
        this.nombre_alumno = nombre_alumno;
        this.apellido_alumno = apellido_alumno;
        this.ci_alumno = ci_alumno;
        this.genero = genero;
        this.nacionalidad = nacionalidad;
        this.ciudad = ciudad;
        this.telefono_alumno = telefono_alumno;
        this.obs_alumno = obs_alumno;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getNombre_alumno() {
        return nombre_alumno;
    }

    public void setNombre_alumno(String nombre_alumno) {
        this.nombre_alumno = nombre_alumno;
    }

    public String getApellido_alumno() {
        return apellido_alumno;
    }

    public void setApellido_alumno(String apellido_alumno) {
        this.apellido_alumno = apellido_alumno;
    }

    public String getCi_alumno() {
        return ci_alumno;
    }

    public void setCi_alumno(String ci_alumno) {
        this.ci_alumno = ci_alumno;
    }

    public Generos getGenero() {
        return genero;
    }

    public void setGenero(Generos genero) {
        this.genero = genero;
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

    public String getTelefono_alumno() {
        return telefono_alumno;
    }

    public void setTelefono_alumno(String telefono_alumno) {
        this.telefono_alumno = telefono_alumno;
    }

    public String getObs_alumno() {
        return obs_alumno;
    }

    public void setObs_alumno(String obs_alumno) {
        this.obs_alumno = obs_alumno;
    }

    
}
