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
            
            Map<String, Object> model = new HashMap<>();

            get("", (req, res) -> {
                //Prueba de conexión a la BD
                Connection c=Conexion.getConexion();
                String vista="/mantenimiento.vm";
                req.session().removeAttribute("correo");
                req.session(false);
                if(c!=null){
                    vista="/login.vm";
                    c.close();
                }
                return new ModelAndView(model, vista);
            }, new VelocityTemplateEngine());

            post("login", (req, res) -> {
                Gson gson=new Gson();
                Ginecologa aux = gson.fromJson(req.body(), Ginecologa.class);
                String correo=aux.getCorreo();
                String clave=aux.getClave();
                if (GinecologaDAO.acceso(correo, clave)) {
                    req.session().attribute("correo", correo);
                    return "SI";
                } 
                return "NO";
                
            });

            get("ginelife", (req, res) -> {
                req.session(true);
                //System.out.println("CORREO SESION"+req.session().attribute("correo"));
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
