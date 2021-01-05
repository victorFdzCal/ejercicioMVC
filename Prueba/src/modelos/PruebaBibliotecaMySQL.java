package modelos;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;

import clases.Autor;
import clases.Libro;
import clases.Prestamo;
import clases.Usuario;

public class PruebaBibliotecaMySQL {
	private static void mostrarUsuarios(ArrayList<Usuario> usuarios) {
		System.out.println("---------------------TABLA USUARIOS----------------------");
		for(Usuario u:usuarios) {
			System.out.println(u.toString());
		}
		System.out.println("---------------------------------------------------------");
	}
	
	private static void mostrarLibros(ArrayList<Libro> libros) {
		System.out.println("---------------------TABLA LIBROS----------------------");
		for(Libro l:libros) {
			System.out.println(l.toString());
		}
		System.out.println("---------------------------------------------------------");
	}
	
	private static void mostrarPrestamos(ArrayList<Prestamo> prestamos) {
		System.out.println("---------------------TABLA PRESTAMOS----------------------");
		for(Prestamo p:prestamos) {
			System.out.println(p.toString());
		}
		System.out.println("---------------------------------------------------------");
	}
	
	private static void mostrarAutores(ArrayList<Autor> autores) {
		System.out.println("---------------------TABLA AUTORES----------------------");
		for(Autor a:autores) {
			System.out.println(a.toString());
		}
		System.out.println("---------------------------------------------------------");
	}
	
	public static void main(String[] args) throws ParseException {
		Prestamo p = new Prestamo("05-01-2021", "20-01-2021", "11542981G","EPR");
		boolean insercionOK = PrestamoDB.insertarPrestamo(p);
		if(insercionOK) {
			System.out.println("Insercion correcta");
		}else {
			System.out.println("Fallo en la insercion");
		}
		// TODO Auto-generated method stub
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = UsuarioDB.obtenerDatosTablaUsuarios();
		if(usuarios != null) {
			mostrarUsuarios(usuarios);
		}
		ArrayList<Libro> libros = new ArrayList<Libro>();
		libros = LibroDB.obtenerLibros();
		if(libros != null) {
			mostrarLibros(libros);
		}
		ArrayList<Autor> autores = new ArrayList<Autor>();
		autores = AutorDB.obtenerDatosAutor();
		if(autores != null) {
			mostrarAutores(autores);
		}
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		prestamos = PrestamoDB.obtenerPrestamos();
		if(prestamos != null) {
			mostrarPrestamos(prestamos);
		}
		//----------------------------------------------------------------------
		/*Usuario u = new Usuario("04234661Q", "Victor", "Fernandez Calleja", "victorfc@gmail.com","Calle Valencia 11", "Yuncos", "Toledo");
		boolean insercionOK = UsuarioDB.insertarUsuario(u);
		if(insercionOK) {
			System.out.println("Insercion correcta del usuario");
		}else {
			System.out.println("Fallo al insertar el usuario");
		}
		//----------------------------------------------------------------------------
		Usuario u2 = new Usuario("04234661Q", "Victor", "Fernandez Calleja", "victorfc@gmail.com","Calle Valencia 10", "Yuncos", "Toledo");
		boolean actualizacionOK = UsuarioDB.actualizarUsuario(u2);
		if(actualizacionOK) {
			System.out.println("Actualizacion correcta del usuario");
		}else {
			System.out.println("Fallo al actualizar el usuario");
		}*/
		//----------------------------------------------------------------------------------
/*
		String dni = "04234661Q";
		boolean borradoOK = UsuarioDB.borrarUsuario("dni", dni);
		if(borradoOK) {
			System.out.println("Se ha borrado al usuario correctamente");
		}else {
			System.out.println("Fallo al borrar el usuario");
		}*/
		//----------------------------------------------------------------------------------
		ArrayList<Usuario> usuariosBuscados = UsuarioDB.buscarUsuario("dni","11542981G");
		if(usuariosBuscados != null) {
			mostrarUsuarios(usuariosBuscados);
		}
	}

}
