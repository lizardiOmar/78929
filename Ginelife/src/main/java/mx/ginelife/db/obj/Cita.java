package mx.ginelife.db.obj;


public class Cita {
    private int idCitas;
    private String nombre;
    private String hora;
    private String fecha;
    private String descripcion;
    private int idGinecologa;
    private int estado;
    
    public Cita(String hora) {
        this.hora = hora;
    }
    
    public Cita(int idCitas, String hora, String fecha, int estado) {
        this.idCitas = idCitas;
        this.hora = hora;
        this.fecha = fecha;
        this.estado = estado;
    }
    public Cita(int idCitas, String nombre, String hora, String fecha, String descripcion, int idGinecologa, int estado) {
        this.idCitas = idCitas;
        this.nombre = nombre;
        this.hora = hora;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.idGinecologa = idGinecologa;
        this.estado = estado;
    }
    public int getIdCitas() {
        return idCitas;
    }
    public void setIdCitas(int idCitas) {
        this.idCitas = idCitas;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getIdGinecologa() {
        return idGinecologa;
    }
    public void setIdGinecologa(int idGinecologa) {
        this.idGinecologa = idGinecologa;
    }
    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cita [descripcion=" + descripcion + ", estado=" + estado + ", fecha=" + fecha + ", hora=" + hora
                + ", idCitas=" + idCitas + ", idGinecologa=" + idGinecologa + ", nombre=" + nombre + "]";
    }
    
}
