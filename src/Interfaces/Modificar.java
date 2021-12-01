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
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;

import Conneccion.DBconexion;

public class Modificar extends JFrame {

	private JPanel contentPane;
	private JTextField Musuario;
	private JTextField Mnombre;
	private JTextField Mapellido;
	private JTextField Mtelefono;
	private JTextField Mcorreo;

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
					Modificar frame = new Modificar();
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
	public Modificar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Musuario = new JTextField();
		Musuario.setBounds(135, 49, 133, 20);
		contentPane.add(Musuario);
		Musuario.setColumns(10);

		Mnombre = new JTextField();
		Mnombre.setColumns(10);
		Mnombre.setBounds(135, 73, 133, 20);
		contentPane.add(Mnombre);

		Mapellido = new JTextField();
		Mapellido.setColumns(10);
		Mapellido.setBounds(135, 100, 133, 20);
		contentPane.add(Mapellido);

		Mtelefono = new JTextField();
		Mtelefono.setColumns(10);
		Mtelefono.setBounds(135, 127, 133, 20);
		contentPane.add(Mtelefono);

		Mcorreo = new JTextField();
		Mcorreo.setColumns(10);
		Mcorreo.setBounds(135, 153, 133, 20);
		contentPane.add(Mcorreo);

		JLabel usuario = new JLabel("Usuario");
		usuario.setBounds(79, 52, 46, 14);
		contentPane.add(usuario);

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(79, 76, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Apellido");
		lblNewLabel_1.setBounds(79, 103, 46, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Telefono");
		lblNewLabel_2.setBounds(78, 130, 46, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Correo");
		lblNewLabel_3.setBounds(79, 156, 46, 14);
		contentPane.add(lblNewLabel_3);

		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(44, 213, 121, 37);
		contentPane.add(btnNewButton);

		JButton btnModificar = new JButton("Actualizar");
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

					Connection con = null;

					PreparedStatement ps;


					try {

						con = getConection();
						ps = (PreparedStatement) con.prepareStatement("UPDATE usuarios SET  usuario=?, nombre=?, apellido=?, telefono=?, correo=?, id=? WHERE usuario=?");
						ps.setString(1, Musuario.getText());
						ps.setString(2, Mnombre.getText());
						ps.setString(3, Mapellido.getText());
						ps.setString(4, Mtelefono.getText());
						ps.setString(5, Mcorreo.getText());
						ps.setString(6, Mid.getText());
						ps.setString(7, Musuario.getText());
						int res = ps.executeUpdate();

						if (res > 0) {

							JOptionPane.showMessageDialog(null, "Cliente modificado con exito");
							limpiar();
							Clientes.TablaAct();


						} else {

							JOptionPane.showMessageDialog(null, "Error al modificar cliente, Verifique los datos");
						}

						con.close();

					} catch (SQLException ex) {
						System.err.println(ex);
					}


			}

			private void limpiar() {
				Musuario.setText("");
				Mnombre.setText("");
				Mapellido.setText("");
				Mtelefono.setText("");
				Mcorreo.setText("");
			}

		});
		btnModificar.setBounds(247, 213, 121, 37);
		contentPane.add(btnModificar);

		JSeparator separator = new JSeparator();
		separator.setBounds(30, 192, 377, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(30, 36, 377, 2);
		contentPane.add(separator_1);

		JLabel lblNewLabel_4 = new JLabel("MODIFICAR CLIENTE");
		lblNewLabel_4.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 21));
		lblNewLabel_4.setBounds(95, 0, 224, 38);
		contentPane.add(lblNewLabel_4);

		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Connection con = null;

				PreparedStatement ps;
				ResultSet rs;

				try {

					con = getConection();
					ps = (PreparedStatement) con.prepareStatement("SELECT * FROM usuarios WHERE usuario = ?");
					ps.setString(1, Musuario.getText() );

					rs = ps.executeQuery();

					if(rs.next()) {
						Mid.setText(rs.getString("id"));
						Musuario.setText(rs.getString("usuario"));
						Mnombre.setText(rs.getString("nombre"));
						Mapellido.setText(rs.getString("apellido"));
						Mtelefono.setText(rs.getString("telefono"));
						Mcorreo.setText(rs.getString("correo"));


					} else {

						JOptionPane.showMessageDialog(null, "No existe este nombre de usuario");
					}

				} catch (SQLException ex) {
					System.err.println(ex);
				}


			}

		});
		btnNewButton_1.setBounds(278, 48, 89, 23);
		contentPane.add(btnNewButton_1);

		Mid = new JTextField();
		Mid.setEnabled(false);
		Mid.setBounds(387, 50, 37, 17);
		contentPane.add(Mid);
		Mid.setColumns(10);


	}
}
