package mx.ginelife.db.dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import mx.ginelife.db.Conexion;
import mx.ginelife.db.obj.ExploracionFisica;

public class ExploracionFisicaDAO {
    private static final String table="exploracionesFisicas";
    //Crear Exploración física en la Base de Datos
    public static boolean registrarExploracionFisica(ExploracionFisica ef){
        boolean resultado=false;
        System.out.println(ef.toString());
        try{
            String query =  " insert into "+table+" (fecha, hora, consciente, habitus, peso, estatura, ta, fc, fr, temperatura, pulmones, pulmones_nota, corazon, corazon_nota, cabeza, cabeza_nota, cuello, cuello_nota, pronostico, planManejo, idPaciente)"+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?);";
            Connection conn= Conexion.getConexion();
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setDate(1, Date.valueOf(ef.getFecha()));
            preparedStmt.setTime(2, Time.valueOf(ef.getHora())); 
            preparedStmt.setInt(3, ef.getConsciente()); 
            preparedStmt.setString(4, ef.getHabitus());
            preparedStmt.setString(5, ef.getPeso());  
            preparedStmt.setString(6, ef.getEstatura()); 
            preparedStmt.setString(7, ef.getTa());
            preparedStmt.setString(8, ef.getFc());
            preparedStmt.setString(9, ef.getFr());
            preparedStmt.setString(10, ef.getTemperatura());
            preparedStmt.setInt(11, ef.getPulmones()); 
            preparedStmt.setString(12, ef.getPulmones_nota());
            preparedStmt.setInt(13, ef.getCorazon()); 
            preparedStmt.setString(14, ef.getCorazon_nota());
            preparedStmt.setInt(15, ef.getCabeza()); 
            preparedStmt.setString(16, ef.getCabeza_nota());
            preparedStmt.setInt(17, ef.getCuello()); 
            preparedStmt.setString(18, ef.getCuello_nota());
            preparedStmt.setString(19, ef.getPronostico());
            preparedStmt.setString(20, ef.getPlan());
            preparedStmt.setInt(21, ef.getIdPaciente()); 
            preparedStmt.execute();
            resultado=true;
            preparedStmt.close();
            conn.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
        return resultado;
    }
    //Obtener ultima exploración registrada
    public static int getLastExploracionFisicaId(){
        int id=0;
        try{
            Connection c= Conexion.getConexion();
            Statement statement = c.createStatement();
            ResultSet rs=statement.executeQuery("SELECT * FROM "+ table +" ORDER BY idExploracionFisica DESC LIMIT 1;");
            if(!rs.next()){
                System.out.println("no hubo una coincidencia con la base de datos");
                return 0;
            }else{
                id = rs.getInt("idExploracionFisica");
                
            }
            rs.close();
            statement.close();
            c.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
        System.out.println("ID exploración física: "+id);
        return id;
    }
    //Obtener todas las exploraciones por paciente
    public static List <ExploracionFisica> getExploracionesFisicasByPaciente(int idPaciente){
        List <ExploracionFisica> exploraciones=new ArrayList<>();
        try{
            Connection c= Conexion.getConexion();
            Statement statement = c.createStatement();
            ResultSet rs=statement.executeQuery("select * from "+ table + " where idPaciente  = " + idPaciente + " order by fecha, hora asc" );
            if(!rs.next()){
                System.out.println("no hubo una coincidencia con la base de datos");
                return null;
            }else{
                ExploracionFisica aux = new ExploracionFisica(rs.getInt(1), String.valueOf(rs.getDate(2)), String.valueOf(rs.getTime(3)), rs.getString(20), rs.getString(21), rs.getInt(22));
                exploraciones.add(aux);
                while(rs.next()){
                    aux = new ExploracionFisica(rs.getInt(1), String.valueOf(rs.getDate(2)), String.valueOf(rs.getTime(3)), rs.getString(20), rs.getString(21), rs.getInt(22));
                    exploraciones.add(aux);
                }
            }
            rs.close();
            statement.close();
            c.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
        return exploraciones;
    }
    //Get exploracion fisica by id
    public static ExploracionFisica getExFiyId(int idEF){
        Statement statement;
        ExploracionFisica aux = null;
        try{
            Connection conn= Conexion.getConexion();
            statement = conn.createStatement();
            ResultSet rs=statement.executeQuery("select * from "+ table + " where idExploracionFisica='"
            +idEF+"';");
            if(rs.next()){
                //Crear Exploracion Fisica obj
                aux=new ExploracionFisica(rs.getInt("idExploracionFisica"), String.valueOf(rs.getDate("fecha")), String.valueOf(rs.getTime("hora")), rs.getInt("consciente"), rs.getString("habitus"), rs.getString("peso"), rs.getString("estatura"), rs.getString("ta"), rs.getString("fc"), rs.getString("fr"), rs.getString("temperatura"), rs.getInt("pulmones"), rs.getString("pulmones_nota"), rs.getInt("corazon"), rs.getString("corazon_nota"), rs.getInt("cabeza"), rs.getString("cabeza_nota"), rs.getInt("cuello"), rs.getString("cuello_nota"), rs.getString("pronostico"), rs.getString("planManejo"), rs.getInt("idPaciente"));
            }
            rs.close();
            statement.close();
            conn.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }

        return aux;
    }
}
