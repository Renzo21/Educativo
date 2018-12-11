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
public class Mallas {
    private int id_malla;
    private String modalidad_malla;
    private Grados grado;
    private Especialidades especialidad;

    public Mallas() {
    }

    public Mallas(int id_malla, String modalidad_malla, Grados grado, Especialidades especialidad) {
        this.id_malla = id_malla;
        this.modalidad_malla = modalidad_malla;
        this.grado = grado;
        this.especialidad = especialidad;
    }

    public int getId_malla() {
        return id_malla;
    }

    public void setId_malla(int id_malla) {
        this.id_malla = id_malla;
    }

    public String getModalidad_malla() {
        return modalidad_malla;
    }

    public void setModalidad_malla(String modalidad_malla) {
        this.modalidad_malla = modalidad_malla;
    }

    public Grados getGrado() {
        return grado;
    }

    public void setGrado(Grados grado) {
        this.grado = grado;
    }

    public Especialidades getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidades especialidad) {
        this.especialidad = especialidad;
    }
    
    
}
