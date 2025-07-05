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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class VentanaRegistroCliente extends JFrame implements ActionListener {

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistroCliente frame = new VentanaRegistroCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaRegistroCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			txtNombreR = new JTextField();
			txtNombreR.setBounds(66, 30, 86, 20);
			contentPane.add(txtNombreR);
			txtNombreR.setColumns(10);
		}
		{
			txtDniR = new JTextField();
			txtDniR.setColumns(10);
			txtDniR.setBounds(217, 30, 86, 20);
			contentPane.add(txtDniR);
		}
		{
			txtTelefonoR = new JTextField();
			txtTelefonoR.setColumns(10);
			txtTelefonoR.setBounds(383, 30, 86, 20);
			contentPane.add(txtTelefonoR);
		}
		{
			lblNewLabel = new JLabel("Nombre");
			lblNewLabel.setBounds(10, 33, 46, 14);
			contentPane.add(lblNewLabel);
		}
		{
			lblDni = new JLabel("DNI");
			lblDni.setBounds(181, 33, 46, 14);
			contentPane.add(lblDni);
		}
		{
			lblTelefono = new JLabel("telefono");
			lblTelefono.setBounds(327, 33, 46, 14);
			contentPane.add(lblTelefono);
		}
		{
			btnRegistrarC = new JButton("Registrar Cliente");
			btnRegistrarC.addActionListener(this);
			btnRegistrarC.setBounds(269, 96, 155, 50);
			contentPane.add(btnRegistrarC);
		}
		{
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setBounds(46, 71, 128, 110);
			contentPane.add(lblNewLabel_1);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrarC) {
			do_btnRegistrarC_actionPerformed(e);
		}
	}
	protected void do_btnRegistrarC_actionPerformed(ActionEvent e) {

		String nombre = txtNombreR.getText();
		int dni = Integer.parseInt(txtDniR.getText());
		int telefono =Integer.parseInt(txtTelefonoR.getText());
   ArregloClienteBD Erick=new ArregloClienteBD();
   Cliente a=new Cliente(nombre,dni,telefono);
   Erick.insertar(a);
		JOptionPane.showMessageDialog(null, "Cliente registrado:\nNombre: " + nombre + "\nDNI: " + dni + "\nTeléfono: " + telefono);
		
	}
}