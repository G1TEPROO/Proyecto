package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import arrays.ArregloBoletaBD;
import clases.Boleta;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListaBoletas extends JDialog{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JTable tS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaBoletas dialog = new ListaBoletas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaBoletas() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 11, 414, 239);
			contentPanel.add(scrollPane);
			{
				tS = new JTable();
				tS.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Codigo", "Empleado", "Fecha", "Precio Total"
					}
				));
				tS.setFillsViewportHeight(true);
				tS.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() == 2) {
							int fila = tS.getSelectedRow();
							int codBoleta = (int) tS.getValueAt(fila, 0);
							mostrarDetalleBoleta(codBoleta);
						}
					}
				});
				scrollPane.setViewportView(tS);
				cargarBoletas();
			}
		}
	
	}
	private void cargarBoletas() {
	    DefaultTableModel model = (DefaultTableModel) tS.getModel();
	    model.setRowCount(0);
	    ArrayList<Object[]> lista = ArregloBoletaBD.listarBoletasConEmpleado();
	    for (Object[] fila : lista) {
	        model.addRow(fila);
	    }
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
}
