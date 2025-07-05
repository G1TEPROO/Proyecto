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

public class ListarBoletas extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JTable tS;
	private JButton btnConsultar;

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
		setBounds(100, 100, 545, 410);
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
			btnConsultar.addActionListener(this);
			btnConsultar.setEnabled(false);
			btnConsultar.setBounds(213, 337, 110, 23);
			contentPanel.add(btnConsultar);
		}
		cargarBoletas();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnConsultar) {
			do_btnConsultar_actionPerformed(e);
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
	    ArrayList<Object[]> lista = ArregloBoletaBD.listarBoletasConEmpleado(); // Asume que la consulta también retorna cliente
	    for (Object[] fila : lista) {
	        model.addRow(fila);
	    }
	}
}
