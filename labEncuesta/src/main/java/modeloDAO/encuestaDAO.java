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
    public Encuesta buscarEncuestaPorUsuario(int userId) {
    Encuesta encuesta = null;
    String sql = "SELECT * FROM encuesta WHERE user_id = ?";
    try {
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setInt(1, userId);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            encuesta = new Encuesta();
            // Obtén los valores de la encuesta desde el ResultSet y configura el objeto encuesta
            encuesta.setUser_id(rs.getInt("user_id"));
            encuesta.setNombre(rs.getString("nombre"));
            encuesta.setSexo(rs.getString("sexo"));
              encuesta.setDeporte_favorito(rs.getString("deporte_favorito"));
                encuesta.setNivel_estudio(rs.getString("nivel_estudio"));
                encuesta.setTemas_favoritos(rs.getString("temas_favoritos"));
                encuesta.setFecha(rs.getString("fecha"));
                encuesta.setHora(rs.getString("hora"));
            // Configura otros atributos de la encuesta
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return encuesta;
}

    
    
    public boolean usuarioRealizoEncuesta(int userId) {
        String sql = "SELECT COUNT(*) AS count FROM encuesta WHERE user_id = ?";
        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int count = result.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
