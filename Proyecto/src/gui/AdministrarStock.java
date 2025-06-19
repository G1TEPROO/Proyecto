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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
			btnModificar.setBounds(380, 76, 108, 23);
			contentPanel.add(btnModificar);
		}
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
