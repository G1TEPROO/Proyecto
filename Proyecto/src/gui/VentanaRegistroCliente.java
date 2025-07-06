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
import java.awt.Color;
import java.awt.Font;

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
	private JLabel lblNewLabel_2;

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
			txtNombreR.setForeground(new Color(78, 52, 46));
			txtNombreR.setBounds(67, 134, 86, 20);
			contentPane.add(txtNombreR);
			txtNombreR.setColumns(10);
		}
		{
			txtDniR = new JTextField();
			txtDniR.setForeground(new Color(78, 52, 46));
			txtDniR.setColumns(10);
			txtDniR.setBounds(220, 134, 86, 20);
			contentPane.add(txtDniR);
		}
		{
			txtTelefonoR = new JTextField();
			txtTelefonoR.setForeground(new Color(78, 52, 46));
			txtTelefonoR.setColumns(10);
			txtTelefonoR.setBounds(386, 134, 86, 20);
			contentPane.add(txtTelefonoR);
		}
		{
			lblNewLabel = new JLabel("Nombre:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel.setForeground(new Color(78, 52, 46));
			lblNewLabel.setBounds(13, 137, 65, 14);
			contentPane.add(lblNewLabel);
		}
		{
			lblDni = new JLabel("DNI:");
			lblDni.setForeground(new Color(78, 52, 46));
			lblDni.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblDni.setBounds(184, 137, 46, 14);
			contentPane.add(lblDni);
		}
		{
			lblTelefono = new JLabel("Telefono:");
			lblTelefono.setForeground(new Color(78, 52, 46));
			lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblTelefono.setBounds(323, 136, 53, 14);
			contentPane.add(lblTelefono);
		}
		{
			btnRegistrarC = new JButton("Registrar Cliente");
			btnRegistrarC.setForeground(new Color(255, 255, 255));
			btnRegistrarC.setBackground(new Color(169, 116, 88));
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
		{
			lblNewLabel_2 = new JLabel("New label");
			lblNewLabel_2.setIcon(new ImageIcon(VentanaRegistroCliente.class.getResource("/imagenes/hand-drawn-seamless-pattern-of-bread-and-bakery-products-baked-goods-background-illustration-vector.jpg")));
			lblNewLabel_2.setBounds(0, 0, 494, 230);
			contentPane.add(lblNewLabel_2);
		}
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
	        if (ac.Verificar_dni(dni)|| ac.Verificar_telefono(txtTelefonoR.getText().trim())) {
	        	JOptionPane.showMessageDialog(this, "El dni o telefono no puede ser igual al de un cliente registrado.");
	            return;
	        	}
	       
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