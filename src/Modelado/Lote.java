/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelado;

/**
 *
 * @author Haider
 */

public class Lote {
    private int numLote;
    private double area;
    private int idCultivo;

    public Lote() {}

    public Lote(int numLote, double area, int idCultivo) {
        this.numLote = numLote;
        this.area = area;
        this.idCultivo = idCultivo;
    }

    public int getNumLote() { return numLote; }
    public void setNumLote(int numLote) { this.numLote = numLote; }

    public double getArea() { return area; }
    public void setArea(double area) { this.area = area; }

    public int getIdCultivo() { return idCultivo; }
    public void setIdCultivo(int idCultivo) { this.idCultivo = idCultivo; }
}
