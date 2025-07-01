package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Arrays.ArregloBoleta;
import Arrays.ArregloProducto;
import clases.Boleta;
import clases.Producto;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

import java.util.Iterator;

import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class VentanaBoleta extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox cbProducto;
	private JLabel lblNewLabel_1;
	private JSpinner sCantidad;
	private JLabel lblPrecio;
	private JTextField txtPrecio;
	private JButton btnAgregar;
	private JButton btnGenerar;
	private JButton btnCerrar;
	private JButton btnModificar;
	private JScrollPane scrollPane;
	private JTable tS;
	private JButton btnQuitar;
	private ArregloProducto ap;
	private ArrayList<ArregloBoleta> lb = new ArrayList<>();

	public VentanaBoleta(ArregloProducto ap, ArrayList<ArregloBoleta> lb) {
		this.ap = ap;
		this.lb = lb;
		setTitle("GENERAR BOLETA");
		setModal(true);
		setBounds(100, 100, 522, 272);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("PRODUCTO");
			lblNewLabel.setBounds(10, 11, 81, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			ArregloProducto pro = ap;
			cbProducto = new JComboBox();
			cbProducto.addActionListener(this);
			
			cbProducto.setBounds(81, 7, 138, 22);
			contentPanel.add(cbProducto);
		}
		{
			lblNewLabel_1 = new JLabel("CANTIDAD");
			lblNewLabel_1.setBounds(229, 11, 81, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			SpinnerNumberModel model = new SpinnerNumberModel(1,1,100,1 );
			sCantidad = new JSpinner(model);
			DefaultEditor editor=(DefaultEditor) sCantidad.getEditor();
			JTextField textField=editor.getTextField();
			textField.setEditable(false);
			sCantidad.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
				CalcularPrecio();
				}
			});
			sCantidad.setBounds(293, 8, 36, 20);
			contentPanel.add(sCantidad);
		}
		{
			lblPrecio = new JLabel("PRECIO");
			lblPrecio.setBounds(357, 11, 81, 14);
			contentPanel.add(lblPrecio);
		}
		{
			txtPrecio = new JTextField();
			txtPrecio.setText("0.20");
			txtPrecio.setEditable(false);
			txtPrecio.setEnabled(false);
			txtPrecio.setBounds(405, 8, 86, 20);
			contentPanel.add(txtPrecio);
			txtPrecio.setColumns(10);
		}
		{
			btnAgregar = new JButton("AGREGAR");
			btnAgregar.addActionListener(this);
			btnAgregar.setBounds(387, 53, 104, 23);
			contentPanel.add(btnAgregar);
		}
		{
			btnGenerar = new JButton("GENERAR");
			btnGenerar.addActionListener(this);
			btnGenerar.setBounds(387, 165, 104, 23);
			contentPanel.add(btnGenerar);
		}
		{
			btnCerrar = new JButton("CERRAR");
			btnCerrar.addActionListener(this);
			btnCerrar.setBounds(387, 199, 104, 23);
			contentPanel.add(btnCerrar);
		}
		{
			btnModificar =new JButton("MODIFICAR");
			btnModificar.addActionListener(this);
			btnModificar.setBounds(387, 87, 104, 23);
			contentPanel.add(btnModificar);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 53, 367, 169);
			contentPanel.add(scrollPane);
			{
				tS = new JTable();
				tS.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Producto", "Cantidad", "Precio"
					}
				));
				tS.getColumnModel().getColumn(0).setPreferredWidth(125);
				tS.getColumnModel().getColumn(1).setPreferredWidth(55);
				scrollPane.setViewportView(tS);
			}
		}
		{
			btnQuitar = new JButton("QUITAR");
			btnQuitar.addActionListener(this);
			btnQuitar.setBounds(387, 121, 104, 23);
			contentPanel.add(btnQuitar);
		}
		Actualizar();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCerrar) {
			do_btnCerrar_actionPerformed(e);
		}
		if (e.getSource() == btnGenerar) {
			do_btnGenerar_actionPerformed(e);
		}
		if (e.getSource() == btnQuitar) {
			do_btnQuitar_actionPerformed(e);
		}
		if (e.getSource() == btnModificar) {
			do_btnModificar_actionPerformed(e);
		}
		if (e.getSource() == cbProducto) {
			do_cbProducto_1_actionPerformed(e);
		}
		if (e.getSource() == btnAgregar) {
			do_btnAgregar_actionPerformed(e);
		}
	}
	
	protected void do_cbProducto_1_actionPerformed(ActionEvent e) {
		CalcularPrecio();
	}
	
	protected void do_btnAgregar_actionPerformed(ActionEvent e) {
		
		String nombre = PosicionProducto().getProducto();
		int cantidad = CantidadProducto();
		double precio = Double.parseDouble(txtPrecio.getText());
		boolean repetido = false;
		
		DefaultTableModel modelo = (DefaultTableModel) tS.getModel();
		
		if (modelo.getRowCount() > 0) {
			for (int i = 0; i < modelo.getRowCount(); i++) {
		        String nombreTabla = modelo.getValueAt(i, 0).toString();
		        if (nombreTabla.equals(nombre)) {

		            int cantidadTotal = Integer.parseInt(modelo.getValueAt(i, 1).toString()) + CantidadProducto();
		            double precioTotal = Double.parseDouble(modelo.getValueAt(i, 2).toString()) + Double.parseDouble(txtPrecio.getText());

		            modelo.setValueAt(cantidadTotal, i, 1);
		            modelo.setValueAt(String.format("%.2f", precioTotal), i, 2);
		            repetido = true;
		            break;
		        }
			}
			if (repetido == false) modelo.addRow(new Object[] {nombre, cantidad, precio});
		} else modelo.addRow(new Object[] {nombre, cantidad, precio});
		
		RestaurarPosicion();
	}
	
	protected void do_btnModificar_actionPerformed(ActionEvent e) {
		String producto = cbProducto.getSelectedItem().toString();
	    int nuevaCantidad = CantidadProducto();
	    DefaultTableModel model = (DefaultTableModel) tS.getModel();
	    boolean encontrado = false;

	    for (int i = 0; i < model.getRowCount(); i++) {
	        if (model.getValueAt(i, 0).toString().equals(producto)) {
	            double precioUnitario = PosicionProducto().getPrecio();
	            double nuevoPrecio = nuevaCantidad * precioUnitario;

	            model.setValueAt(nuevaCantidad, i, 1);
	            model.setValueAt(String.format("%.2f", nuevoPrecio), i, 2);

	            encontrado = true;
	            break;
	        }
	    }

	    if (!encontrado) {
	        JOptionPane.showMessageDialog(this, 
	            "El producto no está en la lista", 
	            "Error", 
	            JOptionPane.ERROR_MESSAGE);
	    }

	    RestaurarPosicion();
	}
	
	protected void do_btnQuitar_actionPerformed(ActionEvent e) {
		String producto = cbProducto.getSelectedItem().toString();
		 DefaultTableModel model = (DefaultTableModel) tS.getModel();
		 boolean existe = false;
		 for (int i = 0; i < model.getRowCount(); i++) {
		     if (model.getValueAt(i, 0).toString().equals(producto)) {
		         model.removeRow(i);
		         existe = true;
		         break;
		     }
		 }
		 if (!existe) {
		     JOptionPane.showMessageDialog(this, 
		         "El producto no está en la lista", 
		         "Error", 
		         JOptionPane.ERROR_MESSAGE);
		 }
		 RestaurarPosicion();
	}
	
	protected void do_btnGenerar_actionPerformed(ActionEvent e) {
		DefaultTableModel model = (DefaultTableModel) tS.getModel();
	    StringBuilder boletaTexto = new StringBuilder();
	    ArregloBoleta boleta = new ArregloBoleta();

	    boletaTexto.append("Boleta ").append(boleta.getCodigo()).append("\n");
	    boletaTexto.append("--------------------\n");

	    for (int i = 0; i < model.getRowCount(); i++) {
	        String producto = model.getValueAt(i, 0).toString();
	        int cantidad = Integer.parseInt(model.getValueAt(i, 1).toString());

	        double precioUnitario = 0;
	        for (int j = 0; j < ap.Tamano(); j++) {
	            if (ap.Obtener(j).getProducto().equals(producto)) {
	                precioUnitario = ap.Obtener(j).getPrecio();
	                break;
	            }
	        }

	        double precioTotalItem = precioUnitario * cantidad;

	        Boleta item = new Boleta(producto, cantidad, precioTotalItem);
	        boleta.AgregarItem(item); 
	        boletaTexto.append(String.format("%s - Cantidad: %d - Precio: %.2f\n", producto, cantidad, precioTotalItem));
	    }

	    boletaTexto.append("--------------------\n");
	    boletaTexto.append(String.format("Total: %.2f", boleta.getTotal()));

	    lb.add(boleta);

	    JOptionPane.showMessageDialog(this, boletaTexto.toString(), "Boleta" + boleta.getCodigo(), JOptionPane.INFORMATION_MESSAGE);
	    
	    for (int i = 0; i < model.getRowCount(); i++) {
	    	String nombre = model.getValueAt(i, 0).toString();
	    	int cantidadVendida = Integer.parseInt(model.getValueAt(i, 1).toString());

	    	for (int j = 0; j < ap.Tamano(); j++) {
	    		Producto p = ap.Obtener(j);
	    		if (p.getProducto().equals(nombre)) {
	    			int nuevoStock = p.getStock() - cantidadVendida;
	    			if (nuevoStock < 0) nuevoStock = 0;
	    			p.setStock(nuevoStock);
	    		}
	    	}
	    }
	    
	    model.setRowCount(0);
	    RestaurarPosicion();
	}
	
	protected void do_btnCerrar_actionPerformed(ActionEvent e) {
		dispose();
	}
	
	public Producto PosicionProducto() {
		ArregloProducto pro = ap;
		return pro.Obtener(cbProducto.getSelectedIndex());
	}
	
	public int CantidadProducto() {
		return Integer.parseInt(sCantidad.getValue().toString());
	}
	
	public void CalcularPrecio(int x) {
		ArregloProducto pro = ap;
		int cantidadProducto = Integer.parseInt(sCantidad.getValue().toString());
		double total = cantidadProducto * pro.Obtener(x).getPrecio();
		txtPrecio.setText(String.format("%.2f", total));
	}
	public void CalcularPrecio() {
		int index = cbProducto.getSelectedIndex();
		if (index >= 0 && index <1000) {
			CalcularPrecio(index);
		} else {
			txtPrecio.setText("0.00");
		}
	}
	
	public void RestaurarPosicion() {
		cbProducto.setSelectedIndex(0);
	    sCantidad.setValue(1);
	}
	public void Actualizar() {
		cbProducto.removeAllItems();
		ArrayList<Producto> lista = new ArrayList<Producto>();
		ArregloProducto PRO=new ArregloProducto();
		lista =PRO.listarPro();
		Iterator it=lista.iterator();
		int i =0;
		while(it.hasNext()) {
			Object obj=it.next();
			Producto p= (Producto)obj;
			cbProducto.addItem(p.getProducto());
i++;
			
		}
	}
}
