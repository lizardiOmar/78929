package mx.ginelife.db.obj;

public class AntecedentesQuirurgicos {
    private int idAntecedentesQuirurgicos;
    private String descripcion;
    private String fecha;
    private int idHistoriaClinica;
    public AntecedentesQuirurgicos(int idAntecedentesQuirurgicos, String descripcion, String fecha,
            int idHistoriaClinica) {
        this.idAntecedentesQuirurgicos = idAntecedentesQuirurgicos;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.idHistoriaClinica = idHistoriaClinica;
    }
    public int getIdAntecedentesQuirurgicos() {
        return idAntecedentesQuirurgicos;
    }
    public void setIdAntecedentesQuirurgicos(int idAntecedentesQuirurgicos) {
        this.idAntecedentesQuirurgicos = idAntecedentesQuirurgicos;
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
        return "AntecedentesQuirurgicos [descripcion=" + descripcion + ", fecha=" + fecha
                + ", idAntecedentesQuirurgicos=" + idAntecedentesQuirurgicos + ", idHistoriaClinica="
                + idHistoriaClinica + "]";
    }
}
