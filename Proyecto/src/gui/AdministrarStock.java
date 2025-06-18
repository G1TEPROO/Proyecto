package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdministrarStock extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtProducto;
	private JTextArea txtS;
	private JButton btnAnadir;
	private JButton btnEliminar;
	private JButton btnCerrar;
	private JButton btnBuscar;
	private JTextField txtPrecio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdministrarStock dialog = new AdministrarStock();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AdministrarStock() {
		setModal(true);
		setBounds(100, 100, 377, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre Producto:");
		lblNewLabel.setBounds(10, 11, 93, 14);
		contentPanel.add(lblNewLabel);
		
		txtProducto = new JTextField();
		txtProducto.setBounds(113, 8, 137, 20);
		contentPanel.add(txtProducto);
		txtProducto.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 239, 189);
		contentPanel.add(scrollPane);
		{
			txtS = new JTextArea();
			scrollPane.setViewportView(txtS);
		}
		{
			btnAnadir = new JButton("AÑADIR");
			btnAnadir.addActionListener(this);
			btnAnadir.setBounds(260, 7, 89, 23);
			contentPanel.add(btnAnadir);
		}
		{
			btnEliminar = new JButton("ELIMINAR");
			btnEliminar.addActionListener(this);
			btnEliminar.setBounds(259, 38, 89, 23);
			contentPanel.add(btnEliminar);
		}
		{
			btnCerrar = new JButton("CERRAR");
			btnCerrar.addActionListener(this);
			btnCerrar.setBounds(259, 227, 89, 23);
			contentPanel.add(btnCerrar);
		}
		{
			btnBuscar = new JButton("BUSCAR");
			btnBuscar.addActionListener(this);
			btnBuscar.setBounds(259, 72, 89, 23);
			contentPanel.add(btnBuscar);
		}
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(113, 33, 137, 20);
		contentPanel.add(txtPrecio);
		
		JLabel lblNewLabel_1 = new JLabel("Precio Unitario:");
		lblNewLabel_1.setBounds(10, 36, 93, 14);
		contentPanel.add(lblNewLabel_1);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCerrar) {
			do_btnCerrar_actionPerformed(e);
		}
		if (e.getSource() == btnBuscar) {
			do_btnBuscar_actionPerformed(e);
		}
		if (e.getSource() == btnEliminar) {
			do_btnEliminar_actionPerformed(e);
		}
		if (e.getSource() == btnAnadir) {
			do_btnAnadir_actionPerformed(e);
		}
	}
	protected void do_btnAnadir_actionPerformed(ActionEvent e) {
	}
	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
	}
	protected void do_btnBuscar_actionPerformed(ActionEvent e) {
	}
	protected void do_btnCerrar_actionPerformed(ActionEvent e) {
		dispose();
	}
}
