package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.TextArea;

public class AdministrarEmpleado extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JTextField txtCod;
	private JLabel lblNewLabel_1;
	private JTextField txtdni;
	private JLabel lblNewLabel_2;
	private JTextField txtNom;
	private JLabel lblNewLabel_3;
	private JTextField txtApellido;
	private JLabel lblNewLabel_4;
	private JTextField txtCargo;
	private JLabel lblNewLabel_5;
	private JTextField txtSueldo;
	private JButton btnBuscar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnAgregar;
	private JScrollPane scrollPane;
	private TextArea txtS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdministrarEmpleado dialog = new AdministrarEmpleado();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AdministrarEmpleado() {
		setTitle("ADMINISTRAR EMPLEADO");
		setBounds(100, 100, 450, 375);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblNewLabel = new JLabel("Código:");
			lblNewLabel.setBounds(27, 24, 46, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			txtCod = new JTextField();
			txtCod.setBounds(98, 21, 86, 20);
			contentPanel.add(txtCod);
			txtCod.setColumns(10);
		}
		{
			lblNewLabel_1 = new JLabel("DNI:");
			lblNewLabel_1.setBounds(27, 59, 46, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			txtdni = new JTextField();
			txtdni.setBounds(98, 56, 86, 20);
			contentPanel.add(txtdni);
			txtdni.setColumns(10);
		}
		{
			lblNewLabel_2 = new JLabel("Nombre:");
			lblNewLabel_2.setBounds(27, 95, 46, 14);
			contentPanel.add(lblNewLabel_2);
		}
		{
			txtNom = new JTextField();
			txtNom.setBounds(98, 92, 86, 20);
			contentPanel.add(txtNom);
			txtNom.setColumns(10);
		}
		{
			lblNewLabel_3 = new JLabel("Apellido");
			lblNewLabel_3.setBounds(241, 95, 46, 14);
			contentPanel.add(lblNewLabel_3);
		}
		{
			txtApellido = new JTextField();
			txtApellido.setBounds(314, 92, 86, 20);
			contentPanel.add(txtApellido);
			txtApellido.setColumns(10);
		}
		{
			lblNewLabel_4 = new JLabel("Cargo:");
			lblNewLabel_4.setBounds(241, 24, 46, 14);
			contentPanel.add(lblNewLabel_4);
		}
		{
			txtCargo = new JTextField();
			txtCargo.setBounds(314, 21, 86, 20);
			contentPanel.add(txtCargo);
			txtCargo.setColumns(10);
		}
		{
			lblNewLabel_5 = new JLabel("Sueldo:");
			lblNewLabel_5.setBounds(241, 59, 46, 14);
			contentPanel.add(lblNewLabel_5);
		}
		{
			txtSueldo = new JTextField();
			txtSueldo.setBounds(314, 56, 86, 20);
			contentPanel.add(txtSueldo);
			txtSueldo.setColumns(10);
		}
		{
			btnBuscar = new JButton("BUSCAR");
			btnBuscar.setBounds(10, 120, 89, 23);
			contentPanel.add(btnBuscar);
		}
		{
			btnModificar = new JButton("MODIFICAR");
			btnModificar.setBounds(108, 120, 101, 23);
			contentPanel.add(btnModificar);
		}
		{
			btnEliminar = new JButton("ELIMINAR");
			btnEliminar.setBounds(219, 120, 89, 23);
			contentPanel.add(btnEliminar);
		}
		{
			btnAgregar = new JButton("AGREGAR");
			btnAgregar.setBounds(324, 120, 89, 23);
			contentPanel.add(btnAgregar);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(27, 154, 383, 171);
			contentPanel.add(scrollPane);
			{
				txtS = new TextArea();
				scrollPane.setViewportView(txtS);
			}
		}
	}
}
