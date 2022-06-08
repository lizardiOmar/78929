package mx.ginelife.db.obj;

public class AntecedentesPersonalesPatologicos {
    private int idAntecedentesPersonalesPatologicos;
    private int diabetes;
    private int hta;
    private int nefropatias;
    private int cardiopatias;
    private int epilepsia;
    private int idHistoriaClinica;
    public AntecedentesPersonalesPatologicos(int idAntecedentesPersonalesPatologicos, int diabetes, int hta,
            int nefropatias, int cardiopatias, int epilepsia, int idHistoriaClinica) {
        this.idAntecedentesPersonalesPatologicos = idAntecedentesPersonalesPatologicos;
        this.diabetes = diabetes;
        this.hta = hta;
        this.nefropatias = nefropatias;
        this.cardiopatias = cardiopatias;
        this.epilepsia = epilepsia;
        this.idHistoriaClinica = idHistoriaClinica;
    }
    public int getIdAntecedentesPersonalesPatologicos() {
        return idAntecedentesPersonalesPatologicos;
    }
    public void setIdAntecedentesPersonalesPatologicos(int idAntecedentesPersonalesPatologicos) {
        this.idAntecedentesPersonalesPatologicos = idAntecedentesPersonalesPatologicos;
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
    public int getNefropatias() {
        return nefropatias;
    }
    public void setNefropatias(int nefropatias) {
        this.nefropatias = nefropatias;
    }
    public int getCardiopatias() {
        return cardiopatias;
    }
    public void setCardiopatias(int cardiopatias) {
        this.cardiopatias = cardiopatias;
    }
    public int getEpilepsia() {
        return epilepsia;
    }
    public void setEpilepsia(int epilepsia) {
        this.epilepsia = epilepsia;
    }
    public int getIdHistoriaClinica() {
        return idHistoriaClinica;
    }
    public void setIdHistoriaClinica(int idHistoriaClinica) {
        this.idHistoriaClinica = idHistoriaClinica;
    }
    @Override
    public String toString() {
        return "AntecedentesPersonalesPatologicos [cardiopatias=" + cardiopatias + ", diabetes=" + diabetes
                + ", epilepsia=" + epilepsia + ", hta=" + hta + ", idAntecedentesPersonalesPatologicos="
                + idAntecedentesPersonalesPatologicos + ", idHistoriaClinica=" + idHistoriaClinica + ", nefropatias="
                + nefropatias + "]";
    }
    
}
