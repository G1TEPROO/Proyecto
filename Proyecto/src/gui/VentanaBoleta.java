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

import arrays.ArregloBoleta;
import arrays.ArregloBoletaBD;
import arrays.ArregloProducto;
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
			txtPrecio.setText("0.00");
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
			btnModificar.setEnabled(false);
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
				tS.getSelectionModel().addListSelectionListener(e -> {
				    if (!e.getValueIsAdjusting() && tS.getSelectedRow() != -1) {
				        int filaSeleccionada = tS.getSelectedRow();
				        String nombre = tS.getValueAt(filaSeleccionada, 0).toString();
				        int cantidad = Integer.parseInt(tS.getValueAt(filaSeleccionada, 1).toString());

				        cbProducto.setSelectedItem(nombre);
				        sCantidad.setValue(cantidad);
				        btnModificar.setEnabled(true);
				        btnQuitar.setEnabled(true);
				    }
				});
				scrollPane.setViewportView(tS);
			}
		}
		{
			btnQuitar = new JButton("QUITAR");
			btnQuitar.setEnabled(false);
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
		String nombre = (String) cbProducto.getSelectedItem();
	    int cantidad = CantidadProducto();

	    if (nombre == null || cantidad <= 0) {
	        JOptionPane.showMessageDialog(this, "Seleccione un producto válido y una cantidad mayor a 0.");
	        return;
	    }

	    ArregloProducto ap = new ArregloProducto();
	    Producto producto = ap.ConsultarPro(nombre);
	    if (producto == null) {
	        JOptionPane.showMessageDialog(this, "Producto no encontrado.");
	        return;
	    }

	    int stockDisponible = producto.getStock();
	    int cantidadYaAgregada = 0;

	    DefaultTableModel model = (DefaultTableModel) tS.getModel();
	    for (int i = 0; i < model.getRowCount(); i++) {
	        String nombreTabla = model.getValueAt(i, 0).toString();
	        if (nombreTabla.equals(nombre)) {
	            cantidadYaAgregada = Integer.parseInt(model.getValueAt(i, 1).toString());
	            break;
	        }
	    }

	    int cantidadTotalSolicitada = cantidadYaAgregada + cantidad;
	    if (cantidadTotalSolicitada > stockDisponible) {
	        JOptionPane.showMessageDialog(this,
	            "Stock insuficiente para: " + nombre + "\nStock disponible: " + stockDisponible +
	            "\nYa en lista: " + cantidadYaAgregada +
	            "\nIntentando agregar: " + cantidad,
	            "Error de stock",
	            JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    double precioUnitario = producto.getPrecio();
	    double nuevoPrecioTotal = precioUnitario * cantidadTotalSolicitada;
	    boolean encontrado = false;

	    for (int i = 0; i < model.getRowCount(); i++) {
	        String nombreTabla = model.getValueAt(i, 0).toString();
	        if (nombreTabla.equals(nombre)) {
	            model.setValueAt(cantidadTotalSolicitada, i, 1);
	            model.setValueAt(String.format("%.2f", nuevoPrecioTotal), i, 2);
	            encontrado = true;
	            break;
	        }
	    }

	    if (!encontrado) {
	        model.addRow(new Object[] {
	            nombre,
	            cantidad,
	            String.format("%.2f", precioUnitario * cantidad)
	        });
	    }

	    RestaurarPosicion();
	    CalcularPrecio();
	}
	
	protected void do_btnModificar_actionPerformed(ActionEvent e) {
		String nombreSeleccionado = (String) cbProducto.getSelectedItem();
	    int nuevaCantidad = CantidadProducto();

	    if (nombreSeleccionado == null || nuevaCantidad <= 0) {
	        JOptionPane.showMessageDialog(this, "Selecciona un producto y cantidad válida", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    DefaultTableModel model = (DefaultTableModel) tS.getModel();
	    boolean encontrado = false;

	    ArregloProducto arregloProducto = new ArregloProducto();
	    Producto producto = arregloProducto.ConsultarPro(nombreSeleccionado);

	    if (producto == null) {
	        JOptionPane.showMessageDialog(this, "Producto no encontrado en el sistema", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    int stockDisponible = producto.getStock();

	    for (int i = 0; i < model.getRowCount(); i++) {
	        String nombreEnTabla = model.getValueAt(i, 0).toString();

	        if (nombreSeleccionado.equals(nombreEnTabla)) {
	            int cantidadAnterior = Integer.parseInt(model.getValueAt(i, 1).toString());
	            int stockDisponibleReal = stockDisponible + cantidadAnterior;

	            if (nuevaCantidad > stockDisponibleReal) {
	                JOptionPane.showMessageDialog(this,
	                    "No hay suficiente stock para modificar la cantidad.\nStock disponible: " + stockDisponibleReal,
	                    "Error de stock", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            double nuevoPrecioTotal = nuevaCantidad * producto.getPrecio();
	            model.setValueAt(nuevaCantidad, i, 1);
	            model.setValueAt(String.format("%.2f", nuevoPrecioTotal), i, 2);
	            
	            encontrado = true;
	            break;
	        }
	    }

	    if (!encontrado) {
	        JOptionPane.showMessageDialog(this, "El producto no está en la tabla", "Error", JOptionPane.ERROR_MESSAGE);
	    }

	    RestaurarPosicion();
		btnModificar.setEnabled(false);
	}
	
	protected void do_btnQuitar_actionPerformed(ActionEvent e) {
		int filaSeleccionada = tS.getSelectedRow();
	    if (filaSeleccionada != -1) {
	        String nombreProducto = tS.getValueAt(filaSeleccionada, 0).toString();

	        int respuesta = JOptionPane.showConfirmDialog(
	            this,
	            "¿Estás seguro de quitar el producto \"" + nombreProducto + "\" de la boleta?",
	            "Confirmar eliminación",
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE
	        );

	        if (respuesta == JOptionPane.YES_OPTION) {
	            DefaultTableModel model = (DefaultTableModel) tS.getModel();
	            model.removeRow(filaSeleccionada);

	            RestaurarPosicion();
	            btnModificar.setEnabled(false);
	            btnQuitar.setEnabled(false);
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "Selecciona una fila para quitar", "Advertencia", JOptionPane.WARNING_MESSAGE);
	    }
	}
	
	protected void do_btnGenerar_actionPerformed(ActionEvent e) {
		DefaultTableModel model = (DefaultTableModel) tS.getModel();
	    if (model.getRowCount() == 0) {
	        JOptionPane.showMessageDialog(this, "No hay productos para generar la boleta", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    double total = 0;
	    ArregloBoleta arregloBoleta = new ArregloBoleta();
	    ArregloProducto ap = new ArregloProducto();
	    boolean stockSuficiente = true;

	    for (int i = 0; i < model.getRowCount(); i++) {
	        String nombre = model.getValueAt(i, 0).toString();
	        int cantidad = Integer.parseInt(model.getValueAt(i, 1).toString());
	        double precio = Double.parseDouble(model.getValueAt(i, 2).toString());

	        Producto p = ap.ConsultarPro(nombre);
	        if (p != null && p.getStock() >= cantidad) {
	            p.setStock(p.getStock() - cantidad);
	            ap.editar(p);

	            Boleta item = new Boleta(nombre, cantidad, precio);
	            arregloBoleta.AgregarItem(item);
	            total += precio;
	        } else {
	            stockSuficiente = false;
	            JOptionPane.showMessageDialog(this, "Stock insuficiente para: " + nombre);
	            break;
	        }
	    }

	    if (stockSuficiente) {
	        int codEmpleado = Integer.parseInt(Principal.getCodigoEmpleadoLog());
	        int codigoBoleta = ArregloBoletaBD.insertarBoleta(total, codEmpleado);

	        for (Boleta item : arregloBoleta.getItems()) {
	        	ArregloBoletaBD.insertarDetalle(codigoBoleta, item);
	        }

	        lb.add(arregloBoleta);

	        JOptionPane.showMessageDialog(this, "Boleta generada correctamente.\nCódigo: " + codigoBoleta, "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        model.setRowCount(0);
	        RestaurarPosicion();
	    }
	}
	
	protected void do_btnCerrar_actionPerformed(ActionEvent e) {
		dispose();
	}
	
	public void CalcularPrecio() {
		String nombreSeleccionado = (String) cbProducto.getSelectedItem();
	    int cantidad = CantidadProducto();

	    if (nombreSeleccionado != null && cantidad > 0) {
	        ArregloProducto pro = new ArregloProducto();
	        Producto producto = pro.ConsultarPro(nombreSeleccionado);

	        if (producto != null) {
	            double precioTotal = cantidad * producto.getPrecio();
	            txtPrecio.setText(String.format("%.2f", precioTotal));
	        } else {
	            txtPrecio.setText("0.00");
	        }
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
	public int CantidadProducto() {
	    return (int) sCantidad.getValue();
	}
}
