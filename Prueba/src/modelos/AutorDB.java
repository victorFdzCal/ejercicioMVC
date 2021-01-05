package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Autor;

public class AutorDB {
	public static String URLDB = ConfiguracionMVC.URLMYSQL;
	public static String USUARIODB = ConfiguracionMVC.USUARIODB;
	public static String CLAVEDB = ConfiguracionMVC.CLAVEDB;
	
	public static ArrayList<Autor> obtenerDatosAutor(){
		ArrayList<Autor> autores = new ArrayList<Autor>();
		Connection conexion = BaseDB.conectarConBaseDeDatos(URLDB, USUARIODB, CLAVEDB);
		if(conexion == null) {
			return null;
		}
		ResultSet resultado = BaseDB.obtenerDatosTabla(conexion, "autor");
		if(resultado == null) {
			return null;
		}
		try {
			while(resultado.next()) {
				String idAutor = resultado.getString("idautor");
				String nombre = resultado.getString("nombre");
				String apellidos = resultado.getString("apellidos");
				String nacionalidad = resultado.getString("nacionalidad");
				autores.add(new Autor(idAutor,nombre,apellidos,nacionalidad));
			}
			resultado.close();
			conexion.close();
			return autores;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean insertarAutor(Autor a) {
		Connection conexion = BaseDB.conectarConBaseDeDatos(URLDB, USUARIODB, CLAVEDB);
		if (conexion == null) {
			return false;
		}
		try {
			String ordenSQL = "INSERT INTO biblioteca.autor (idautor, nombre, apellidos,nacionalidad) VALUES (?, ?, ?, ?);";
			PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
			sentencia.setString(1, a.getIdAutor());
			sentencia.setString(2, a.getNombre());
			sentencia.setString(3, a.getApellidos());
			sentencia.setString(4, a.getNacionalidad());
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
	
	public static boolean actualizarAutor(Autor a) {
		Connection conexion = BaseDB.conectarConBaseDeDatos(URLDB, USUARIODB, CLAVEDB);
		if(conexion == null) {
			return false;
		}
		try {
			String ordenSQL = "UPDATE biblioteca.autor set nombre = ?, apellidos = ?, nacionalidad = ? where idautor = ?";
			PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
			sentencia.setString(1, a.getNombre());
			sentencia.setString(2, a.getApellidos());
			sentencia.setString(3, a.getNacionalidad());
			sentencia.setString(4, a.getIdAutor());
			int filasAfectadas = sentencia.executeUpdate();
			sentencia.close();
			conexion.close();
			if(filasAfectadas > 0) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException e) {
			return false;
		}
	}
	
	public static boolean borrarAutor(String columna, String valorColumna) {
		Connection conexion = BaseDB.conectarConBaseDeDatos(URLDB, USUARIODB, CLAVEDB);
		if(conexion == null) {
			return false;
		}
		boolean borradoOK = BaseDB.borrarDatosTabla(conexion, "autor", columna, valorColumna);
		return borradoOK;
	}
	
	public static ArrayList<Autor> buscarAutores(String columna, String valorColumna){
		ArrayList<Autor> autores = new ArrayList<Autor>();
		Connection conexion = BaseDB.conectarConBaseDeDatos(URLDB, USUARIODB, CLAVEDB);
		if(conexion == null) {
			return null;
		}
		ResultSet resultado = BaseDB.buscarFilasEnTabla(conexion, "autor", columna, valorColumna);
		if(resultado == null) {
			return null;
		}
		try {
			while(resultado.next()) {
				String idAutor = resultado.getString("idautor");
				String nombre = resultado.getString("nombre");
				String apellidos = resultado.getString("apellidos");
				String nacionalidad = resultado.getString("nacionalidad");
				autores.add(new Autor(idAutor, nombre, apellidos,nacionalidad));
			}
			resultado.close();
			conexion.close();
			return autores;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
