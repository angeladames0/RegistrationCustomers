package Interfaces;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;

import Conneccion.DBconexion;

public class Nuevo extends JFrame {

	private JPanel contentPane;
	private JTextField Nusuario;
	private JTextField Nnombre;
	private JTextField Napellido;
	private JTextField Ntelefono;
	private JTextField Ncorreo;
	private JPasswordField Npassword;


	private final String user = "root";
	private final String password = "12345678";
	private final String url = "jdbc:mysql://localhost:3306/usuarios";
	private Connection con = null;
	PreparedStatement ps;

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
					Nuevo frame = new Nuevo();
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
	public Nuevo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 371, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Nusuario = new JTextField();
		Nusuario.setBounds(112, 44, 124, 20);
		contentPane.add(Nusuario);
		Nusuario.setColumns(10);

		Nnombre = new JTextField();
		Nnombre.setBounds(112, 98, 124, 20);
		contentPane.add(Nnombre);
		Nnombre.setColumns(10);

		Napellido = new JTextField();
		Napellido.setBounds(112, 129, 124, 20);
		contentPane.add(Napellido);
		Napellido.setColumns(10);

		Ntelefono = new JTextField();
		Ntelefono.setBounds(112, 160, 124, 20);
		contentPane.add(Ntelefono);
		Ntelefono.setColumns(10);

		Ncorreo = new JTextField();
		Ncorreo.setBounds(112, 191, 124, 20);
		contentPane.add(Ncorreo);
		Ncorreo.setColumns(10);

		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Connection con = null;

				PreparedStatement ps;
				ResultSet rs;

				String pass = new String(Npassword.getPassword());

				try {

					con = getConection();
					ps = (PreparedStatement) con.prepareStatement("INSERT INTO usuarios (usuario, password, nombre, apellido, telefono, correo) VALUES (?,?,?,?,?,?)");
					ps.setString(1, Nusuario.getText());
					ps.setString(2, pass);
					ps.setString(3, Nnombre.getText());
					ps.setString(4, Napellido.getText());
					ps.setString(5, Ntelefono.getText());
					ps.setString(6, Ncorreo.getText());

					int res = ps.executeUpdate();

					if (res > 0) {

						JOptionPane.showMessageDialog(null, "Cliente Guardado");
						limpiar();
						Clientes.TablaAct();


					} else {

						JOptionPane.showMessageDialog(null, "Error al guardar cliente, Verifique los datos");
					}

					con.close();

				} catch (SQLException ex) {
					System.err.println(ex);
				}
			}

			private void limpiar() {
				Nusuario.setText("");
				Npassword.setText("");
				Nnombre.setText("");
				Napellido.setText("");
				Ntelefono.setText("");
				Ncorreo.setText("");
			}

		});


		btnNewButton.setBounds(205, 251, 124, 38);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(21, 251, 124, 38);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(54, 47, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(54, 101, 46, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Apellido");
		lblNewLabel_2.setBounds(54, 132, 46, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Telefono");
		lblNewLabel_3.setBounds(54, 163, 60, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Correo");
		lblNewLabel_4.setBounds(54, 194, 46, 14);
		contentPane.add(lblNewLabel_4);

		JLabel password = new JLabel("Contrase\u00F1a");
		password.setBounds(40, 76, 74, 14);
		contentPane.add(password);

		JLabel lblNewLabel_5 = new JLabel("REGISTRAR CLIENTE");
		lblNewLabel_5.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 21));
		lblNewLabel_5.setBounds(69, 0, 202, 33);
		contentPane.add(lblNewLabel_5);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 230, 335, 3);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 30, 335, 3);
		contentPane.add(separator_1);

		Npassword = new JPasswordField();
		Npassword.setBounds(112, 71, 124, 20);
		contentPane.add(Npassword);
	}

}
