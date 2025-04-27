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
		setBounds(100, 100, 285, 278);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			lblNewLabel = new JLabel("PANADERIA LUCHO'S");
			lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.BOLD, 22));
			lblNewLabel.setBounds(10, 32, 249, 59);
			contentPane.add(lblNewLabel);
		}
		{
			btnBoleta = new JButton("GENERAR BOLETA");
			btnBoleta.addActionListener(this);
			btnBoleta.setBounds(10, 110, 249, 28);
			contentPane.add(btnBoleta);
		}
		{
			btnAdministrarStock = new JButton("ADMINISTRAR PRODUCTOS");
			btnAdministrarStock.addActionListener(this);
			btnAdministrarStock.setBounds(10, 149, 249, 28);
			contentPane.add(btnAdministrarStock);
		}
	}
	public void actionPerformed(ActionEvent e) {
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
	}
}
