package clases;

public class Autor {
	private String idAutor;
	private String nombre;
	private String apellidos;
	private String nacionalidad;
	
	public Autor(String idAutor, String nombre, String apellidos, String nacionalidad) {
		super();
		this.idAutor = idAutor;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nacionalidad = nacionalidad;
	}

	public String getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(String idAutor) {
		this.idAutor = idAutor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	@Override
	public String toString() {
		return "Autor [idAutor=" + idAutor + ", nombre=" + nombre + ", apellidos=" + apellidos + ", nacionalidad="
				+ nacionalidad + "]";
	}
	
	
}
