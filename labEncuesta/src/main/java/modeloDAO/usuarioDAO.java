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
import modelo.Usuario;
/**
 *
 * @author X1
 */
public class usuarioDAO {
    private Connection conexion;

    public usuarioDAO() {
        Conexion conexionManager = new Conexion();
        conexion = conexionManager.getConection();
    }

    public void guardarUsuario(Usuario usuario) {
        try {
            String sql = "INSERT INTO Usuarios (Nombre, CorreoElectronico, Contraseña, Rol) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getCorreoElectronico());
            statement.setString(3, usuario.getContraseña());
            statement.setInt(4, usuario.getRol());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
    }

    public Usuario obtenerUsuarioPorID(int id) {
        try {
            String sql = "SELECT * FROM Usuarios WHERE ID = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                int ID = result.getInt("ID");
                String nombre = result.getString("Nombre");
                String correoElectronico = result.getString("CorreoElectronico");
                String contraseña = result.getString("Contraseña");
                int rol = result.getInt("Rol");
                return new Usuario(ID, nombre, correoElectronico, contraseña, rol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
        return null;
    }
    public Usuario iniciarSesion(String correo, String contrasena) {
        String sql = "SELECT * FROM Usuarios WHERE CorreoElectronico = ? AND Contraseña = ?";
    
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, correo); // Establece el valor para el primer ? como correo
            ps.setString(2, contrasena); // Establece el valor para el segundo ? como contrasena
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int ID = rs.getInt("ID");
                String nombre = rs.getString("Nombre");
                String correoElectronico = rs.getString("CorreoElectronico");
                int rol = rs.getInt("Rol"); // Supongamos que el rol es un entero

                // Crea un objeto Usuario con los datos obtenidos
                Usuario usuario = new Usuario(ID, nombre, correoElectronico, contrasena, rol);

                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Maneja las excepciones aquí.
        }
        return null; // Retornar null si la autenticación falla.
    }

}
