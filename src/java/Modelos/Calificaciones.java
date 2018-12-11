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
public class Calificaciones {
    private int id_calificaciones;
    private Date fecha_calificacion;
    private Etapas etapa;
    private DetallesConvocatoria detalleconvocatoria;

    public Calificaciones() {
    }

    public Calificaciones(int id_calificaciones, Date fecha_calificacion, Etapas etapa, DetallesConvocatoria detalleconvocatoria) {
        this.id_calificaciones = id_calificaciones;
        this.fecha_calificacion = fecha_calificacion;
        this.etapa = etapa;
        this.detalleconvocatoria = detalleconvocatoria;
    }

    public int getId_calificaciones() {
        return id_calificaciones;
    }

    public void setId_calificaciones(int id_calificaciones) {
        this.id_calificaciones = id_calificaciones;
    }

    public Date getFecha_calificacion() {
        return fecha_calificacion;
    }

    public void setFecha_calificacion(Date fecha_calificacion) {
        this.fecha_calificacion = fecha_calificacion;
    }

    public Etapas getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapas etapa) {
        this.etapa = etapa;
    }

    public DetallesConvocatoria getDetalleconvocatoria() {
        return detalleconvocatoria;
    }

    public void setDetalleconvocatoria(DetallesConvocatoria detalleconvocatoria) {
        this.detalleconvocatoria = detalleconvocatoria;
    }

    
}
