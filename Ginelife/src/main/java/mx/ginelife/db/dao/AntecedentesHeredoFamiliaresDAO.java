package mx.ginelife.db.dao;

import java.net.URISyntaxException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mx.ginelife.db.Conexion;
import mx.ginelife.db.obj.AntecedentesHeredoFamiliares;

public class AntecedentesHeredoFamiliaresDAO {
    private static final String table="antecedentesHeredoFamiliares";
    //Agregar antecedentes heredo familiares a la Base de Datos
    public static boolean registrarAntecedentesHeredoFamiliares(AntecedentesHeredoFamiliares ahf){
        boolean resultado=false;
        System.out.println(ahf.toString());
        try{
            String query =  "insert into "+table+" (diabetes, hta, neoplasticos, cardiopatias, tiroides, especificaciones, idHistoriaClinica)"+ " values (?, ?, ?, ?, ?, ?, ?);";
            Connection conn= Conexion.getConexion();
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setInt(1, ahf.getDiabetes()); 
            preparedStmt.setInt(2, ahf.getHta()); 
            preparedStmt.setInt(3, ahf.getNeoplasticos()); 
            preparedStmt.setInt(4, ahf.getCardiopatias()); 
            preparedStmt.setInt(5, ahf.getTiroides()); 
            preparedStmt.setString(6, ahf.getEspecificaciones());
            preparedStmt.setInt(7, ahf.getIdHistoriaClinica()); 

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

}
