package es.udc.muei.riws.proyecto;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class SolrServer {
	
	private static CommonsHttpSolrServer server;
	static{
		try {
			server = 
					new CommonsHttpSolrServer("http://localhost:8983/solr");
		} catch (MalformedURLException e) {
			System.out.println("(MalformedURLException) No encuentra el solr");
		}
	}

	public void indexarProductos(List<Curso> cursos){
		try {
			
			
			List<SolrInputDocument> inputs = new ArrayList<SolrInputDocument>();
			int i =0;
			for(Curso curso : cursos){
				SolrInputDocument cursoIndexado = new SolrInputDocument();
				cursoIndexado.addField("id", curso.getId());
				cursoIndexado.addField("nombre", curso.getNombre());
				cursoIndexado.addField("descripcion", curso.getDescripcion());
				cursoIndexado.addField("tipo", curso.getTipo());
				cursoIndexado.addField("foto", curso.getFoto());
				cursoIndexado.addField("pagina", curso.getPagina());
				cursoIndexado.addField("url", curso.getUrl());
				//cursoIndexado.addField("idioma", curso.getIdioma());
				inputs.add(cursoIndexado);
				i++;
				System.out.println(i);
			}
			server.add(inputs); 
			server.commit();
	 
		} catch (MalformedURLException e) {
			System.out.println("(MalformedURLException) No encuentra el solr");
		} catch (SolrServerException e) {
			System.out.println("(SolrServerException) Error al indexar en Solr");
		} catch (IOException e) {
			System.out.println("(IOException) Error al indexar en Solr");
		}
	}
	
	public SolrDocumentList buscar(String id) throws SolrServerException{
		final SolrQuery query = new SolrQuery(); 
		query.setQuery("id:" + id); 
		return server.query(query).getResults();	
	}
}
