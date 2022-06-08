package mx.ginelife.db.obj;

public class EstudiosLaboratorio {
    private int idEstudiosLaboratorio;
    private int idExploracionFisica;
    private String grupoSanguineo;
    private String rh;
    private String hemoglobina;
    private String hematrocito;
    private String leucocitos;
    private String plaquetas;
    private String tp;
    private String tpt;
    private String glucosa;
    private String vdrl;
    private String antihiv;
    public EstudiosLaboratorio(int idEstudiosLaboratorio, int idExploracionFisica, String grupoSanguineo, String rh,
            String hemoglobina, String hematrocito, String leucocitos, String plaquetas, String tp, String tpt,
            String glucosa, String vdrl, String antihiv) {
        this.idEstudiosLaboratorio = idEstudiosLaboratorio;
        this.idExploracionFisica = idExploracionFisica;
        this.grupoSanguineo = grupoSanguineo;
        this.rh = rh;
        this.hemoglobina = hemoglobina;
        this.hematrocito = hematrocito;
        this.leucocitos = leucocitos;
        this.plaquetas = plaquetas;
        this.tp = tp;
        this.tpt = tpt;
        this.glucosa = glucosa;
        this.vdrl = vdrl;
        this.antihiv = antihiv;
    }
    public EstudiosLaboratorio() {
    }
    public int getIdEstudiosLaboratorio() {
        return idEstudiosLaboratorio;
    }
    public void setIdEstudiosLaboratorio(int idEstudiosLaboratorio) {
        this.idEstudiosLaboratorio = idEstudiosLaboratorio;
    }
    public int getIdExploracionFisica() {
        return idExploracionFisica;
    }
    public void setIdExploracionFisica(int idExploracionFisica) {
        this.idExploracionFisica = idExploracionFisica;
    }
    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }
    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }
    public String getRh() {
        return rh;
    }
    public void setRh(String rh) {
        this.rh = rh;
    }
    public String getHemoglobina() {
        return hemoglobina;
    }
    public void setHemoglobina(String hemoglobina) {
        this.hemoglobina = hemoglobina;
    }
    public String getHematrocito() {
        return hematrocito;
    }
    public void setHematrocito(String hematrocito) {
        this.hematrocito = hematrocito;
    }
    public String getLeucocitos() {
        return leucocitos;
    }
    public void setLeucocitos(String leucocitos) {
        this.leucocitos = leucocitos;
    }
    public String getPlaquetas() {
        return plaquetas;
    }
    public void setPlaquetas(String plaquetas) {
        this.plaquetas = plaquetas;
    }
    public String getTp() {
        return tp;
    }
    public void setTp(String tp) {
        this.tp = tp;
    }
    public String getTpt() {
        return tpt;
    }
    public void setTpt(String tpt) {
        this.tpt = tpt;
    }
    public String getGlucosa() {
        return glucosa;
    }
    public void setGlucosa(String glucosa) {
        this.glucosa = glucosa;
    }
    public String getVdrl() {
        return vdrl;
    }
    public void setVdrl(String vdrl) {
        this.vdrl = vdrl;
    }
    public String getAntihiv() {
        return antihiv;
    }
    public void setAntihiv(String antihiv) {
        this.antihiv = antihiv;
    }
    @Override
    public String toString() {
        return "EstudiosLaboratorio [antihiv=" + antihiv + ", glucosa=" + glucosa + ", grupoSanguineo=" + grupoSanguineo
                + ", hematrocito=" + hematrocito + ", hemoglobina=" + hemoglobina + ", idEstudiosLaboratorio="
                + idEstudiosLaboratorio + ", idExploracionFisica=" + idExploracionFisica + ", leucocitos=" + leucocitos
                + ", plaquetas=" + plaquetas + ", rh=" + rh + ", tp=" + tp + ", tpt=" + tpt + ", vdrl=" + vdrl + "]";
    }
    

}
