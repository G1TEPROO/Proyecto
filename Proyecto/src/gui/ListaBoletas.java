package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import arrays.ArregloBoleta;
import clases.Boleta;

public class ListaBoletas extends JDialog {

	private ArrayList<ArregloBoleta> listaBoletas = new ArrayList<>();
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;

	
	public ListaBoletas(ArrayList<ArregloBoleta>lisBoletas) {
		setBounds(100, 100, 486, 371);
		getContentPane().setLayout(new BorderLayout());
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);

			{
				table = new JTable();
				table.setFillsViewportHeight(true);
				scrollPane.setViewportView(table);
				DefaultTableModel model = new DefaultTableModel();
		        model.addColumn("Código");
		        model.addColumn("Productos");
		        model.addColumn("Total");
		        for (ArregloBoleta b : lisBoletas) {
		            StringBuilder productosTexto = new StringBuilder();
		            for (Boleta item : b.getItems()) {
		                productosTexto.append(String.format("%s (x%d) - %.2f\n",
		                        item.getProducto(), item.getCantidad(), item.getPrecio()));
		            }
		            model.addRow(new Object[] {
		                b.getCodigo(),
		                productosTexto.toString(),
		                String.format("%.2f", b.getTotal())
		            });
		            }
		            table.setModel(model);
		            table.getColumnModel().getColumn(0).setPreferredWidth(80);
		            table.getColumnModel().getColumn(1).setPreferredWidth(300);
		            table.getColumnModel().getColumn(2).setPreferredWidth(80);
		        }
			}
		}	
}
