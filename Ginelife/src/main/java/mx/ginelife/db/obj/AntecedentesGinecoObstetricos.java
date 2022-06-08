package mx.ginelife.db.obj;

public class AntecedentesGinecoObstetricos {
    private int idAntecedentesGinecoObstetricos;
    private String menarca;
    private String ritmo;
    private String ivs;
    private int compaSexuales;
    private int gestaciones;
    private int partos;
    private int abortos;
    private int ceareas;
    private String ectopico;
    private String molar;
    private String fup;
    private String fum;
    private String fpp;
    private String padecimientoActual;
    private int idHistoriaClinica;
    public AntecedentesGinecoObstetricos(int idAntecedentesGinecoObstetricos, String menarca, String ritmo, String ivs,
            int compaSexuales, int gestaciones, int partos, int abortos, int ceareas, String ectopico,
            String molar, String fup, String fum, String fpp, String padecimientoActual, int idHistoriaClinica) {
        this.idAntecedentesGinecoObstetricos = idAntecedentesGinecoObstetricos;
        this.menarca = menarca;
        this.ritmo = ritmo;
        this.ivs = ivs;
        this.compaSexuales = compaSexuales;
        this.gestaciones = gestaciones;
        this.partos = partos;
        this.abortos = abortos;
        this.ceareas = ceareas;
        this.ectopico = ectopico;
        this.molar = molar;
        this.fup = fup;
        this.fum = fum;
        this.fpp = fpp;
        this.padecimientoActual = padecimientoActual;
        this.idHistoriaClinica = idHistoriaClinica;
    }
    public int getIdAntecedentesGinecoObstetricos() {
        return idAntecedentesGinecoObstetricos;
    }
    public void setIdAntecedentesGinecoObstetricos(int idAntecedentesGinecoObstetricos) {
        this.idAntecedentesGinecoObstetricos = idAntecedentesGinecoObstetricos;
    }
    public String getMenarca() {
        return menarca;
    }
    public void setMenarca(String menarca) {
        this.menarca = menarca;
    }
    public String getRitmo() {
        return ritmo;
    }
    public void setRitmo(String ritmo) {
        this.ritmo = ritmo;
    }
    public String getIvs() {
        return ivs;
    }
    public void setIvs(String ivs) {
        this.ivs = ivs;
    }
    public int getCompañerosSexuales() {
        return compaSexuales;
    }
    public void setCompañerosSexuales(int compaSexuales) {
        this.compaSexuales = compaSexuales;
    }
    public int getGestaciones() {
        return gestaciones;
    }
    public void setGestaciones(int gestaciones) {
        this.gestaciones = gestaciones;
    }
    public int getPartos() {
        return partos;
    }
    public void setPartos(int partos) {
        this.partos = partos;
    }
    public int getAbortos() {
        return abortos;
    }
    public void setAbortos(int abortos) {
        this.abortos = abortos;
    }
    public int getCeareas() {
        return ceareas;
    }
    public void setCeareas(int ceareas) {
        this.ceareas = ceareas;
    }
    public String getEctopico() {
        return ectopico;
    }
    public void setEctopico(String ectopico) {
        this.ectopico = ectopico;
    }
    public String getMolar() {
        return molar;
    }
    public void setMolar(String molar) {
        this.molar = molar;
    }
    public String getFup() {
        return fup;
    }
    public void setFup(String fup) {
        this.fup = fup;
    }
    public String getFum() {
        return fum;
    }
    public void setFum(String fum) {
        this.fum = fum;
    }
    public String getFpp() {
        return fpp;
    }
    public void setFpp(String fpp) {
        this.fpp = fpp;
    }
    public String getPadecimientoActual() {
        return padecimientoActual;
    }
    public void setPadecimientoActual(String padecimientoActual) {
        this.padecimientoActual = padecimientoActual;
    }
    public int getIdHistoriaClinica() {
        return idHistoriaClinica;
    }
    public void setIdHistoriaClinica(int idHistoriaClinica) {
        this.idHistoriaClinica = idHistoriaClinica;
    }
    @Override
    public String toString() {
        return "AntecedentesGinecoObstetricos [abortos=" + abortos + ", ceareas=" + ceareas + ", compaSexuales="
                + compaSexuales + ", ectopico=" + ectopico + ", fpp=" + fpp + ", fum=" + fum + ", fup=" + fup
                + ", gestaciones=" + gestaciones + ", idAntecedentesGinecoObstetricos="
                + idAntecedentesGinecoObstetricos + ", idHistoriaClinica=" + idHistoriaClinica + ", ivs=" + ivs
                + ", menarca=" + menarca + ", molar=" + molar + ", padecimientoActual=" + padecimientoActual
                + ", partos=" + partos + ", ritmo=" + ritmo + "]";
    }
}
