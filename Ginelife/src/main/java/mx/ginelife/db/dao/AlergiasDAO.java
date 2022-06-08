package mx.ginelife.db.dao;

import java.net.URISyntaxException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import mx.ginelife.db.Conexion;
import mx.ginelife.db.obj.Alergias;

public class AlergiasDAO {
    private static final String table="alergias";
    //Agregar alergia a la Base de Datos
    public static boolean registrarAlergia(Alergias alergia){
        boolean resultado=false;
        System.out.println(alergia.toString());
        try{
            String query =  "insert into "+table+" (nombre, idHistoriaClinica) values (?, ?);";
            Connection conn= Conexion.getConexion();
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString(1, alergia.getNombre()); 
            preparedStmt.setInt(2, alergia.getIdHistoriaClinica()); 

            preparedStmt.execute();
            resultado=true;
            preparedStmt.close();
            conn.close();
        }catch(SQLException | URISyntaxException e){
            resultado=false;
            System.out.println(e.getMessage());
        }
        return resultado;
    }
    public static List <Alergias> getAlergiasByHC(int idHC){
        List <Alergias> alergias=new ArrayList<>();
        try{
            Connection c= Conexion.getConexion();
            Statement statement = c.createStatement();
            ResultSet rs=statement.executeQuery("select * from "+ table + " where idHistoriaClinica  = " + idHC );
            if(!rs.next()){
                System.out.println("no hubo una coincidencia con la base de datos");
                return null;
            }else{
                Alergias aux = new Alergias(rs.getInt(1), rs.getString(2), rs.getInt(3));
                alergias.add(aux);
                while(rs.next()){
                    aux = new Alergias(rs.getInt(1), rs.getString(2), rs.getInt(3));
                    alergias.add(aux);
                }
            }
            rs.close();
            statement.close();
            c.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
        return alergias;
    }
}
