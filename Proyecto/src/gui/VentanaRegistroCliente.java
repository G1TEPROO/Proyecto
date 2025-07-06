package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import arrays.ArregloClienteBD;
import clases.Cliente;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class VentanaRegistroCliente extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombreR;
	private JTextField txtDniR;
	private JTextField txtTelefonoR;
	private JLabel lblNewLabel;
	private JLabel lblDni;
	private JLabel lblTelefono;
	private JButton btnRegistrarC;
	private JLabel lblNewLabel_1;
	private VentanaBoleta ventanaBoleta;
	private boolean registradoConExito = false;

	/**
	 * Create the frame.
	 */
	public VentanaRegistroCliente(VentanaBoleta ventanaBoleta, int dniInicial) {
	    this.ventanaBoleta = ventanaBoleta;
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 510, 269);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			txtNombreR = new JTextField();
			txtNombreR.setBounds(69, 134, 86, 20);
			contentPane.add(txtNombreR);
			txtNombreR.setColumns(10);
		}
		{
			txtDniR = new JTextField();
			txtDniR.setColumns(10);
			txtDniR.setBounds(220, 134, 86, 20);
			contentPane.add(txtDniR);
		}
		{
			txtTelefonoR = new JTextField();
			txtTelefonoR.setColumns(10);
			txtTelefonoR.setBounds(386, 134, 86, 20);
			contentPane.add(txtTelefonoR);
		}
		{
			lblNewLabel = new JLabel("Nombre:");
			lblNewLabel.setBounds(13, 137, 46, 14);
			contentPane.add(lblNewLabel);
		}
		{
			lblDni = new JLabel("DNI:");
			lblDni.setBounds(184, 137, 46, 14);
			contentPane.add(lblDni);
		}
		{
			lblTelefono = new JLabel("Telefono:");
			lblTelefono.setBounds(330, 137, 46, 14);
			contentPane.add(lblTelefono);
		}
		{
			btnRegistrarC = new JButton("Registrar Cliente");
			btnRegistrarC.addActionListener(this);
			btnRegistrarC.setBounds(170, 165, 155, 50);
			contentPane.add(btnRegistrarC);
		}
		{
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\sebastian\\Desktop\\newrpoyecto\\Proyecto\\Proyecto\\src\\imagenes\\userlogo.png"));
			lblNewLabel_1.setBounds(170, 11, 136, 110);
			contentPane.add(lblNewLabel_1);
		}
		txtDniR.setText(String.valueOf(dniInicial));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrarC) {
			do_btnRegistrarC_actionPerformed(e);
		}
	}
	protected void do_btnRegistrarC_actionPerformed(ActionEvent e) {
		try {
	        String nombre = txtNombreR.getText().trim();
	        int dni = Integer.parseInt(txtDniR.getText().trim());
	        int telefono = Integer.parseInt(txtTelefonoR.getText().trim());

	        if (nombre.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.");
	            return;
	        }

	        ArregloClienteBD ac = new ArregloClienteBD();
	        Cliente a = new Cliente(nombre, dni, telefono);

	        if (ac.insertar(a)) {
	            JOptionPane.showMessageDialog(this, "Cliente registrado:\nNombre: " + nombre + "\nDNI: " + dni + "\nTeléfono: " + telefono);
	            
	            if (ventanaBoleta != null) {
	                ventanaBoleta.ActualizarClientes();
	            }
	            registradoConExito = true;
	            dispose();
	        } else {
	            JOptionPane.showMessageDialog(this, "Error al registrar el cliente.");
	        }

	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(this, "DNI y Teléfono deben ser números válidos.", "Error de formato", JOptionPane.ERROR_MESSAGE);
	    }
		
	}
	
	public boolean fueRegistrado() {
	    return registradoConExito;
	}
}