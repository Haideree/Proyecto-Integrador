/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelado;

/**
 *
 * @author Haider
 */

public class Predio {

    private int numRegistroIca;        // PK
    private String nombre;
    private String departamento;
    private String municipio;
    private String vereda;
    
    private Integer numeroDocProductor;  // Puede ser null
    private Integer numeroRegIca;        // Puede ser null
    private Integer numeroDocProp;       // Puede ser null

    public Predio() {
    }

    public Predio(int numRegistroIca, String nombre, String departamento, String municipio,
                  String vereda, Integer numeroDocProductor, Integer numeroRegIca, Integer numeroDocProp) {
        this.numRegistroIca = numRegistroIca;
        this.nombre = nombre;
        this.departamento = departamento;
        this.municipio = municipio;
        this.vereda = vereda;
        this.numeroDocProductor = numeroDocProductor;
        this.numeroRegIca = numeroRegIca;
        this.numeroDocProp = numeroDocProp;
    }

    public int getNumRegistroIca() {
        return numRegistroIca;
    }

    public void setNumRegistroIca(int numRegistroIca) {
        this.numRegistroIca = numRegistroIca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getVereda() {
        return vereda;
    }

    public void setVereda(String vereda) {
        this.vereda = vereda;
    }

    public Integer getNumeroDocProductor() {
        return numeroDocProductor;
    }

    public void setNumeroDocProductor(Integer numeroDocProductor) {
        this.numeroDocProductor = numeroDocProductor;
    }

    public Integer getNumeroRegIca() {
        return numeroRegIca;
    }

    public void setNumeroRegIca(Integer numeroRegIca) {
        this.numeroRegIca = numeroRegIca;
    }

    public Integer getNumeroDocProp() {
        return numeroDocProp;
    }

    public void setNumeroDocProp(Integer numeroDocProp) {
        this.numeroDocProp = numeroDocProp;
    }
}
