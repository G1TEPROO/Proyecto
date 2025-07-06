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
import javax.swing.table.DefaultTableModel;

import arrays.ArregloBoletaBD;
import clases.Boleta;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class ListarBoletas extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JTable tS;
	private JButton btnConsultar;
	private JButton btnListaEmp;
	private JButton btnListaClie;
	private JButton btnConsultar_1;
	private JButton btnRestablecer;
	private JTextField txtTotal;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarBoletas dialog = new ListarBoletas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarBoletas() {
		setBounds(100, 100, 545, 440);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 11, 509, 309);
			contentPanel.add(scrollPane);
			{
				tS = new JTable();
				tS.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Codigo", "Empleado", "Cliente", "Fecha", "Precio"
					}
				));
				tS.getColumnModel().getColumn(0).setPreferredWidth(60);
				tS.getColumnModel().getColumn(1).setPreferredWidth(150);
				tS.getColumnModel().getColumn(2).setPreferredWidth(150);
				tS.getColumnModel().getColumn(3).setPreferredWidth(90);
				tS.setFillsViewportHeight(true);
				scrollPane.setViewportView(tS);
				tS.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				    public void valueChanged(ListSelectionEvent e) {
				        if (!e.getValueIsAdjusting() && tS.getSelectedRow() != -1) {
				            btnConsultar.setEnabled(true);
				        } else {
				            btnConsultar.setEnabled(false);
				        }
				    }
				});
			}
		}
		{
			btnConsultar = new JButton("CONSULTAR");
			btnConsultar.setForeground(new Color(255, 255, 255));
			btnConsultar.setBackground(new Color(169, 116, 88));
			btnConsultar.addActionListener(this);
			btnConsultar.setEnabled(false);
			btnConsultar.setBounds(212, 331, 110, 23);
			contentPanel.add(btnConsultar);
		}
		{
			btnListaEmp = new JButton("LISTA DE EMPLEADO");
			btnListaEmp.setBackground(new Color(169, 116, 88));
			btnListaEmp.setForeground(new Color(255, 255, 255));
			btnListaEmp.addActionListener(this);
			btnListaEmp.setBounds(10, 331, 148, 23);
			contentPanel.add(btnListaEmp);
		}
		{
			btnListaClie = new JButton("LISTA DE CLIENTE");
			btnListaClie.setForeground(new Color(255, 255, 255));
			btnListaClie.setBackground(new Color(169, 116, 88));
			btnListaClie.addActionListener(this);
			btnListaClie.setBounds(10, 365, 148, 23);
			contentPanel.add(btnListaClie);
		}
		{
			btnConsultar_1 = new JButton("CERRAR");
			btnConsultar_1.setForeground(new Color(255, 255, 255));
			btnConsultar_1.setBackground(new Color(169, 116, 88));
			btnConsultar_1.addActionListener(this);
			btnConsultar_1.setBounds(371, 365, 148, 23);
			contentPanel.add(btnConsultar_1);
		}
		{
			btnRestablecer = new JButton("RESTABLECER");
			btnRestablecer.setForeground(new Color(255, 255, 255));
			btnRestablecer.setBackground(new Color(169, 116, 88));
			btnRestablecer.addActionListener(this);
			btnRestablecer.setBounds(371, 331, 148, 23);
			contentPanel.add(btnRestablecer);
		}
		{
			txtTotal = new JTextField();
			txtTotal.setForeground(new Color(255, 255, 255));
			txtTotal.setBackground(new Color(169, 116, 88));
			txtTotal.setEditable(false);
			txtTotal.setBounds(222, 365, 86, 20);
			contentPanel.add(txtTotal);
			txtTotal.setColumns(10);
		}
		{
			lblNewLabel = new JLabel("New label");
			lblNewLabel.setIcon(new ImageIcon(ListarBoletas.class.getResource("/imagenes/hand-drawn-seamless-pattern-of-bread-and-bakery-products-baked-goods-background-illustration-vector.jpg")));
			lblNewLabel.setBounds(0, 0, 529, 401);
			contentPanel.add(lblNewLabel);
		}
		cargarBoletas();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRestablecer) {
			do_btnRestablecer_actionPerformed(e);
		}
		if (e.getSource() == btnConsultar_1) {
			do_btnConsultar_1_actionPerformed(e);
		}
		if (e.getSource() == btnListaClie) {
			do_btnListaClie_actionPerformed(e);
		}
		if (e.getSource() == btnListaEmp) {
			do_btnListaEmp_actionPerformed(e);
		}
		if (e.getSource() == btnConsultar) {
			do_btnConsultar_actionPerformed(e);
		}
	}
	
	protected void do_btnListaEmp_actionPerformed(ActionEvent e) {
		String dni = JOptionPane.showInputDialog(this, "Ingrese el DNI del empleado:");

	    if (dni != null && !dni.trim().isEmpty()) {
	        ArrayList<Object[]> boletasFiltradas = ArregloBoletaBD.listarBoletasPorDniEmpleado(dni.trim());

	        DefaultTableModel model = (DefaultTableModel) tS.getModel();
	        model.setRowCount(0);

	        if (boletasFiltradas.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "No se encontraron boletas para el DNI ingresado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            for (Object[] fila : boletasFiltradas) {
	                model.addRow(fila);
	            }
	        }
	        actualizarTotalBoletas();
	    }
	}
	
	protected void do_btnListaClie_actionPerformed(ActionEvent e) {
		String dniStr = JOptionPane.showInputDialog(this, "Ingrese el DNI del cliente:");

	    if (dniStr != null && !dniStr.trim().isEmpty()) {
	        try {
	            int dni = Integer.parseInt(dniStr.trim());
	            ArrayList<Object[]> lista = ArregloBoletaBD.listarBoletasPorDniCliente(dni);

	            DefaultTableModel model = (DefaultTableModel) tS.getModel();
	            model.setRowCount(0);
	            for (Object[] fila : lista) {
	                model.addRow(fila);
	            }

	            if (lista.isEmpty()) {
	                JOptionPane.showMessageDialog(this, "No se encontraron boletas para ese cliente.");
	            }
	            
	            actualizarTotalBoletas();

	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(this, "DNI inválido. Debe ser un número.");
	        }
	    }
	}
	
	protected void do_btnConsultar_actionPerformed(ActionEvent e) {
		int filaSeleccionada = tS.getSelectedRow();
	    if (filaSeleccionada == -1) {
	        JOptionPane.showMessageDialog(this, "Seleccione una boleta.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    int codigoBoleta = (int) tS.getValueAt(filaSeleccionada, 0);
	    mostrarDetalleBoleta(codigoBoleta);
	}
	
	protected void do_btnRestablecer_actionPerformed(ActionEvent e) {
		cargarBoletas();
	}
	
	protected void do_btnConsultar_1_actionPerformed(ActionEvent e) {
		dispose();
	}
	
	private void mostrarDetalleBoleta(int codigoBoleta) {
	    ArrayList<Boleta> detalles = ArregloBoletaBD.obtenerDetallesBoleta(codigoBoleta);
	    if (detalles == null || detalles.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "No hay detalles para esta boleta.");
	        return;
	    }

	    StringBuilder mensaje = new StringBuilder("Detalles de Boleta " + codigoBoleta + ":\n");
	    for (Boleta b : detalles) {
	        mensaje.append(String.format("- %s: %d unidades, S/ %.2f\n",
	            b.getProducto(), b.getCantidad(), b.getPrecio()));
	    }

	    JOptionPane.showMessageDialog(this, mensaje.toString(), "Detalle de Boleta", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void cargarBoletas() {
	    DefaultTableModel model = (DefaultTableModel) tS.getModel();
	    model.setRowCount(0);
	    ArrayList<Object[]> lista = ArregloBoletaBD.listarBoletasConEmpleado();
	    for (Object[] fila : lista) {
	        model.addRow(fila);
	    }
	    actualizarTotalBoletas();
	}
	
	private void actualizarTotalBoletas() {
	    DefaultTableModel model = (DefaultTableModel) tS.getModel();
	    int totalFilas = model.getRowCount();
	    txtTotal.setText("Total: " + String.valueOf(totalFilas));
	}
}
