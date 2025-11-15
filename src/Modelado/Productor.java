package Modelado;

public class Productor {
    private int documento;
    private String nombre;
    private String telefono;
    private String correo;
    private String contrasena;

    // IDs para relaciones (necesarios para eliminar en cascada)
    private int idTelefono;
    private int idCorreo;

    // Constructor completo (7 parámetros)
    public Productor(int documento, String nombre, String telefono, String correo,
                     String contrasena, int idTelefono, int idCorreo) {
        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasena = contrasena;
        this.idTelefono = idTelefono;
        this.idCorreo = idCorreo;
    }

    // Getters y setters
    public int getDocumento() { return documento; }
    public void setDocumento(int documento) { this.documento = documento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public int getIdTelefono() { return idTelefono; }
    public void setIdTelefono(int idTelefono) { this.idTelefono = idTelefono; }

    public int getIdCorreo() { return idCorreo; }
    public void setIdCorreo(int idCorreo) { this.idCorreo = idCorreo; }
}

