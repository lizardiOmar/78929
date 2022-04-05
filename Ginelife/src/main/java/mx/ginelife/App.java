package mx.ginelife;

import static spark.Spark.*;

import java.sql.Array;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import com.google.gson.Gson;

import mx.ginelife.db.Conexion;
import mx.ginelife.db.dao.CitaDAO;
import mx.ginelife.db.dao.GinecologaDAO;
import mx.ginelife.db.obj.Cita;
import mx.ginelife.db.obj.Ginecologa;



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
                //System.out.println("CORREO SESION"+req.session().attribute("correo"));
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
            
        });
        //Vista citas
        get("citas", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String vista="/citas.vm";
            Ginecologa ginecologa=GinecologaDAO.getDoctoraByCorreo(req.session().attribute("correo"));
            if(ginecologa!=null){
                List <Cita> citas=CitaDAO.getCitas();
                model.put("ginecologa", ginecologa);
                if(citas!=null){
                    model.put("citas", citas);
                    List <Cita> citasFiltradas=CitaDAO.getCitasByGinecologa(ginecologa.getIdGinecologa());
                    if(citasFiltradas!=null){
                        model.put("citasFiltradas", citasFiltradas);
                    }else{
                        model.put("citasFiltradas", "No hay ninguna cita registrada.");
                    }
                }else{
                    model.put("citas", "No hay citas registradas.");
                }
            }else{
                vista="/NoSesion.vm";
            }
            System.out.println("SOLICITUD DE LA PÁGINA: CITAS");
            return new ModelAndView(model, vista);
        }, new VelocityTemplateEngine());
        //Obtener itinerario por día y mes
        get("itinerario", (req, res) -> {
            Gson gson=new Gson();
            Map<String, Object> model = new HashMap<>();
            String correo = "NO";
            String dia = req.queryParams("dia");
            String mes = req.queryParams("mes");
            List <Cita> citas=null;
            if(req.session().attribute("correo")!=null){
                correo=req.session().attribute("correo");

                citas=CitaDAO.getItinerarioByDiaAndMes(dia, mes);
                if(citas==null){
                    model.put("itinerario", "No hay citas registradas.");
                }else{
                    model.put("itinerario", citas);
                }
            }
            model.put("usuario", correo);
            
            String jsonCitas=gson.toJson(model);
            return jsonCitas;
        });
        post("/crearCita", (req, res) -> {
            Gson gson=new Gson();
            Cita aux = gson.fromJson(req.body(), Cita.class);
            if (CitaDAO.registrarCita(aux)) {
                return "SI";
            } else {
                return "NO";
            }
        });
    }
    //Nueva cita
        
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; // return default port if heroku-port isn't set (i.e. on localhost)
    }
}
