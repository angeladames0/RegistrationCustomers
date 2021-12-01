package Interfaces;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;

import Conneccion.DBconexion;

public class Eliminar extends JFrame {

	private JPanel contentPane;
	private JTextField Eusuario;

	private final String user = "root";
	private final String password = "12345678";
	private final String url = "jdbc:mysql://localhost:3306/usuarios";
	private Connection con = null;
	PreparedStatement ps;
	private JTextField Mid;

	public Connection getConection()

	{
		try {

		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException ex) {
			Logger.getLogger(DBconexion.class.getName()).log(Level.SEVERE, null, ex);

		} catch (SQLException ex) {
			Logger.getLogger(DBconexion.class.getName()).log(Level.SEVERE, null, ex);
		}
		return con;

	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Eliminar frame = new Eliminar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Eliminar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 393, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel usuario = new JLabel("Usuario");
		usuario.setBounds(107, 72, 46, 14);
		contentPane.add(usuario);

		Eusuario = new JTextField();
		Eusuario.setBounds(160, 69, 114, 20);
		contentPane.add(Eusuario);
		Eusuario.setColumns(10);

		JButton Ecancelar = new JButton("Cancelar");
		Ecancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Ecancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		Ecancelar.setBounds(41, 141, 124, 36);
		contentPane.add(Ecancelar);

		JButton Eeliminar = new JButton("Eliminar");
		Eeliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Eeliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Connection con = null;

				PreparedStatement ps;


				try {

					con = getConection();
					ps = (PreparedStatement) con.prepareStatement("DELETE FROM usuarios WHERE usuario=?");
					ps.setString(1, Eusuario.getText() );

					int res = ps.executeUpdate();

					if (res > 0) {
						JOptionPane.showMessageDialog(null, "Cliente eliminado");
						limpiar();
						Clientes.TablaAct();


					} else {

						JOptionPane.showMessageDialog(null, "Eroor al eliminar cliente");
					}

					con.close();

				} catch (SQLException ex) {
					System.err.println(ex);
				}


		}

		private void limpiar() {
			Eusuario.setText("");

		}

		});
		Eeliminar.setBounds(233, 141, 124, 36);
		contentPane.add(Eeliminar);

		JLabel lblNewLabel = new JLabel("ELIMINAR CLIENTE");
		lblNewLabel.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 21));
		lblNewLabel.setBounds(91, 0, 198, 36);
		contentPane.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(34, 37, 312, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(34, 114, 312, 2);
		contentPane.add(separator_1);
	}
}
