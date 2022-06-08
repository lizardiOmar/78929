package mx.ginelife.db.obj;

public class HistoriaClinica {
    private int idHistoriaClinica;
    private String fecha;
    private String hora;
    private int idPaciente;
    public HistoriaClinica(int idHistoriaClinica, String fecha, String hora, int idPaciente) {
        this.idHistoriaClinica = idHistoriaClinica;
        this.fecha = fecha;
        this.hora = hora;
        this.idPaciente = idPaciente;
    }
    public int getIdHistoriaClinica() {
        return idHistoriaClinica;
    }
    public void setIdHistoriaClinica(int idHistoriaClinica) {
        this.idHistoriaClinica = idHistoriaClinica;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
    public int getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
    @Override
    public String toString() {
        return "HistoriaClinica [fecha=" + fecha + ", hora=" + hora + ", idHistoriaClinica=" + idHistoriaClinica
                + ", idPaciente=" + idPaciente + "]";
    }
    
}
