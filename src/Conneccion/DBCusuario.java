package Conneccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBCusuario extends DBconexion {

	public boolean registrar(DBregistro usr)
	{
		PreparedStatement ps = null;
		Connection con = getDBconexion();

		String sql = "INSERT INTO usuarios (usuario, password, nombre, apellido, telefono, correo) VALUES (?,?,?,?,?,?)" ;

		try {

			ps = con.prepareStatement(sql);
			ps.setString(1, usr.getUsuario());
			ps.setString(2, usr.getPassword());
			ps.setString(3, usr.getNombre());
			ps.setString(4, usr.getApellido());
			ps.setString(5, usr.getTelefono());
			ps.setString(6, usr.getCorreo());
			ps.execute();
			return true;

		} catch (SQLException ex) {
			Logger.getLogger(DBCusuario.class.getName()).log(Level.SEVERE, null, ex);
		}


		return false;

	}

	public int validarusuario(String usuario)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getDBconexion();

		String sql = "SELECT count(id) FROM usuarios WHERE usuario = ?" ;

		try {

			ps = con.prepareStatement(sql);
			ps.setString(1, usuario);
			rs = ps.executeQuery();

			if(rs.next())
			{
				return rs.getInt(1);
			}

			return 1;


		} catch (SQLException ex) {
			Logger.getLogger(DBCusuario.class.getName()).log(Level.SEVERE, null, ex);
		}


		return 1;

	}

	public boolean validaremail(String correo)
	{


		 Pattern pattern = Pattern.compile ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		 Matcher mather = pattern.matcher(correo);

		 return mather.find();
	}

	public boolean login(DBregistro usr)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getDBconexion();

		String sql = "SELECT id, usuario, password, nombre FROM usuarios WHERE usuario = ?" ;

		try {

			ps = con.prepareStatement(sql);
			ps.setString(1, usr.getUsuario());
			rs = ps.executeQuery();

			if(rs.next())
			{
				if (usr.getPassword().equals(rs.getString(3))) {


					usr.setId(rs.getInt(1));
					usr.setNombre(rs.getString(4));


					return true;
				} else {
					return false;
				}
			}

			return false;


		} catch (SQLException ex) {
			Logger.getLogger(DBCusuario.class.getName()).log(Level.SEVERE, null, ex);
		}


		return false;

	}

}


