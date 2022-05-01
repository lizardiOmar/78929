package mx.ginelife.db.dao;

import mx.ginelife.db.Conexion;
import mx.ginelife.db.obj.Domicilio;

import java.net.URISyntaxException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class DomicilioDAO {
    private static final String table="domicilios";
    public static boolean registrarDomicilio(Domicilio d){
        boolean resultado=false;
        try{
            String query =  " insert into "+table+" (municipio, colonia, calle, numero, cp, idpaciente)"+ " values (?, ?, ?, ?, ?, ?);";
            Connection conn= Conexion.getConexion();
            PreparedStatement preparedStmt = conn.prepareStatement(query);
			
            preparedStmt.setString(1, d.getMunicipio());
            preparedStmt.setString(2, d.getColonia());
            preparedStmt.setString(3, d.getCalle());
            preparedStmt.setString(4, d.getNumero());
            preparedStmt.setString(5, d.getCp());
            preparedStmt.setInt(6, d.getIdPaciente());

            preparedStmt.execute();
            resultado=true;
            preparedStmt.close();
            conn.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
        return resultado;
    }
    public static Domicilio getDomicilioByPaciente(Integer idPaciente){
        Domicilio d=null;
        try{
            Connection c= Conexion.getConexion();
            Statement statement = c.createStatement();
            ResultSet rs=statement.executeQuery("select * from "+ table + " where idpaciente  = "+idPaciente+";");
            if(!rs.next()){
                System.out.println("no hubo una coincidencia con la base de datos");
                return d;
            }else{
                d = new Domicilio(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
            }
            rs.close();
            statement.close();
            c.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
        return d;
    }
}
