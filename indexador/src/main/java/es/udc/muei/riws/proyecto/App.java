package es.udc.muei.riws.proyecto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class App{
	
   public static void main(String[] args)
   {
        App app = new App();
        app.indexar();
        //app.buscarPorId("ud837");
        
   }
   
   private void buscarPorId(String id){
	   SolrServer solr = new SolrServer();
	   try {
			SolrDocumentList resultados = solr.buscar(id);
			for(SolrDocument obj : resultados){
				System.out.println("id = " + obj.get("id"));
				System.out.println("nombre = " + obj.get("nombre"));
				System.out.println("descripcion = " + obj.get("descripcion"));
				System.out.println("tipo = " + obj.get("tipo"));
				
			}
		} catch (SolrServerException e) {
			System.out.println("(SolrServerException)Error al "
					+ "realizar la busqueda por id: " + e.getMessage());
		}
   }
	
   private void indexar(){

	   List<Curso> cursos = parsearContenidoCoursera();
	   cursos.addAll(parsearContenidoUdacity());
	     
//	   List<Curso> cursos = new ArrayList<Curso>();
//	   Curso cursoPrueba = new Curso();
//	   cursoPrueba.setDescripcion("descripcion");
//	   cursoPrueba.setId("id");
//	   cursoPrueba.setNombre("nombre");
//	   cursoPrueba.setTipo("tipo");
//	   cursos.add(cursoPrueba);
	   
	   SolrServer solr = new SolrServer();
	   solr.indexarProductos(cursos);
   }
   
   private String obtenerContenido(HttpsURLConnection con) throws IOException{
	   String resultado = null;
	   
	   if(con != null){
		   int code = con.getResponseCode();
		   if(code == 200){
			   resultado = "";
			   BufferedReader br = new BufferedReader(
					   new InputStreamReader(con.getInputStream()));
			   String input;
			   while ((input = br.readLine()) != null){
				   resultado += input;
			   }
			   br.close();
		   }
	   }
	   
	   return resultado;
   }
   
   private JSONObject obtenerContenidoUrl(String https_url) throws IOException, ParseException{
	   URL url;
       List<Curso> cursos = new ArrayList<Curso>();
       url = new URL(https_url);
       HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
       
       String contenido = obtenerContenido(con);
       JSONParser parser = new JSONParser();
       Object obj = parser.parse(contenido);
       JSONObject jsonObject = (JSONObject) obj;
       
       return jsonObject;
   }
	
   private List<Curso> parsearContenidoCoursera(){
	  //String https_url = "https://api.coursera.org/api/courses.v1";
	   String https_url = "https://api.coursera.org/api/courses.v1/?&fields=photoUrl,domainTypes,primaryLanguages,description";
      List<Curso> cursos = new ArrayList<Curso>();
      try {
			
	     JSONObject jsonObject = obtenerContenidoUrl(https_url);
	   
	     JSONArray elements = (JSONArray) jsonObject.get("elements");
	     JSONArray tipo;
	     JSONObject idioma;
	     if(elements != null){
		   Iterator<JSONObject> iterator = elements.iterator();
		   while (iterator.hasNext()) {
			   Curso curso = new Curso();
			   JSONObject cursoJson = (JSONObject)iterator.next();
			   curso.setId((String)cursoJson.get("id"));
			   curso.setNombre((String)cursoJson.get("name"));
			   //curso.setTipo((String)cursoJson.get("slug"));
			   curso.setFoto((String)cursoJson.get("photoUrl"));
			   tipo = (JSONArray) cursoJson.get("domainTypes");
			   //idioma = (JSONObject) cursoJson.get("primaryLanguages");
			   curso.setTipo((String) ((JSONObject) tipo.get(0)).get("domainId"));
			   cursos.add(curso);
	
		   }
	  	}
      } catch (MalformedURLException e) {
 	     e.printStackTrace();
       } catch (IOException e) {
 	     e.printStackTrace();
       } catch (org.json.simple.parser.ParseException e) {
 		System.out.println("no se pudo parsear el resultado:" + e.getMessage());
       }
	   return cursos;
   }
   
   private List<Curso> parsearContenidoUdacity(){
		  String https_url = "https://udacity.com/public-api/v0/courses";
	      List<Curso> cursos = new ArrayList<Curso>();
	      try {
				
		     JSONObject jsonObject = obtenerContenidoUrl(https_url);
		   
		     JSONArray courses = (JSONArray) jsonObject.get("courses");
		     if(courses != null){
			   Iterator<JSONObject> iterator = courses.iterator();
			   while (iterator.hasNext()) {
				   Curso curso = new Curso();
				   JSONObject cursoJson = (JSONObject)iterator.next();
				   curso.setId((String)cursoJson.get("key"));
				   curso.setNombre((String)cursoJson.get("title"));
				   curso.setDescripcion((String)cursoJson.get("project_description"));
				   
				   cursos.add(curso);
				   
			   }
		  	}
	      } catch (MalformedURLException e) {
	 	     e.printStackTrace();
	       } catch (IOException e) {
	 	     e.printStackTrace();
	       } catch (org.json.simple.parser.ParseException e) {
	 		System.out.println("no se pudo parsear el resultado:" + e.getMessage());
	       }
		   return cursos;
	   }
}
