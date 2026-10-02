package vallegrande.edu.pe.agrofrutoslambayeque.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContactoDao {

    public List<Contacto> listarContactos() {
        List<Contacto> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, apellido, telefono, correo, mensaje, fecha_creacion FROM contactos";

        try (Connection conn = Conexion.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Contacto c = new Contacto();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setTelefono(rs.getString("telefono"));
                c.setCorreo(rs.getString("correo"));
                c.setMensaje(rs.getString("mensaje"));
                c.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean agregarContacto(Contacto contacto) {
        String sql = "INSERT INTO contactos (nombre, apellido, telefono, correo, mensaje) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, contacto.getNombre());
            pstmt.setString(2, contacto.getApellido());
            pstmt.setString(3, contacto.getTelefono());
            pstmt.setString(4, contacto.getCorreo());
            pstmt.setString(5, contacto.getMensaje());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}