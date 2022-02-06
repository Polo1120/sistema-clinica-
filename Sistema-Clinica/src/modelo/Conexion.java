package modelo;

import java.sql.DriverManager;

import java.sql.Connection;
import javax.swing.JOptionPane;
import vista.LoginFrm;



public class Conexion {

    
    Connection con = null;
     LoginFrm objlogin = new LoginFrm();
//    String url = "jdbc:mysql://localhost:3306/login1";
//    String user = "root";
//    String pass = "";

    public  Connection Conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection)DriverManager.getConnection( "jdbc:mysql://localhost:3306/sistema_clinica","root","");
           
        } catch (Exception e) {
           JOptionPane.showMessageDialog(objlogin,"error " + e);
        }
        return con;
    }
}
