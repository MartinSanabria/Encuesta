/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDAO;
import DBConection.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Encuesta;
import modelo.Usuario;
/**
 *
 * @author X1
 */
public class encuestaDAO {
 private Connection conexion;

    public encuestaDAO() {
        Conexion conexionManager = new Conexion();
        conexion = conexionManager.getConection();
    }


    
    public boolean agregar(Encuesta encuesta){
        String sql="insert into encuesta (user_id, nombre, sexo, deporte_favorito, nivel_estudio, temas_favoritos, fecha, hora) values(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, encuesta.getUser_id());
            statement.setString(2, encuesta.getNombre());
            statement.setString(3, encuesta.getSexo());
            statement.setString(4, encuesta.getDeporte_favorito());
            statement.setString(5, encuesta.getNivel_estudio());
            statement.setString(6, encuesta.getTemas_favoritos());
            statement.setString(7, encuesta.getFecha()); // Agregar fecha
            statement.setString(8, encuesta.getHora());  // Agregar hora
            int filasAfectadas=statement.executeUpdate();
            if(filasAfectadas>0){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }
    
    
    public Encuesta buscarPorID(int id){
        String sql="select * from encuesta where encuesta_id=?";
        Encuesta encuesta=new Encuesta();
        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                encuesta.setUser_id(result.getInt("user_id"));
                encuesta.setNombre(result.getString("nombre"));
                encuesta.setSexo(result.getString("sexo"));
                encuesta.setDeporte_favorito(result.getString("deporte_favorito"));
                encuesta.setNivel_estudio(result.getString("nivel_estudio"));
                encuesta.setTemas_favoritos(result.getString("temas_favoritos"));
                encuesta.setFecha(result.getString("fecha"));
                encuesta.setHora(result.getString("hora"));
            
            }

        }catch (Exception e){

        }
        return encuesta;
    }

}
