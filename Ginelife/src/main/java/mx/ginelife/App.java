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
import mx.ginelife.db.dao.GinecologaDAO;
import mx.ginelife.db.obj.Ginecologa;



public class App {

    public static void main(String[] args) {
        
        port(getHerokuAssignedPort());
        staticFiles.location("/");
        init();
        path("/", () -> {
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
            post("logout", (req, res)->{
                req.session().removeAttribute("correo");
                if(req.session().attribute("correo")==null){
                    req.session(false);
                    return "EXIT";
                }else{
                    return "ERROR";
                }
            });
            before("ginelife", (req, res) ->{
                if(req.session().attribute("correo")==null){
                    System.out.println("Sesión no válida");
                }else{
                    System.out.println("Sesión: "+req.session().attribute("correo"));
                }
            });
            get("ginelife", (req, res) -> {
                //System.out.println("CORREO SESION"+req.session().attribute("correo"));
                Map<String, Object> model = new HashMap<>();
                Ginecologa doctora=GinecologaDAO.getDoctoraByCorreo(req.session().attribute("correo"));
                if(doctora!=null){
                    model.put("nombres", doctora.getNombres());
                    model.put("apellidos", doctora.getApellidoPaterno()+" "+doctora.getApellidoMaterno());
                    model.put("correo", doctora.getCorreo());
                    model.put("cedulaProfesional", doctora.getCedulaProfesional());
                    model.put("cedulaEspecialista", doctora.getCedulaEspecialista());
                    model.put("telefono", doctora.getTelefono());
                }
                return new ModelAndView(model, "/main.vm");
            }, new VelocityTemplateEngine());
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
