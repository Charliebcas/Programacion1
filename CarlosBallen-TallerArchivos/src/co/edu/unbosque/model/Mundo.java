package co.edu.unbosque.model;

import java.io.*;

public class Mundo {
	
	private String archivoData = "./data/planillaPagos.txt";
	private String master = "./data/nominaMaestro.txt";
	private String nomina1 = "./data/nomina1.txt";
	private String nomina2 = "./data/nomina2.txt";
	private String nomina3 = "./data/nomina3.txt";
	
	private String[] documentos;
	private String[] nombres;
	private int[] salarios;
	private int[][] nomina10;
	private int[][] nomina15;
	private int[][] nomina20;
	private int size;
	private int sizeN10;
	private int sizeN15;
	private int sizeN20;
	private int rete10;
	private int rete15;
	private int rete20;

	
	public Mundo() {
		rete10=10;
		rete15=15;
		rete20=20;
		size=0;
	}
	
	public void inicializarArreglos() {
		
		int count=0;
		String linea="";
		File f = new File(this.archivoData);
		try {
			
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			linea=br.readLine();
			while(linea!=null){
				linea=br.readLine();
				count++;
			}
			fr.close();
		}
		catch(IOException e){
			
		}
		size=count-1;
		documentos=new String[size];
		nombres=new String[size];
		salarios=new int[size];
	}
	
	
	public void inicializarArregloNomina() {
		for(int i=0; i<size; i++) {
			if(salarios[i]<=2000) {
				sizeN10++;
			}
			else if(salarios[i]<=5000) {
				sizeN15++;
			}
			else if(salarios[i]>5000) {
				sizeN20++;
			}
		}
		nomina10=new int[sizeN10][2];
		nomina15=new int[sizeN15][2];
		nomina20=new int[sizeN20][2];
	}
	

	public void leerDatos() {
		
		String linea="";
		this.inicializarArreglos();
		File f = new File(this.archivoData);
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			linea=br.readLine();
			linea=br.readLine();
			int i=0;
			while(linea!=null){
				String[] datos = new String[3];
				datos=linea.split(";");
				documentos[i]= datos[0].trim();
 				nombres[i]= datos[1].trim();
				salarios[i]= Integer.parseInt(datos[2].trim());
				linea=br.readLine();
				i++;
			}
			fr.close();
		}
		catch(IOException e){

		}
	}
	
	
	public void escribirArchivoMaster() {
		File f = new File(master);
		
		try {
			f.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		try {
			FileWriter fw= new FileWriter(f);
			PrintWriter pr= new PrintWriter(fw);
			pr.println("Documento ;Nombre Estudiante");
			for(int i=0;i<size;i++)
			{
				pr.println(documentos[i]+" ;"+nombres[i]);
			}
			fw.close();
		}
		catch(IOException e){
		}
	}
	
	
	
	public void guardarNomina(String archivo, int[][] listNomina) {
		File f = new File(archivo);
		int id=0;
		try {
			f.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			FileWriter fw= new FileWriter(f);
			PrintWriter pr= new PrintWriter(fw);
			pr.println("Documento ; Nomina con retencion");
			for(int i=0;i<listNomina.length;i++)
			{
				id=listNomina[i][0];
				pr.println(documentos[id]+" ;"+listNomina[i][1]);
			}
			fw.close();
		}
		catch(IOException e){
			
		}
	}
	
	
	
	public int getSalario(String cedula) {
		int i=buscarUsuario(cedula);
		return salarios[i];
	}

	
	public void cambiarSalario(String cedula, int salario) {
		int i = buscarUsuario(cedula);
		salarios[i]=salario;
	}

	public int buscarUsuario(String cedula) {
		for(int i=0;i<size;i++) {
			if(documentos[i].contains(cedula))
				return i;
		}
		return -1;
	}
	
	public void aplicarRetencion() {
		int j1=0;
		int j2=0;
		int j3=0;
		for(int i=0; i<size; i++) {

			if(salarios[i]<=2000) {
				nomina10[j1][0]=i;
				nomina10[j1][1]=salarios[i]-(salarios[i]*rete10)/100;
				j1++;
			}
			else if(salarios[i]<=5000) {
				nomina15[j2][0]=i;
				nomina15[j2][1]=salarios[i]-(salarios[i]*rete15)/100;
				j2++;
			}
			else if(salarios[i]>5000) {
				nomina20[j3][0]=i;
				nomina20[j3][1]=salarios[i]-(salarios[i]*rete20)/100;
				j3++;
			}
		}
	}
		
	public void calcularNomina(){
		inicializarArregloNomina();
		aplicarRetencion();
		escribirArchivoMaster();
		guardarNomina(this.nomina1,this.nomina10);
		guardarNomina(this.nomina2,this.nomina15);
		guardarNomina(this.nomina3,this.nomina20);
	}
	
}
