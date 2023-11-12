package conexion;

import java.sql.*;
public class Conexion {
    public static Connection conexionBD(){
        Connection cn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost/gmedina", "root", "");
            System.out.println("La base de datos esta conectada!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Base de datos no conectada!");
        }
        return cn;
    }
    public static void main(String[] args) {
        conexionBD();
    }
    
}
