package mx.ginelife.db.obj;

public class AntecedentesHeredoFamiliares {
    private int idAntecedentesHeredoFamiliares;
    private int diabetes;
    private int hta;
    private int neoplasticos;
    private int cardiopatias;
    private int tiroides;
    private String especificaciones;
    private int idHistoriaClinica;
    public AntecedentesHeredoFamiliares(int idAntecedentesHeredoFamiliares, int diabetes, int hta, int neoplasticos,
            int cardiopatias, int tiroides, String especificaciones, int idHistoriaClinica) {
        this.idAntecedentesHeredoFamiliares = idAntecedentesHeredoFamiliares;
        this.diabetes = diabetes;
        this.hta = hta;
        this.neoplasticos = neoplasticos;
        this.cardiopatias = cardiopatias;
        this.tiroides = tiroides;
        this.especificaciones = especificaciones;
        this.idHistoriaClinica = idHistoriaClinica;
    }
    public int getIdAntecedentesHeredoFamiliares() {
        return idAntecedentesHeredoFamiliares;
    }
    public void setIdAntecedentesHeredoFamiliares(int idAntecedentesHeredoFamiliares) {
        this.idAntecedentesHeredoFamiliares = idAntecedentesHeredoFamiliares;
    }
    public int getDiabetes() {
        return diabetes;
    }
    public void setDiabetes(int diabetes) {
        this.diabetes = diabetes;
    }
    public int getHta() {
        return hta;
    }
    public void setHta(int hta) {
        this.hta = hta;
    }
    public int getNeoplasticos() {
        return neoplasticos;
    }
    public void setNeoplasticos(int neoplasticos) {
        this.neoplasticos = neoplasticos;
    }
    public int getCardiopatias() {
        return cardiopatias;
    }
    public void setCardiopatias(int cardiopatias) {
        this.cardiopatias = cardiopatias;
    }
    public int getTiroides() {
        return tiroides;
    }
    public void setTiroides(int tiroides) {
        this.tiroides = tiroides;
    }
    public String getEspecificaciones() {
        return especificaciones;
    }
    public void setEspecificaciones(String especificaciones) {
        this.especificaciones = especificaciones;
    }
    public int getIdHistoriaClinica() {
        return idHistoriaClinica;
    }
    public void setIdHistoriaClinica(int idHistoriaClinica) {
        this.idHistoriaClinica = idHistoriaClinica;
    }
    @Override
    public String toString() {
        return "AntecedentesHeredoFamiliares [cardiopatias=" + cardiopatias + ", diabetes=" + diabetes
                + ", especificaciones=" + especificaciones + ", hta=" + hta + ", idAntecedentesHeredoFamiliares="
                + idAntecedentesHeredoFamiliares + ", idHistoriaClinica=" + idHistoriaClinica + ", neoplasticos="
                + neoplasticos + ", tiroides=" + tiroides + "]";
    }
    
}
