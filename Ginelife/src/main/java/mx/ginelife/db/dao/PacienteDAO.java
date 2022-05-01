package mx.ginelife.db.dao;

import java.net.URISyntaxException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import mx.ginelife.db.Conexion;
import mx.ginelife.db.obj.Paciente;

public class PacienteDAO {
    private static final String table="pacientes";
    public static boolean registrarPaciente(Paciente p){
        boolean resultado=false;
        try{
            String query =  " insert into "+table+" (nombres, apellidopaterno, apellidomaterno, edad, estadocivil, ocupacion, escolaridad, ciudaddenacimiento, entidaddenacimiento, telefono, correo, idginecologa, fechanacimiento)"+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            Connection conn= Conexion.getConexion();
            PreparedStatement preparedStmt = conn.prepareStatement(query);
			
            preparedStmt.setString(1, p.getNombres());
            preparedStmt.setString(2, p.getApellidoPaterno());
            preparedStmt.setString(3, p.getApellidoMaterno());
            preparedStmt.setString(4, p.getEdad());
            preparedStmt.setString(5, p.getEstadoCivil());
            preparedStmt.setString(6, p.getOcupacion());
            preparedStmt.setString(7, p.getEscolaridad());
            preparedStmt.setString(8, p.getCiudadDeNacimiento());
            preparedStmt.setString(9, p.getEntidadDeNacimiento());
            preparedStmt.setString(10, p.getTelefono());
            preparedStmt.setString(11, p.getCorreo());
            preparedStmt.setInt(12, p.getIdGinecologa());
            preparedStmt.setDate(13, Date.valueOf(p.getFechaDeNacimiento()));

            preparedStmt.execute();
            resultado=true;
            preparedStmt.close();
            conn.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
        return resultado;
    }
    public static int getLastPacienteId(int idGinecologa){
        int id=0;
        try{
            Connection c= Conexion.getConexion();
            Statement statement = c.createStatement();
            ResultSet rs=statement.executeQuery("SELECT * FROM "+ table + " where idginecologa  = "+idGinecologa+" ORDER BY idpaciente DESC LIMIT 1;");
            if(!rs.next()){
                System.out.println("no hubo una coincidencia con la base de datos");
                return 0;
            }else{
                id = rs.getInt(1);
            }
            rs.close();
            statement.close();
            c.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
        return id;
    }
    public static List <Paciente> getPacientessByGinecologa(int idginecologa){
        List <Paciente> pacientes=new ArrayList<>();
        try{
            Connection c= Conexion.getConexion();
            Statement statement = c.createStatement();
            ResultSet rs=statement.executeQuery("select * from "+ table + " where idginecologa  = " + idginecologa + " order by apellidopaterno, apellidomaterno, nombres asc" );
            if(!rs.next()){
                System.out.println("no hubo una coincidencia con la base de datos");
                return null;
            }else{
                Paciente aux = new Paciente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getInt(13), rs.getString(14));
                pacientes.add(aux);
                while(rs.next()){
                    aux = new Paciente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getInt(13), rs.getString(14));
                    pacientes.add(aux);
                }
            }
            rs.close();
            statement.close();
            c.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
        return pacientes;
    }
    public static Paciente getPacienteById(Integer idPaciente){
        Paciente p=null;        
        try{
            Connection c= Conexion.getConexion();
            Statement statement = c.createStatement();
            ResultSet rs=statement.executeQuery("select * from "+ table + " where idpaciente  = "+idPaciente+";");
            if(!rs.next()){
                System.out.println("no hubo una coincidencia con la base de datos");
                return p;
            }else{
                p = new Paciente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getInt(13), rs.getString(14));
            }
            rs.close();
            statement.close();
            c.close();
        }catch(SQLException | URISyntaxException e){
            System.out.println(e.getMessage());
        }
        return p;
    }
}
