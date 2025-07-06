package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

import clases.Empleado;

import javax.swing.table.DefaultTableModel;
import arrays.ArregloEmpleadoBD;
import java.awt.Font;

public class AdministrarEmpleado extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo, txtDNI, txtNombre, txtApellido, txtCargo, txtSueldo;
	private JButton btnBuscar, btnModificar, btnEliminar, btnAgregar;

	private ArregloEmpleadoBD lista = new ArregloEmpleadoBD();
	private JTable ts;
	private DefaultTableModel model;

	private boolean modoModificar = false; // bandera para saber si se está editando
	private JLabel lblNewLabel_6;

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
		lblNewLabel.setForeground(new Color(78, 52, 46));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(27, 24, 46, 14);
		contentPanel.add(lblNewLabel);

		txtCodigo = new JTextField();
		txtCodigo.setForeground(new Color(78, 52, 46));
		txtCodigo.setBounds(85, 21, 101, 20);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("DNI:");
		lblNewLabel_1.setForeground(new Color(78, 52, 46));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(201, 24, 46, 14);
		contentPanel.add(lblNewLabel_1);

		txtDNI = new JTextField();
		txtDNI.setForeground(new Color(78, 52, 46));
		txtDNI.setBounds(257, 21, 101, 20);
		contentPanel.add(txtDNI);
		txtDNI.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setForeground(new Color(78, 52, 46));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(27, 59, 72, 14);
		contentPanel.add(lblNewLabel_2);

		txtNombre = new JTextField();
		txtNombre.setForeground(new Color(78, 52, 46));
		txtNombre.setBounds(85, 56, 101, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Apellido:");
		lblNewLabel_3.setForeground(new Color(78, 52, 46));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(196, 59, 60, 14);
		contentPanel.add(lblNewLabel_3);

		txtApellido = new JTextField();
		txtApellido.setForeground(new Color(78, 52, 46));
		txtApellido.setBounds(257, 56, 101, 20);
		contentPanel.add(txtApellido);
		txtApellido.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Cargo:");
		lblNewLabel_4.setForeground(new Color(78, 52, 46));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(388, 24, 46, 14);
		contentPanel.add(lblNewLabel_4);

		txtCargo = new JTextField();
		txtCargo.setForeground(new Color(78, 52, 46));
		txtCargo.setBounds(434, 21, 101, 20);
		contentPanel.add(txtCargo);
		txtCargo.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Sueldo:");
		lblNewLabel_5.setForeground(new Color(78, 52, 46));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(388, 59, 46, 14);
		contentPanel.add(lblNewLabel_5);

		txtSueldo = new JTextField();
		txtSueldo.setForeground(new Color(78, 52, 46));
		txtSueldo.setBounds(434, 56, 101, 20);
		contentPanel.add(txtSueldo);
		txtSueldo.setColumns(10);

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setForeground(new Color(255, 255, 255));
		btnAgregar.setBackground(new Color(169, 116, 88));
		btnAgregar.setBounds(27, 306, 100, 23);
		btnAgregar.addActionListener(this);
		contentPanel.add(btnAgregar);

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setBackground(new Color(169, 116, 88));
		btnEliminar.setBounds(257, 306, 100, 23);
		btnEliminar.addActionListener(this);
		contentPanel.add(btnEliminar);

		btnModificar = new JButton("MODIFICAR");
		btnModificar.setForeground(new Color(255, 255, 255));
		btnModificar.setBackground(new Color(169, 116, 88));
		btnModificar.setBounds(383, 306, 110, 23);
		btnModificar.addActionListener(this);
		contentPanel.add(btnModificar);

		btnBuscar = new JButton("BUSCAR");
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(169, 116, 88));
		btnBuscar.setBounds(137, 306, 100, 23);
		btnBuscar.addActionListener(this);
		contentPanel.add(btnBuscar);
	
		String[] columnas = { "Código", "Nombre", "Apellido", "DNI", "Cargo", "Sueldo" };
		model = new DefaultTableModel(columnas, 0) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		ts = new JTable(model);

		// Permitir solo selección de filas (opcional)
		ts.setRowSelectionAllowed(true);
		ts.setColumnSelectionAllowed(false);
		ts.setCellSelectionEnabled(false); 

		JScrollPane scrollPane = new JScrollPane(ts);
		scrollPane.setBounds(20, 84, 569, 200);
		contentPanel.add(scrollPane);
		
		lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setIcon(new ImageIcon(AdministrarEmpleado.class.getResource("/imagenes/hand-drawn-seamless-pattern-of-bread-and-bakery-products-baked-goods-background-illustration-vector.jpg")));
		lblNewLabel_6.setBounds(0, 0, 634, 361);
		contentPanel.add(lblNewLabel_6);
		 deshabilitarCampos();
		ts.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        int fila = ts.getSelectedRow();
		        if (fila != -1) {
		            txtCodigo.setText(model.getValueAt(fila, 0).toString());
		            txtNombre.setText(model.getValueAt(fila, 1).toString());
		            txtApellido.setText(model.getValueAt(fila, 2).toString());
		            txtDNI.setText(model.getValueAt(fila, 3).toString());
		            txtCargo.setText(model.getValueAt(fila, 4).toString());
		            txtSueldo.setText(model.getValueAt(fila, 5).toString());
	
		        }
		    }
		});

		// Llamas a tu método que llena la tabla
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
			if (!modoModificar) {
				modoModificar = true;
				txtCodigo.setText("");
				habilitarCampos();
				txtCodigo.setEnabled(false);
				JOptionPane.showMessageDialog(this, "Ahora puedes modificar los campos.");
				
			}else {
				if(lista.Verificar_dni(txtDNI.getText().trim())) {
					
					JOptionPane.showMessageDialog(this, "El dni esta duplicado.");
					return ;
				}
				
				
				txtCodigo.setEnabled(true);
				modoModificar = false;
				
			Empleado emp = new Empleado(
				txtCodigo.getText().trim(),
				txtNombre.getText().trim(),
				txtApellido.getText().trim(),
				txtDNI.getText().trim(),
				txtCargo.getText().trim(),
				Double.parseDouble(txtSueldo.getText().trim()));
			deshabilitarCampos();
			lista.insertar(emp);
			actualizarTabla();
			limpiarCampos();
				
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Datos inválidos.");
		}
		limpiarCampos();
	}

	private void do_btnEliminar_actionPerformed(ActionEvent e) {
		try {
		int codigo = Integer.parseInt( txtCodigo.getText().trim());
		if (lista.eliminar(codigo)) {
			actualizarTabla();
			limpiarCampos();
			deshabilitarCampos();
			JOptionPane.showMessageDialog(this, "Empleado eliminado.");
		} else {
			JOptionPane.showMessageDialog(this, "No se encontró el empleado.");
		}
	}catch (Exception e_2) {
		JOptionPane.showMessageDialog(this, "No se pudo eliminar el empleado");
	}
		limpiarCampos();
	}

	private void do_btnModificar_actionPerformed(ActionEvent e) {

		if (!modoModificar) {
			habilitarCampos();
			btnAgregar.setEnabled(false);
			btnEliminar.setEnabled(false);
			btnBuscar.setEnabled(false);
			modoModificar = true;
			txtCodigo.setEnabled(false);
			JOptionPane.showMessageDialog(this, "Ahora puedes modificar los campos.");
			
		} else {
			btnAgregar.setEnabled(true);
			btnEliminar.setEnabled(true);
			btnBuscar.setEnabled(true);
			txtCodigo.setEnabled(true);
			int codigo = Integer.parseInt( txtCodigo.getText().trim());
			Empleado emp = lista.buscar(codigo);
			if (emp != null) {
				try {
					emp.setNombre(txtNombre.getText().trim());
					emp.setApellido(txtApellido.getText().trim());
					emp.setDni(txtDNI.getText().trim());
					emp.setCargo(txtCargo.getText().trim());
					emp.setSueldo(Double.parseDouble(txtSueldo.getText().trim()));
					lista.editar(emp);
					actualizarTabla();
					JOptionPane.showMessageDialog(this, "Empleado modificado.");
					modoModificar = false;
					deshabilitarCampos();
					limpiarCampos();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, "Sueldo inválido.");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Empleado no encontrado.");
			}
		}
		
		
	}

	private void do_btnBuscar_actionPerformed(ActionEvent e) {
		try {
		int codigo = Integer.parseInt( txtCodigo.getText().trim());
		Empleado emp = lista.buscar(codigo);
		if (emp != null) {
			txtNombre.setText(emp.getNombre());
			txtApellido.setText(emp.getApellido());
			txtDNI.setText(emp.getDni());
			txtCargo.setText(emp.getCargo());
			txtSueldo.setText(String.valueOf(emp.getSueldo()));
			deshabilitarCampos();
			
		} else {
			JOptionPane.showMessageDialog(this, "Empleado no encontrado.");
			limpiarCampos();
		}
		btnAgregar.setEnabled(true);
		}catch (Exception es) {
			JOptionPane.showMessageDialog(this, "No se pudo buscar el empleado , ingrese valores correctos");
			limpiarCampos();
		}
		
	}

	private void actualizarTabla() {
		ArrayList<Empleado> lista_1 = new ArrayList<Empleado>();
		DefaultTableModel modelo = (DefaultTableModel)  ts.getModel();
		model.setRowCount(0);
		ArregloEmpleadoBD Pro =new ArregloEmpleadoBD();
		lista_1= Pro.listar();
		Iterator it=lista_1.iterator();
		while(it.hasNext()) {
			Object obj=it.next();
			Empleado p= (Empleado)obj;
			model.addRow(new Object[] {p.getCodigo(), p.getNombre(), p.getApellido(), p.getDni(),p.getCargo(),p.getSueldo()});
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

	private void deshabilitarCampos() {
		txtNombre.setEnabled(false);
		txtApellido.setEnabled(false);
		txtDNI.setEnabled(false);
		txtCargo.setEnabled(false);
		txtSueldo.setEnabled(false);
	}

	private void habilitarCampos() {
		txtNombre.setEnabled(true);
		txtApellido.setEnabled(true);
		txtDNI.setEnabled(true);
		txtCargo.setEnabled(true);
		txtSueldo.setEnabled(true);
	}
}
 


