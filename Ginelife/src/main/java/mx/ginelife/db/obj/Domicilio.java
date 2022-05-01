package mx.ginelife.db.obj;

public class Domicilio {
    private int idDomicilio;
    private String municipio;
    private String colonia;
    private String calle;
    private String numero;
    private String cp;
    private int idPaciente;
    public Domicilio(int idDomicilio, String municipio, String colonia, String calle, String numero, String cp,
            int idPaciente) {
        this.idDomicilio = idDomicilio;
        this.municipio = municipio;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
        this.cp = cp;
        this.idPaciente = idPaciente;
    }
    public int getIdDomicilio() {
        return idDomicilio;
    }
    public void setIdDomicilio(int idDomicilio) {
        this.idDomicilio = idDomicilio;
    }
    public String getMunicipio() {
        return municipio;
    }
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    public String getColonia() {
        return colonia;
    }
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }
    public String getCalle() {
        return calle;
    }
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getCp() {
        return cp;
    }
    public void setCp(String cp) {
        this.cp = cp;
    }
    public int getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
    @Override
    public String toString() {
        return "Domicilio [calle=" + calle + ", colonia=" + colonia + ", cp=" + cp + ", idDomicilio=" + idDomicilio
                + ", idPaciente=" + idPaciente + ", municipio=" + municipio + ", numero=" + numero + "]";
    }
    
}
