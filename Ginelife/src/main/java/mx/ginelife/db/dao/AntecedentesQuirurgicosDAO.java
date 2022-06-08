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
import mx.ginelife.db.obj.AntecedentesQuirurgicos;

public class AntecedentesQuirurgicosDAO {
    private static final String table="antecedentesQuirurgicos";
    //Agregar antecedente traumatico a la Base de Datos
    public static boolean registrarAntecedenteQuirurgico(AntecedentesQuirurgicos aq){
        boolean resultado=false;
        System.out.println(aq.toString());
        try{
            String query =  "insert into "+table+" (descripcion, fecha, idHistoriaClinica) values (?, ?, ?);";
            Connection conn= Conexion.getConexion();
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString(1, aq.getDescripcion()); 
            preparedStmt.setDate(2, Date.valueOf(aq.getFecha()));
            preparedStmt.setInt(3, aq.getIdHistoriaClinica()); 

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
    public static List <AntecedentesQuirurgicos> getAQByHC(int idHC){
        List <AntecedentesQuirurgicos> AT=new ArrayList<>();
        try{
            Connection c= Conexion.getConexion();
            Statement statement = c.createStatement();
            ResultSet rs=statement.executeQuery("select * from "+ table + " where idHistoriaClinica  = " + idHC + " order by fecha desc" );
            if(!rs.next()){
                System.out.println("no hubo una coincidencia con la base de datos");
                return null;
            }else{
                AntecedentesQuirurgicos aux = new AntecedentesQuirurgicos(rs.getInt(1), rs.getString(2), String.valueOf(rs.getDate(3)) , rs.getInt(4));
                AT.add(aux);
                while(rs.next()){
                    aux = new AntecedentesQuirurgicos(rs.getInt(1), rs.getString(2), String.valueOf(rs.getDate(3)) , rs.getInt(4));
                    AT.add(aux);
                }
            }
            rs.close();
            statement.close();
            c.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
        return AT;
    }
}
