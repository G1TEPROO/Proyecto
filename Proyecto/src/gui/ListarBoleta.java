package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ListarBoleta extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tS;
	private JButton btnConsultar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarBoleta dialog = new ListarBoleta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarBoleta() {
		setBounds(100, 100, 450, 338);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 11, 414, 239);
			contentPanel.add(scrollPane);
			{
				DefaultTableModel model = new DefaultTableModel(
						new Object[][] {},
						new String[] {"Código", "DNI Empleado", "Fecha", "Total"}
					) {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};

					tS = new JTable(model);
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
			btnConsultar = new JButton("Consultar");
			btnConsultar.setEnabled(false);
			btnConsultar.addActionListener(this);
			btnConsultar.setBounds(169, 265, 89, 23);
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
