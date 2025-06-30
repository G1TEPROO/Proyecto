package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.ArregloProducto;
import clases.Producto;

public class AdministrarStock extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtProducto;
	private JButton btnAnadir;
	private JButton btnEliminar;
	private JButton btnCerrar;
	private JButton btnBuscar;
	private JTextField txtPrecio;
	private JTable tS;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private JButton btnModificar;
	private ArregloProducto ap;

	public AdministrarStock(ArregloProducto ap) {
		this.ap = ap;
		setModal(true);
		setBounds(100, 100, 514, 360);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre Producto:");
		lblNewLabel.setBounds(10, 11, 107, 14);
		contentPanel.add(lblNewLabel);
		
		txtProducto = new JTextField();
		txtProducto.setBounds(127, 8, 207, 20);
		contentPanel.add(txtProducto);
		txtProducto.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 99, 359, 211);
		contentPanel.add(scrollPane);
		{
			tS = new JTable();
			tS.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Codigo", "Nombre", "Precio", "Stock"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class, Object.class, Object.class, Object.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			tS.getColumnModel().getColumn(1).setPreferredWidth(195);
			tS.getColumnModel().getColumn(2).setPreferredWidth(90);
			scrollPane.setViewportView(tS);
			
			tS.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tS.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					if (!e.getValueIsAdjusting() && tS.getSelectedRow() != -1) {
						int fila = tS.getSelectedRow();
						DefaultTableModel modelo = (DefaultTableModel) tS.getModel();

						txtProducto.setText(modelo.getValueAt(fila, 1).toString());
						txtPrecio.setText(modelo.getValueAt(fila, 2).toString());
						txtCantidad.setText(modelo.getValueAt(fila, 3).toString());
						btnModificar.setEnabled(true);
		                btnEliminar.setEnabled(true);
		            } else {
		                Limpiar();
		                btnModificar.setEnabled(false);
		                btnEliminar.setEnabled(false);
		            }
				}
			});
			tS.setRowSelectionAllowed(true);
			tS.setColumnSelectionAllowed(false);
			tS.setCellSelectionEnabled(false);
		}
		{
			btnAnadir = new JButton("AÑADIR");
			btnAnadir.addActionListener(this);
			btnAnadir.setBounds(380, 11, 108, 23);
			contentPanel.add(btnAnadir);
		}
		{
			btnEliminar = new JButton("ELIMINAR");
			btnEliminar.addActionListener(this);
			btnEliminar.setBounds(379, 42, 109, 23);
			contentPanel.add(btnEliminar);
		}
		{
			btnCerrar = new JButton("CERRAR");
			btnCerrar.addActionListener(this);
			btnCerrar.setBounds(380, 287, 108, 23);
			contentPanel.add(btnCerrar);
		}
		{
			btnBuscar = new JButton("BUSCAR");
			btnBuscar.addActionListener(this);
			btnBuscar.setBounds(380, 110, 108, 23);
			contentPanel.add(btnBuscar);
		}
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(215, 33, 119, 20);
		contentPanel.add(txtPrecio);
		
		JLabel lblNewLabel_1 = new JLabel("Precio Unitario:");
		lblNewLabel_1.setBounds(10, 36, 93, 14);
		contentPanel.add(lblNewLabel_1);
		{
			lblCantidad = new JLabel("Cantidad:");
			lblCantidad.setBounds(10, 61, 107, 14);
			contentPanel.add(lblCantidad);
		}
		{
			txtCantidad = new JTextField();
			txtCantidad.setColumns(10);
			txtCantidad.setBounds(215, 58, 119, 20);
			contentPanel.add(txtCantidad);
		}
		{
			btnModificar = new JButton("MODIFICAR");
			btnModificar.addActionListener(this);
			btnModificar.setBounds(380, 76, 108, 23);
			contentPanel.add(btnModificar);
		}
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
		ActualizarTabla();
	}
	public void actionPerformed(ActionEvent e) {
		/*if (e.getSource() == btnModificar) {
			do_btnModificar_actionPerformed(e);
		}
		
		if (e.getSource() == btnBuscar) {
			do_btnBuscar_actionPerformed(e);
		}
		if (e.getSource() == btnEliminar) {
			do_btnEliminar_actionPerformed(e);
		}*/
		if (e.getSource() == btnAnadir) {
			do_btnAnadir_actionPerformed(e);
		
		}
		if (e.getSource() == btnCerrar) {
			do_btnCerrar_actionPerformed(e);
		}
	}
	protected void do_btnAnadir_actionPerformed(ActionEvent e) {
		try {
			String nombre = txtProducto.getText().trim();
			double precio = Double.parseDouble(txtPrecio.getText().trim());
			int stock = Integer.parseInt(txtCantidad.getText().trim());
			int codigo = 00;
			Producto pro= new Producto(codigo, nombre, precio, stock);
			ArregloProducto M= new ArregloProducto();
		 M.Agregar(pro);
			
			ActualizarTabla();
			Limpiar();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Datos inválidos");
		}
	}
	/*protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		int filaSeleccionada = tS.getSelectedRow();

		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(this, "Selecciona una fila para eliminar");
			return;
		}

		DefaultTableModel modelo = (DefaultTableModel) tS.getModel();
		int codigo = (int) modelo.getValueAt(filaSeleccionada, 0); 

		if (ap.Eliminar(codigo)) {
			modelo.removeRow(filaSeleccionada);
			JOptionPane.showMessageDialog(this, "Producto eliminado correctamente");
			Limpiar();
		} else {
			JOptionPane.showMessageDialog(this, "No se pudo eliminar el producto");
		}
	}
	protected void do_btnBuscar_actionPerformed(ActionEvent e) {
		String nombre = txtProducto.getText().trim();
		for (int i = 0; i < ap.Tamano(); i++) {
			Producto p = ap.Obtener(i);
			if (p.getProducto().equalsIgnoreCase(nombre)) {
				txtPrecio.setText(String.valueOf(p.getPrecio()));
				txtCantidad.setText(String.valueOf(p.getStock()));

				JOptionPane.showMessageDialog(this, "Producto encontrado:\nPrecio: " + p.getPrecio() + "\nStock: " + p.getStock(), "Éxito", 
					JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
		JOptionPane.showMessageDialog(this, "Producto no encontrado", "Aviso", JOptionPane.WARNING_MESSAGE);
	}
	protected void do_btnModificar_actionPerformed(ActionEvent e) {
		int filaSeleccionada = tS.getSelectedRow();

		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(this, "Selecciona una fila para modificar");
			return;
		}

		try {
			String nuevoNombre = txtProducto.getText().trim();
			double nuevoPrecio = Double.parseDouble(txtPrecio.getText().trim());
			int nuevoStock = Integer.parseInt(txtCantidad.getText().trim());

			DefaultTableModel modelo = (DefaultTableModel) tS.getModel();
			int codigo = (int) modelo.getValueAt(filaSeleccionada, 0);

			Producto p = ap.Buscar(codigo);
			if (p != null) {
				p.setProducto(nuevoNombre);
				p.setPrecio(nuevoPrecio);
				p.setStock(nuevoStock);

				modelo.setValueAt(nuevoNombre, filaSeleccionada, 1);
				modelo.setValueAt(nuevoPrecio, filaSeleccionada, 2);
				modelo.setValueAt(nuevoStock, filaSeleccionada, 3);

				JOptionPane.showMessageDialog(this, "Producto modificado correctamente");
				Limpiar();
			} else {
				JOptionPane.showMessageDialog(this, "No se encontró el producto");
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Datos inválidos. Verifica precio y cantidad");
		}
	}
	
	
	*/private void ActualizarTabla() {
		ArrayList<Producto>lista =new ArrayList<Producto>();
		DefaultTableModel modelo = (DefaultTableModel) tS.getModel();
		modelo.setRowCount(0);
		ArregloProducto Pro=new ArregloProducto();
		lista= Pro.listarPro();
		Iterator it=lista.iterator();
		int i =0;
		while(it.hasNext()) {
			Object obj=it.next();
			Producto p= (Producto)obj;
			modelo.addRow(new Object[] {p.getCodigoProducto(), p.getProducto(), p.getPrecio(), p.getStock()});

			
		}
		}
	protected void do_btnCerrar_actionPerformed(ActionEvent e) {
		dispose();
	}
	private void Limpiar() {
		txtProducto.setText("");
		txtPrecio.setText("");
		txtCantidad.setText("");
		tS.clearSelection();
	}
}
