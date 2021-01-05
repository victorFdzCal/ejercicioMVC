package clases;

public class Libro {
	private String idLibro;
	private String nombre;
	private String editorial;
	private String idAutor;
	
	public Libro(String idLibro, String nombre, String editorial, String idAutor) {
		super();
		this.idLibro = idLibro;
		this.nombre = nombre;
		this.editorial = editorial;
		this.idAutor = idAutor;
	}
	
	public String getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(String idLibro) {
		this.idLibro = idLibro;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	public String getIdAutor() {
		return idAutor;
	}
	public void setIdAutor(String idAutor) {
		this.idAutor = idAutor;
	}

	@Override
	public String toString() {
		return "Libro [idLibro=" + idLibro + ", nombre=" + nombre + ", editorial=" + editorial + ", idAutor=" + idAutor
				+ "]";
	}
	
	
}
