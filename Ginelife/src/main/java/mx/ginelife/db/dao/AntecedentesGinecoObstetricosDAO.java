package mx.ginelife.db.dao;

import java.net.URISyntaxException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mx.ginelife.db.Conexion;
import mx.ginelife.db.obj.AntecedentesGinecoObstetricos;

public class AntecedentesGinecoObstetricosDAO {
    private static final String table="antecedentesGinecoObstetricos";
    //Agregar antecedentes gineco obstetricos a la Base de Datos
    public static boolean registrarAntecedentesGinecoObstetricos(AntecedentesGinecoObstetricos ago){
        boolean resultado=false;
        System.out.println(ago.toString());
        try{
            String query =  "insert into "+table+" (menarca, ritmo, ivs, compaSexuales, gestaciones, partos, abortos, ceareas, ectopico, molar, fup, fum, fpp, padiecimientoActual, idHistoriaClinica) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            Connection conn= Conexion.getConexion();
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString(1, ago.getMenarca()); 
            preparedStmt.setString(2, ago.getRitmo()); 
            preparedStmt.setString(3, ago.getIvs()); 
            preparedStmt.setInt(4, ago.getCompañerosSexuales()); 
            preparedStmt.setInt(5, ago.getGestaciones()); 
            preparedStmt.setInt(6, ago.getPartos()); 
            preparedStmt.setInt(7, ago.getAbortos()); 
            preparedStmt.setInt(8, ago.getCeareas()); 
            preparedStmt.setString(9, ago.getEctopico()); 
            preparedStmt.setString(10, ago.getMolar()); 
            preparedStmt.setString(11, ago.getFup()); 
            preparedStmt.setString(12, ago.getFum()); 
            preparedStmt.setString(13, ago.getFpp()); 
            preparedStmt.setString(14, ago.getPadecimientoActual());
            preparedStmt.setInt(15, ago.getIdHistoriaClinica()); 

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
