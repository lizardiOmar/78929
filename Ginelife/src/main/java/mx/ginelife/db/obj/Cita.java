package mx.ginelife.db.obj;

public class Cita {
    private int idCitas;
    private String nombre;
    private String hora;
    private String dia;
    private String mes;
    private String descripcion;
    private int idGinecologa;
    private int estado;
    
    public Cita(String hora) {
        this.hora = hora;
    }
    
    public Cita(int idCitas, String hora, String dia, String mes, int estado) {
        this.idCitas = idCitas;
        this.hora = hora;
        this.dia = dia;
        this.mes = mes;
        this.estado = estado;
    }
    public Cita(int idCitas, String nombre, String hora, String dia, String mes, String descripcion, int idGinecologa,
            int estado) {
        this.idCitas = idCitas;
        this.nombre = nombre;
        this.hora = hora;
        this.dia = dia;
        this.mes = mes;
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
    public String getDia() {
        return dia;
    }
    public void setDia(String dia) {
        this.dia = dia;
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
    public String getMes() {
        return mes;
    }
    public void setMes(String mes) {
        this.mes = mes;
    }
    public int getEstado() {
        return estado;
    }
    public void setEstado(int estado) {
        this.estado = estado;
    }
    
}
