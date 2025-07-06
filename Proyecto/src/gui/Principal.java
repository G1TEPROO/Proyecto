package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import arrays.ArregloBoleta;
import arrays.ArregloProducto;
import clases.Producto;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Principal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JButton btnBoleta;
	private JButton btnAdministrarStock;
	private JButton btnEmpleados;
	private JButton btnListarBoletas;
	private ArregloProducto ap = new ArregloProducto();
	private ArrayList<ArregloBoleta> lb = new ArrayList<>();
	private static String codigoEmpleadoLog;
	private JButton btnCambiarContra;
	private JLabel lblNewLabel_1;
	private JButton btnEstadisticas;
	private JButton btnClientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			LogIn logIn = new LogIn();
			logIn.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			logIn.setVisible(true);

			if (!logIn.isLogueado()) {
				System.exit(0);
			} else {
				codigoEmpleadoLog = logIn.getCodigoEmpleadoLogueado();
				Principal frame = new Principal(codigoEmpleadoLog);
				frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public Principal(String codigoEmpleadoLog) {
		this.codigoEmpleadoLog = codigoEmpleadoLog;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			lblNewLabel = new JLabel("PANADERIA LUCHO'S");
			lblNewLabel.setBounds(201, 50, 274, 59);
			lblNewLabel.setFont(new Font("Britannic Bold", Font.BOLD | Font.ITALIC, 27));
			contentPane.add(lblNewLabel);
		}
		{
			btnBoleta = new JButton("GENERAR BOLETA");
			btnBoleta.setBounds(64, 178, 249, 28);
			btnBoleta.addActionListener(this);
			contentPane.add(btnBoleta);
		}
		{
			btnAdministrarStock = new JButton("ADMINISTRAR PRODUCTOS");
			btnAdministrarStock.setBounds(64, 293, 249, 28);
			btnAdministrarStock.addActionListener(this);
			contentPane.add(btnAdministrarStock);
		}
		
		btnEmpleados = new JButton("ADMINISTRAR EMPLEADOS");
		btnEmpleados.setEnabled(false);
		btnEmpleados.setBounds(358, 217, 249, 28);
		btnEmpleados.addActionListener(this);
		contentPane.add(btnEmpleados);
		{
			btnListarBoletas = new JButton("LISTAR BOLETAS");
			btnListarBoletas.addActionListener(this);
			btnListarBoletas.setBounds(64, 217, 249, 28);
			contentPane.add(btnListarBoletas);
		}
		{
			btnCambiarContra = new JButton("CAMBIAR CONTRASEÑA");
			btnCambiarContra.addActionListener(this);
			btnCambiarContra.setEnabled(false);
			btnCambiarContra.setBounds(358, 256, 249, 28);
			contentPane.add(btnCambiarContra);
		}
		{
			btnEstadisticas = new JButton("ESTADISTICAS");
			btnEstadisticas.setEnabled(false);
			btnEstadisticas.addActionListener(this);
			btnEstadisticas.setBounds(358, 181, 249, 23);
			contentPane.add(btnEstadisticas);
		}
		{
			btnClientes = new JButton("ADMINISTRAR CLIENTES");
			btnClientes.addActionListener(this);
			btnClientes.setBounds(64, 256, 249, 28);
			contentPane.add(btnClientes);
		}
		{
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\sebastian\\Desktop\\newrpoyecto\\Proyecto\\Proyecto\\src\\imagenes\\fondo.png"));
			lblNewLabel_1.setBounds(1, 0, 673, 356);
			contentPane.add(lblNewLabel_1);
		}
		if ("admin".equals(codigoEmpleadoLog)) {
			btnEmpleados.setEnabled(true);
			btnCambiarContra.setEnabled(true);
			btnBoleta.setEnabled(false);
			btnEstadisticas.setEnabled(true);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClientes) {
			do_btnClientes_actionPerformed(e);
		}
		if (e.getSource() == btnEstadisticas) {
			do_btnEstadisticas_actionPerformed(e);
		}
		if (e.getSource() == btnCambiarContra) {
			do_btnCambiarContra_actionPerformed(e);
		}
		if (e.getSource() == btnListarBoletas) {
			do_btnListarBoletas_actionPerformed(e);
		}
		if (e.getSource() == btnEmpleados) {
			do_btnEmpleados_actionPerformed(e);
		}
		if (e.getSource() == btnAdministrarStock) {
			do_btnAdministrarStock_actionPerformed(e);
		}
		if (e.getSource() == btnBoleta) {
			do_btnBoleta_actionPerformed(e);
		}
	}
	protected void do_btnBoleta_actionPerformed(ActionEvent e) {
		VentanaBoleta b = new VentanaBoleta(ap, lb);
		b.setVisible(true);
		
	}
	protected void do_btnAdministrarStock_actionPerformed(ActionEvent e) {
		AdministrarStock as = new AdministrarStock(codigoEmpleadoLog);
		as.setVisible(true);
	}
	protected void do_btnEmpleados_actionPerformed(ActionEvent e) {
		AdministrarEmpleado ae = new AdministrarEmpleado();
		ae.setVisible(true);
	}
	protected void do_btnClientes_actionPerformed(ActionEvent e) {
		AdministrarCliente ac = new AdministrarCliente();
		ac.setVisible(true);
	}
	protected void do_btnListarBoletas_actionPerformed(ActionEvent e) {
		ListarBoletas l = new ListarBoletas();
		l.setVisible(true);
	}
	protected void do_btnCambiarContra_actionPerformed(ActionEvent e) {
		CambiarContra cc = new CambiarContra();
		cc.setVisible(true);
	}
	protected void do_btnEstadisticas_actionPerformed(ActionEvent e) {
		Estadistica est = new Estadistica();
		est.setVisible(true);
	}
	public static String getCodigoEmpleadoLog() {
	    return codigoEmpleadoLog;
	}
}