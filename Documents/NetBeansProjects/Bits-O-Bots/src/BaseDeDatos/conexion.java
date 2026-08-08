/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author allan
 */
public class conexion {
    
    static Connection conectar = null;
    static String usuario = "root";
    static String clave = "btpi";
    static String cadena = "jdbc:mysql://localhost:3306/bits_o_bots";
    
    public static Connection crearConexion () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena, usuario, clave);
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Conexión fallida, error: " + e.toString());
        }
        return conectar;
    }
    
    public static void cerrarConexion(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e);
        }
    }
}