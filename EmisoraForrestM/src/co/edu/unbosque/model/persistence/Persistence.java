package co.edu.unbosque.model.persistence;

public abstract class Persistence {
	
	public abstract void escribir(String dato, String ruta);
	
	public abstract void leer(String dato, String ruta);

}
