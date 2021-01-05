package modelos;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDB {
	protected static Connection conectarConBaseDeDatos(String url, String usuariodb, String clavedb) {
		try {
			Connection conexion = DriverManager.getConnection(url, usuariodb, clavedb);
			return conexion;
		} catch (SQLException e) {
			System.out.println("no se pudo establecer la conexion con la base de datos");
			return null;
		}		
	}	
	//-----------------------------------------------------------
	public static void mostrarInformacionBaseDeDatos(String urldb,String nombredb, String usuariodb, String clavedb )
	{
		/* 		 
		urldb = ConfiguracionMVC.URLMYSQL;
		nombredb = ConfiguracionMVC.NOMBREDB;
		usuariodb = ConfiguracionMVC.USUARIODB;
		clavedb = ConfiguracionMVC.CLAVEDB; 
		*/
		Connection conexion = conectarConBaseDeDatos(urldb, usuariodb, clavedb);
		try {
			DatabaseMetaData dbmd = conexion.getMetaData();
			String nombreDB = dbmd.getDatabaseProductName();
			String driver = dbmd.getDriverName();
			String url = dbmd.getURL();
			String usuario = dbmd.getUserName();
			//----------------------------------------------
			System.out.println("-------------------------------------------");
			System.out.println("nombre de la base de datos: " + nombreDB);
			System.out.println("driver: " + driver);
			System.out.println("url: " + url);
			System.out.println("usuario: " + usuario);
			System.out.println("----------------------TABLAS---------------");
			//----------------------------------------------
			ResultSet result = dbmd.getTables(nombredb,null, null, null);
			while(result.next())
			{
				//String catalogo = result.getString(1);
				// String esquema = result.getString(2);
				String tabla = result.getString(3);
				// String tipo = result.getString(4);
				System.out.println("----------tabla "  + tabla +": ---------------");
				ResultSet resultabla = dbmd.getColumns(nombredb, nombredb, tabla, null);
				while(resultabla.next())
				{
					String nombrecol = resultabla.getString("COLUMN_NAME");
					String tipocol = resultabla.getString("TYPE_NAME");
					String tamcol = resultabla.getString("COLUMN_NAME");
					String nulo = resultabla.getString("IS_NULLABLE");
					System.out.println("columna->" + nombrecol + " , tipo:" + tipocol + " , tam:" + tamcol + " , nulo:" + nulo );				
				}
			}			System.out.println("-------------------------------------------");			
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	//-----------------------------------------------------------
	public static ResultSet obtenerDatosTabla(Connection conexion, String nombreTabla)
	{
		try {
			String ordensql = "select * from " + nombreTabla;
			Statement sentencia = conexion.createStatement();
			ResultSet resultado = sentencia.executeQuery(ordensql);
			return resultado;
		} catch (SQLException e) {
			return null;
		}
	}
	//-----------------------------------------------------------
	public static boolean borrarDatosTabla(Connection conexion, String tabla, String columna, String valorColumna) {
		try {
			String ordensql= "delete from " + tabla +" where "+ columna + " like ? ;";
			PreparedStatement sentencia = conexion.prepareStatement(ordensql);
			sentencia.setString(1, valorColumna);
			int filasafectadas = sentencia.executeUpdate();
			sentencia.close();
			conexion.close();
			if(filasafectadas >0 )
			{
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	//-----------------------------------------------------------------------------------
	public static ResultSet buscarFilasEnTabla(Connection conexion, String nombreTabla, String columna, String valorcolumna)
	{
		try {
			String ordensql = "select * from " + nombreTabla  + " where " + columna + " like ? ";
			PreparedStatement sentencia = conexion.prepareStatement(ordensql);
			sentencia.setString(1, valorcolumna);
			ResultSet resultado = sentencia.executeQuery();
			return resultado;
		} catch (SQLException e) {
			return null;
		}
}
}
