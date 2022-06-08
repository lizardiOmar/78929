package mx.ginelife.db.dao;

import java.net.URISyntaxException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mx.ginelife.db.Conexion;
import mx.ginelife.db.obj.AntecedentesPersonalesPatologicos;

public class AntecedentesPersonalesPatologicosDAO {
    private static final String table="antecedentesPersonalesPatologicos";
    //Agregar antecedentes personales patologicos a la Base de Datos
    public static boolean registrarAntecedentesPersonalesPatologicos(AntecedentesPersonalesPatologicos app){
        boolean resultado=false;
        System.out.println(app.toString());
        try{
            String query =  "insert into "+table+" (diabetes, hta, nefropatias, cardiopatias, epilepsia, idHistoriaClinica) values (?, ?, ?, ?, ?, ?);";
            Connection conn= Conexion.getConexion();
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setInt(1, app.getDiabetes()); 
            preparedStmt.setInt(2, app.getHta()); 
            preparedStmt.setInt(3, app.getNefropatias()); 
            preparedStmt.setInt(4, app.getCardiopatias()); 
            preparedStmt.setInt(5, app.getEpilepsia()); 
            preparedStmt.setInt(6, app.getIdHistoriaClinica()); 

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
