/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Luisao
 */
public class DetallesMallas {
    private int id_detallemalla;
    private String cargahoraria_total;
    private Mallas malla;
    private Asignaturas asignatura;

    public DetallesMallas() {
    }

    public DetallesMallas(int id_detallemalla, String cargahoraria_total, Mallas malla, Asignaturas asignatura) {
        this.id_detallemalla = id_detallemalla;
        this.cargahoraria_total = cargahoraria_total;
        this.malla = malla;
        this.asignatura = asignatura;
    }

    public int getId_detallemalla() {
        return id_detallemalla;
    }

    public void setId_detallemalla(int id_detallemalla) {
        this.id_detallemalla = id_detallemalla;
    }

    public String getCargahoraria_total() {
        return cargahoraria_total;
    }

    public void setCargahoraria_total(String cargahoraria_total) {
        this.cargahoraria_total = cargahoraria_total;
    }

    public Mallas getMalla() {
        return malla;
    }

    public void setMalla(Mallas malla) {
        this.malla = malla;
    }

    public Asignaturas getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignaturas asignatura) {
        this.asignatura = asignatura;
    }

    
    
}
