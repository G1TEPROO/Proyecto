package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Iterator;

import clases.Producto;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import arrays.ArregloProducto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class Modificar extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txt_n_viejo;
	private JTextField txt_p_antiguo;
	private JTextField txt_stock_antiguo;
	private JTextField txt_nombre;
	private JTextField txt_precio;
	private JTextField txt_stock;
	private JComboBox cbx;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Modificar dialog = new Modificar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Modificar() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Nombre_antiguo");
			lblNewLabel.setForeground(new Color(78, 52, 46));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel.setBounds(10, 58, 98, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nombre nuevo");
			lblNewLabel_1.setForeground(new Color(78, 52, 46));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(214, 58, 94, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			txt_n_viejo = new JTextField();
			txt_n_viejo.setForeground(new Color(78, 52, 46));
			txt_n_viejo.setEnabled(false);
			txt_n_viejo.setEditable(false);
			txt_n_viejo.setBounds(118, 55, 86, 20);
			contentPanel.add(txt_n_viejo);
			txt_n_viejo.setColumns(10);
		}
		{
			JLabel lblPrecioAntiguo = new JLabel("Precio Antiguo");
			lblPrecioAntiguo.setForeground(new Color(78, 52, 46));
			lblPrecioAntiguo.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblPrecioAntiguo.setBounds(10, 90, 98, 14);
			contentPanel.add(lblPrecioAntiguo);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Stock");
			lblNewLabel_1.setBackground(new Color(169, 116, 88));
			lblNewLabel_1.setForeground(new Color(78, 52, 46));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(20, 129, 46, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			txt_p_antiguo = new JTextField();
			txt_p_antiguo.setForeground(new Color(78, 52, 46));
			txt_p_antiguo.setEnabled(false);
			txt_p_antiguo.setEditable(false);
			txt_p_antiguo.setColumns(10);
			txt_p_antiguo.setBounds(118, 86, 86, 20);
			contentPanel.add(txt_p_antiguo);
		}
		{
			txt_stock_antiguo = new JTextField();
			txt_stock_antiguo.setForeground(new Color(78, 52, 46));
			txt_stock_antiguo.setEnabled(false);
			txt_stock_antiguo.setEditable(false);
			txt_stock_antiguo.setColumns(10);
			txt_stock_antiguo.setBounds(118, 126, 86, 20);
			contentPanel.add(txt_stock_antiguo);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Precio");
			lblNewLabel_1.setForeground(new Color(78, 52, 46));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(238, 90, 46, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Stock");
			lblNewLabel_1.setForeground(new Color(78, 52, 46));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(238, 129, 46, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			txt_nombre = new JTextField();
			txt_nombre.setForeground(new Color(78, 52, 46));
			txt_nombre.setColumns(10);
			txt_nombre.setBounds(318, 55, 86, 20);
			contentPanel.add(txt_nombre);
		}
		{
			txt_precio = new JTextField();
			txt_precio.setForeground(new Color(78, 52, 46));
			txt_precio.setColumns(10);
			txt_precio.setBounds(318, 87, 86, 20);
			contentPanel.add(txt_precio);
		}
		{
			txt_stock = new JTextField();
			txt_stock.setForeground(new Color(78, 52, 46));
			txt_stock.setColumns(10);
			txt_stock.setBounds(318, 126, 86, 20);
			contentPanel.add(txt_stock);
		}
		{
			JLabel codigo = new JLabel("codigo");
			codigo.setForeground(new Color(78, 52, 46));
			codigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
			codigo.setBounds(20, 21, 46, 14);
			contentPanel.add(codigo);
		}
		
		cbx = new JComboBox();
		cbx.setForeground(new Color(78, 52, 46));
		cbx.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				  if (e.getStateChange() == ItemEvent.SELECTED) {
			            int cod = Integer.parseInt( cbx.getSelectedItem().toString());
			            llenartexto(cod);
			        }
			}
		});
		cbx.setBounds(89, 17, 100, 22);
		contentPanel.add(cbx);
		{
			JLabel lblNewLabel_2 = new JLabel("New label");
			lblNewLabel_2.setIcon(new ImageIcon(Modificar.class.getResource("/imagenes/hand-drawn-seamless-pattern-of-bread-and-bakery-products-baked-goods-background-illustration-vector.jpg")));
			lblNewLabel_2.setBounds(0, 0, 434, 263);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(new Color(169, 116, 88));
				okButton.setForeground(new Color(255, 255, 255));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
						Producto p;
						ArregloProducto ac= new ArregloProducto();
						int cod = Integer.parseInt( cbx.getSelectedItem().toString());
						String nombre = txt_nombre.getText();
						Double precio= Double.parseDouble(txt_precio.getText().trim());
						int Stock = Integer.parseInt(txt_stock.getText().trim());
						p= new Producto(cod, nombre, precio, Stock);
						ac.editar(p);
						
						System.out.println("Se edito correctamente");
						
						AdministrarStock as= new AdministrarStock(null);
							as.setVisible(true);
						 dispose();
						 setVisible(false);
						}
						catch (NumberFormatException ex) {
						    JOptionPane.showMessageDialog(Modificar.this, "Precio o stock inválido","Aviso" ,JOptionPane.WARNING_MESSAGE);
						} catch (Exception ex) {
						    JOptionPane.showMessageDialog(Modificar.this, "Error al modificar el producto","Aviso" ,JOptionPane.WARNING_MESSAGE);
						    ex.printStackTrace(); // Para depurar en consola
							
							
							
						}
						 
					}
						
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(169, 116, 88));
				cancelButton.setForeground(new Color(255, 255, 255));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AdministrarStock as= new AdministrarStock(null);
						 as.setVisible(true);
						 dispose();
						 setVisible(false);
						 
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				
				
			}
		}
		
		
		llenar_combo();
	}
	public void llenartexto(int cod ) {
		  System.out.println("Código recibido: " + cod); // para depurar
		ArrayList<Producto> lista=new ArrayList<Producto>();
		ArregloProducto acC= new ArregloProducto();
		lista=acC.ConsultarCod(cod);
		Iterator it=lista.iterator();
		if (lista.isEmpty()) {
	        System.out.println("No se encontró producto");
	    }
		while (it.hasNext()) {
			Object obj=it.next();
			Producto ac=(Producto)obj;
			txt_n_viejo.setText(ac.getProducto());
			txt_p_antiguo.setText(ac.getPrecio()+"");
			txt_stock_antiguo.setText(ac.getStock()+"");
			System.out.println("PRODCUO ENCONTRADO");
		}
		
		
	}
	
	
	public void llenar_combo() {
		ArrayList<Producto> lista=new ArrayList<Producto>();
		ArregloProducto ac= new ArregloProducto();
		lista=ac.listarPro();
		Iterator<Producto> it=lista.iterator();
	
		while (it.hasNext()) {
			Object obj=it.next();
			Producto acce=(Producto)obj;
			cbx.addItem(acce.getCodigoProducto());
			
	
	}
	}
}
