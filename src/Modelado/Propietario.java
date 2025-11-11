/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelado;

public class Propietario {
    private int documento;
    private String nombre;
    private String telefono;
    private String correo;
    private String contrasena;

    public Propietario(int documento, String nombre, String telefono, String correo, String contrasena) {
        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    // Getters
    public int getDocumento() { return documento; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }
    public String getContrasena() { return contrasena; }

    // Setters
    public void setDocumento(int documento) { this.documento = documento; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}
