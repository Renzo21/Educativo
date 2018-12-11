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
public class Asistencias {
    private int id_asistencia;
    private Date fecha_asistencia;
    private Convocatorias convocatoria;
    private DetallesConvocatoria detalleconvocatoria;

    public Asistencias() {
    }

    public Asistencias(int id_asistencia, Date fecha_asistencia, Convocatorias convocatoria, DetallesConvocatoria detalleconvocatoria) {
        this.id_asistencia = id_asistencia;
        this.fecha_asistencia = fecha_asistencia;
        this.convocatoria = convocatoria;
        this.detalleconvocatoria = detalleconvocatoria;
    }

    public int getId_asistencia() {
        return id_asistencia;
    }

    public void setId_asistencia(int id_asistencia) {
        this.id_asistencia = id_asistencia;
    }

    public Date getFecha_asistencia() {
        return fecha_asistencia;
    }

    public void setFecha_asistencia(Date fecha_asistencia) {
        this.fecha_asistencia = fecha_asistencia;
    }

    public Convocatorias getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(Convocatorias convocatoria) {
        this.convocatoria = convocatoria;
    }

    public DetallesConvocatoria getDetalleconvocatoria() {
        return detalleconvocatoria;
    }

    public void setDetalleconvocatria(DetallesConvocatoria detalleconvocatoria) {
        this.detalleconvocatoria = detalleconvocatoria;
    }

    
}
