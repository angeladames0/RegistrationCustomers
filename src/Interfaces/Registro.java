package Interfaces;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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

public class Registro extends JFrame {

	private JPanel contentPane;
	private JTextField Rusuario;
	private JTextField Rnombre;
	private JPasswordField RCpassword;
	private JPasswordField Rpassword;
	private JTextField Rtelefono;
	private JTextField Rapellido;
	private JLabel lblNewLabel_6;
	private JSeparator separator;
	private JSeparator separator_1;
	private JTextField Rcorreo;
	private JLabel lblNewLabel_7;
	private JButton btnRegresar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Registro frame = new Registro();
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
	public Registro() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(42, 46, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(250, 43, 46, 20);
		contentPane.add(lblNewLabel_1);

		Rusuario = new JTextField();
		Rusuario.setToolTipText("");
		Rusuario.setBounds(42, 62, 133, 20);
		contentPane.add(Rusuario);
		Rusuario.setColumns(10);

		Rnombre = new JTextField();
		Rnombre.setBounds(250, 62, 133, 20);
		contentPane.add(Rnombre);
		Rnombre.setColumns(10);

		RCpassword = new JPasswordField();
		RCpassword.setBounds(42, 137, 133, 20);
		contentPane.add(RCpassword);
		RCpassword.setColumns(10);

		Rpassword = new JPasswordField();
		Rpassword.setBounds(42, 98, 133, 20);
		contentPane.add(Rpassword);
		Rpassword.setColumns(10);

		Rtelefono = new JTextField();
		Rtelefono.setBounds(250, 137, 133, 20);
		contentPane.add(Rtelefono);
		Rtelefono.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Apellido");
		lblNewLabel_2.setBounds(250, 84, 46, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Telefono");
		lblNewLabel_3.setBounds(250, 122, 57, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Confirmar contrase\u00F1a");
		lblNewLabel_4.setBounds(42, 120, 133, 20);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Contrase\u00F1a");
		lblNewLabel_5.setBounds(42, 84, 73, 14);
		contentPane.add(lblNewLabel_5);

		Rapellido = new JTextField();
		Rapellido.setColumns(10);
		Rapellido.setBounds(250, 98, 133, 20);
		contentPane.add(Rapellido);

		JButton btnNewButton = new JButton("Registrarse");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				DBCusuario conexiones = new DBCusuario();
				DBregistro Stringssave = new DBregistro();
				String pass = new String(Rpassword.getPassword());
				String passCon = new String(RCpassword.getPassword());

				if (Rusuario.getText().equals("") || pass.equals("") || passCon.equals("")
						|| Rusuario.getText().equals("") || Rnombre.getText().equals("")
						|| Rapellido.getText().equals("") || Rtelefono.getText().equals("")
						|| Rcorreo.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Rellene todos los campos");

				} else {

					if (pass.equals(passCon)) {

						if (conexiones.validarusuario(Rusuario.getText()) == 0) {

							if (conexiones.validaremail(Rcorreo.getText())) {

								Stringssave.setUsuario(Rusuario.getText());
								Stringssave.setPassword(pass);
								Stringssave.setNombre(Rnombre.getText());
								Stringssave.setApellido(Rapellido.getText());
								Stringssave.setTelefono(Rtelefono.getText());
								Stringssave.setCorreo(Rcorreo.getText());

							if (conexiones.registrar(Stringssave)) {
								JOptionPane.showMessageDialog(null, "Registro guardado");
								limpiar();
								AbrirLogin();


								setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

							} else {
								JOptionPane.showMessageDialog(null, "Registro no guardado");
							}
							} else  {
								JOptionPane.showMessageDialog(null, "El correo no es valido");
							}
						} else {
							JOptionPane.showMessageDialog(null, "El usuario ya existe");
						}

					} else {
						JOptionPane.showMessageDialog(null, "La contraseña no coincide");
					}

				}
			}

			public void AbrirLogin() {
				Login frmLogin = new Login();
				frmLogin.setVisible(true);
			    dispose();
			}

			private void limpiar() {
				Rusuario.setText("");
				Rpassword.setText("");
				Rnombre.setText("");
				Rapellido.setText("");
				Rtelefono.setText("");
				Rcorreo.setText("");
				RCpassword.setText("");
			}

		});
		btnNewButton.setBounds(228, 210, 180, 40);
		contentPane.add(btnNewButton);

		lblNewLabel_6 = new JLabel("REGISTRO");
		lblNewLabel_6.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 21));
		lblNewLabel_6.setBounds(153, 0, 101, 28);
		contentPane.add(lblNewLabel_6);

		separator = new JSeparator();
		separator.setBounds(65, 33, 291, 2);
		contentPane.add(separator);

		separator_1 = new JSeparator();
		separator_1.setBounds(65, 202, 291, 2);
		contentPane.add(separator_1);

		Rcorreo = new JTextField();
		Rcorreo.setColumns(10);
		Rcorreo.setBounds(250, 175, 133, 20);
		contentPane.add(Rcorreo);

		lblNewLabel_7 = new JLabel("Correo");
		lblNewLabel_7.setBounds(250, 162, 46, 14);
		contentPane.add(lblNewLabel_7);

		btnRegresar = new JButton("Regresar");
		btnRegresar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegresar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				this.Regresar();
			}

			public void Regresar() {
				Login frmLogin = new Login();
				frmLogin.setVisible(true);
			    dispose();
			}

		});
		btnRegresar.setBounds(24, 210, 180, 40);
		contentPane.add(btnRegresar);
	}
}
