package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import arrays.ArregloEmpleadoBD;
import arrays.ContrasenaBD;
import clases.Empleado;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogIn extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtUsuario;
	private JPasswordField pswContra;
	private boolean logueado = false;
	private String codigoEmpleadoLog;
	private JButton btnIngresar;

	/**
	 * Create the dialog.
	 */
	public LogIn() {
		setModal(true);
		setBounds(100, 100, 290, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblNewLabel = new JLabel("Usuario:");
			lblNewLabel.setBounds(22, 90, 83, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			lblNewLabel_1 = new JLabel("Contraseña:");
			lblNewLabel_1.setBounds(22, 124, 83, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			txtUsuario = new JTextField();
			txtUsuario.setBounds(99, 87, 149, 20);
			contentPanel.add(txtUsuario);
			txtUsuario.setColumns(10);
		}
		{
			pswContra = new JPasswordField();
			pswContra.setBounds(98, 121, 150, 20);
			contentPanel.add(pswContra);
		}
		{
			btnIngresar = new JButton("ingresar");
			btnIngresar.addActionListener(this);
			btnIngresar.setBounds(84, 157, 89, 23);
			contentPanel.add(btnIngresar);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnIngresar) {
			do_btnIngresar_actionPerformed(e);
		}
	}
	protected void do_btnIngresar_actionPerformed(ActionEvent e) {
		String usuario = txtUsuario.getText();
		String contra = new String(pswContra.getPassword());

		if (usuario.equals("admin") && contra.equals("adminroot")) {
			logueado = true;
			codigoEmpleadoLog = "admin";
			dispose();
			return;
		}

		ArregloEmpleadoBD bd = new ArregloEmpleadoBD();
		for (Empleado emp : bd.listar()) {
			String usuarioEmp = emp.getCodigo() + emp.getDni();
			String claveSistema = ContrasenaBD.obtenerClave();
			if (usuario.equals(usuarioEmp) && contra.equals(claveSistema)) {
				logueado = true;
				codigoEmpleadoLog = emp.getCodigo();
				dispose();
				return;
			}
		}

		JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
	}
	public boolean isLogueado() {
		return logueado;
	}

	public String getCodigoEmpleadoLogueado() {
		return codigoEmpleadoLog;
	}
}
