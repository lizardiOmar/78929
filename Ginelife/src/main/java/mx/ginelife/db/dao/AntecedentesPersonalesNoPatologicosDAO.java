package mx.ginelife.db.dao;

import java.net.URISyntaxException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mx.ginelife.db.Conexion;
import mx.ginelife.db.obj.AntecedentesPersonalesNoPatologicos;

public class AntecedentesPersonalesNoPatologicosDAO {
    private static final String table="antecedentesPersonalesNoPatologicos";
    //Agregar antecedentes personales no patologicos a la Base de Datos
    public static boolean registrarAntecedentesPersonalesNoPatologicos(AntecedentesPersonalesNoPatologicos apnp){
        boolean resultado=false;
        System.out.println(apnp.toString());
        try{
            String query =  "insert into "+table+" (tabaquismo, alcoholismo, drogas, rubeola, influenza, tetanos, covid19, idHistoriaClinica) values (?, ?, ?, ?, ?, ?, ?, ?);";
            Connection conn= Conexion.getConexion();
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setInt(1, apnp.getTabaquismo()); 
            preparedStmt.setInt(2, apnp.getAlcoholismo()); 
            preparedStmt.setInt(3, apnp.getDrogas()); 
            preparedStmt.setInt(4, apnp.getRubeola()); 
            preparedStmt.setInt(5, apnp.getInfluenza()); 
            preparedStmt.setInt(6, apnp.getTetanos()); 
            preparedStmt.setInt(7, apnp.getCovid19()); 
            preparedStmt.setInt(8, apnp.getIdHistoriaClinica()); 

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
