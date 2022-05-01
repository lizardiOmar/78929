package mx.ginelife.db.dao;

import java.util.ArrayList;
import java.util.List;

import java.net.URISyntaxException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import mx.ginelife.db.Conexion;
import mx.ginelife.db.obj.Cita;

public class CitaDAO {

    private static final String table="citas";

    public static List <Cita> getCitas(){
        List <Cita> citas=new ArrayList<>();
        
        try{
            Connection c= Conexion.getConexion();
            Statement statement = c.createStatement();
            ResultSet rs=statement.executeQuery("select * from "+ table);
            if(!rs.next()){
                System.out.println("no hubo una coincidencia con la base de datos");
                return null;
            }else{
                Cita aux= new Cita(rs.getInt(1), rs.getString(7), rs.getString(6), rs.getInt(5));
                citas.add(aux);
                while(rs.next()){
                    aux= new Cita(rs.getInt(1), rs.getString(7), rs.getString(6), rs.getInt(5));
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
    public static List <Cita> getItinerarioByFecha(String fecha, Integer idGinecologa){
        List <Cita> citas=new ArrayList<>();
        
        try{
            Connection c= Conexion.getConexion();
            Statement statement = c.createStatement();
            ResultSet rs=statement.executeQuery("select * from "+ table + " where fecha  = '" + fecha + "' and idginecologa = "+idGinecologa+" and estado = 0 order by hora asc;" );
            if(!rs.next()){
                System.out.println("no hubo una coincidencia con la base de datos");
                return null;
            }else{
                Cita aux= new Cita(rs.getInt(1), rs.getString(2), rs.getString(7), rs.getString(6), rs.getString(3), rs.getInt(4), rs.getInt(5));
                citas.add(aux);
                while(rs.next()){
                    aux= new Cita(rs.getInt(1), rs.getString(2), rs.getString(7), rs.getString(6), rs.getString(3), rs.getInt(4), rs.getInt(5));
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
    public static List <Cita> getCitasByDateRange(String fecha1, String fecha2){
        List <Cita> citas=new ArrayList<>();
        
        try{
            Connection c= Conexion.getConexion();
            Statement statement = c.createStatement();
            ResultSet rs=statement.executeQuery("select * from "+ table + " where '[" + fecha1 +", "+ fecha2 + "]'::daterange @> fecha and estado=0 order by fecha asc;" );
            if(!rs.next()){
                System.out.println("no hubo una coincidencia con la base de datos");
                return null;
            }else{
                Cita aux= new Cita(rs.getInt(1), rs.getString(2), rs.getString(7), rs.getString(6), rs.getString(3), rs.getInt(4), rs.getInt(5));
                citas.add(aux);
                while(rs.next()){
                    aux= new Cita(rs.getInt(1), rs.getString(2), rs.getString(7), rs.getString(6), rs.getString(3), rs.getInt(4), rs.getInt(5));
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
    public static Cita getCitaById(Integer idCita){
        Cita cita=null;
        
        try{
            Connection c= Conexion.getConexion();
            Statement statement = c.createStatement();
            ResultSet rs=statement.executeQuery("select * from "+ table + " where idcitas  = "+idCita+";");
            if(!rs.next()){
                System.out.println("no hubo una coincidencia con la base de datos");
                return cita;
            }else{
                cita = new Cita(rs.getInt(1), rs.getString(2), rs.getString(7), rs.getString(6), rs.getString(3), rs.getInt(4), rs.getInt(5));
            }
            rs.close();
            statement.close();
            c.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
        return cita;
    }
    public static List <Cita> getCitasByGinecologa(int idginecologa){
        List <Cita> citas=new ArrayList<>();
        
        try{
            Connection c= Conexion.getConexion();
            Statement statement = c.createStatement();
            ResultSet rs=statement.executeQuery("select * from "+ table + " where idginecologa  = " + idginecologa );
            if(!rs.next()){
                System.out.println("no hubo una coincidencia con la base de datos");
                return null;
            }else{
                Cita aux= new Cita(rs.getInt(1), rs.getString(2), rs.getString(7), rs.getString(6), rs.getString(3), rs.getInt(4), rs.getInt(5));
                citas.add(aux);
                while(rs.next()){
                    aux= new Cita(rs.getInt(1), rs.getString(2), rs.getString(7), rs.getString(6), rs.getString(3), rs.getInt(4), rs.getInt(5));
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
        System.out.println(c.toString());
        try{
            String query =  " insert into "+table+" (nombre, descripcion, idGinecologa, estado, fecha, hora)"+ " values (?, ?, ?, ?, ?, ?)";
            Connection conn= Conexion.getConexion();
            PreparedStatement preparedStmt = conn.prepareStatement(query);
			
            preparedStmt.setString(1, c.getNombre());
            preparedStmt.setString(2, c.getDescripcion());
            preparedStmt.setInt(3, c.getIdGinecologa());
            preparedStmt.setInt(4, c.getEstado());
            preparedStmt.setDate(5, Date.valueOf(c.getFecha()));
            preparedStmt.setTime(6, Time.valueOf(c.getHora())); 
            preparedStmt.execute();
            resultado=true;
            preparedStmt.close();
            conn.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
        return resultado;
    }
    public static boolean cancelarCitaById(int idCita){
        Boolean respuesta=false;
        int affectedrows = 0;
        try{
            Connection c= Conexion.getConexion();
            String query = "update "+table+" set estado = 1 where idcitas = ?";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setInt(1, idCita);
            affectedrows = pstmt.executeUpdate();
            pstmt.close();
            c.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
        if(affectedrows!=0){
            respuesta=true;
        }
        return respuesta;
    }
}
