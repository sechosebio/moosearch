package es.udc.muei.riws.proyecto;

import java.util.ArrayList;
import java.util.List;

public class Curso {
	
	private String id;

	private String nombre;
	
	private String descripcion;
	
	private String tipo;
	
	private String fotoUrl;
	
	private String idioma;
	
	private String pagina;
	
	private String url;
	
	private String fechaInicio;
	
	private List<String> idiomas;
	
	public Curso(){
		idiomas = new ArrayList<String>();
	}
	
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

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public List<String> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(List<String> idiomas) {
		this.idiomas = idiomas;
	}
}
