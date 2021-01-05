package clases;

public class Usuario {
	private String dni;
	private String nombre;
	private String apellidos;
	private String email;
	private String direccion;
	private String poblacion;
	private String provincia;
	
	public Usuario(String dni, String nombre, String apellidos, String email, String direccion, String poblacion,
			String provincia) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.direccion = direccion;
		this.poblacion = poblacion;
		this.provincia = provincia;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	@Override
	public String toString() {
		return "Usuario [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", direccion=" + direccion + ", poblacion=" + poblacion + ", provincia=" + provincia + "]";
	}
	
	
}
