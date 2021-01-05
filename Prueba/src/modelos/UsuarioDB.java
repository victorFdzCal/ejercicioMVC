package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Usuario;

public class UsuarioDB {
	public static String URLDB = ConfiguracionMVC.URLMYSQL;
	public static String USUARIODB = ConfiguracionMVC.USUARIODB;
	public static String CLAVEDB = ConfiguracionMVC.CLAVEDB;
	
	public static ArrayList<Usuario> obtenerDatosTablaUsuarios(){
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Connection conexion = BaseDB.conectarConBaseDeDatos(URLDB,USUARIODB,CLAVEDB);
		if(conexion == null) {
			return null;
		}
		ResultSet resultado = BaseDB.obtenerDatosTabla(conexion, "usuario");
		if(resultado == null) {
			return null;
		}
		try {
			while(resultado.next()) {
				String dni = resultado.getString("dni");
				String nombre = resultado.getString("nombre");
				String apellidos = resultado.getString("apellidos");
				String email = resultado.getString("email");
				String direccion = resultado.getString("direccion");
				String poblacion = resultado.getString("poblacion");
				String provincia = resultado.getString("provincia");
				usuarios.add(new Usuario(dni,nombre,apellidos,email,direccion,poblacion,provincia));
			}
			resultado.close();
			conexion.close();
			return usuarios;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean insertarUsuario(Usuario u) {
		Connection conexion = BaseDB.conectarConBaseDeDatos(URLDB, USUARIODB, CLAVEDB);
		if (conexion == null) {
			return false;
		}
		try {
			String orden = "INSERT INTO biblioteca.usuario (dni, nombre, apellidos, email, direccion, poblacion, provincia) VALUES (?,?,?,?,?,?,?);";
			PreparedStatement sentencia = conexion.prepareStatement(orden);
			sentencia.setString(1, u.getDni());
			sentencia.setString(2, u.getNombre());
			sentencia.setString(3, u.getApellidos());
			sentencia.setString(4, u.getEmail());
			sentencia.setString(5, u.getDireccion());
			sentencia.setString(6, u.getPoblacion());
			sentencia.setString(7, u.getProvincia());
			int filasAfectadas = sentencia.executeUpdate();
			sentencia.close();
			conexion.close();
			if(filasAfectadas != 0) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean actualizarUsuario(Usuario u) {
		Connection conexion = BaseDB.conectarConBaseDeDatos(URLDB, USUARIODB, CLAVEDB);
		if(conexion == null) {
			return false;
		}
		try {		
			String orden = "UPDATE biblioteca.usuario set nombre = ?, apellidos = ?, email = ?, direccion = ?, poblacion = ?, provincia = ? WHERE dni = ?;";
			PreparedStatement sentencia = conexion.prepareStatement(orden);
			sentencia.setString(1, u.getNombre());
			sentencia.setString(2, u.getApellidos());
			sentencia.setString(3, u.getEmail());
			sentencia.setString(4, u.getDireccion());
			sentencia.setString(5, u.getPoblacion());
			sentencia.setString(6, u.getProvincia());
			sentencia.setString(7, u.getDni());
			int filasAfectadas = sentencia.executeUpdate();
			sentencia.close();
			conexion.close();
			if(filasAfectadas > 0) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static boolean borrarUsuario(String columna, String valorColumna) {
		Connection conexion = BaseDB.conectarConBaseDeDatos(URLDB, USUARIODB, CLAVEDB);
		if(conexion == null) {
			return false;
		}
		boolean borradoOK = BaseDB.borrarDatosTabla(conexion, "usuario", columna, valorColumna);
		return borradoOK;
	}
	
	public static ArrayList<Usuario> buscarUsuario(String columna, String valor) {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Connection conexion = BaseDB.conectarConBaseDeDatos(URLDB, USUARIODB, CLAVEDB);
		if(conexion == null) {
			return null;
		}
		ResultSet resultado = BaseDB.buscarFilasEnTabla(conexion, "usuario", columna, valor);
		if (resultado == null) {
			return null;
		}
		try {
			while(resultado.next()) {
				String dni = resultado.getString("dni");
				String nombre = resultado.getString("nombre");
				String apellidos = resultado.getString("apellidos");
				String email = resultado.getString("email");
				String direccion = resultado.getString("direccion");
				String poblacion = resultado.getString("poblacion");
				String provincia = resultado.getString("provincia");
				usuarios.add(new Usuario(dni,nombre,apellidos,email,direccion,poblacion,provincia));
			}
			resultado.close();
			conexion.close();
			return usuarios;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
