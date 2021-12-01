package Interfaces;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

import Conneccion.DBconexion;

public class Clientes extends JFrame {

	protected static Object frmLog;
	private JPanel contentPane;
	private JTable Tclientes;
	private static JTable table;
	DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Clientes frame = new Clientes();
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
	public Clientes() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Nuevo");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Nuevo frmNuevo = new Nuevo();
				frmNuevo.setVisible(true);

			}
		});
		btnNewButton.setBounds(21, 243, 119, 38);
		contentPane.add(btnNewButton);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnActualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Modificar frmModificar = new Modificar();
				frmModificar.setVisible(true);

			}
		});
		btnActualizar.setBounds(177, 243, 119, 38);
		contentPane.add(btnActualizar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Eliminar frmEliminar = new Eliminar();
				frmEliminar.setVisible(true);


			}
		});
		btnEliminar.setBounds(330, 243, 119, 38);
		contentPane.add(btnEliminar);

		JButton btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			this.Cerrarsesion();

			}

			public void Cerrarsesion() {
				Login frmLogin = new Login();
				frmLogin.setVisible(true);
			    dispose();
			}

		});
		btnCerrarSesion.setBounds(477, 243, 132, 38);
		contentPane.add(btnCerrarSesion);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 37, 599, 193);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		TablaAct();

		JLabel lblNewLabel = new JLabel("CLIENTES");
		lblNewLabel.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 21));
		lblNewLabel.setBounds(251, 5, 132, 28);
		contentPane.add(lblNewLabel);
	}


		static void TablaAct() {

		DBconexion conn = new DBconexion();
		Connection con = conn.getDBconexion();

		String sql = "SELECT nombre, apellido, telefono, correo, usuario FROM usuarios" ;

		Statement st;
		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("Nombre");
		model.addColumn("Apellido");
		model.addColumn("Telefono");
		model.addColumn("Correo");
		model.addColumn("Usuario");

		table.setModel(model);

		String[] dato = new String[5];

		try {


			st = (Statement) con.createStatement();

			ResultSet result = st.executeQuery(sql);


			while(result.next()){
				dato[0]=result.getString(1);
				dato[1]=result.getString(2);
				dato[2]=result.getString(3);
				dato[3]=result.getString(4);
				dato[4]=result.getString(5);
				model.addRow(dato);

			}



		} catch (SQLException ex) {
			System.err.println(ex.toString());
	}



}


}
