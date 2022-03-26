package mx.ginelife.db.dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

import mx.ginelife.db.Conexion;
import mx.ginelife.db.obj.Ginecologa;

public class GinecologaDAO {
    private static final String table="ginecologas";
    public static Ginecologa getDoctoraByCorreo(String correo){
        Statement statement;
        Ginecologa aux = null;
        try{
            Connection conn= Conexion.getConexion();
            statement = conn.createStatement();
            ResultSet rs=statement.executeQuery("select * from "+ table + " where correo='"
            +correo+"';");
            if(rs.next()){
                aux = new Ginecologa(rs.getInt("idginecologa"), rs.getString("nombres"), rs.getString("apellidoPaterno"), rs.getString("apellidoMaterno"), rs.getString("cedulaProfesional"), rs.getString("cedulaEspecialista"), rs.getString("telefono"), rs.getString("correo"), rs.getString("clave"));
            }
            rs.close();
            statement.close();
            conn.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }

        return aux;
    }
    public static Boolean acceso(String correo, String clave){
        Statement statement;
        Boolean result=false;
        try{
            Connection conn= Conexion.getConexion();
            statement = conn.createStatement();
            String str = clave;
            String b64 = Base64.getEncoder().encodeToString(str.getBytes());
            ResultSet rs=statement.executeQuery("select * from "+table+" where correo='"+correo+"' and clave='"+b64+"';");
            if(rs.next()){
                result=true;
            }
            rs.close();
            statement.close();
            conn.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }

        return result;
    }
}
