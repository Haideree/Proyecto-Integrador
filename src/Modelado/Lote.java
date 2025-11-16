package Modelado;

/**
 * Clase Lote ajustada a la estructura de la tabla LOTE
 */
public class Lote {
    private int numLote;
    private String nombreLote;
    private double area;
    private int idCultivo;
    private int idLugarProd;
    private String estado;

    // Constructor vacío
    public Lote() {}

    // Constructor con todos los campos
    public Lote(int numLote, String nombreLote, double area, int idCultivo, int idLugarProd, String estado) {
        this.numLote = numLote;
        this.nombreLote = nombreLote;
        this.area = area;
        this.idCultivo = idCultivo;
        this.idLugarProd = idLugarProd;
        this.estado = estado;
    }

    // Getters y setters
    public int getNumLote() { return numLote; }
    public void setNumLote(int numLote) { this.numLote = numLote; }

    public String getNombreLote() { return nombreLote; }
    public void setNombreLote(String nombreLote) { this.nombreLote = nombreLote; }

    public double getArea() { return area; }
    public void setArea(double area) { this.area = area; }

    public int getIdCultivo() { return idCultivo; }
    public void setIdCultivo(int idCultivo) { this.idCultivo = idCultivo; }

    public int getIdLugarProd() { return idLugarProd; }
    public void setIdLugarProd(int idLugarProd) { this.idLugarProd = idLugarProd; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
