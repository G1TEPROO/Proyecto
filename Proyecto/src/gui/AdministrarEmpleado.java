package gui;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;
import clases.Empleado;
import clases.ArregloEmpleado;
import javax.swing.table.DefaultTableModel;

public class AdministrarEmpleado extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo, txtDNI, txtNombre, txtApellido, txtCargo, txtSueldo;
	private JButton btnBuscar, btnModificar, btnEliminar, btnAgregar;

	private ArregloEmpleado lista = new ArregloEmpleado();
	private JTable table;
	private DefaultTableModel model;

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
		setBounds(100, 100, 650, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("Código:");
		lblNewLabel.setBounds(27, 24, 46, 14);
		contentPanel.add(lblNewLabel);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(85, 21, 101, 20);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("DNI:");
		lblNewLabel_1.setBounds(196, 24, 46, 14);
		contentPanel.add(lblNewLabel_1);

		txtDNI = new JTextField();
		txtDNI.setBounds(230, 21, 101, 20);
		contentPanel.add(txtDNI);
		txtDNI.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setBounds(27, 59, 72, 14);
		contentPanel.add(lblNewLabel_2);

		txtNombre = new JTextField();
		txtNombre.setBounds(85, 56, 101, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Apellido:");
		lblNewLabel_3.setBounds(196, 59, 60, 14);
		contentPanel.add(lblNewLabel_3);

		txtApellido = new JTextField();
		txtApellido.setBounds(230, 56, 101, 20);
		contentPanel.add(txtApellido);
		txtApellido.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Cargo:");
		lblNewLabel_4.setBounds(350, 24, 46, 14);
		contentPanel.add(lblNewLabel_4);

		txtCargo = new JTextField();
		txtCargo.setBounds(395, 21, 101, 20);
		contentPanel.add(txtCargo);
		txtCargo.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Sueldo:");
		lblNewLabel_5.setBounds(350, 59, 46, 14);
		contentPanel.add(lblNewLabel_5);

		txtSueldo = new JTextField();
		txtSueldo.setBounds(395, 56, 101, 20);
		contentPanel.add(txtSueldo);
		txtSueldo.setColumns(10);

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setBounds(27, 90, 100, 23);
		btnAgregar.addActionListener(this);
		contentPanel.add(btnAgregar);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setBounds(137, 90, 100, 23);
		btnEliminar.addActionListener(this);
		contentPanel.add(btnEliminar);

		btnModificar = new JButton("MODIFICAR");
		btnModificar.setBounds(247, 90, 110, 23);
		btnModificar.addActionListener(this);
		contentPanel.add(btnModificar);

		btnBuscar = new JButton("BUSCAR");
		btnBuscar.setBounds(367, 90, 100, 23);
		btnBuscar.addActionListener(this);
		contentPanel.add(btnBuscar);

		String[] columnas = { "Código", "Nombre", "Apellido", "DNI", "Cargo", "Sueldo" };
		model = new DefaultTableModel(columnas, 0);
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(27, 130, 569, 200);
		contentPanel.add(scrollPane);

		// Datos iniciales
		lista.agregar(new Empleado("E001", "Renzo", "Alvarez", "12345678", "Jefe", 3200.0));
		lista.agregar(new Empleado("E002", "Ariana", "Perez", "87654321", "Asistente", 2500.0));
		lista.agregar(new Empleado("E003", "Diego", "Canevaro", "11112222", "Analista", 3000.0));
		actualizarTabla();
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

	private void do_btnAgregar_actionPerformed(ActionEvent e) {
		try {
			Empleado emp = new Empleado(
				txtCodigo.getText().trim(),
				txtNombre.getText().trim(),
				txtApellido.getText().trim(),
				txtDNI.getText().trim(),
				txtCargo.getText().trim(),
				Double.parseDouble(txtSueldo.getText().trim())
			);
			lista.agregar(emp);
			actualizarTabla();
			limpiarCampos();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Datos inválidos.");
		}
	}

	private void do_btnEliminar_actionPerformed(ActionEvent e) {
		String codigo = txtCodigo.getText().trim();
		if (lista.eliminar(codigo)) {
			actualizarTabla();
			limpiarCampos();
			JOptionPane.showMessageDialog(this, "Empleado eliminado.");
		} else {
			JOptionPane.showMessageDialog(this, "No se encontró el empleado.");
		}
	}

	private void do_btnModificar_actionPerformed(ActionEvent e) {
		String codigo = txtCodigo.getText().trim();
		Empleado emp = lista.buscar(codigo);
		if (emp != null) {
			try {
				emp.setNombre(txtNombre.getText().trim());
				emp.setApellido(txtApellido.getText().trim());
				emp.setDni(txtDNI.getText().trim());
				emp.setCargo(txtCargo.getText().trim());
				emp.setSueldo(Double.parseDouble(txtSueldo.getText().trim()));
				actualizarTabla();
				JOptionPane.showMessageDialog(this, "Empleado modificado.");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Sueldo inválido.");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Empleado no encontrado.");
		}
	}

	private void do_btnBuscar_actionPerformed(ActionEvent e) {
		String codigo = txtCodigo.getText().trim();
		Empleado emp = lista.buscar(codigo);
		if (emp != null) {
			txtNombre.setText(emp.getNombre());
			txtApellido.setText(emp.getApellido());
			txtDNI.setText(emp.getDni());
			txtCargo.setText(emp.getCargo());
			txtSueldo.setText(String.valueOf(emp.getSueldo()));
		} else {
			JOptionPane.showMessageDialog(this, "Empleado no encontrado.");
		}
	}

	private void actualizarTabla() {
		model.setRowCount(0);
		for (int i = 0; i < lista.tamaño(); i++) {
			Empleado emp = lista.obtener(i);
			Object[] fila = {
				emp.getCodigo(),
				emp.getNombre(),
				emp.getApellido(),
				emp.getDni(),
				emp.getCargo(),
				emp.getSueldo()
			};
			model.addRow(fila);
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
