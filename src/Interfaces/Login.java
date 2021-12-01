package Interfaces;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Conneccion.DBCusuario;
import Conneccion.DBregistro;

public class Login extends JFrame {

	protected static Object frmLogin;
	private JPanel contentPane;
	private JTextField Lusuario;
	private JPasswordField Lpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 370, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel DBuser = new JLabel("Usuario");
		DBuser.setBounds(111, 76, 46, 14);
		contentPane.add(DBuser);

		JLabel DBpass = new JLabel("Contrase\u00F1a");
		DBpass.setBounds(111, 121, 75, 14);
		contentPane.add(DBpass);

		Lusuario = new JTextField();
		Lusuario.setBounds(111, 90, 120, 20);
		contentPane.add(Lusuario);
		Lusuario.setColumns(10);

		JButton BTregistro = new JButton("Registro");
		BTregistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		BTregistro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Registro frmRegistro = new Registro();
				frmRegistro.setVisible(true);
				dispose();

			}
		});
		BTregistro.setBounds(36, 228, 109, 37);
		contentPane.add(BTregistro);

		JButton BTLogin = new JButton("Login");
		BTLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		BTLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				DBCusuario conexiones = new DBCusuario();
				DBregistro Stringssave = new DBregistro();

				String pass = new String(Lpassword.getPassword());

				if (Lusuario.getText().equals("") || pass.equals("")) {

					JOptionPane.showMessageDialog(null, "Debe ingresar su usuario y contraseña, si no esta registrado debe registrarse");

				} else {

				if(!Lusuario.getText().equals("") && !Lpassword.equals(""))
				{
					Stringssave.setUsuario(Lusuario.getText());
					Stringssave.setPassword(pass);

					if(conexiones.login(Stringssave)) {

						JOptionPane.showMessageDialog(null, "Has ingresado con exito!");
						this.AbrirCliente();

					} else {
						JOptionPane.showMessageDialog(null, "Datos invalidos");
					}
				}

			}
		}

			public void AbrirCliente() {
				Clientes frmClientes = new Clientes();
				frmClientes.setVisible(true);
				dispose();
			}


		});
		BTLogin.setBounds(190, 228, 109, 37);
		contentPane.add(BTLogin);

		JCheckBox showpass = new JCheckBox("Mostrar contrase\u00F1a");
		showpass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if(showpass.isSelected())
				{
					Lpassword.setEchoChar((char)0);
				} else {
					Lpassword.setEchoChar('*');
				}
			}
		});

		showpass.setBounds(111, 162, 139, 23);
		contentPane.add(showpass);

		Lpassword = new JPasswordField();
		Lpassword.setBounds(111, 135, 120, 20);
		contentPane.add(Lpassword);

		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 21));
		lblNewLabel.setBounds(141, 11, 109, 20);
		contentPane.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(33, 204, 291, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(33, 56, 291, 2);
		contentPane.add(separator_1);
	}
}
