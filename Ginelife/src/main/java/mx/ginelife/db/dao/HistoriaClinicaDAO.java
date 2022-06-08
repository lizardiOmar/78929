package mx.ginelife.db.dao;


import java.net.URISyntaxException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import mx.ginelife.db.Conexion;
import mx.ginelife.db.obj.HistoriaClinica;

public class HistoriaClinicaDAO {
    private static final String table="historiasClinicas";
    //Crear Historia Clínica en la Base de Datos
    public static boolean registrarHistoriaClinica(HistoriaClinica hc){
        boolean resultado=false;
        System.out.println(hc.toString());
        try{
            String query =  " insert into "+table+" (fecha, hora, idPaciente)"+ " values (?, ?, ?);";
            Connection conn= Conexion.getConexion();
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setDate(1, Date.valueOf(hc.getFecha()));
            preparedStmt.setTime(2, Time.valueOf(hc.getHora())); 
            preparedStmt.setInt(3, hc.getIdPaciente()); 
            preparedStmt.execute();
            resultado=true;
            preparedStmt.close();
            conn.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
        return resultado;
    }

    public static int getLastHistoriaClinicaId(){
        int id=0;
        try{
            Connection c= Conexion.getConexion();
            Statement statement = c.createStatement();
            ResultSet rs=statement.executeQuery("SELECT * FROM "+ table +" ORDER BY idHistoriaClinica DESC;");
            rs.next();
            id = rs.getInt(1);
            rs.close();
            statement.close();
            c.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
        return id;
    }
}
