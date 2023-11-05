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

     public boolean agregar(Usuario usuario){
        String sql="insert into usuarios (nombre, email, password, rol) values(?,?,?,?)";
        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1,usuario.getNombre());
            statement.setString(2,usuario.getEmail());
            statement.setString(3,usuario.getPassword());
            statement.setInt(4,usuario.getRol());
            int filasAfectadas=statement.executeUpdate();
            if(filasAfectadas>0){
                return true;
            }
        }catch (Exception e){

        }
        return false;
    }
    
     
    public Usuario buscarPorID(int id) {
        String sql = "SELECT * FROM usuarios WHERE user_id = ?";
        Usuario usuario = null;

        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                usuario = new Usuario();
                usuario.setUser_id(result.getInt("user_id"));
                usuario.setNombre(result.getString("nombre"));
                usuario.setEmail(result.getString("email"));
                usuario.setPassword(result.getString("password"));
                usuario.setRol(result.getInt("rol"));
                // Configura otros atributos del usuario según sea necesario
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Aquí puedes manejar la excepción de una manera más adecuada, como lanzar una excepción personalizada o retornar un valor especial
        }

        return usuario;
    }
    public Usuario actualizarUser(int userId, String email, String nombre,String password) {
    String sql = "UPDATE usuarios SET email = ?, nombre = ?, password = ? WHERE user_id = ?";
    Usuario usuario = null;

    try {
        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, nombre);
        statement.setString(3, password);
        statement.setInt(4, userId);

        int rowsUpdated = statement.executeUpdate();

        if (rowsUpdated > 0) {
            usuario = new Usuario();
            usuario.setUser_id(userId);
            usuario.setEmail(email);
            usuario.setNombre(nombre);
            usuario.setPassword(password);
            // Configura otros atributos del perfil según sea necesario
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Manejo de la excepción, como lanzar una excepción personalizada o registrar el error
    }

    return usuario;
}



    
    public Usuario ConsultaUsuario(String email, String password, int user_rol){

        Usuario user=new Usuario();
        String sql="select * from usuarios where email = ?  and password = ? and rol = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, email); // Establece el valor para el primer ? como correo
            ps.setString(2, password);
            ps.setInt(3, user_rol);// Establece el valor para el segundo ? como contrasena
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                user.setUser_id(rs.getInt("user_id"));
                user.setNombre(rs.getString("nombre"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRol(rs.getInt("rol"));
                
            }
        }catch (Exception e){

        }
        return user;
    }
    
    
     public Usuario buscarPorCorreo(String correo){
        String sql="select * from usuarios where email=?";
        Usuario usuario=new Usuario();
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, correo); // Establece el valor para el primer ? como correo
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                usuario.setUser_id(rs.getInt("user_id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
                usuario.setRol(rs.getInt("rol"));
            
            }

        }catch (Exception e){

        }
        return usuario;
    }

}
