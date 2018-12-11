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
public class Justificativos {
    private int id_justificativo;
    private String motivo_justificativo;

    public Justificativos() {
    }

    public Justificativos(int id_justificativo, String motivo_justificativo) {
        this.id_justificativo = id_justificativo;
        this.motivo_justificativo = motivo_justificativo;
    }

    public int getId_justificativo() {
        return id_justificativo;
    }

    public void setId_justificativo(int id_justificativo) {
        this.id_justificativo = id_justificativo;
    }

    public String getMotivo_justificativo() {
        return motivo_justificativo;
    }

    public void setMotivo_justificativo(String motivo_justificativo) {
        this.motivo_justificativo = motivo_justificativo;
    }
    
    
}
