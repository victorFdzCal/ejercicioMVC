package modelos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Prestamo;

public class PrestamoDB {
	public static String URLDB = ConfiguracionMVC.URLMYSQL;
	public static String USUARIODB = ConfiguracionMVC.USUARIODB;
	public static String CLAVEDB = ConfiguracionMVC.CLAVEDB;
	
	public static ArrayList<Prestamo> obtenerPrestamos(){
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		Connection conexion = BaseDB.conectarConBaseDeDatos(URLDB, USUARIODB, CLAVEDB);
		if(conexion == null) {
			return null;
		}
		ResultSet resultado = BaseDB.obtenerDatosTabla(conexion, "prestamo");
		if(resultado == null) {
			return null;
		}
		try {
			while(resultado.next()) {
				int idPrestamo = resultado.getInt("idprestamo");
				Date fechaInicio = resultado.getDate("fecha_inicio");
				Date fechaFin = resultado.getDate("fecha_fin");
				String dniUsuario = resultado.getString("usuario_dni");
				String idLibro = resultado.getString("libro_idlibro");
				Date fechaDevolucion = resultado.getDate("fecha_devolucion");
				prestamos.add(new Prestamo(idPrestamo,fechaInicio,fechaFin,dniUsuario,idLibro,fechaDevolucion));
			}
			resultado.close();
			conexion.close();
			return prestamos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean insertarPrestamo(Prestamo p) {
		Connection conexion = BaseDB.conectarConBaseDeDatos(URLDB, USUARIODB, CLAVEDB);
		if(conexion == null) {
			return false;
		}
		try {
			String ordenSQL = "INSERT INTO biblioteca.prestamo (fecha_inicio, fecha_fin,usuario_dni,libro_idlibro) VALUES (?,?,?,?);";
			PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
			sentencia.setDate(1, p.getFechaInicio());
			sentencia.setDate(2, p.getFechaFin());
			sentencia.setString(3, p.getDniUsuario());
			sentencia.setString(4, p.getIdLibro());
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
	
	public static boolean actualizarPrestamo(Prestamo p) {
		Connection conexion = BaseDB.conectarConBaseDeDatos(URLDB, USUARIODB, CLAVEDB);
		if(conexion == null) {
			return false;
		}
		try {
			String ordenSQL = "UPDATE biblioteca.prestamo SET fecha_inicio = ?, fecha_fin = ?, usuario_dni = ?, libro_idlibro = ?, fecha_devolucion = ? WHERE idprestamo = ?;";
			PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
			sentencia.setDate(1, p.getFechaInicio());
			sentencia.setDate(2, p.getFechaFin());
			sentencia.setString(3, p.getDniUsuario());
			sentencia.setString(4, p.getIdLibro());
			sentencia.setDate(5,p.getFechaDevolucion());
			sentencia.setInt(6, p.getIdPrestamo());
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
	
	public static boolean borrarPrestamo(String columna, String valorColumna) {
		Connection conexion = BaseDB.conectarConBaseDeDatos(URLDB, USUARIODB, CLAVEDB);
		if(conexion == null) {
			return false;
		}
		boolean borradoOK = BaseDB.borrarDatosTabla(conexion, "prestamo", columna, valorColumna);
		return borradoOK;
	}
	
	public static ArrayList<Prestamo> buscarPrestamo(String columna, String valorColumna){
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		Connection conexion = BaseDB.conectarConBaseDeDatos(URLDB, USUARIODB, CLAVEDB);
		if(conexion == null) {
			return null;
		}
		ResultSet resultado = BaseDB.buscarFilasEnTabla(conexion, "prestamo", columna, valorColumna);
		if(resultado == null) {
			return null;
		}
		try {
			while(resultado.next()) {
				int idPrestamo = resultado.getInt("idprestamo");
				Date fechaInicio = resultado.getDate("fecha_inicio");
				Date fechaFin = resultado.getDate("fecha_fin");
				String dniUsuario = resultado.getString("dni_usuario");
				String idLibro = resultado.getString("libro_idlibro");
				Date fechaDevolucion = resultado.getDate("fecha_devolucion");
				prestamos.add(new Prestamo(idPrestamo,fechaInicio,fechaFin,dniUsuario,idLibro,fechaDevolucion));
			}
			resultado.close();
			conexion.close();
			return prestamos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
