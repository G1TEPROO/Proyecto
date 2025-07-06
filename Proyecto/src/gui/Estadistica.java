package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import arrays.ArregloBoletaBD;

import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class Estadistica extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField txtProductoMV;
	private JLabel lblNewLabel_3;
	private JTextField txtProductoCV;
	private JTextField txtClienteG;
	private JTextField txtEmpleadoC;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField txtClienteCG;
	private JTextField txtEmpleadoCC;
	private JScrollPane scrollPane;
	private JTable tS;
	private JLabel lblProductoMenosVendido;
	private JTextField txtProductoLV;
	private JLabel lblNewLabel_6;
	private JTextField txtProductoCLV;
	private JRadioButton rdbtnDiario;
	private JRadioButton rdbtnMes;
	private JButton btnCerrar;
	private JTextField txtTotal;
	private JLabel lblNewLabel_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Estadistica dialog = new Estadistica();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Estadistica() {
		setModal(true);
		setTitle("ESTADISTICAS");
		setBounds(100, 100, 550, 370);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblNewLabel = new JLabel("PRODUCTO MÁS VENDIDO: ");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel.setForeground(new Color(78, 52, 46));
			lblNewLabel.setBackground(new Color(78, 52, 46));
			lblNewLabel.setBounds(10, 14, 162, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			lblNewLabel_1 = new JLabel("CLIENTE QUE MÁS GASTÓ:");
			lblNewLabel_1.setForeground(new Color(78, 52, 46));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(10, 67, 162, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			lblNewLabel_2 = new JLabel("EMPLEADO QUE MAS VENDIO:");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_2.setForeground(new Color(78, 52, 46));
			lblNewLabel_2.setBounds(10, 92, 175, 14);
			contentPanel.add(lblNewLabel_2);
		}
		{
			txtProductoMV = new JTextField();
			txtProductoMV.setForeground(new Color(78, 52, 46));
			txtProductoMV.setEditable(false);
			txtProductoMV.setBounds(182, 11, 100, 20);
			contentPanel.add(txtProductoMV);
			txtProductoMV.setColumns(10);
		}
		{
			lblNewLabel_3 = new JLabel("CANTIDAD VENDIDA:");
			lblNewLabel_3.setForeground(new Color(78, 52, 46));
			lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_3.setBounds(320, 14, 124, 14);
			contentPanel.add(lblNewLabel_3);
		}
		{
			txtProductoCV = new JTextField();
			txtProductoCV.setForeground(new Color(78, 52, 46));
			txtProductoCV.setEditable(false);
			txtProductoCV.setBounds(454, 11, 70, 20);
			contentPanel.add(txtProductoCV);
			txtProductoCV.setColumns(10);
		}
		{
			txtClienteG = new JTextField();
			txtClienteG.setForeground(new Color(78, 52, 46));
			txtClienteG.setEditable(false);
			txtClienteG.setBounds(182, 64, 100, 20);
			contentPanel.add(txtClienteG);
			txtClienteG.setColumns(10);
		}
		{
			txtEmpleadoC = new JTextField();
			txtEmpleadoC.setForeground(new Color(78, 52, 46));
			txtEmpleadoC.setEditable(false);
			txtEmpleadoC.setColumns(10);
			txtEmpleadoC.setBounds(182, 89, 100, 20);
			contentPanel.add(txtEmpleadoC);
		}
		{
			lblNewLabel_4 = new JLabel("CANTIDAD GASTADA:");
			lblNewLabel_4.setForeground(new Color(78, 52, 46));
			lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_4.setBounds(320, 67, 124, 14);
			contentPanel.add(lblNewLabel_4);
		}
		{
			lblNewLabel_5 = new JLabel("BOLETAS GENERADAS:");
			lblNewLabel_5.setForeground(new Color(78, 52, 46));
			lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_5.setBounds(320, 92, 138, 14);
			contentPanel.add(lblNewLabel_5);
		}
		{
			txtClienteCG = new JTextField();
			txtClienteCG.setForeground(new Color(78, 52, 46));
			txtClienteCG.setEditable(false);
			txtClienteCG.setColumns(10);
			txtClienteCG.setBounds(454, 64, 70, 20);
			contentPanel.add(txtClienteCG);
		}
		{
			txtEmpleadoCC = new JTextField();
			txtEmpleadoCC.setForeground(new Color(78, 52, 46));
			txtEmpleadoCC.setEditable(false);
			txtEmpleadoCC.setColumns(10);
			txtEmpleadoCC.setBounds(454, 89, 70, 20);
			contentPanel.add(txtEmpleadoCC);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 127, 332, 193);
			contentPanel.add(scrollPane);
			{
				tS = new JTable();
				tS.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Fecha", "Boletas", "Ingresos"
					}
				));
				tS.getColumnModel().getColumn(0).setPreferredWidth(110);
				tS.getColumnModel().getColumn(2).setPreferredWidth(100);
				tS.setFillsViewportHeight(true);
				scrollPane.setViewportView(tS);
			}
		}
		{
			lblProductoMenosVendido = new JLabel("PRODUCTO MENOS VENDIDO: ");
			lblProductoMenosVendido.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblProductoMenosVendido.setForeground(new Color(78, 52, 46));
			lblProductoMenosVendido.setBounds(10, 39, 175, 14);
			contentPanel.add(lblProductoMenosVendido);
		}
		{
			txtProductoLV = new JTextField();
			txtProductoLV.setForeground(new Color(78, 52, 46));
			txtProductoLV.setEditable(false);
			txtProductoLV.setColumns(10);
			txtProductoLV.setBounds(182, 36, 100, 20);
			contentPanel.add(txtProductoLV);
		}
		{
			lblNewLabel_6 = new JLabel("CANTIDAD VENDIDA:");
			lblNewLabel_6.setForeground(new Color(78, 52, 46));
			lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_6.setBounds(320, 39, 124, 14);
			contentPanel.add(lblNewLabel_6);
		}
		{
			txtProductoCLV = new JTextField();
			txtProductoCLV.setForeground(new Color(78, 52, 46));
			txtProductoCLV.setEditable(false);
			txtProductoCLV.setColumns(10);
			txtProductoCLV.setBounds(454, 36, 70, 20);
			contentPanel.add(txtProductoCLV);
		}
		{
			rdbtnDiario = new JRadioButton("DATOS DEL DIA");
			rdbtnDiario.setForeground(new Color(255, 255, 255));
			rdbtnDiario.setBackground(new Color(169, 116, 88));
			rdbtnDiario.addActionListener(this);
			rdbtnDiario.setSelected(true);
			rdbtnDiario.setBounds(381, 172, 131, 23);
			contentPanel.add(rdbtnDiario);
		}
		{
			rdbtnMes = new JRadioButton("DATOS DEL MES");
			rdbtnMes.setBackground(new Color(169, 116, 88));
			rdbtnMes.setForeground(new Color(255, 255, 255));
			rdbtnMes.addActionListener(this);
			rdbtnMes.setBounds(381, 219, 131, 23);
			contentPanel.add(rdbtnMes);
		}
		
		{
			btnCerrar = new JButton("CERRAR");
			btnCerrar.setBackground(new Color(169, 116, 88));
			btnCerrar.setForeground(new Color(255, 255, 255));
			btnCerrar.addActionListener(this);
			btnCerrar.setBounds(391, 297, 89, 23);
			contentPanel.add(btnCerrar);
		}
		{
			txtTotal = new JTextField();
			txtTotal.setForeground(new Color(78, 52, 46));
			txtTotal.setBackground(new Color(255, 255, 255));
			txtTotal.setEditable(false);
			txtTotal.setBounds(391, 266, 89, 20);
			contentPanel.add(txtTotal);
			txtTotal.setColumns(10);
		}
		{
			lblNewLabel_7 = new JLabel("New label");
			lblNewLabel_7.setIcon(new ImageIcon(Estadistica.class.getResource("/imagenes/hand-drawn-seamless-pattern-of-bread-and-bakery-products-baked-goods-background-illustration-vector.jpg")));
			lblNewLabel_7.setBounds(0, 0, 534, 331);
			contentPanel.add(lblNewLabel_7);
		}
		cargarResumenDiario();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == rdbtnMes) {
			do_rdbtnMes_actionPerformed(e);
		}
		if (e.getSource() == rdbtnDiario) {
			do_rdbtnDiario_actionPerformed(e);
		}
		if (e.getSource() == btnCerrar) {
			do_btnCerrar_actionPerformed(e);
		}
	}
	
	protected void do_rdbtnDiario_actionPerformed(ActionEvent e) {
		rdbtnMes.setSelected(false);
		cargarResumenDiario();
	}
	
	protected void do_rdbtnMes_actionPerformed(ActionEvent e) {
		rdbtnDiario.setSelected(false);
		cargarResumenMensual();
	}
	
	protected void do_btnCerrar_actionPerformed(ActionEvent e) {
		dispose();
	}
	
	private void cargarResumenDiario() {
		if (rdbtnDiario.isSelected()) {
	        DefaultTableModel model = (DefaultTableModel) tS.getModel();
	        model.setRowCount(0);

	        Object[] fila = ArregloBoletaBD.obtenerResumenDiario();
	        if (fila != null && (int) fila[1] > 0) {
	            model.addRow(fila);
	            txtTotal.setText(String.format("Total: %.2f", (Double) fila[2]));
	            txtTotal.setVisible(true);
	        } else {
	            txtTotal.setVisible(false);
	        }
	    }
		Object[] prodMV = ArregloBoletaBD.obtenerProductoMasVendidoDiario();
        if (prodMV != null) {
            txtProductoMV.setText((String) prodMV[0]);
            txtProductoCV.setText(prodMV[1] + "");
        } else {
            txtProductoMV.setText("Ninguno");
            txtProductoCV.setText("0");
        }
        Object[] prodLV = ArregloBoletaBD.obtenerProductoMenosVendidoDiario();
        if (prodLV != null) {
            txtProductoLV.setText((String) prodLV[0]);
            txtProductoCLV.setText(prodLV[1] + "");
        } else {
            txtProductoLV.setText("Ninguno");
            txtProductoCLV.setText("0");
        }
        Object[] clienteG = ArregloBoletaBD.obtenerClienteMasGastoDiario();
        if (clienteG != null) {
            txtClienteG.setText((String) clienteG[0]);
            txtClienteCG.setText(String.format("%.2f", (Double) clienteG[1]));
        } else {
            txtClienteG.setText("Ninguno");
            txtClienteCG.setText("0.00");
        }
        Object[] empleadoTop = ArregloBoletaBD.obtenerMejorEmpleadoDiario();
        if (empleadoTop != null) {
            txtEmpleadoC.setText((String) empleadoTop[0]);
            txtEmpleadoCC.setText(empleadoTop[1] + "");
        } else {
            txtEmpleadoC.setText("Ninguno");
            txtEmpleadoCC.setText("0");
        }
	}
	
	private void cargarResumenMensual() {
		DefaultTableModel model = (DefaultTableModel) tS.getModel();
	    model.setRowCount(0);

	    ArrayList<Object[]> resumen = ArregloBoletaBD.obtenerResumenMensual();
	    double totalIngresos = 0;

	    if (!resumen.isEmpty()) {
	        for (Object[] fila : resumen) {
	            model.addRow(fila);
	            totalIngresos += (Double) fila[2];
	        }
	        txtTotal.setText(String.format("Total: %.2f", totalIngresos));
	        txtTotal.setVisible(true);
	    } else {
	        txtTotal.setVisible(false);
	    }
	    Object[] prodMV = ArregloBoletaBD.obtenerProductoMasVendidoMensual();
	    if (prodMV != null) {
	        txtProductoMV.setText((String) prodMV[0]);
	        txtProductoCV.setText(prodMV[1] + "");
	    } else {
	        txtProductoMV.setText("Ninguno");
	        txtProductoCV.setText("0");
	    }
	    Object[] prodLV = ArregloBoletaBD.obtenerProductoMenosVendidoMensual();
	    if (prodLV != null) {
	        txtProductoLV.setText((String) prodLV[0]);
	        txtProductoCLV.setText(prodLV[1] + "");
	    } else {
	        txtProductoLV.setText("Ninguno");
	        txtProductoCLV.setText("0");
	    }
	    Object[] clienteG = ArregloBoletaBD.obtenerClienteMasGastoMensual();
	    if (clienteG != null) {
	        txtClienteG.setText((String) clienteG[0]);
	        txtClienteCG.setText(String.format("%.2f", (Double) clienteG[1]));
	    } else {
	        txtClienteG.setText("Ninguno");
	        txtClienteCG.setText("0.00");
	    }
	    Object[] empleadoTop = ArregloBoletaBD.obtenerMejorEmpleadoMensual();
	    if (empleadoTop != null) {
	        txtEmpleadoC.setText((String) empleadoTop[0]);
	        txtEmpleadoCC.setText(empleadoTop[1] + "");
	    } else {
	        txtEmpleadoC.setText("Ninguno");
	        txtEmpleadoCC.setText("0");
	    }
	}
}
