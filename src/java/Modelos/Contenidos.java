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
public class Contenidos {
    private int id_contenido;
    private String descripcion_contenido;
    private Unidades unidad;

    public Contenidos() {
    }

    public Contenidos(int id_contenido, String descripcion_contenido, Unidades unidad) {
        this.id_contenido = id_contenido;
        this.descripcion_contenido = descripcion_contenido;
        this.unidad = unidad;
    }

    public int getId_contenido() {
        return id_contenido;
    }

    public void setId_contenido(int id_contenido) {
        this.id_contenido = id_contenido;
    }

    public String getDescripcion_contenido() {
        return descripcion_contenido;
    }

    public void setDescripcion_contenido(String descripcion_contenido) {
        this.descripcion_contenido = descripcion_contenido;
    }

    public Unidades getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidades unidad) {
        this.unidad = unidad;
    }
    
    
}
