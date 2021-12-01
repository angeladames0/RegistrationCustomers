package Conneccion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBconexion {

	private final String user = "root";
	private final String password = "12345678";
	private final String url = "jdbc:mysql://localhost:3306/usuarios";
	private Connection con = null;
	PreparedStatement ps;

	public Connection getDBconexion()

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


}
