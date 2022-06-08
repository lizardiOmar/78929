package mx.ginelife.db.obj;

public class Alergias {
    private int idAlergia;
    private String nombre;
    private int idHistoriaClinica;
    public Alergias(int idAlergia, String nombre, int idHistoriaClinica) {
        this.idAlergia = idAlergia;
        this.nombre = nombre;
        this.idHistoriaClinica = idHistoriaClinica;
    }
    public int getIdAlergia() {
        return idAlergia;
    }
    public void setIdAlergia(int idAlergia) {
        this.idAlergia = idAlergia;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getIdHistoriaClinica() {
        return idHistoriaClinica;
    }
    public void setIdHistoriaClinica(int idHistoriaClinica) {
        this.idHistoriaClinica = idHistoriaClinica;
    }
    @Override
    public String toString() {
        return "Alergias [idAlergia=" + idAlergia + ", idHistoriaClinica=" + idHistoriaClinica + ", nombre=" + nombre
                + "]";
    }
}
