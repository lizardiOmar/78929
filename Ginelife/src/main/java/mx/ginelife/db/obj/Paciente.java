package mx.ginelife.db.obj;

public class Paciente {
    private Integer idPaciente;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String edad;
    private String estadoCivil;
    private String ocupacion;
    private String escolaridad;
    private String ciudadDeNacimiento;
    private String entidadDeNacimiento;
    private String telefono;
    private String correo;
    private Integer idGinecologa;
    private String fechaDeNacimiento;
    public Paciente(Integer idPaciente, String nombres, String apellidoPaterno, String apellidoMaterno, String edad,
            String estadoCivil, String ocupacion, String escolaridad, String ciudadDeNacimiento,
            String entidadDeNacimiento, String telefono, String correo, Integer idGinecologa,
            String fechaDeNacimiento) {
        this.idPaciente = idPaciente;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.edad = edad;
        this.estadoCivil = estadoCivil;
        this.ocupacion = ocupacion;
        this.escolaridad = escolaridad;
        this.ciudadDeNacimiento = ciudadDeNacimiento;
        this.entidadDeNacimiento = entidadDeNacimiento;
        this.telefono = telefono;
        this.correo = correo;
        this.idGinecologa = idGinecologa;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }
    public Integer getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
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
    public String getEdad() {
        return edad;
    }
    public void setEdad(String edad) {
        this.edad = edad;
    }
    public String getEstadoCivil() {
        return estadoCivil;
    }
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
    public String getOcupacion() {
        return ocupacion;
    }
    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }
    public String getEscolaridad() {
        return escolaridad;
    }
    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }
    public String getCiudadDeNacimiento() {
        return ciudadDeNacimiento;
    }
    public void setCiudadDeNacimiento(String ciudadDeNacimiento) {
        this.ciudadDeNacimiento = ciudadDeNacimiento;
    }
    public String getEntidadDeNacimiento() {
        return entidadDeNacimiento;
    }
    public void setEntidadDeNacimiento(String entidadDeNacimiento) {
        this.entidadDeNacimiento = entidadDeNacimiento;
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
    public Integer getIdGinecologa() {
        return idGinecologa;
    }
    public void setIdGinecologa(Integer idGinecologa) {
        this.idGinecologa = idGinecologa;
    }
    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }
    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }
    @Override
    public String toString() {
        return "Paciente [apellidoMaterno=" + apellidoMaterno + ", apellidoPaterno=" + apellidoPaterno
                + ", ciudadDeNacimiento=" + ciudadDeNacimiento + ", correo=" + correo + ", edad=" + edad
                + ", entidadDeNacimiento=" + entidadDeNacimiento + ", escolaridad=" + escolaridad + ", estadoCivil="
                + estadoCivil + ", fechaDeNacimiento=" + fechaDeNacimiento + ", idGinecologa=" + idGinecologa
                + ", idPaciente=" + idPaciente + ", nombres=" + nombres + ", ocupacion=" + ocupacion + ", telefono="
                + telefono + "]";
    } 
}
