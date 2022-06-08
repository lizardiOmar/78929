package mx.ginelife;

import static spark.Spark.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import com.google.gson.Gson;

import mx.ginelife.db.Conexion;
import mx.ginelife.db.dao.AlergiasDAO;
import mx.ginelife.db.dao.AntecedentesGinecoObstetricosDAO;
import mx.ginelife.db.dao.AntecedentesHeredoFamiliaresDAO;
import mx.ginelife.db.dao.AntecedentesPersonalesNoPatologicosDAO;
import mx.ginelife.db.dao.AntecedentesPersonalesPatologicosDAO;
import mx.ginelife.db.dao.AntecedentesQuirurgicosDAO;
import mx.ginelife.db.dao.AntecedentesTraumaticosDAO;
import mx.ginelife.db.dao.CitaDAO;
import mx.ginelife.db.dao.DomicilioDAO;
import mx.ginelife.db.dao.EstudiosLaboratorioDAO;
import mx.ginelife.db.dao.ExploracionFisicaDAO;
import mx.ginelife.db.dao.GinecologaDAO;
import mx.ginelife.db.dao.HistoriaClinicaDAO;
import mx.ginelife.db.dao.PacienteDAO;
import mx.ginelife.db.obj.Alergias;
import mx.ginelife.db.obj.AntecedentesGinecoObstetricos;
import mx.ginelife.db.obj.AntecedentesHeredoFamiliares;
import mx.ginelife.db.obj.AntecedentesPersonalesNoPatologicos;
import mx.ginelife.db.obj.AntecedentesPersonalesPatologicos;
import mx.ginelife.db.obj.AntecedentesQuirurgicos;
import mx.ginelife.db.obj.AntecedentesTraumaticos;
import mx.ginelife.db.obj.Cita;
import mx.ginelife.db.obj.Domicilio;
import mx.ginelife.db.obj.EstudiosLaboratorio;
import mx.ginelife.db.obj.ExploracionFisica;
import mx.ginelife.db.obj.Ginecologa;
import mx.ginelife.db.obj.HistoriaClinica;
import mx.ginelife.db.obj.Paciente;
import mx.ginelife.db.obj.VwHistoriaClinica;



public class App {

    public static void main(String[] args) {
        
        port(getHerokuAssignedPort());
        staticFiles.location("/");
        init();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        path("/", () -> {
            //POST: Iniciar sesión e iniciar cookie en el servidor
            post("login", (req, res) -> {
                Gson gson=new Gson();
                Ginecologa aux = gson.fromJson(req.body(), Ginecologa.class);
                String correo=aux.getCorreo();
                String clave=aux.getClave();
                if (GinecologaDAO.acceso(correo, clave)) {
                    req.session().attribute("correo", correo);
                    req.session().maxInactiveInterval(1800);
                    req.session(true);
                    return "SI";
                } 
                return "NO";
            });
            //POST: Cerrar sesión y  borrar cookie en el servidor
            post("logout", (req, res)->{
                req.session().removeAttribute("correo");
                if(req.session().attribute("correo")==null){
                    req.session(false);
                    return "EXIT";
                }else{
                    return "ERROR";
                }
            });
            //GET: Mostrar plantilla de LOGIN
            get("", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                //Prueba de conexión a la BD
                Connection c=Conexion.getConexion();
                String vista="/mantenimiento.vm";
                if(c!=null){
                    vista="/login.vm";
                    c.close();
                }
                return new ModelAndView(model, vista);
            }, new VelocityTemplateEngine()); 
            //GET: Mostrar plantilla de MAIN
            get("ginelife", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                String vista="/Main.vm";
                
                Calendar calendar = Calendar.getInstance();
                System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
                calendar.add(Calendar.HOUR, -5); 
                System.out.println("HORA:" +calendar.get(Calendar.HOUR_OF_DAY));
                List <Cita> citasHoy=null;
                List <Cita> citasSemanaAux=null;
                //citasSemana=CitaDAO.getCitasByDateRange(fecha1, fecha2);
                Ginecologa doctora=GinecologaDAO.getDoctoraByCorreo(req.session().attribute("correo"));
                if(doctora!=null){
                    model.put("nombres", doctora.getNombres());
                    model.put("apellidos", doctora.getApellidoPaterno()+" "+doctora.getApellidoMaterno());
                    model.put("correo", doctora.getCorreo());
                    model.put("cedulaProfesional", doctora.getCedulaProfesional());
                    model.put("cedulaEspecialista", doctora.getCedulaEspecialista());
                    model.put("telefono", doctora.getTelefono());
                    citasHoy=CitaDAO.getItinerarioByFecha(sdf.format(calendar.getTime()), doctora.getIdGinecologa());
                    if(citasHoy!=null){
                        model.put("citasHoy", citasHoy);
                    }else{
                        model.put("citasHoy", "NO");
                    }
                    String fechaHoy=sdf.format(calendar.getTime());
                    calendar.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH+7);
                    calendar.add(Calendar.HOUR, -5); 
                    String fechaSemana=sdf.format(calendar.getTime());
                    citasSemanaAux=CitaDAO.getCitasByDateRange(fechaHoy, fechaSemana);
                    if(citasSemanaAux!=null){
                        //model.put("citasHoy", citasHoy);
                        List <Cita> citasSemana=new ArrayList<>();
                        for (Cita c : citasSemanaAux) {
                            if(c.getIdGinecologa()==doctora.getIdGinecologa()){
                                citasSemana.add(c);
                            }
                        }
                        if (!citasSemana.isEmpty()) {
                            model.put("citasSemana", citasSemana);
                        } else {
                            model.put("citasSemana", "NO");
                        }
                    }else{
                        model.put("citasSemana", "NO");
                    }
                }else{
                    vista="/NoSesion.vm";
                }
                return new ModelAndView(model, vista);
            }, new VelocityTemplateEngine());
            //Vista citas
            get("citas", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                String vista="/citas.vm";
                Ginecologa ginecologa=GinecologaDAO.getDoctoraByCorreo(req.session().attribute("correo"));
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.HOUR, -5);
                List <Cita> citasHoy=null;
                List <Cita> citasSemanaAux=null;
                List <Cita> citasMesAux=null;
                List <Cita> citasYearAux=null;
                String fechaHoy=sdf.format(calendar.getTime());
                calendar.add(Calendar.DAY_OF_YEAR, 8);
                String fechaSemana=sdf.format(calendar.getTime());
                calendar = Calendar.getInstance();
                calendar.add(Calendar.HOUR, -5);
                calendar.add(Calendar.MONTH, 1);
                String fechaMes=sdf.format(calendar.getTime());
                calendar = Calendar.getInstance();
                calendar.add(Calendar.HOUR, -5);
                calendar.add(Calendar.YEAR, 1);
                String fechaYear=sdf.format(calendar.getTime());
                model.put("fechaHoy", fechaHoy);
                model.put("fechasSemana", fechaHoy+" a "+fechaSemana);
                model.put("fechasMes", fechaHoy+" a "+fechaMes);
                model.put("fechasYear", fechaHoy+" a "+fechaYear);
                if(ginecologa!=null){
                    model.put("ginecologa", ginecologa);
                    citasHoy=CitaDAO.getItinerarioByFecha(fechaHoy, ginecologa.getIdGinecologa());
                    if(citasHoy!=null){
                        model.put("citasHoy", citasHoy);
                    }else{
                        model.put("citasHoy", "NO");
                    }
                    citasSemanaAux=CitaDAO.getCitasByDateRange(fechaHoy, fechaSemana);
                    if(citasSemanaAux!=null){
                        //model.put("citasHoy", citasHoy);
                        List <Cita> citasSemana=new ArrayList<>();
                        for (Cita c : citasSemanaAux) {
                            if(c.getIdGinecologa()==ginecologa.getIdGinecologa()){
                                citasSemana.add(c);
                            }
                        }
                        if (!citasSemana.isEmpty()) {
                            model.put("citasSemana", citasSemana);
                        } else {
                            model.put("citasSemana", "NO");
                        }
                    }else{
                        model.put("citasSemana", "NO");
                    }
                    //Citas por mes
                    citasMesAux=CitaDAO.getCitasByDateRange(fechaHoy, fechaMes);
                    if(citasMesAux!=null){
                        //model.put("citasHoy", citasHoy);
                        List <Cita> citasMes=new ArrayList<>();
                        for (Cita c : citasMesAux) {
                            if(c.getIdGinecologa()==ginecologa.getIdGinecologa()){
                                citasMes.add(c);
                            }
                        }
                        if (!citasMes.isEmpty()) {
                            model.put("citasMes", citasMes);
                        } else {
                            model.put("citasMes", "NO");
                        }
                    }else{
                        model.put("citasMes", "NO");
                    }
                    //Citas por año
                    citasYearAux=CitaDAO.getCitasByDateRange(fechaHoy, fechaYear);
                    if(citasYearAux!=null){
                        //model.put("citasHoy", citasHoy);
                        List <Cita> citasYear=new ArrayList<>();
                        for (Cita c : citasYearAux) {
                            if(c.getIdGinecologa()==ginecologa.getIdGinecologa()){
                                citasYear.add(c);
                            }
                        }
                        if (!citasYear.isEmpty()) {
                            model.put("citasYear", citasYear);
                        } else {
                            model.put("citasYear", "NO");
                        }
                    }else{
                        model.put("citasYear", "NO");
                    }
                    //Citas canceladas
                    List <Cita> citasCanceladas=null;
                    citasCanceladas=CitaDAO.getCitasCanceladasByGinecologa(ginecologa.getIdGinecologa());
                    if (citasCanceladas!=null) {
                        model.put("citasCanceladas", citasCanceladas);
                    } else {
                        model.put("citasCanceladas", "NO"); 
                    }

                }else{
                    vista="/NoSesion.vm";
                }
                return new ModelAndView(model, vista);
            }, new VelocityTemplateEngine());
            //Vista registrar paciente
            get("nuevoPaciente", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                String vista="/nuevoPaciente.vm";
                if(req.session().attribute("correo")==null){
                    vista="/NoSesion.vm";
                }
                return new ModelAndView(model, vista);
            }, new VelocityTemplateEngine());
            //Pacientes
            get("pacientes", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                String vista="/Pacientes.vm";
                List <Paciente> pacientes=null;
                
                if(req.session().attribute("correo")==null){
                    vista="/NoSesion.vm";
                }else{
                    Ginecologa ginecologa=GinecologaDAO.getDoctoraByCorreo(req.session().attribute("correo"));
                    model.put("ginecologa", ginecologa);
                    pacientes=PacienteDAO.getPacientessByGinecologa(GinecologaDAO.getDoctoraByCorreo(req.session().attribute("correo")).getIdGinecologa());
                    if (pacientes!=null) {
                        model.put("pacientes", pacientes);
                    } else {
                        model.put("pacientes", "NO");
                    }
                }
                return new ModelAndView(model, vista);
            }, new VelocityTemplateEngine());
            //Pacientes
            get("paciente/:idPaciente", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                String vista="/Paciente.vm";
                if(req.session().attribute("correo")==null){
                    vista="/NoSesion.vm";
                }else{
                    
                    Ginecologa ginecologa=GinecologaDAO.getDoctoraByCorreo(req.session().attribute("correo"));
                    ginecologa.setIdGinecologa(0);
                    model.put("ginecologa", ginecologa);
                    Paciente paciente=PacienteDAO.getPacienteById(Integer.parseInt(req.params(":idPaciente")));
                    
                    if (paciente!=null) {
                        List <ExploracionFisica> exploraciones=ExploracionFisicaDAO.getExploracionesFisicasByPaciente(paciente.getIdPaciente());
                        if(exploraciones!=null){
                            model.put("exploraciones", exploraciones);
                        }else{
                            model.put("exploraciones", "NO");
                        }
                        model.put("paciente", paciente);
                        model.put("an", paciente.getFechaDeNacimiento().substring(0, 4));
                        model.put("mn", paciente.getFechaDeNacimiento().substring(5, 7));
                        model.put("dn", paciente.getFechaDeNacimiento().substring(8, 10));
                        Domicilio domicilio=DomicilioDAO.getDomicilioByPaciente(Integer.parseInt(req.params(":idPaciente")));
                        if (domicilio!=null) {
                            model.put("domicilio", domicilio);
                        }else{
                            model.put("domicilio", "verifique sus datos.");
                        }
                    }else{
                        model.put("paciente", "verifique sus datos.");
                    }
                }
                return new ModelAndView(model, vista);
            }, new VelocityTemplateEngine());
            //Pacientes
            /*
            get("agregarHistoriaClinica/:idPaciente", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                String vista="/NuevaHistoriaClinica.vm";
                if(req.session().attribute("correo")==null){
                    vista="/NoSesion.vm";
                }else{
                    Ginecologa ginecologa=GinecologaDAO.getDoctoraByCorreo(req.session().attribute("correo"));
                    ginecologa.setIdGinecologa(0);
                    model.put("ginecologa", ginecologa);
                    Paciente paciente=PacienteDAO.getPacienteById(Integer.parseInt(req.params(":idPaciente")));
                    if (paciente!=null) {
                        model.put("paciente", paciente);
                    }else{
                        model.put("paciente", "verifique sus datos.");
                    }
                }
                return new ModelAndView(model, vista);
            }, new VelocityTemplateEngine()); */
            //Pacientes
            get("agregarExploracionFisica/:idPaciente", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                String vista="/NuevaExploracionFisica.vm";
                if(req.session().attribute("correo")==null){
                    vista="/NoSesion.vm";
                }else{
                    Ginecologa ginecologa=GinecologaDAO.getDoctoraByCorreo(req.session().attribute("correo"));
                    ginecologa.setIdGinecologa(0);
                    model.put("ginecologa", ginecologa);
                    Paciente paciente=PacienteDAO.getPacienteById(Integer.parseInt(req.params(":idPaciente")));
                    if (paciente!=null) {
                        model.put("paciente", paciente);
                    }else{
                        model.put("paciente", "verifique sus datos.");
                    }
                }
                return new ModelAndView(model, vista);
            }, new VelocityTemplateEngine());
            //Historia clinica
            get("historiaClinica/:idPaciente", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                String vista="/HistoriaClinica.vm";
                if(req.session().attribute("correo")==null){
                    vista="/NoSesion.vm";
                }else{
                    Ginecologa ginecologa=GinecologaDAO.getDoctoraByCorreo(req.session().attribute("correo"));
                    model.put("ginecologa", ginecologa);
                    Paciente paciente=PacienteDAO.getPacienteById(Integer.parseInt(req.params(":idPaciente")));
                    if (paciente!=null) {
                        model.put("paciente", paciente);
                    }else{
                        model.put("paciente", "NO");
                    }
                    VwHistoriaClinica HC=VwHistoriaClinica.getHVwHistoriaClinicaByPaciente(paciente.getIdPaciente());
                    if(HC!=null){
                        model.put("HC", HC);
                        model.put("coSe", HC.getAgo().getCompañerosSexuales());
                        List <AntecedentesTraumaticos> AT=AntecedentesTraumaticosDAO.getATByHC(HC.getAgo().getIdHistoriaClinica());
                        if (AT!=null) {
                            model.put("AT", AT);
                        } else {
                            model.put("AT", "NO");
                        }
                        List <Alergias> alergias=AlergiasDAO.getAlergiasByHC(HC.getAgo().getIdHistoriaClinica()); 
                        if (alergias!=null) {
                            model.put("alergias", alergias);
                        } else {
                            model.put("alergias", "NO");
                        }
                        List <AntecedentesQuirurgicos> AQ = AntecedentesQuirurgicosDAO.getAQByHC(HC.getAgo().getIdHistoriaClinica());
                        if (AQ!=null) {
                            model.put("AQ", AQ);
                        } else {
                            model.put("AQ", "NO");
                        }
                    }else{
                        vista="NuevaHistoriaClinica.vm";
                    }

                }
                return new ModelAndView(model, vista);
            }, new VelocityTemplateEngine());
            //
            get("exploracionFisica/:idExploracionFisica/:idPaciente", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                String vista="/ExploracionFisica.vm";
                if(req.session().attribute("correo")==null){
                    vista="/NoSesion.vm";
                }else{
                    Paciente paciente=PacienteDAO.getPacienteById(Integer.parseInt(req.params(":idPaciente")));
                    if (paciente!=null) {
                        model.put("paciente", paciente);
                    }else{
                        model.put("paciente", "NO");
                    }
                    Ginecologa ginecologa=GinecologaDAO.getDoctoraByCorreo(req.session().attribute("correo"));
                    model.put("ginecologa", ginecologa);
                    int idExploracionFisica=Integer.parseInt(req.params(":idExploracionFisica"));
                    ExploracionFisica EF=ExploracionFisicaDAO.getExFiyId(idExploracionFisica);
                    if(EF!=null){
                        model.put("EF", EF);
                        //Estudios Lab.
                        EstudiosLaboratorio EL=EstudiosLaboratorioDAO.getExFiyId(EF.getIdExploracionFisica());
                        if (EL!=null) {
                            model.put("EL", EL);
                        } else {
                            model.put("EL", "NO");
                        }
                        
                    }else{
                        model.put("EF", "NO");
                    }

                }
                return new ModelAndView(model, vista);
            }, new VelocityTemplateEngine());
            //Obtener itinerario por fecha
            get("itinerario", (req, res) -> {
                Gson gson=new Gson();
                Map<String, Object> model = new HashMap<>();
                String fecha = req.queryParams("fecha");
                List <Cita> citas=null;
                if(req.session().attribute("correo")!=null){
                    citas=CitaDAO.getCitas(fecha);
                    if(citas==null){
                        model.put("itinerario", "NO");
                    }else{
                        model.put("itinerario", citas);
                    }
                }
                String jsonCitas=gson.toJson(model);
                return jsonCitas;
            });
            //Obtener itinerario por rango
            get("itinerarioRange", (req, res) -> {
                Gson gson=new Gson();
                Map<String, Object> model = new HashMap<>();
                String fecha1 = req.queryParams("fecha1");
                String fecha2 = req.queryParams("fecha2");
                List <Cita> citas=null;
                if(req.session().attribute("correo")!=null){
                    citas=CitaDAO.getCitasByDateRange(fecha1, fecha2);
                    if(citas==null){
                        model.put("citas", "No hay citas registradas.");
                        model.put("usuario", req.session().attribute("correo"));
                    }else{
                        model.put("citas", citas);
                        model.put("usuario", req.session().attribute("correo"));
                    }
                }
                String jsonCitas=gson.toJson(model);
                return jsonCitas;
            });
            //Obtener pacientes por doctora
            get("pacientesPorDoctora", (req, res) -> {
                Gson gson=new Gson();
                Map<String, Object> model = new HashMap<>();
                List <Paciente> pacientes=null;
                if(req.session().attribute("correo")!=null){
                    pacientes=PacienteDAO.getPacientessByGinecologa(GinecologaDAO.getDoctoraByCorreo(req.session().attribute("correo")).getIdGinecologa());
                    if(pacientes==null){
                        model.put("pacientes", "No hay pacientes registradas.");
                    }else{
                        model.put("pacientes", pacientes);
                    }
                }
                String jsonPacientes=gson.toJson(model.get("pacientes"));
                return jsonPacientes;
            });
            //Obtener cita por id
            get("citaById", (req, res) -> {
                Gson gson=new Gson();
                Map<String, Object> model = new HashMap<>();
                String correo = "NO";
                String idCita = req.queryParams("idCita");
                Cita cita=null;
                if(req.session().attribute("correo")!=null){
                    cita=CitaDAO.getCitaById(Integer.parseInt(idCita));
                    if(cita==null){
                        model.put("itinerario", "No hay ninguna cita que coincida con el registro solicitado.");
                        model.put("usuario", correo);
                    }else{
                        model.put("cita", cita);
                        model.put("usuario", req.session().attribute("correo"));
                    }
                }
                String jsonCita=gson.toJson(model);
                return jsonCita;
            });
            //Obtener cita por id
            get("cancelarCitaById", (req, res) -> {
                Gson gson=new Gson();
                Map<String, Object> model = new HashMap<>();
                String idCita = req.queryParams("idCita");
                if(req.session().attribute("correo")!=null){
                    if(CitaDAO.cancelarCitaById(Integer.parseInt(idCita))){
                        model.put("estado", "SI");
                    }else{
                        model.put("estado", "NO");
                    }
                }
                String jsonCita=gson.toJson(model);
                return jsonCita;
            });
            //Nueva cita
            post("crearCita", (req, res) -> {
                Integer idGinecologa=GinecologaDAO.getDoctoraByCorreo(req.session().attribute("correo")).getIdGinecologa();
                if(req.session().attribute("correo")!=null){
                    Gson gson=new Gson();
                    Cita aux = gson.fromJson(req.body(), Cita.class);
                    aux.setIdGinecologa(idGinecologa);
                    if (CitaDAO.registrarCita(aux)) {
                        return "SI";
                    } else {
                        return "NO";
                    }
                }else{
                    return "No hay sesión iniciada.";
                }
            });
            //Nuevo paciente
            post("crearPaciente", (req, res) -> {
                Integer idGinecologa=null;
                if(req.session().attribute("correo")!=null){
                    idGinecologa=GinecologaDAO.getDoctoraByCorreo(req.session().attribute("correo")).getIdGinecologa();
                    Gson gson=new Gson();
                    Paciente aux = gson.fromJson(req.body(), Paciente.class);
                    aux.setIdGinecologa(idGinecologa);
                    if (PacienteDAO.registrarPaciente(aux)) {
                        //System.out.println(PacienteDAO.getLastPacienteId(idGinecologa));
                        return "SI";
                    } else {
                        return "NO";
                    }
                }else{
                    return "No hay sesión iniciada.";
                }
            });
            //Nueva dirección
            post("agregarDomicilio", (req, res) -> {
                Integer idGinecologa=null;
                idGinecologa=GinecologaDAO.getDoctoraByCorreo(req.session().attribute("correo")).getIdGinecologa();
                if(req.session().attribute("correo")!=null){
                    Gson gson=new Gson();
                    Domicilio aux = gson.fromJson(req.body(), Domicilio.class);
                    aux.setIdPaciente(PacienteDAO.getLastPacienteId(idGinecologa));
                    
                    if (DomicilioDAO.registrarDomicilio(aux)) {
                        System.out.println(aux.toString());
                        return "SI";
                    } else {
                        return "NO";
                    }
                }else{
                    return "No hay sesión iniciada.";
                }
            });
            //Nueva Historia Clínica
            post("agregarHistoriaClinica", (req, res) -> {
                if(req.session().attribute("correo")!=null){
                    Gson gson=new Gson();
                    HistoriaClinica aux = gson.fromJson(req.body(), HistoriaClinica.class);
                    if (HistoriaClinicaDAO.registrarHistoriaClinica(aux)) {
                        req.session().attribute("lastHC", HistoriaClinicaDAO.getLastHistoriaClinicaId());
                        return "SI";
                    } else {
                        return "NO";
                    }
                }else{
                    return "No hay sesión iniciada.";
                }
            });
            //Agregar antecedentes heredo familiares
            post("agregarAHF", (req, res) -> {
                if(req.session().attribute("correo")!=null){
                    Gson gson=new Gson();
                    AntecedentesHeredoFamiliares aux = gson.fromJson(req.body(), AntecedentesHeredoFamiliares.class);
                    aux.setIdHistoriaClinica(req.session().attribute("lastHC"));
                    if (AntecedentesHeredoFamiliaresDAO.registrarAntecedentesHeredoFamiliares(aux)) {
                        return "SI";
                    } else {
                        return "NO";
                    }
                }else{
                    return "No hay sesión iniciada.";
                }
            });
            //Agregar antecedentes personales patologicos 
            post("agregarAPP", (req, res) -> {
                if(req.session().attribute("correo")!=null){
                    Gson gson=new Gson();
                    AntecedentesPersonalesPatologicos aux = gson.fromJson(req.body(), AntecedentesPersonalesPatologicos.class);
                    aux.setIdHistoriaClinica(req.session().attribute("lastHC"));
                    if (AntecedentesPersonalesPatologicosDAO.registrarAntecedentesPersonalesPatologicos(aux)) {
                        return "SI";
                    } else {
                        return "NO";
                    }
                }else{
                    return "No hay sesión iniciada.";
                }
            });
            //Agregar alergias (desde cero hasta n)
            post("agregarAlergia", (req, res) -> {
                if(req.session().attribute("correo")!=null){
                    Gson gson=new Gson();
                    Alergias aux = gson.fromJson(req.body(), Alergias.class);
                    aux.setIdHistoriaClinica(req.session().attribute("lastHC"));
                    if (AlergiasDAO.registrarAlergia(aux)) {
                        return "SI";
                    } else {
                        return "NO";
                    }
                }else{
                    return "No hay sesión iniciada.";
                }
            });
            //Agregar antecedente traumatico
            post("agregarAT", (req, res) -> {
                if(req.session().attribute("correo")!=null){
                    Gson gson=new Gson();
                    AntecedentesTraumaticos aux = gson.fromJson(req.body(), AntecedentesTraumaticos.class);
                    aux.setIdHistoriaClinica(req.session().attribute("lastHC"));
                    if (AntecedentesTraumaticosDAO.registrarAntecedenteTraumatico(aux)) {
                        return "SI";
                    } else {
                        return "NO";
                    }
                }else{
                    return "No hay sesión iniciada.";
                }
            });
            //Agregar antecedente quirurgico
            post("agregarAQ", (req, res) -> {
                if(req.session().attribute("correo")!=null){
                    Gson gson=new Gson();
                    AntecedentesQuirurgicos aux = gson.fromJson(req.body(), AntecedentesQuirurgicos.class);
                    aux.setIdHistoriaClinica(req.session().attribute("lastHC"));
                    if (AntecedentesQuirurgicosDAO.registrarAntecedenteQuirurgico(aux)) {
                        return "SI";
                    } else {
                        return "NO";
                    }
                }else{
                    return "No hay sesión iniciada.";
                }
            });
            //Agregar antecedentes Personales No Patologicos 
            post("agregarAPNP", (req, res) -> {
                if(req.session().attribute("correo")!=null){
                    Gson gson=new Gson();
                    AntecedentesPersonalesNoPatologicos aux = gson.fromJson(req.body(), AntecedentesPersonalesNoPatologicos.class);
                    aux.setIdHistoriaClinica(req.session().attribute("lastHC"));
                    if (AntecedentesPersonalesNoPatologicosDAO.registrarAntecedentesPersonalesNoPatologicos(aux)) {
                        System.out.println(aux.toString());
                        return "SI";
                    } else {
                        return "NO";
                    }
                }else{
                    return "No hay sesión iniciada.";
                }
            });
            //Agregar antecedentes gineco obstetricos
            post("agregarAGO", (req, res) -> {
                if(req.session().attribute("correo")!=null){
                    Gson gson=new Gson();
                    AntecedentesGinecoObstetricos aux = gson.fromJson(req.body(), AntecedentesGinecoObstetricos.class);
                    aux.setIdHistoriaClinica(req.session().attribute("lastHC"));
                    if (AntecedentesGinecoObstetricosDAO.registrarAntecedentesGinecoObstetricos(aux)) {
                        System.out.println(aux.toString());
                        return "SI";
                    } else {
                        return "NO";
                    }
                }else{
                    return "No hay sesión iniciada.";
                }
            });
            //Nueva exploración física
            post("agregarExploracionFisica", (req, res) -> {
                if(req.session().attribute("correo")!=null){
                    Gson gson=new Gson();
                    ExploracionFisica aux = gson.fromJson(req.body(), ExploracionFisica.class);
                    if (ExploracionFisicaDAO.registrarExploracionFisica(aux)) {
                        System.out.println(aux.toString());
                        req.session().attribute("lastEF", ExploracionFisicaDAO.getLastExploracionFisicaId());
                        return "SI";
                    } else {
                        return "NO";
                    }
                }else{
                    return "No hay sesión iniciada.";
                }
            });
            //estudios de laboratorio
            post("agregarEstudiosLaboratorio", (req, res) -> {
                if(req.session().attribute("correo")!=null){
                    Gson gson=new Gson();
                    EstudiosLaboratorio aux = gson.fromJson(req.body(), EstudiosLaboratorio.class);
                    aux.setIdExploracionFisica(req.session().attribute("lastEF"));
                    System.out.println("REGISTRANDO: "+aux.toString());
                    if (EstudiosLaboratorioDAO.registrarEstudiosLaboratorio(aux)) {
                        
                        return "SI";
                    } else {
                        return "NO";
                    }
                }else{
                    return "No hay sesión iniciada.";
                }
            });
        });
       
    }
    
        
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; // return default port if heroku-port isn't set (i.e. on localhost)
    }
}
