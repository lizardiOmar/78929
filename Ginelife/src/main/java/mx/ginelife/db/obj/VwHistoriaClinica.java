package mx.ginelife.db.obj;
import java.net.URISyntaxException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import mx.ginelife.db.Conexion;

public class VwHistoriaClinica {
    private int idPaciente;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String edad;
    private String estadoCivil;
    private String ocupacion;
    private String lugarDeNacimiento;
	private String fechanacimiento;
    private String escolaridad;
    private String telefono;
    private String correo;
    private String municipio;
    private String colonia;
    private String calle;
    private String numero;
    private String cp;
    private String fechaHC;
    private String horaHC;
    private int diabeteshf;
    private int htahf;
    private int neoplasticoshf;
    private int cardiopatiashf;
    private int tiroideshf;
    private String especificacioneshf;
    private int diabetespp;
    private int htapp;
    private int nefropatiaspp;
    private int cardiopatiaspp;
    private int epilepsiapp;
    private int tabaquismo;
    private int alcoholismo;
    private int drogas;
    private int rubeola;
    private int influenza;
    private int tetanos;
    private int covid19;
    private AntecedentesGinecoObstetricos ago;
    public VwHistoriaClinica(int idPaciente, String nombres, String apellidoPaterno, String apellidoMaterno,
            String edad, String estadoCivil, String ocupacion, String lugarDeNacimiento, String fechanacimiento,
            String escolaridad, String telefono, String correo, String municipio, String colonia, String calle,
            String numero, String cp, String fechaHC, String horaHC, int diabeteshf, int htahf, int neoplasticoshf,
            int cardiopatiashf, int tiroideshf, String especificacioneshf, int diabetespp, int htapp, int nefropatiaspp,
            int cardiopatiaspp, int epilepsiapp, int tabaquismo, int alcoholismo, int drogas, int rubeola,
            int influenza, int tetanos, int covid19, AntecedentesGinecoObstetricos ago) {
        this.idPaciente = idPaciente;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.edad = edad;
        this.estadoCivil = estadoCivil;
        this.ocupacion = ocupacion;
        this.lugarDeNacimiento = lugarDeNacimiento;
        this.fechanacimiento = fechanacimiento;
        this.escolaridad = escolaridad;
        this.telefono = telefono;
        this.correo = correo;
        this.municipio = municipio;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
        this.cp = cp;
        this.fechaHC = fechaHC;
        this.horaHC = horaHC;
        this.diabeteshf = diabeteshf;
        this.htahf = htahf;
        this.neoplasticoshf = neoplasticoshf;
        this.cardiopatiashf = cardiopatiashf;
        this.tiroideshf = tiroideshf;
        this.especificacioneshf = especificacioneshf;
        this.diabetespp = diabetespp;
        this.htapp = htapp;
        this.nefropatiaspp = nefropatiaspp;
        this.cardiopatiaspp = cardiopatiaspp;
        this.epilepsiapp = epilepsiapp;
        this.tabaquismo = tabaquismo;
        this.alcoholismo = alcoholismo;
        this.drogas = drogas;
        this.rubeola = rubeola;
        this.influenza = influenza;
        this.tetanos = tetanos;
        this.covid19 = covid19;
        this.ago = ago;
    }
    public int getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public String getEdad() {
        return edad;
    }
    public void setEdad(String edad) {
        this.edad = edad;
    }
    public String getEstadoCivil() {
        return estadoCivil;
    }
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
    public String getOcupacion() {
        return ocupacion;
    }
    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }
    public String getLugarDeNacimiento() {
        return lugarDeNacimiento;
    }
    public void setLugarDeNacimiento(String lugarDeNacimiento) {
        this.lugarDeNacimiento = lugarDeNacimiento;
    }
    public String getFechanacimiento() {
        return fechanacimiento;
    }
    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }
    public String getEscolaridad() {
        return escolaridad;
    }
    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
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
    public String getFechaHC() {
        return fechaHC;
    }
    public void setFechaHC(String fechaHC) {
        this.fechaHC = fechaHC;
    }
    public String getHoraHC() {
        return horaHC;
    }
    public void setHoraHC(String horaHC) {
        this.horaHC = horaHC;
    }
    public int getDiabeteshf() {
        return diabeteshf;
    }
    public void setDiabeteshf(int diabeteshf) {
        this.diabeteshf = diabeteshf;
    }
    public int getHtahf() {
        return htahf;
    }
    public void setHtahf(int htahf) {
        this.htahf = htahf;
    }
    public int getNeoplasticoshf() {
        return neoplasticoshf;
    }
    public void setNeoplasticoshf(int neoplasticoshf) {
        this.neoplasticoshf = neoplasticoshf;
    }
    public int getCardiopatiashf() {
        return cardiopatiashf;
    }
    public void setCardiopatiashf(int cardiopatiashf) {
        this.cardiopatiashf = cardiopatiashf;
    }
    public int getTiroideshf() {
        return tiroideshf;
    }
    public void setTiroideshf(int tiroideshf) {
        this.tiroideshf = tiroideshf;
    }
    public String getEspecificacioneshf() {
        return especificacioneshf;
    }
    public void setEspecificacioneshf(String especificacioneshf) {
        this.especificacioneshf = especificacioneshf;
    }
    public int getDiabetespp() {
        return diabetespp;
    }
    public void setDiabetespp(int diabetespp) {
        this.diabetespp = diabetespp;
    }
    public int getHtapp() {
        return htapp;
    }
    public void setHtapp(int htapp) {
        this.htapp = htapp;
    }
    public int getNefropatiaspp() {
        return nefropatiaspp;
    }
    public void setNefropatiaspp(int nefropatiaspp) {
        this.nefropatiaspp = nefropatiaspp;
    }
    public int getCardiopatiaspp() {
        return cardiopatiaspp;
    }
    public void setCardiopatiaspp(int cardiopatiaspp) {
        this.cardiopatiaspp = cardiopatiaspp;
    }
    public int getEpilepsiapp() {
        return epilepsiapp;
    }
    public void setEpilepsiapp(int epilepsiapp) {
        this.epilepsiapp = epilepsiapp;
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
    public AntecedentesGinecoObstetricos getAgo() {
        return ago;
    }
    public void setAgo(AntecedentesGinecoObstetricos ago) {
        this.ago = ago;
    }
    @Override
    public String toString() {
        return "VwHistoriaClinica [ago=" + ago + ", alcoholismo=" + alcoholismo + ", apellidoMaterno=" + apellidoMaterno
                + ", apellidoPaterno=" + apellidoPaterno + ", calle=" + calle + ", cardiopatiashf=" + cardiopatiashf
                + ", cardiopatiaspp=" + cardiopatiaspp + ", colonia=" + colonia + ", correo=" + correo + ", covid19="
                + covid19 + ", cp=" + cp + ", diabeteshf=" + diabeteshf + ", diabetespp=" + diabetespp + ", drogas="
                + drogas + ", edad=" + edad + ", epilepsiapp=" + epilepsiapp + ", escolaridad=" + escolaridad
                + ", especificacioneshf=" + especificacioneshf + ", estadoCivil=" + estadoCivil + ", fechaHC=" + fechaHC
                + ", fechanacimiento=" + fechanacimiento + ", horaHC=" + horaHC + ", htahf=" + htahf + ", htapp="
                + htapp + ", idPaciente=" + idPaciente + ", influenza=" + influenza + ", lugarDeNacimiento="
                + lugarDeNacimiento + ", municipio=" + municipio + ", nefropatiaspp=" + nefropatiaspp
                + ", neoplasticoshf=" + neoplasticoshf + ", nombres=" + nombres + ", numero=" + numero + ", ocupacion="
                + ocupacion + ", rubeola=" + rubeola + ", tabaquismo=" + tabaquismo + ", telefono=" + telefono
                + ", tetanos=" + tetanos + ", tiroideshf=" + tiroideshf + "]";
    }
    public static VwHistoriaClinica getHVwHistoriaClinicaByPaciente(int idPaciente){
        VwHistoriaClinica HC=null;
        try{
            Connection c= Conexion.getConexion();
            Statement statement = c.createStatement();
            ResultSet rs=statement.executeQuery("select * from vw_historias_clinicas where idpaciente  = "+idPaciente+";");
            if(!rs.next()){
                System.out.println("no hubo una coincidencia con la base de datos");
                return HC;
            }else{
                HC = new VwHistoriaClinica(
                    rs.getInt(1), 
                    rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19), 
                    rs.getInt(20), rs.getInt(21), rs.getInt(22), rs.getInt(23), rs.getInt(24), rs.getString(25), rs.getInt(26), rs.getInt(27), rs.getInt(28), rs.getInt(29), rs.getInt(30), rs.getInt(31), rs.getInt(32), rs.getInt(33), rs.getInt(34), rs.getInt(35), rs.getInt(36), rs.getInt(37), 
                    new AntecedentesGinecoObstetricos(rs.getInt("idAantecedentesGinecoObstetricos"), rs.getString("menarca"), rs.getString("ritmo"), rs.getString("ivs"), rs.getInt("compasexuales"), rs.getInt("gestaciones"), rs.getInt("partos"), rs.getInt("abortos"), rs.getInt("ceareas"), rs.getString("ectopico"), rs.getString("molar"), rs.getString("fup"), rs.getString("fum"), rs.getString("fpp"), rs.getString("padiecimientoActual"), rs.getInt("idHistoriaClinica")));
            }
            rs.close();
            statement.close();
            c.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
        return HC;
    }
}
