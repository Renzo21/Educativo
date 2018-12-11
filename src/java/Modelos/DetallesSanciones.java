/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.Date;

/**
 *
 * @author ALUMNO
 */
public class DetallesSanciones {
    private int id_detallesancion;
    private Date fecha_sancion;
    private Inscripciones inscripcion;
    private Sanciones sancion;

    public DetallesSanciones() {
    }

    public DetallesSanciones(int id_detallesancion, Date fecha_sancion, Inscripciones inscripcion, Sanciones sancion) {
        this.id_detallesancion = id_detallesancion;
        this.fecha_sancion = fecha_sancion;
        this.inscripcion = inscripcion;
        this.sancion = sancion;
    }

    public int getId_detallesancion() {
        return id_detallesancion;
    }

    public void setId_detallesancion(int id_detallesancion) {
        this.id_detallesancion = id_detallesancion;
    }

    public Date getFecha_sancion() {
        return fecha_sancion;
    }

    public void setFecha_sancion(Date fecha_sancion) {
        this.fecha_sancion = fecha_sancion;
    }

    public Inscripciones getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripciones inscripcion) {
        this.inscripcion = inscripcion;
    }

    public Sanciones getSancion() {
        return sancion;
    }

    public void setSancion(Sanciones sancion) {
        this.sancion = sancion;
    }
    
    
}
