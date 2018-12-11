/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.Date;

/**
 *
 * @author Luisao
 */
public class DetallesPermisos {
    private int id_detallepermiso;
    private Date fecha_permiso;
    private String descripcion_permiso;
    private Inscripciones inscripcion;
    private Permisos permiso;

    public DetallesPermisos() {
    }

    public DetallesPermisos(int id_detallepermiso, Date fecha_permiso, String descripcion_permiso, Inscripciones inscripcion, Permisos permiso) {
        this.id_detallepermiso = id_detallepermiso;
        this.fecha_permiso = fecha_permiso;
        this.descripcion_permiso = descripcion_permiso;
        this.inscripcion = inscripcion;
        this.permiso = permiso;
    }

    public int getId_detallepermiso() {
        return id_detallepermiso;
    }

    public void setId_detallepermiso(int id_detallepermiso) {
        this.id_detallepermiso = id_detallepermiso;
    }

    public Date getFecha_permiso() {
        return fecha_permiso;
    }

    public void setFecha_permiso(Date fecha_permiso) {
        this.fecha_permiso = fecha_permiso;
    }

    public String getDescripcion_permiso() {
        return descripcion_permiso;
    }

    public void setDescripcion_permiso(String descripcion_permiso) {
        this.descripcion_permiso = descripcion_permiso;
    }

    public Inscripciones getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripciones inscripcion) {
        this.inscripcion = inscripcion;
    }

    public Permisos getPermiso() {
        return permiso;
    }

    public void setPermiso(Permisos permiso) {
        this.permiso = permiso;
    }
    
    
}
