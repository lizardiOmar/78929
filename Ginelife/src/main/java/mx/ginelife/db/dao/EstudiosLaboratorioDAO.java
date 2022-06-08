package mx.ginelife.db.dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mx.ginelife.db.Conexion;
import mx.ginelife.db.obj.EstudiosLaboratorio;

public class EstudiosLaboratorioDAO {
    private static final String table="estudioslaboratorio";
    //Crear Estudio de lab. en la Base de Datos
    public static boolean registrarEstudiosLaboratorio(EstudiosLaboratorio el){
        boolean resultado=false;
        System.out.println(el.toString());
        try{
            String query =  " insert into "+table+" (idExploracionFisica, grupoSanguineo, rh, hemoglobina, hematrocito, leucocitos, plaquetas, tp, tpt, glucosa, vdrl, antihiv)"+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            Connection conn= Conexion.getConexion();
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, el.getIdExploracionFisica()); 
            preparedStmt.setString(2, el.getGrupoSanguineo());
            preparedStmt.setString(3, el.getRh());  
            preparedStmt.setString(4, el.getHemoglobina()); 
            preparedStmt.setString(5, el.getHematrocito());
            preparedStmt.setString(6, el.getLeucocitos());
            preparedStmt.setString(7, el.getPlaquetas());
            preparedStmt.setString(8, el.getTp());
            preparedStmt.setString(9, el.getTpt()); 
            preparedStmt.setString(10, el.getGlucosa());
            preparedStmt.setString(11, el.getVdrl()); 
            preparedStmt.setString(12, el.getAntihiv());
            preparedStmt.execute();
            resultado=true;
            preparedStmt.close();
            conn.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
        return resultado;
    }
    //Get exploracion fisica by id
    public static EstudiosLaboratorio getExFiyId(int idEL){
        Statement statement;
        EstudiosLaboratorio aux = null;
        try{
            Connection conn= Conexion.getConexion();
            statement = conn.createStatement();
            ResultSet rs=statement.executeQuery("select * from "+ table + " where idExploracionFisica='"
            +idEL+"';");
            if(rs.next()){
                //Crear Estudios Lab. obj
                aux=new EstudiosLaboratorio(rs.getInt("idEstudioLaboratorio"), rs.getInt("idExploracionFisica"), rs.getString("grupoSanguineo"), rs.getString("rh"), rs.getString("hemoglobina"), rs.getString("hematrocito"), rs.getString("leucocitos"), rs.getString("plaquetas"), rs.getString("tp"), rs.getString("tpt"), rs.getString("glucosa"), rs.getString("vdrl"), rs.getString("antihiv"));
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
