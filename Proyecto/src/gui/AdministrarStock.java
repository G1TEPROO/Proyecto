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

import arrays.ArregloProducto;
import clases.Producto;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

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
	private boolean modoModificar = false;
	private JButton btnModificar;
	private ArregloProducto ap;
	private String codigoEmpleadoLog;
	private JLabel lblNewLabel_2;
	
	public AdministrarStock(String codigoEmpleadoLog) {
		this.codigoEmpleadoLog = codigoEmpleadoLog;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 514, 360);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre Producto:");
		lblNewLabel.setForeground(new Color(78, 52, 46));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 11, 107, 14);
		contentPanel.add(lblNewLabel);
		
		txtProducto = new JTextField();
		txtProducto.setForeground(new Color(78, 52, 46));
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
					if ( tS.getSelectedRow() != -1) {
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
			btnAnadir.setForeground(new Color(255, 255, 255));
			btnAnadir.setBackground(new Color(169, 116, 88));
			btnAnadir.addActionListener(this);
			btnAnadir.setBounds(379, 32, 108, 23);
			contentPanel.add(btnAnadir);
		}
		{
			btnEliminar = new JButton("ELIMINAR");
			btnEliminar.setForeground(new Color(255, 255, 255));
			btnEliminar.setBackground(new Color(169, 116, 88));
			btnEliminar.addActionListener(this);
			btnEliminar.setBounds(379, 102, 109, 23);
			contentPanel.add(btnEliminar);
		}
		{
			btnCerrar = new JButton("VOLVER");
			btnCerrar.setForeground(new Color(255, 255, 255));
			btnCerrar.setBackground(new Color(169, 116, 88));
			btnCerrar.addActionListener(this);
			btnCerrar.setBounds(380, 287, 108, 23);
			contentPanel.add(btnCerrar);
		}
		{
			btnBuscar = new JButton("BUSCAR");
			btnBuscar.setForeground(new Color(255, 255, 255));
			btnBuscar.setBackground(new Color(169, 116, 88));
			btnBuscar.addActionListener(this);
			btnBuscar.setBounds(380, 216, 108, 23);
			contentPanel.add(btnBuscar);
		}
		
		txtPrecio = new JTextField();
		txtPrecio.setForeground(new Color(78, 52, 46));
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(215, 33, 119, 20);
		contentPanel.add(txtPrecio);
		
		JLabel lblNewLabel_1 = new JLabel("Precio Unitario:");
		lblNewLabel_1.setForeground(new Color(78, 52, 46));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 36, 93, 14);
		contentPanel.add(lblNewLabel_1);
		{
			lblCantidad = new JLabel("Cantidad:");
			lblCantidad.setForeground(new Color(78, 52, 46));
			lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblCantidad.setBounds(10, 61, 107, 14);
			contentPanel.add(lblCantidad);
		}
		{
			txtCantidad = new JTextField();
			txtCantidad.setForeground(new Color(78, 52, 46));
			txtCantidad.setColumns(10);
			txtCantidad.setBounds(215, 58, 119, 20);
			contentPanel.add(txtCantidad);
		}
		{
			btnModificar = new JButton("MODIFICAR");
			btnModificar.setForeground(new Color(255, 255, 255));
			btnModificar.setBackground(new Color(169, 116, 88));
			btnModificar.addActionListener(this);
			btnModificar.setBounds(380, 247, 108, 23);
			contentPanel.add(btnModificar);
		}
		{
			lblNewLabel_2 = new JLabel("New label");
			lblNewLabel_2.setIcon(new ImageIcon(AdministrarStock.class.getResource("/imagenes/hand-drawn-seamless-pattern-of-bread-and-bakery-products-baked-goods-background-illustration-vector.jpg")));
			lblNewLabel_2.setBounds(0, 0, 498, 321);
			contentPanel.add(lblNewLabel_2);
		}
		
		ActualizarTabla();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnModificar) {
			do_btnModificar_actionPerformed(e);
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
		if (e.getSource() == btnCerrar) {
			do_btnCerrar_actionPerformed(e);
		}
	}
	protected void do_btnAnadir_actionPerformed(ActionEvent e) {
		try {
			ArregloProducto M= new ArregloProducto();
			String nombre = txtProducto.getText().trim();
			
			if (M.Verificar_nombre(nombre)) {
				
				JOptionPane.showMessageDialog(this, "El nombre del producto ya existe ");
				return ;
			}
			
			
			double precio = Double.parseDouble(txtPrecio.getText().trim());
			int stock = Integer.parseInt(txtCantidad.getText().trim());
			int codigo = 00;
			Producto pro= new Producto(codigo, nombre, precio, stock);
			
		 M.Agregar(pro);
			
			ActualizarTabla();
			Limpiar();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Datos inválidos");
		}
	}
	protected void do_btnModificar_actionPerformed(ActionEvent e) {
			setVisible(false);
	        Modificar m = new Modificar(); 
	        m.setVisible(true);
	        ActualizarTabla(); // 
	        
	}
	
	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		try {
		int filaSeleccionada = tS.getSelectedRow();
		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(this, "Selecciona una fila para eliminar");
			return;
		}
		DefaultTableModel modelo = (DefaultTableModel) tS.getModel();
		int codigo = (int) modelo.getValueAt(filaSeleccionada, 0); 
		ArregloProducto M= new ArregloProducto();
		M.eliminar(codigo);
	
		
		ActualizarTabla();
		Limpiar();
		JOptionPane.showMessageDialog(this, "Producto eliminado correctamente");
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(this, "No se pudo eliminar el producto");
			
			
		}
		
		
		
		
		
}
	protected void do_btnBuscar_actionPerformed(ActionEvent e) {
	
		String nombre = txtProducto.getText().trim();
		Limpiar();
		if (!modoModificar) {
		  txtCantidad.setEnabled(false);			
		  txtPrecio.setEnabled(false);
		  modoModificar = true;
			JOptionPane.showMessageDialog(this, "Ingrese el nombre del prodcuto a buscar","Aviso",JOptionPane.INFORMATION_MESSAGE);
		}else {
			modoModificar = false;
			  txtCantidad.setEnabled(true);			
			  txtPrecio.setEnabled(true);
				
				if(nombre == null || nombre.isEmpty()) {
					
					
					JOptionPane.showMessageDialog(this, "Ingrese un valora a buscar", "Aviso", JOptionPane.WARNING_MESSAGE);
					
					
				}else {
					Limpiar();

					ArregloProducto Pro=new ArregloProducto();
					Producto p= Pro.ConsultarPro(nombre);
					if(p==null) {
						
						JOptionPane.showMessageDialog(this, "Producto no encontrado", "Aviso", JOptionPane.WARNING_MESSAGE);
						
					return;
					}else {
						
						JOptionPane.showMessageDialog(this, "El prodcuto existe \n Codigo :"+p.getCodigoProducto()+"\n Nombre :"+p.getProducto()+"\n Precio : "+p.getPrecio()+"\n Stock : "+p.getStock()
						,"Aviso",JOptionPane.INFORMATION_MESSAGE);
						
					}
					
				}
				
	
		}
	
		
	}
		
		
		
		
	
			
			
			
			
		
		
		
	

	
	
	private void ActualizarTabla() {
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
	public  void Limpiar() {
		txtProducto.setText("");
		txtPrecio.setText("");
		txtCantidad.setText("");
		tS.clearSelection();
	}
}
