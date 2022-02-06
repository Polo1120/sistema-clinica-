package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import vista.LoginFrm;

public class UsuarioDAO {

    PreparedStatement ps;
    ResultSet rs;
    Conexion con = new Conexion();
    Connection acceso;
    LoginFrm objFrm = new LoginFrm();

    public Usuario ValidarUsuario(String nickUser, String passUser, String tipoUser) {
        Usuario objUsuario = new Usuario();
        String sql = "select * from usuarios where NickUsuario=? and PassUsuario=? and TipoUsuario=?";
        try {
            acceso = con.Conectar();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, nickUser);
            ps.setString(2, passUser);
            ps.setString(3, tipoUser);
            rs = ps.executeQuery();
            while (rs.next()) {

                objUsuario.setIdUsuario(rs.getInt(1));
                objUsuario.setNickUsuario(rs.getString(2));
                objUsuario.setPassUsuario(rs.getString(3));
                objUsuario.setTipoUsuario(rs.getString(4));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(objFrm, "Error " + e);
        }
        return objUsuario;
    }
}
