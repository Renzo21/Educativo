/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.Date;

/**
 *
 * @author Administrator
 */
public class DetallesJustificativos {
    private int id_detallejustificativos;
    private Date fecha_justificativo;
    private Justificativos justificativo;
    private Inscripciones inscripcion;

    public DetallesJustificativos() {
    }

    public DetallesJustificativos(int id_detallejustificativos, Date fecha_justificativo, Justificativos justificativo, Inscripciones inscripcion) {
        this.id_detallejustificativos = id_detallejustificativos;
        this.fecha_justificativo = fecha_justificativo;
        this.justificativo = justificativo;
        this.inscripcion = inscripcion;
    }

    public int getId_detallejustificativos() {
        return id_detallejustificativos;
    }

    public void setId_detallejustificativos(int id_detallejustificativos) {
        this.id_detallejustificativos = id_detallejustificativos;
    }

    public Date getFecha_justificativo() {
        return fecha_justificativo;
    }

    public void setFecha_justificativo(Date fecha_justificativo) {
        this.fecha_justificativo = fecha_justificativo;
    }

    public Justificativos getJustificativo() {
        return justificativo;
    }

    public void setJustificativo(Justificativos justificativo) {
        this.justificativo = justificativo;
    }

    public Inscripciones getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripciones inscripcion) {
        this.inscripcion = inscripcion;
    }
    
    
}
