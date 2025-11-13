package Modelado;

public class Tecnico {
    private int identificacion;
    private long tarjetapro;
    private String nombre;
    private String correo;
    private String telefono;
    private String contrasena;
    private String tipoTecnico;

    // 🔹 Constructor
    public Tecnico(int identificacion, long tarjetapro, String nombre, String telefono, String correo, String contrasena, String tipoTecnico) {
        this.identificacion = identificacion;
        this.tarjetapro = tarjetapro;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasena = contrasena;
        this.tipoTecnico = tipoTecnico;
    }

    // 🔹 Getters y Setters
    public int getIdentificacion() { return identificacion; }
    public void setIdentificacion(int identificacion) { this.identificacion = identificacion; }

    public long getTarjetapro() { return tarjetapro; }
    public void setTarjetapro(long tarjeApro) { this.tarjetapro = tarjetapro; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getTipoTecnico() { return tipoTecnico; }
    public void setTipoTecnico(String tipoTecnico) { this.tipoTecnico = tipoTecnico; }
}
