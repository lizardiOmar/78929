package mx.ginelife.db.dao;

import java.util.ArrayList;
import java.util.List;

import java.net.URISyntaxException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mx.ginelife.db.Conexion;
import mx.ginelife.db.obj.Cita;

public class CitaDAO {

    private static final String table="citas";

    public static List <Cita> getCitas(){
        List <Cita> citas=new ArrayList<>();
        Statement statement;
        try{
            Connection c= Conexion.getConexion();
            statement = c.createStatement();
            ResultSet rs=statement.executeQuery("select * from "+ table);
            if(!rs.next()){
                System.out.println("no hubo una coincidencia con la base de datos");
                return null;
            }else{
                Cita aux= new Cita(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(8));
                citas.add(aux);
                while(rs.next()){
                    aux= new Cita(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(8));
                    citas.add(aux);
                }
            }
            rs.close();
            statement.close();
            c.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
        return citas;
    }
    public static List <Cita> getItinerarioByDiaAndMes(String dia, String mes){
        List <Cita> citas=new ArrayList<>();
        Statement statement;
        try{
            Connection c= Conexion.getConexion();
            statement = c.createStatement();
            ResultSet rs=statement.executeQuery("select * from "+ table + " where dia  = '" + dia + "' and mes = '"+mes+"';" );
            if(!rs.next()){
                System.out.println("no hubo una coincidencia con la base de datos");
                return null;
            }else{
                Cita aux= new Cita(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
                citas.add(aux);
                while(rs.next()){
                    aux= new Cita(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7),  rs.getInt(8));
                    citas.add(aux);
                }
            }
            rs.close();
            statement.close();
            c.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
        return citas;
    }
    public static List <Cita> getCitasByGinecologa(int idginecologa){
        List <Cita> citas=new ArrayList<>();
        Statement statement;
        try{
            Connection c= Conexion.getConexion();
            statement = c.createStatement();
            ResultSet rs=statement.executeQuery("select * from "+ table + " where idginecologa  = " + idginecologa );
            if(!rs.next()){
                System.out.println("no hubo una coincidencia con la base de datos");
                return null;
            }else{
                Cita aux= new Cita(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
                citas.add(aux);
                while(rs.next()){
                    aux= new Cita(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7),  rs.getInt(8));
                    citas.add(aux);
                }
            }
            rs.close();
            statement.close();
            c.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
        return citas;
    }
    public static boolean registrarCita(Cita c){
        boolean resultado=false;
        try{
            String query =  " insert into "+table+" (nombre, hora, dia, mes, descripcion, idGinecologa, estado)"+ " values (?, ?, ?, ?, ?, ?, ?)";
            Connection conn= Conexion.getConexion();
            PreparedStatement preparedStmt = conn.prepareStatement(query);
			
            preparedStmt.setString(1, c.getNombre());
            preparedStmt.setString(2, c.getHora());
            preparedStmt.setString(3, c.getDia());
            preparedStmt.setString(4, c.getMes());
            preparedStmt.setString(5, c.getDescripcion());
            preparedStmt.setInt(6, c.getIdGinecologa());
            preparedStmt.setInt(7, c.getEstado());
            preparedStmt.execute();
            resultado=true;
            preparedStmt.close();
            conn.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
        return resultado;
    }
}
