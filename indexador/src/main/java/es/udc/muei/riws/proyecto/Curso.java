package es.udc.muei.riws.proyecto;

public class Curso {
	
	private String id;

	private String nombre;
	
	private String descripcion;
	
	private String tipo;
	
	private String fotoUrl;
	
	private String idioma;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFoto() {
		return fotoUrl;
	}

	public void setFoto(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
}
