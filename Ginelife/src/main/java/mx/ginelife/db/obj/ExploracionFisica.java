package mx.ginelife.db.obj;

public class ExploracionFisica {
    private int idExploracionFisica;
    private String fecha;
    private String hora;
    private int consciente;
    private String habitus;
    private String peso;
    private String estatura;
    private String ta;
    private String fc;
    private String fr;
    private String temperatura;
    private int pulmones;
    private String pulmones_nota;
    private int corazon;
    private String corazon_nota;
    private int cabeza;
    private String cabeza_nota;
    private int cuello;
    private String cuello_nota;
    private String pronostico;
    private String plan;
    private int idPaciente;
    public ExploracionFisica(int idExploracionFisica, String fecha, String hora, String pronostico, String plan,
            int idPaciente) {
        this.idExploracionFisica = idExploracionFisica;
        this.fecha = fecha;
        this.hora = hora;
        this.pronostico = pronostico;
        this.plan = plan;
        this.idPaciente = idPaciente;
    }
    public ExploracionFisica(int idExploracionFisica, String fecha, String hora, int consciente, String habitus,
            String peso, String estatura, String ta, String fc, String fr, String temperatura, int pulmones,
            String pulmones_nota, int corazon, String corazon_nota, int cabeza, String cabeza_nota, int cuello,
            String cuello_nota, String pronostico, String plan, int idPaciente) {
        this.idExploracionFisica = idExploracionFisica;
        this.fecha = fecha;
        this.hora = hora;
        this.consciente = consciente;
        this.habitus = habitus;
        this.peso = peso;
        this.estatura = estatura;
        this.ta = ta;
        this.fc = fc;
        this.fr = fr;
        this.temperatura = temperatura;
        this.pulmones = pulmones;
        this.pulmones_nota = pulmones_nota;
        this.corazon = corazon;
        this.corazon_nota = corazon_nota;
        this.cabeza = cabeza;
        this.cabeza_nota = cabeza_nota;
        this.cuello = cuello;
        this.cuello_nota = cuello_nota;
        this.pronostico = pronostico;
        this.plan = plan;
        this.idPaciente = idPaciente;
    }
    public int getIdExploracionFisica() {
        return idExploracionFisica;
    }
    public void setIdExploracionFisica(int idExploracionFisica) {
        this.idExploracionFisica = idExploracionFisica;
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
    public int getConsciente() {
        return consciente;
    }
    public void setConsciente(int consciente) {
        this.consciente = consciente;
    }
    public String getHabitus() {
        return habitus;
    }
    public void setHabitus(String habitus) {
        this.habitus = habitus;
    }
    public String getPeso() {
        return peso;
    }
    public void setPeso(String peso) {
        this.peso = peso;
    }
    public String getEstatura() {
        return estatura;
    }
    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }
    public String getTa() {
        return ta;
    }
    public void setTa(String ta) {
        this.ta = ta;
    }
    public String getFc() {
        return fc;
    }
    public void setFc(String fc) {
        this.fc = fc;
    }
    public String getFr() {
        return fr;
    }
    public void setFr(String fr) {
        this.fr = fr;
    }
    public String getTemperatura() {
        return temperatura;
    }
    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }
    public int getPulmones() {
        return pulmones;
    }
    public void setPulmones(int pulmones) {
        this.pulmones = pulmones;
    }
    public String getPulmones_nota() {
        return pulmones_nota;
    }
    public void setPulmones_nota(String pulmones_nota) {
        this.pulmones_nota = pulmones_nota;
    }
    public int getCorazon() {
        return corazon;
    }
    public void setCorazon(int corazon) {
        this.corazon = corazon;
    }
    public String getCorazon_nota() {
        return corazon_nota;
    }
    public void setCorazon_nota(String corazon_nota) {
        this.corazon_nota = corazon_nota;
    }
    public int getCabeza() {
        return cabeza;
    }
    public void setCabeza(int cabeza) {
        this.cabeza = cabeza;
    }
    public String getCabeza_nota() {
        return cabeza_nota;
    }
    public void setCabeza_nota(String cabeza_nota) {
        this.cabeza_nota = cabeza_nota;
    }
    public int getCuello() {
        return cuello;
    }
    public void setCuello(int cuello) {
        this.cuello = cuello;
    }
    public String getCuello_nota() {
        return cuello_nota;
    }
    public void setCuello_nota(String cuello_nota) {
        this.cuello_nota = cuello_nota;
    }
    public String getPronostico() {
        return pronostico;
    }
    public void setPronostico(String pronostico) {
        this.pronostico = pronostico;
    }
    public String getPlan() {
        return plan;
    }
    public void setPlan(String plan) {
        this.plan = plan;
    }
    public int getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
    @Override
    public String toString() {
        return "ExploracionFisica [cabeza=" + cabeza + ", cabeza_nota=" + cabeza_nota + ", consciente=" + consciente
                + ", corazon=" + corazon + ", corazon_nota=" + corazon_nota + ", cuello=" + cuello + ", cuello_nota="
                + cuello_nota + ", estatura=" + estatura + ", fc=" + fc + ", fecha=" + fecha + ", fr=" + fr
                + ", habitus=" + habitus + ", hora=" + hora + ", idExploracionFisica=" + idExploracionFisica
                + ", idPaciente=" + idPaciente + ", peso=" + peso + ", plan=" + plan + ", pronostico=" + pronostico
                + ", pulmones=" + pulmones + ", pulmones_nota=" + pulmones_nota + ", ta=" + ta + ", temperatura="
                + temperatura + "]";
    }
}
