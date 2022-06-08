package mx.ginelife.db.obj;

public class AntecedentesTraumaticos {
    private int idAntecedenteTraumatico;
    private String descripcion;
    private String fecha;
    private int idHistoriaClinica;
    public AntecedentesTraumaticos(int idAntecedenteTraumatico, String descripcion, String fecha,
            int idHistoriaClinica) {
        this.idAntecedenteTraumatico = idAntecedenteTraumatico;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.idHistoriaClinica = idHistoriaClinica;
    }
    public int getIdAntecedenteTraumatico() {
        return idAntecedenteTraumatico;
    }
    public void setIdAntecedenteTraumatico(int idAntecedenteTraumatico) {
        this.idAntecedenteTraumatico = idAntecedenteTraumatico;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public int getIdHistoriaClinica() {
        return idHistoriaClinica;
    }
    public void setIdHistoriaClinica(int idHistoriaClinica) {
        this.idHistoriaClinica = idHistoriaClinica;
    }
    @Override
    public String toString() {
        return "AntecedentesTraumaticos [descripcion=" + descripcion + ", fecha=" + fecha + ", idAntecedenteTraumatico="
                + idAntecedenteTraumatico + ", idHistoriaClinica=" + idHistoriaClinica + "]";
    }
    
}
