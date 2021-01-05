package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Libro;

public class LibroDB {
	public static String URLDB = ConfiguracionMVC.URLMYSQL;
	public static String USUARIODB = ConfiguracionMVC.USUARIODB;
	public static String CLAVEDB = ConfiguracionMVC.CLAVEDB;
	
	public static ArrayList<Libro> obtenerLibros(){
		ArrayList<Libro> libros = new ArrayList<Libro>();
		Connection conexion = BaseDB.conectarConBaseDeDatos(URLDB, USUARIODB, CLAVEDB);
		if(conexion == null) {
			return null;
		}
		ResultSet resultado = BaseDB.obtenerDatosTabla(conexion, "libro");
		if(resultado == null) {
			return null;
		}
		try {
			while(resultado.next()) {
				String idLibro = resultado.getString("idlibro");
				String nombre = resultado.getString("nombre");
				String editorial = resultado.getString("editorial");
				String autor = resultado.getString("autor_idautor");
				libros.add(new Libro(idLibro,nombre,editorial,autor));
			}
			resultado.close();
			conexion.close();
			return libros;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean insertarLibro(Libro l) {
		Connection conexion = BaseDB.conectarConBaseDeDatos(URLDB, USUARIODB, CLAVEDB);
		if(conexion == null) {
			return false;
		}
		try {
			String ordenSQL = "INSERT INTO biblioteca.libro (idlibro, nombre, editorial, idautor) VALUES (?, ?, ?, ?);";
			PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
			sentencia.setString(1, l.getIdLibro());
			sentencia.setString(2, l.getNombre());
			sentencia.setString(3, l.getEditorial());
			sentencia.setString(4, l.getIdAutor());
			int filasAfectadas = sentencia.executeUpdate();
			sentencia.close();
			conexion.close();
			if(filasAfectadas != 0) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException e) {
			return false;
		}
	}
	
	public static boolean actualizarLibro(Libro l) {
		Connection conexion = BaseDB.conectarConBaseDeDatos(URLDB, USUARIODB, CLAVEDB);
		if(conexion == null) {
			return false;
		}
		try {
			String ordenSQL = "UPDATE biblioteca.libro SET nombre = ?, editorial = ?, autor_idautor = ? WHERE idlibro = ?";
			PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
			sentencia.setString(1, l.getNombre());
			sentencia.setString(2, l.getEditorial());
			sentencia.setString(3, l.getIdAutor());
			sentencia.setString(4, l.getIdLibro());
			int filasAfectadas = sentencia.executeUpdate();
			sentencia.close();
			conexion.close();
			if(filasAfectadas != 0) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException e) {
			return false;
		}
	}
	
	public static boolean borrarLibro(String columna, String valorColumna) {
		Connection conexion = BaseDB.conectarConBaseDeDatos(URLDB, USUARIODB, CLAVEDB);
		if(conexion == null) {
			return false;
		}
		boolean borradoOK = BaseDB.borrarDatosTabla(conexion, "libro", columna, valorColumna);
		return borradoOK;
	}
	
	public static ArrayList<Libro> buscarLibro(String columna, String valorColumna){
		ArrayList<Libro> libros = new ArrayList<Libro>();
		Connection conexion = BaseDB.conectarConBaseDeDatos(URLDB, USUARIODB, CLAVEDB);
		if(conexion == null) {
			return null;
		}
		ResultSet resultado = BaseDB.buscarFilasEnTabla(conexion, "libro", columna, valorColumna);
		if(resultado == null) {
			return null;
		}
		try {
			while(resultado.next()) {
				String idLibro = resultado.getString("idlibro");
				String nombre = resultado.getString("nombre");
				String editorial = resultado.getString("editorial");
				String autor = resultado.getString("autor_idautor");
				libros.add(new Libro(idLibro,nombre,editorial,autor));
			}
			resultado.close();
			conexion.close();
			return libros;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
