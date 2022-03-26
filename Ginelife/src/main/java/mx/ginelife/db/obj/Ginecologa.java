package mx.ginelife.db.obj;

public class Ginecologa {
    private int idGinecologa;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String cedulaProfesional;
    private String cedulaEspecialista;
    private String telefono;
    private String correo;
    private String clave;
    public Ginecologa(int idGinecologa, String nombres, String apellidoPaterno, String apellidoMaterno,
            String cedulaProfesional, String cedulaEspecialista, String telefono, String correo, String clave) {
        this.idGinecologa = idGinecologa;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.cedulaProfesional = cedulaProfesional;
        this.cedulaEspecialista = cedulaEspecialista;
        this.telefono = telefono;
        this.correo = correo;
        this.clave = clave;
    }
    public int getIdGinecologa() {
        return idGinecologa;
    }
    public void setIdGinecologa(int idGinecologa) {
        this.idGinecologa = idGinecologa;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public String getCedulaProfesional() {
        return cedulaProfesional;
    }
    public void setCedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }
    public String getCedulaEspecialista() {
        return cedulaEspecialista;
    }
    public void setCedulaEspecialista(String cedulaEspecialista) {
        this.cedulaEspecialista = cedulaEspecialista;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
    @Override
    public String toString() {
        return "Ginecologa [apellidoMaterno=" + apellidoMaterno + ", apellidoPaterno=" + apellidoPaterno
                + ", cedulaEspecialista=" + cedulaEspecialista + ", cedulaProfesional=" + cedulaProfesional + ", clave="
                + clave + ", correo=" + correo + ", idGinecologa=" + idGinecologa + ", nombres=" + nombres
                + ", telefono=" + telefono + "]";
    }
    
}
