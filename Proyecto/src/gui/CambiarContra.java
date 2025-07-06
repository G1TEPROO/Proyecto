package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import arrays.ContrasenaBD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class CambiarContra extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnAceptar;
	private JPasswordField pswC;
	private JButton btnCancelar;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CambiarContra dialog = new CambiarContra();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CambiarContra() {
		setTitle("CAMBIAR CONTRASEÑA");
		setModal(true);
		setBounds(100, 100, 381, 160);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("NUEVA CONTRASEÑA:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel.setForeground(new Color(78, 52, 46));
			lblNewLabel.setBounds(29, 44, 126, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			btnAceptar = new JButton("Aceptar");
			btnAceptar.setBackground(new Color(169, 116, 88));
			btnAceptar.setForeground(new Color(255, 255, 255));
			btnAceptar.addActionListener(this);
			btnAceptar.setBounds(66, 69, 89, 23);
			contentPanel.add(btnAceptar);
		}
		{
			pswC = new JPasswordField();
			pswC.setForeground(new Color(78, 52, 46));
			pswC.setBackground(new Color(255, 255, 255));
			pswC.setBounds(165, 41, 154, 17);
			contentPanel.add(pswC);
		}
		{
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBackground(new Color(169, 116, 88));
			btnCancelar.setForeground(new Color(255, 255, 255));
			btnCancelar.setBounds(175, 69, 89, 23);
			contentPanel.add(btnCancelar);
		}
		{
			lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon(CambiarContra.class.getResource("/imagenes/hand-drawn-seamless-pattern-of-bread-and-bakery-products-baked-goods-background-illustration-vector.jpg")));
			lblNewLabel_1.setBounds(0, 0, 365, 121);
			contentPanel.add(lblNewLabel_1);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAceptar) {
			do_btnAceptar_actionPerformed(e);
		}
	}
	protected void do_btnAceptar_actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAceptar) {
            String nuevaClave = new String(pswC.getPassword()).trim();

            if (nuevaClave.isEmpty()) {
                JOptionPane.showMessageDialog(this, "La contraseña no puede estar vacía", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (ContrasenaBD.actualizarClave(nuevaClave)) {
                JOptionPane.showMessageDialog(this, "Contraseña actualizada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar la contraseña", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == btnCancelar) {
            dispose();
        }
	}
}
