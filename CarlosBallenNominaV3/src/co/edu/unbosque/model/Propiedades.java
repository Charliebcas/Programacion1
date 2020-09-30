package co.edu.unbosque.model;

import java.io.*;
import java.util.Properties;

public class Propiedades {
	
	
	private Properties properties = new Properties();
	private String archivoProperties = "./data/nomina.properties";
	
	public int escribirPropiedades(){
		try {
			properties.setProperty("nombreProyecto", "CarlosBallenNominaV3");
			properties.setProperty("numArchSalida", "3");
			properties.setProperty("maxRegistros", "30");
			properties.setProperty("ArchInterior", "nomina1.txt");
			properties.setProperty("ArchMedio", "nomina2.txt");
			properties.setProperty("ArchSuperior", "nomina3.txt");
			properties.setProperty("Retencion1", "10");
			properties.setProperty("Retencion2", "15");
			properties.setProperty("Retencion3", "20");
			
			properties.store(new FileOutputStream(archivoProperties), null);
		}
		catch(IOException e){
			return -1;
		}
		return 0;
	}
	
	public String leerPropiedadeS(String dato) {
		
		String linea="";
		
		try {
			properties.load(new FileInputStream(archivoProperties));
			linea=properties.getProperty(dato);
		}
		catch(IOException e) {
			return null;
		}
		return linea;
	}
	
	
}
