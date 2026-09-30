package vallegrande.edu.pe.agrofrutoslambayeque.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:33062/agrofrutos_db_2";
    private static final String USER = "root";
    private static final String PASSWORD = "111";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}