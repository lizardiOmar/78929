package mx.ginelife;

import static spark.Spark.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import com.google.gson.Gson;

import mx.ginelife.db.Conexion;
import mx.ginelife.db.dao.CitaDAO;
import mx.ginelife.db.dao.DomicilioDAO;
import mx.ginelife.db.dao.GinecologaDAO;
import mx.ginelife.db.dao.PacienteDAO;
import mx.ginelife.db.obj.Cita;
import mx.ginelife.db.obj.Domicilio;
import mx.ginelife.db.obj.Ginecologa;
import mx.ginelife.db.obj.Paciente;



public class App {

    public static void main(String[] args) {
        
        port(getHerokuAssignedPort());
        staticFiles.location("/");
        init();

        path("/", () -> {
            //Registro del inicio de sesón y cookie
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
            //Cerrar sesión y  borrar cookie
            post("logout", (req, res)->{
                req.session().removeAttribute("correo");
                if(req.session().attribute("correo")==null){
                    req.session(false);
                    return "EXIT";
                }else{
                    return "ERROR";
                }
            });
            //Página de login
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
            //Página principal
            get("ginelife", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                String vista="/main.vm";
                Ginecologa doctora=GinecologaDAO.getDoctoraByCorreo(req.session().attribute("correo"));
                if(doctora!=null){
                    model.put("nombres", doctora.getNombres());
                    model.put("apellidos", doctora.getApellidoPaterno()+" "+doctora.getApellidoMaterno());
                    model.put("correo", doctora.getCorreo());
                    model.put("cedulaProfesional", doctora.getCedulaProfesional());
                    model.put("cedulaEspecialista", doctora.getCedulaEspecialista());
                    model.put("telefono", doctora.getTelefono());
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
                if(ginecologa!=null){
                    model.put("ginecologa", ginecologa);
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
                if(req.session().attribute("correo")==null){
                    vista="/NoSesion.vm";
                }else{
                    Ginecologa ginecologa=GinecologaDAO.getDoctoraByCorreo(req.session().attribute("correo"));
                    ginecologa.setIdGinecologa(0);
                    model.put("ginecologa", ginecologa);
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
            //Obtener itinerario por fecha
            get("itinerario", (req, res) -> {
                Gson gson=new Gson();
                Map<String, Object> model = new HashMap<>();
                String correo = "NO";
                String fecha = req.queryParams("fecha");
                List <Cita> citas=null;
                if(req.session().attribute("correo")!=null){
                    Integer idGinecologa=GinecologaDAO.getDoctoraByCorreo(req.session().attribute("correo")).getIdGinecologa();
                    citas=CitaDAO.getItinerarioByFecha(fecha, idGinecologa);
                    if(citas==null){
                        model.put("itinerario", "No hay citas registradas.");
                        model.put("usuario", correo);
                    }else{
                        model.put("itinerario", citas);
                        model.put("usuario", req.session().attribute("correo"));
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
                if(idGinecologa!=null){
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
                Integer idGinecologa=GinecologaDAO.getDoctoraByCorreo(req.session().attribute("correo")).getIdGinecologa();
                if(idGinecologa!=null){
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
                Integer idGinecologa=GinecologaDAO.getDoctoraByCorreo(req.session().attribute("correo")).getIdGinecologa();
                if(idGinecologa!=null){
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
