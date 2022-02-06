package modelo;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MedicamentosDAO implements CRUD {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public int actualizarStock(int cant, int idp) {
        String sql = "update medicamentos set Stock=? where IdMedicamento=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setInt(2, idp);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    public Medicamentos ListarID(int id) {
        Medicamentos md = new Medicamentos();
        String sql = "select * from medicamentos where IdMedicamento=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                md.setIdMedicamento(rs.getInt(1));
                md.setNombreMedicamento(rs.getString(2));
                md.setStock(rs.getInt(3));
                md.setEstado(rs.getString(4));
            }
        } catch (Exception e) {
        }
        return md;
    }

    @Override
    public List listar() {
        List<Medicamentos> lista = new ArrayList<>();
        String sql = "select * from medicamentos";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Medicamentos md = new Medicamentos();
                md.setIdMedicamento(rs.getInt(1));
                md.setNombreMedicamento(rs.getString(2));
                md.setStock(rs.getInt(3));
                md.setEstado(rs.getString(4));
                lista.add(md);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    @Override
    public int add(Object[] o) {
        int r = 0;
        String sql = "insert into medicamentos (NombreMedicamento,Stock,Estado) values(?,?,?)";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            r = ps.executeUpdate();

        } catch (Exception e) {
        }
        return r;
    }

    @Override
    public int actualizar(Object[] o) {
        int r = 0;
        String sql = "update medicamentos set NombreMedicamento=?,Stock=?,Estado=? where IdMedicamento=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    @Override
    public void eliminar(int id) {
        String sql = "delete from medicamentos where IdMedicamento=?";
        try {
            con = cn.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

}
