package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JButton btnBoleta;
	private JButton btnAdministrarStock;
	private JButton btnEmpleados;
	private JButton btnListarBoletas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 285, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			lblNewLabel = new JLabel("PANADERIA LUCHO'S");
			lblNewLabel.setBounds(10, 32, 249, 59);
			lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.BOLD, 22));
			contentPane.add(lblNewLabel);
		}
		{
			btnBoleta = new JButton("GENERAR BOLETA");
			btnBoleta.setBounds(10, 110, 249, 28);
			btnBoleta.addActionListener(this);
			contentPane.add(btnBoleta);
		}
		{
			btnAdministrarStock = new JButton("ADMINISTRAR PRODUCTOS");
			btnAdministrarStock.setBounds(10, 149, 249, 28);
			btnAdministrarStock.addActionListener(this);
			contentPane.add(btnAdministrarStock);
		}
		
		btnEmpleados = new JButton("ADMINISTRAR EMPLEADOS");
		btnEmpleados.setBounds(10, 188, 249, 28);
		btnEmpleados.addActionListener(this);
		contentPane.add(btnEmpleados);
		{
			btnListarBoletas = new JButton("LISTAR BOLETAS");
			btnListarBoletas.setBounds(10, 227, 249, 28);
			contentPane.add(btnListarBoletas);
		}
	}
	public void actionPerformed(ActionEvent e) {
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
		Boleta b1 = new Boleta();
		b1.setVisible(true);
	}
	protected void do_btnAdministrarStock_actionPerformed(ActionEvent e) {
		Administrar a1 = new Administrar();
		a1.setVisible(true);
	}
	protected void do_btnEmpleados_actionPerformed(ActionEvent e) {
	}
}
