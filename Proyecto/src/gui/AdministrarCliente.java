package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import arrays.ArregloClienteBD;
import clases.Cliente;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class AdministrarCliente extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tS;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnCerrar;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdministrarCliente dialog = new AdministrarCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AdministrarCliente() {
		setTitle("ADMINISTRAR CLIENTES");
		setModal(true);
		setBounds(100, 100, 571, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 11, 403, 239);
			contentPanel.add(scrollPane);
			{
				tS = new JTable();
				tS.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				    @Override
				    public void valueChanged(ListSelectionEvent e) {
				        if (!e.getValueIsAdjusting()) {
				            btnEliminar.setEnabled(tS.getSelectedRow() != -1);
				            btnModificar.setEnabled(tS.getSelectedRow() != -1);
				        }
				    }
				});
				DefaultTableModel model = new DefaultTableModel(
					    new Object[][] {},
					    new String[] { "Codigo", "Nombre", "DNI", "Telefono" }
					) {
					@Override
					public boolean isCellEditable(int row, int column) {
					    return false;
					}
					};
					tS.setModel(model);
				tS.getColumnModel().getColumn(0).setPreferredWidth(55);
				tS.getColumnModel().getColumn(1).setPreferredWidth(110);
				tS.getColumnModel().getColumn(2).setPreferredWidth(120);
				tS.getColumnModel().getColumn(3).setPreferredWidth(120);
				tS.setFillsViewportHeight(true);
				scrollPane.setViewportView(tS);
			}
		}
		{
			btnAgregar = new JButton("AGREGAR");
			btnAgregar.setForeground(new Color(255, 255, 255));
			btnAgregar.setBackground(new Color(169, 116, 88));
			btnAgregar.addActionListener(this);
			btnAgregar.setBounds(423, 14, 122, 23);
			contentPanel.add(btnAgregar);
		}
		{
			btnModificar = new JButton("MODIFICAR");
			btnModificar.setForeground(new Color(255, 255, 255));
			btnModificar.setBackground(new Color(169, 116, 88));
			btnModificar.setEnabled(false);
			btnModificar.addActionListener(this);
			btnModificar.setBounds(423, 48, 122, 23);
			contentPanel.add(btnModificar);
		}
		{
			btnEliminar = new JButton("ELIMINAR");
			btnEliminar.setForeground(new Color(255, 255, 255));
			btnEliminar.setBackground(new Color(169, 116, 88));
			btnEliminar.setEnabled(false);
			btnEliminar.addActionListener(this);
			btnEliminar.setBounds(423, 82, 122, 23);
			contentPanel.add(btnEliminar);
		}
		{
			btnCerrar = new JButton("CERRAR");
			btnCerrar.setForeground(new Color(255, 255, 255));
			btnCerrar.setBackground(new Color(169, 116, 88));
			btnCerrar.addActionListener(this);
			btnCerrar.setBounds(423, 227, 122, 23);
			contentPanel.add(btnCerrar);
		}
		{
			lblNewLabel = new JLabel("New label");
			lblNewLabel.setIcon(new ImageIcon(AdministrarCliente.class.getResource("/imagenes/hand-drawn-seamless-pattern-of-bread-and-bakery-products-baked-goods-background-illustration-vector.jpg")));
			lblNewLabel.setBounds(0, 0, 545, 261);
			contentPanel.add(lblNewLabel);
		}
		cargarTabla();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCerrar) {
			do_btnCerrar_actionPerformed(e);
		}
		if (e.getSource() == btnEliminar) {
			do_btnEliminar_actionPerformed(e);
		}
		if (e.getSource() == btnModificar) {
			do_btnModificar_actionPerformed(e);
		}
		if (e.getSource() == btnAgregar) {
			do_btnAgregar_actionPerformed(e);
		}
	}
	protected void do_btnAgregar_actionPerformed(ActionEvent e) {
		String nombre = javax.swing.JOptionPane.showInputDialog("Nombre:");
		String dniStr = javax.swing.JOptionPane.showInputDialog("DNI:");
		String telefonoStr = javax.swing.JOptionPane.showInputDialog("Teléfono:");

		if (nombre != null && dniStr != null && telefonoStr != null) {
			try {
				
				int dni = Integer.parseInt(dniStr);
				int telefono = Integer.parseInt(telefonoStr);
				Cliente c = new Cliente(nombre, dni, telefono);
				ArregloClienteBD ac = new ArregloClienteBD();
				 if (ac.Verificar_dni(dni)|| ac.Verificar_telefono(telefonoStr)) {
			        	JOptionPane.showMessageDialog(this, "El dni o telefono no puede ser igual al de un cliente registrado.");
			            return;
			        	}
				 
				 
				
				if (ac.insertar(c)) {
					cargarTabla();
					javax.swing.JOptionPane.showMessageDialog(this, "Cliente agregado correctamente.");
				}
			} catch (NumberFormatException ex) {
				javax.swing.JOptionPane.showMessageDialog(this, "DNI y Teléfono deben ser números.");
			}
		}
	}
	
	protected void do_btnModificar_actionPerformed(ActionEvent e) {
		int fila = tS.getSelectedRow();
	    if (fila == -1) {
	        JOptionPane.showMessageDialog(this, "Selecciona un cliente para modificar.");
	        return;
	    }

	    int codigo = (int) tS.getValueAt(fila, 0);
	    String nombreActual = tS.getValueAt(fila, 1).toString();
	    String dniActual = tS.getValueAt(fila, 2).toString();
	    String telefonoActual = tS.getValueAt(fila, 3).toString();

	    JTextField txtNombre = new JTextField(nombreActual);
	    JTextField txtDni = new JTextField(dniActual);
	    JTextField txtTelefono = new JTextField(telefonoActual);

	    Object[] mensaje = {
	        "Nombre:", txtNombre,
	        "DNI:", txtDni,
	        "Teléfono:", txtTelefono
	    };

	    int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Modificar Cliente", JOptionPane.OK_CANCEL_OPTION);

	    if (opcion == JOptionPane.OK_OPTION) {
	        try {
	            String nombreNuevo = txtNombre.getText().trim();
	            int dniNuevo = Integer.parseInt(txtDni.getText().trim());
	            int telefonoNuevo = Integer.parseInt(txtTelefono.getText().trim());

	            Cliente c = new Cliente(codigo, nombreNuevo, dniNuevo, telefonoNuevo);
	            ArregloClienteBD ac = new ArregloClienteBD();

	            if (ac.editarPorCodigo(c)) {
	                JOptionPane.showMessageDialog(this, "Cliente modificado correctamente.");
	                cargarTabla();
	            } else {
	                JOptionPane.showMessageDialog(this, "Error al modificar cliente.");
	            }

	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(this, "DNI y Teléfono deben ser números válidos.");
	        }
	    }
	}
	
	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		int fila = tS.getSelectedRow();
	    if (fila == -1) {
	        JOptionPane.showMessageDialog(this, "Selecciona un cliente.");
	        return;
	    }

	    int dni = (int) tS.getValueAt(fila, 2);

	    int opcion = JOptionPane.showConfirmDialog(this, 
	        "¿Estás seguro de eliminar al cliente con DNI: " + dni + "?", 
	        "Confirmación", JOptionPane.YES_NO_OPTION);

	    if (opcion == JOptionPane.YES_OPTION) {
	        ArregloClienteBD ac = new ArregloClienteBD();
	        if (ac.eliminar(dni)) {
	            JOptionPane.showMessageDialog(this, "Cliente eliminado exitosamente.");
	            cargarTabla();
	        } else {
	            JOptionPane.showMessageDialog(this, "Error al eliminar cliente.");
	        }
	    }
	}
	
	protected void do_btnCerrar_actionPerformed(ActionEvent e) {
		dispose();
	}
	
	private void cargarTabla() {
		DefaultTableModel model = (DefaultTableModel) tS.getModel();
	    model.setRowCount(0);

	    ArregloClienteBD ac = new ArregloClienteBD();
	    for (Cliente c : ac.listar()) {
	        Object[] fila = {
	            c.getCodigo(),
	            c.getNombre(),
	            c.getDni(),
	            c.getTelefono()
	        };
	        model.addRow(fila);
	    }
	}
}
