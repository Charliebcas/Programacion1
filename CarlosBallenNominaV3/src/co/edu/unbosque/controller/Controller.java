package co.edu.unbosque.controller;

import co.edu.unbosque.model.Mundo;
import co.edu.unbosque.view.View;

public class Controller {
	
	private Mundo m;
	private View gui;
	
	
	public Controller() {
		
		m=new Mundo();
		gui=new View();
		run();
		
	}
	
	public void run() {
		
		gui.imprimirMensaje("Calculando Nomina...");
		try {
			m.leerDatos();
			m.calcularNomina();
			gui.imprimirMensaje("Archivos creados con exito: "+m.getNumArchSalida());
		}
		catch(Exception e){
			gui.imprimirMensaje("Error, intente de nuevo por favor y verifique las propiedades.");
		}
	}

}
