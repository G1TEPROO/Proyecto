package gui;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;
import clases.Empleado;
import clases.ArregloEmpleado;
import java.awt.TextArea;

public class AdministrarEmpleado extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtDNI;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCargo;
	private JTextField txtSueldo;
	private JButton btnBuscar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnAgregar;
	private TextArea txtS;

	private ArregloEmpleado lista = new ArregloEmpleado();

	public static void main(String[] args) {
		try {
			AdministrarEmpleado dialog = new AdministrarEmpleado();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AdministrarEmpleado() {
		setTitle("ADMINISTRAR EMPLEADO");
		setBounds(100, 100, 476, 375);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("Código:");
		lblNewLabel.setBounds(27, 24, 46, 14);
		contentPanel.add(lblNewLabel);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(126, 21, 101, 20);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("DNI:");
		lblNewLabel_1.setBounds(241, 24, 46, 14);
		contentPanel.add(lblNewLabel_1);

		txtDNI = new JTextField();
		txtDNI.setBounds(328, 21, 101, 20);
		contentPanel.add(txtDNI);
		txtDNI.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setBounds(27, 59, 72, 14);
		contentPanel.add(lblNewLabel_2);

		txtNombre = new JTextField();
		txtNombre.setBounds(126, 56, 101, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Apellido:");
		lblNewLabel_3.setBounds(241, 59, 60, 14);
		contentPanel.add(lblNewLabel_3);

		txtApellido = new JTextField();
		txtApellido.setBounds(328, 56, 101, 20);
		contentPanel.add(txtApellido);
		txtApellido.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Cargo:");
		lblNewLabel_4.setBounds(27, 95, 46, 14);
		contentPanel.add(lblNewLabel_4);

		txtCargo = new JTextField();
		txtCargo.setBounds(126, 92, 101, 20);
		contentPanel.add(txtCargo);
		txtCargo.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Sueldo:");
		lblNewLabel_5.setBounds(241, 95, 46, 14);
		contentPanel.add(lblNewLabel_5);

		txtSueldo = new JTextField();
		txtSueldo.setBounds(328, 92, 101, 20);
		contentPanel.add(txtSueldo);
		txtSueldo.setColumns(10);

		btnBuscar = new JButton("BUSCAR");
		btnBuscar.setBounds(340, 120, 89, 23);
		btnBuscar.addActionListener(this);
		contentPanel.add(btnBuscar);

		btnModificar = new JButton("MODIFICAR");
		btnModificar.setBounds(229, 120, 101, 23);
		btnModificar.addActionListener(this);
		contentPanel.add(btnModificar);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setBounds(126, 120, 89, 23);
		btnEliminar.addActionListener(this);
		contentPanel.add(btnEliminar);

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setBounds(27, 120, 89, 23);
		btnAgregar.addActionListener(this);
		contentPanel.add(btnAgregar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 154, 402, 171);
		contentPanel.add(scrollPane);

		txtS = new TextArea();
		scrollPane.setViewportView(txtS);
		
		lista.agregar(new Empleado("E001", "Renzo", "Alvarez", "12345678", "Jefe", 3200.0));
	    lista.agregar(new Empleado("E002", "Ariana", "Perez", "87654321", "Asistente", 2500.0));
	    lista.agregar(new Empleado("E003", "Diego", "Canevaro", "11112222", "Analista", 3000.0));
	    txtS.setText("");
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBuscar) {
			do_btnBuscar_actionPerformed(e);
		} else if (e.getSource() == btnAgregar) {
			do_btnAgregar_actionPerformed(e);
		} else if (e.getSource() == btnModificar) {
			do_btnModificar_actionPerformed(e);
		} else if (e.getSource() == btnEliminar) {
			do_btnEliminar_actionPerformed(e);
		}
	}

	protected void do_btnBuscar_actionPerformed(ActionEvent e) {
		String codigo = txtCodigo.getText().trim();
		Empleado emp = lista.buscar(codigo);

		if (emp != null) {
			txtNombre.setText(emp.getNombre());
			txtApellido.setText(emp.getApellido());
			txtDNI.setText(emp.getDni());
			txtCargo.setText(emp.getCargo());
			txtSueldo.setText(String.valueOf(emp.getSueldo()));
			txtS.setText("Empleado encontrado:\n" + emp.toString());
		} else {
			txtS.setText("Empleado no encontrado.");
		}
	}

	protected void do_btnAgregar_actionPerformed(ActionEvent e) {
		String codigo = txtCodigo.getText().trim();
		String nombre = txtNombre.getText().trim();
		String apellido = txtApellido.getText().trim();
		String dni = txtDNI.getText().trim();
		String cargo = txtCargo.getText().trim();
		double sueldo;

		try {
			sueldo = Double.parseDouble(txtSueldo.getText().trim());
		} catch (NumberFormatException ex) {
			txtS.setText("Error: Sueldo inválido.");
			return;
		}

		Empleado emp = new Empleado(codigo, nombre, apellido, dni, cargo, sueldo);
		lista.agregar(emp);
		txtS.setText("Empleado agregado:\n" + emp.toString());
		limpiarCampos();
	}

	protected void do_btnModificar_actionPerformed(ActionEvent e) {
		String codigo = txtCodigo.getText().trim();
		Empleado emp = lista.buscar(codigo);

		if (emp != null) {
			emp.setNombre(txtNombre.getText().trim());
			emp.setApellido(txtApellido.getText().trim());
			emp.setDni(txtDNI.getText().trim());
			emp.setCargo(txtCargo.getText().trim());

			try {
				emp.setSueldo(Double.parseDouble(txtSueldo.getText().trim()));
				txtS.setText("Empleado modificado:\n" + emp.toString());
			} catch (NumberFormatException ex) {
				txtS.setText("Error: Sueldo inválido.");
			}
		} else {
			txtS.setText("Empleado no encontrado.");
		}
	}

	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		String codigo = txtCodigo.getText().trim();
		boolean eliminado = lista.eliminar(codigo);

		if (eliminado) {
			txtS.setText("Empleado eliminado correctamente.");
			limpiarCampos();
		} else {
			txtS.setText("Empleado no encontrado.");
		}
	}

	private void limpiarCampos() {
		txtCodigo.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtDNI.setText("");
		txtCargo.setText("");
		txtSueldo.setText("");
	}
}
