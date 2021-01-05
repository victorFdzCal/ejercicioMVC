package clases;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Prestamo {
	private int idPrestamo;
	private Date fechaInicio;
	private Date fechaFin;
	private String dniUsuario;
	private String idLibro;
	private Date fechaDevolucion;
	
	public Prestamo(String fechaInicio, String fechaFin, String dniUsuario, String idLibro) throws ParseException {
		DateFormat fecha = new SimpleDateFormat("DD-MM-YYYY"); 
		this.fechaInicio = new Date(fecha.parse(fechaInicio).getTime());
		this.fechaFin = new Date(fecha.parse(fechaFin).getTime());
		this.dniUsuario = dniUsuario;
		this.idLibro = idLibro;
	}
	
	public Prestamo(int idPrestamo, Date fechaInicio, Date fechaFin, String dniUsuario, String idLibro,
			Date fechaDevolucion) {
		super();
		this.idPrestamo = idPrestamo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.dniUsuario = dniUsuario;
		this.idLibro = idLibro;
		this.fechaDevolucion = fechaDevolucion;
	}



	public int getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getDniUsuario() {
		return dniUsuario;
	}
	public void setDniUsuario(String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}
	public String getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(String idLibro) {
		this.idLibro = idLibro;
	}
	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	
	@Override
	public String toString() {
		return "Prestamo [idPrestamo=" + idPrestamo + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", dniUsuario=" + dniUsuario + ", idLibro=" + idLibro + ", fechaDevolucion=" + fechaDevolucion + "]";
	}
	
	
}
