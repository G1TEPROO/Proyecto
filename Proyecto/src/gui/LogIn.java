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
import javax.swing.ImageIcon;

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
	private JLabel lblNewLabel_2;

	/**
	 * Create the dialog.
	 */
	public LogIn() {
		setModal(true);
		setBounds(100, 100, 523, 393);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblNewLabel = new JLabel("Usuario:");
			lblNewLabel.setBounds(108, 155, 83, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			lblNewLabel_1 = new JLabel("Contraseña:");
			lblNewLabel_1.setBounds(108, 196, 83, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			txtUsuario = new JTextField();
			txtUsuario.setBounds(181, 152, 161, 20);
			contentPanel.add(txtUsuario);
			txtUsuario.setColumns(10);
		}
		{
			pswContra = new JPasswordField();
			pswContra.setBounds(181, 193, 162, 20);
			contentPanel.add(pswContra);
		}
		{
			btnIngresar = new JButton("ingresar");
			btnIngresar.addActionListener(this);
			btnIngresar.setBounds(219, 224, 95, 32);
			contentPanel.add(btnIngresar);
		}
		{
			lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\sebastian\\Desktop\\newrpoyecto\\Proyecto\\Proyecto\\src\\imagenes\\loginfondo.png"));
			lblNewLabel_2.setBounds(0, 0, 512, 361);
			contentPanel.add(lblNewLabel_2);
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
