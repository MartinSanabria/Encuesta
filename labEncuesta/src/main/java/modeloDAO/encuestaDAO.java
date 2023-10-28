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

    public void guardarEncuesta(Encuesta encuesta) {
        try {
            String sql = "INSERT INTO Encuesta (UsuarioID, Nombre, Sexo, DeporteFavorito, NivelEstudio, TemasFavoritos, Fecha, Hora) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, encuesta.getUsuarioID());
            statement.setString(2, encuesta.getNombre());
            statement.setString(3, encuesta.getSexo());
            statement.setString(4, encuesta.getDeporteFavorito());
            statement.setString(5, encuesta.getNivelEstudio());
            statement.setString(6, encuesta.getTemasFavoritos());
            statement.setString(7, encuesta.getFecha()); // Agregar fecha
            statement.setString(8, encuesta.getHora());  // Agregar hora
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
    }

    public Encuesta obtenerEncuestaPorID(int id) {
        try {
            String sql = "SELECT * FROM Encuesta WHERE ID = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                int ID = result.getInt("ID");
                int usuarioID = result.getInt("UsuarioID");
                String nombre = result.getString("Nombre");
                String sexo = result.getString("Sexo");
                String deporteFavorito = result.getString("DeporteFavorito");
                String nivelEstudio = result.getString("NivelEstudio");
                String temasFavoritos = result.getString("TemasFavoritos");
                String fecha = result.getString("Fecha"); // Obtener fecha
                String hora = result.getString("Hora");   // Obtener hora
                return new Encuesta(ID, usuarioID, nombre, sexo, deporteFavorito, nivelEstudio, temasFavoritos, fecha, hora);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
        return null;
    }
}
