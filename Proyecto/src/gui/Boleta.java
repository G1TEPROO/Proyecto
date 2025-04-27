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
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.ArregloProducto;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Boleta extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox cbProducto;
	private JLabel lblNewLabel_1;
	private JSpinner sCantidad;
	private JLabel lblPrecio;
	private JTextField txtPrecio;
	private JButton btnAgregar;
	private JButton btnQuitar;
	private JScrollPane scrollPane;
	private JButton btnBoleta;
	ArregloProducto ap1 = new ArregloProducto();
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JTextArea txtS2;
	private JTextArea txtS3;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextArea txtS1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Boleta dialog = new Boleta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Boleta() {
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
			cbProducto = new JComboBox();
			cbProducto.setModel(new DefaultComboBoxModel(new String[] {"Pan Frances", "Pan Chabata", "Pan Yema"}));
			cbProducto.setBounds(81, 7, 124, 22);
			contentPanel.add(cbProducto);
		}
		{
			lblNewLabel_1 = new JLabel("CANTIDAD");
			lblNewLabel_1.setBounds(229, 11, 81, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			SpinnerNumberModel model = new SpinnerNumberModel(0,0,100,1 );
			
			sCantidad = new JSpinner(model);
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
			txtPrecio.setEditable(false);
			txtPrecio.setEnabled(false);
			txtPrecio.setBounds(405, 8, 86, 20);
			contentPanel.add(txtPrecio);
			txtPrecio.setColumns(10);
		}
		{
			btnAgregar = new JButton("AÑADIR");
			btnAgregar.addActionListener(this);
			btnAgregar.setBounds(402, 53, 89, 23);
			contentPanel.add(btnAgregar);
		}
		{
			btnQuitar = new JButton("ELIMINAR");
			btnQuitar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnQuitar.setBounds(402, 87, 89, 23);
			contentPanel.add(btnQuitar);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 69, 152, 153);
			contentPanel.add(scrollPane);
			{
				txtS1 = new JTextArea();
				scrollPane.setViewportView(txtS1);
			}
		}
		{
			btnBoleta = new JButton("GENERAR");
			btnBoleta.setBounds(402, 199, 89, 23);
			contentPanel.add(btnBoleta);
		}
		{
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(172, 69, 86, 153);
			contentPanel.add(scrollPane_1);
			{
				txtS2 = new JTextArea();
				scrollPane_1.setViewportView(txtS2);
			}
		}
		{
			scrollPane_2 = new JScrollPane();
			scrollPane_2.setBounds(268, 69, 124, 153);
			contentPanel.add(scrollPane_2);
			{
				txtS3 = new JTextArea();
				scrollPane_2.setViewportView(txtS3);
			}
		}
		{
			lblNewLabel_2 = new JLabel("Nombre");
			lblNewLabel_2.setBounds(10, 57, 66, 14);
			contentPanel.add(lblNewLabel_2);
		}
		{
			lblNewLabel_3 = new JLabel("Cantidad");
			lblNewLabel_3.setBounds(172, 57, 66, 14);
			contentPanel.add(lblNewLabel_3);
		}
		{
			lblNewLabel_4 = new JLabel("Precio");
			lblNewLabel_4.setBounds(268, 57, 66, 14);
			contentPanel.add(lblNewLabel_4);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregar) {
			do_btnAgregar_actionPerformed(e);
		}
	}
	protected void do_btnAgregar_actionPerformed(ActionEvent e) {
		
		String nombreProducto = cbProducto.getSelectedItem().toString();
		int cantidadProducto = Integer.parseInt(sCantidad.getValue().toString());
		double precioProducto;
		if (cbProducto.getSelectedIndex() == 0) precioProducto = cantidadProducto * 1;
		else if (cbProducto.getSelectedIndex() == 1) precioProducto = cantidadProducto * 1.5;
		else precioProducto = cantidadProducto * 2;		
		
		try {
			if(cantidadProducto > 0) ap1.Adicionar(nombreProducto, cantidadProducto, precioProducto);
			else JOptionPane.showMessageDialog(null, "CANTIDAD DE PRODUCTOS NO VALIDA");
			Listar(txtS1);
		}
		catch(Exception er) {
			JOptionPane.showMessageDialog(null, er);	
		}
		cbProducto.setSelectedIndex(0);
		sCantidad.setValue(0);
	}
	
	public void Limpieza_S(){
		txtS1.setText("");
		txtS2.setText("");
		txtS3.setText("");
	}
	
	public void Listar ( JTextArea S ) {
		
		Limpieza_S();
		for( int i = 0; i < ap1.Tamaño(); i++) {
			txtS1.append(ap1.obtener(i).getProducto()+"\n");
			txtS2.append(ap1.obtener(i).getCantidad()+"\n");
			txtS3.append(ap1.obtener(i).getPrecio()+"\n");
		}
	}
}
