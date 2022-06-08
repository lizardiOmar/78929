package mx.ginelife.db.obj;

public class AntecedentesPersonalesNoPatologicos {
    private int idAntecedentesPersonalesNoPatologicos;
    private int tabaquismo;
    private int alcoholismo;
    private int drogas;
    private int rubeola;
    private int influenza;
    private int tetanos;
    private int covid19;
    private int idHistoriaClinica;
    public AntecedentesPersonalesNoPatologicos(int idAntecedentesPersonalesNoPatologicos, int tabaquismo,
            int alcoholismo, int drogas, int rubeola, int influenza, int tetanos, int covid19, int idHistoriaClinica) {
        this.idAntecedentesPersonalesNoPatologicos = idAntecedentesPersonalesNoPatologicos;
        this.tabaquismo = tabaquismo;
        this.alcoholismo = alcoholismo;
        this.drogas = drogas;
        this.rubeola = rubeola;
        this.influenza = influenza;
        this.tetanos = tetanos;
        this.covid19 = covid19;
        this.idHistoriaClinica = idHistoriaClinica;
    }
    public int getIdAntecedentesPersonalesNoPatologicos() {
        return idAntecedentesPersonalesNoPatologicos;
    }
    public void setIdAntecedentesPersonalesNoPatologicos(int idAntecedentesPersonalesNoPatologicos) {
        this.idAntecedentesPersonalesNoPatologicos = idAntecedentesPersonalesNoPatologicos;
    }
    public int getTabaquismo() {
        return tabaquismo;
    }
    public void setTabaquismo(int tabaquismo) {
        this.tabaquismo = tabaquismo;
    }
    public int getAlcoholismo() {
        return alcoholismo;
    }
    public void setAlcoholismo(int alcoholismo) {
        this.alcoholismo = alcoholismo;
    }
    public int getDrogas() {
        return drogas;
    }
    public void setDrogas(int drogas) {
        this.drogas = drogas;
    }
    public int getRubeola() {
        return rubeola;
    }
    public void setRubeola(int rubeola) {
        this.rubeola = rubeola;
    }
    public int getInfluenza() {
        return influenza;
    }
    public void setInfluenza(int influenza) {
        this.influenza = influenza;
    }
    public int getTetanos() {
        return tetanos;
    }
    public void setTetanos(int tetanos) {
        this.tetanos = tetanos;
    }
    public int getCovid19() {
        return covid19;
    }
    public void setCovid19(int covid19) {
        this.covid19 = covid19;
    }
    public int getIdHistoriaClinica() {
        return idHistoriaClinica;
    }
    public void setIdHistoriaClinica(int idHistoriaClinica) {
        this.idHistoriaClinica = idHistoriaClinica;
    }
    @Override
    public String toString() {
        return "AntecedentesPersonalesNoPatologicos [alcoholismo=" + alcoholismo + ", covid19=" + covid19 + ", drogas="
                + drogas + ", idAntecedentesPersonalesNoPatologicos=" + idAntecedentesPersonalesNoPatologicos
                + ", idHistoriaClinica=" + idHistoriaClinica + ", influenza=" + influenza + ", rubeola=" + rubeola
                + ", tabaquismo=" + tabaquismo + ", tetanos=" + tetanos + "]";
    }
    
}
