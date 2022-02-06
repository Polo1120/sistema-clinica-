package controlador;

import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Medicamentos;
import modelo.MedicamentosDAO;
import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.LoginFrm;
import vista.PrincipalFrm;
import vista.ModuloMedicamentos;

public class Controlador implements ActionListener {

    UsuarioDAO udao = new UsuarioDAO();
    Usuario user = new Usuario();
    LoginFrm loginFrm = new LoginFrm();
    PrincipalFrm principalFrm = new PrincipalFrm();
    ModuloMedicamentos medicamentosFrm = new ModuloMedicamentos();
    Medicamentos objMedicamentos = new Medicamentos();
    MedicamentosDAO objmdDAO = new MedicamentosDAO();
    DefaultTableModel modeloMedicamentos = new DefaultTableModel();

    public Controlador(LoginFrm loginFrm) {
        this.loginFrm = loginFrm;
        this.loginFrm.btniniciar.addActionListener(this);

    }

    public Controlador(PrincipalFrm principalfrm) {
        this.principalFrm = principalfrm;
        this.principalFrm.BtnAgregarMedicamento.addActionListener(this);
    }

    public Controlador(ModuloMedicamentos medicamentosFrm) {

        listarMedicamentos(medicamentosFrm.tblMedicamentos);
        this.medicamentosFrm = medicamentosFrm;
        this.medicamentosFrm.btnAgregar.addActionListener(this);
        this.medicamentosFrm.btnActualizar.addActionListener(this);
        this.medicamentosFrm.btnEliminar.addActionListener(this);
        this.medicamentosFrm.btnNuevo.addActionListener(this);
        this.medicamentosFrm.tblMedicamentos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == medicamentosFrm.tblMedicamentos) {
                    int fila = medicamentosFrm.tblMedicamentos.getSelectedRow();
                    if (fila == -1) {
                        JOptionPane.showMessageDialog(medicamentosFrm, "Debe seleccionar una fila por favor");
                    } else {
                        int idMedicamento = Integer.parseInt(medicamentosFrm.tblMedicamentos.getValueAt(fila, 0).toString());
                        String nMedicamento = medicamentosFrm.tblMedicamentos.getValueAt(fila, 1).toString();
                        int stock = Integer.parseInt(medicamentosFrm.tblMedicamentos.getValueAt(fila, 2).toString());
                        String estado = medicamentosFrm.tblMedicamentos.getValueAt(fila, 3).toString();
                        medicamentosFrm.txtID.setText("" + idMedicamento);
                        medicamentosFrm.txtNombreMedicamento.setText(nMedicamento);
                        medicamentosFrm.txtStock.setText("" + stock);
                        medicamentosFrm.cboEstado.setSelectedItem(estado);

                    }
                }
            }

        });

    }

    public void listarMedicamentos(JTable tblMedicamentos) {

        modeloMedicamentos = (DefaultTableModel) tblMedicamentos.getModel();
        limpiarTabla(modeloMedicamentos);
        List<Medicamentos> listaMedicamentos = objmdDAO.listar();
        Object[] ob = new Object[4];
        for (int i = 0; i < listaMedicamentos.size(); i++) {
            ob[0] = listaMedicamentos.get(i).getIdMedicamento();
            ob[1] = listaMedicamentos.get(i).getNombreMedicamento();
            ob[2] = listaMedicamentos.get(i).getStock();
            ob[3] = listaMedicamentos.get(i).getEstado();
            modeloMedicamentos.addRow(ob);
        }
        tblMedicamentos.setModel(modeloMedicamentos);
    }

    public void agregarMedicamentos(javax.swing.JButton btnAgregar) {
        String nombreMedicamento = medicamentosFrm.txtNombreMedicamento.getText();
        int stock = Integer.parseInt(medicamentosFrm.txtStock.getText());
        String estado = medicamentosFrm.cboEstado.getSelectedItem().toString();
        Object[] ob = new Object[3];
        ob[0] = nombreMedicamento;
        ob[1] = stock;
        ob[2] = estado;
        objmdDAO.add(ob);
        nuevoMedicamento();
        limpiarTabla(modeloMedicamentos);
        listarMedicamentos(medicamentosFrm.tblMedicamentos);
        JOptionPane.showMessageDialog(medicamentosFrm, "Medicamento correctamente agregado");
    }

    public void actualizarMedicamentos(javax.swing.JButton btnActualizar) {
        int idMedicamento = Integer.parseInt(medicamentosFrm.txtID.getText());
        String nombreMedicamento = medicamentosFrm.txtNombreMedicamento.getText();
        int stock = Integer.parseInt(medicamentosFrm.txtStock.getText());
        String estado = medicamentosFrm.cboEstado.getSelectedItem().toString();
        Object[] ob2 = new Object[4];
        ob2[0] = nombreMedicamento;
        ob2[1] = stock;
        ob2[2] = estado;
        ob2[3] = idMedicamento;
        objmdDAO.actualizar(ob2);
        nuevoMedicamento();
        limpiarTabla(modeloMedicamentos);
        listarMedicamentos(medicamentosFrm.tblMedicamentos);
        JOptionPane.showMessageDialog(medicamentosFrm, "Medicamento correctamente actualizado");

    }

    public void eliminarMedicamentos(int fila) {

        try {
            if (fila == -1) {
                JOptionPane.showMessageDialog(medicamentosFrm, "Debe seleccionar una Fila");
            } else {
                int idEliminar = Integer.parseInt(medicamentosFrm.txtID.getText());
                objmdDAO.eliminar(idEliminar);
                JOptionPane.showMessageDialog(medicamentosFrm, "Correctamente eliminado");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(medicamentosFrm, "La fila seleccionadaes " + fila + " El error es: " + e);
        }

    }

    void nuevoMedicamento() {
        medicamentosFrm.txtID.setText("");
        medicamentosFrm.txtNombreMedicamento.setText("");
        medicamentosFrm.txtStock.setText("");
        medicamentosFrm.cboEstado.setSelectedIndex(0);
        medicamentosFrm.txtNombreMedicamento.requestFocus();
    }

    void limpiarTabla(DefaultTableModel modelo) {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    public void validarUsuario(javax.swing.JButton btnRegistro) {
        String pass = loginFrm.txtpass.getText();
        String nickuser = loginFrm.txtuser.getText();
        String rol = loginFrm.btcubo.getSelectedItem().toString();

        if (loginFrm.txtuser.getText().equals("") || loginFrm.txtpass.getText().equals("") || loginFrm.btcubo.getSelectedItem().toString().equals("SELECCIONAR")) {
            JOptionPane.showMessageDialog(loginFrm, "Por favor verifique que todos los datos se encuentren ingresados");
        } else {
            user = udao.ValidarUsuario(nickuser, pass, rol);
            if (user.getNickUsuario() != null && user.getPassUsuario() != null && user.getTipoUsuario() != null) {
                principalFrm.setVisible(true);
                Controlador vprin = new Controlador(principalFrm);
                principalFrm.setExtendedState(MAXIMIZED_BOTH);
                loginFrm.dispose();
            } else {
                JOptionPane.showMessageDialog(loginFrm, "Ingrese datos válidos por favor ");
                loginFrm.txtuser.requestFocus();
            }
        }

    }

    void CentrarVentana(JInternalFrame frame) {
        principalFrm.panelPrincipal.add(frame);
        Dimension dimension = principalFrm.panelPrincipal.getSize();
        Dimension Dframe = frame.getSize();
        frame.setLocation((dimension.width - Dframe.width) / 2, (dimension.height - Dframe.height) / 2);
        frame.show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginFrm.btniniciar) {
            validarUsuario(loginFrm.btniniciar);
        }
        if (e.getSource() == principalFrm.BtnAgregarMedicamento) {
            Controlador objmodulomed = new Controlador(medicamentosFrm);
            objmodulomed.limpiarTabla(modeloMedicamentos);
            CentrarVentana(medicamentosFrm);
            medicamentosFrm.txtID.disable();

        }
        if (e.getSource() == medicamentosFrm.btnAgregar) {
            agregarMedicamentos(medicamentosFrm.btnAgregar);
        }
        if (e.getSource() == medicamentosFrm.btnActualizar) {
            int fila = medicamentosFrm.tblMedicamentos.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(medicamentosFrm, "Debe seleccionar un medicamento");
            } else {
                medicamentosFrm.txtID.setEnabled(false);
                actualizarMedicamentos(medicamentosFrm.btnActualizar);

            }
        }
        if (e.getSource() == medicamentosFrm.btnEliminar) {
            int fila = medicamentosFrm.tblMedicamentos.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(medicamentosFrm, "Debe seleccionar un medicamento");
            } else {

                eliminarMedicamentos(fila);
                nuevoMedicamento();
                limpiarTabla(modeloMedicamentos);
                listarMedicamentos(medicamentosFrm.tblMedicamentos);

            }
        }
        if (e.getSource() == medicamentosFrm.btnNuevo) {
            nuevoMedicamento();
        }

    }

}
