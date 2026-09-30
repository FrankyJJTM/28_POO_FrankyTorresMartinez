package vallegrande.edu.pe.agrofrutoslambayeque.model;

import java.sql.Connection;
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
}