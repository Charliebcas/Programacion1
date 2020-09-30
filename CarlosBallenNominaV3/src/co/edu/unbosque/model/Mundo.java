package co.edu.unbosque.model;

import java.io.*;

public class Mundo {
	
	private Propiedades props;
	
	private String archivoData = "./data/planillaPagos.txt";
	private String master = "./data/nominaMaestro.txt";
	private String archivo1 = "archInterior";
	private String archivo2 = "archMedio";
	private String archivo3 = "archSuperior";
	private String project = "nombreProyecto";
	private int numArchSalida;
	
	private String[] documentos;
	private String[] nombres;
	private int[] salarios;
	private int[][] nomina1;
	private int[][] nomina2;
	private int[][] nomina3;
	private int maxSize;
	private int sizeN1;
	private int sizeN2;
	private int sizeN3;
	private int rete1;
	private int rete2;
	private int rete3;

	
	
	public Mundo() {
		props = new Propiedades();
		rete1=Integer.parseInt(props.leerPropiedadeS("retencion1"));
		rete2=Integer.parseInt(props.leerPropiedadeS("retencion2"));
		rete3=Integer.parseInt(props.leerPropiedadeS("retencion3"));
		maxSize=Integer.parseInt(props.leerPropiedadeS("maxRegistros"));
		archivo1="./data/"+props.leerPropiedadeS(this.archivo1);
		archivo2="./data/"+props.leerPropiedadeS(this.archivo2);
		archivo3="./data/"+props.leerPropiedadeS(this.archivo3);
		project=props.leerPropiedadeS(this.project);
		numArchSalida=Integer.parseInt(props.leerPropiedadeS("numArchSalida"));
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
		if(count-1<maxSize) {
			maxSize=count-1;
		}
		documentos=new String[maxSize];
		nombres=new String[maxSize];
		salarios=new int[maxSize];
	}
	
	
	public void inicializarArregloNomina() {
		for(int i=0; i<maxSize; i++) {
			if(salarios[i]<=2000) {
				sizeN1++;
			}
			else if(salarios[i]<=5000) {
				sizeN2++;
			}
			else if(salarios[i]>5000) {
				sizeN3++;
			}
		}
		nomina1=new int[sizeN1][2];
		nomina2=new int[sizeN2][2];
		nomina3=new int[sizeN3][2];
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
			int count=0;
			while(linea!=null && count<maxSize){
				String[] datos = new String[3];
				datos=linea.split(";");
				documentos[i]= datos[0].trim();
 				nombres[i]= datos[1].trim();
				salarios[i]= Integer.parseInt(datos[2].trim());
				linea=br.readLine();
				i++;
				count++;
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
			pr.println("Nombre Proyecto: "+project);
			pr.println("Numero de archivos creados: "+numArchSalida);
			pr.println("Documento ;Nombre Estudiante");
			for(int i=0;i<maxSize;i++)
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
			pr.println("Nombre Proyecto: "+project);
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
		for(int i=0;i<maxSize;i++) {
			if(documentos[i].contains(cedula))
				return i;
		}
		return -1;
	}
	
	public void aplicarRetencion() {
		int j1=0;
		int j2=0;
		int j3=0;
		for(int i=0; i<maxSize; i++) {

			if(salarios[i]<=2000) {
				nomina1[j1][0]=i;
				nomina1[j1][1]=salarios[i]-(salarios[i]*rete1)/100;
				j1++;
			}
			else if(salarios[i]<=5000) {
				nomina2[j2][0]=i;
				nomina2[j2][1]=salarios[i]-(salarios[i]*rete2)/100;
				j2++;
			}
			else if(salarios[i]>5000) {
				nomina3[j3][0]=i;
				nomina3[j3][1]=salarios[i]-(salarios[i]*rete3)/100;
				j3++;
			}
		}
	}
		
	public void calcularNomina(){
		inicializarArregloNomina();
		aplicarRetencion();
		escribirArchivoMaster();
		guardarNomina(this.archivo1,this.nomina1);
		guardarNomina(this.archivo2,this.nomina2);
		guardarNomina(this.archivo3,this.nomina3);
	}

	public Propiedades getProps() {
		return props;
	}

	public void setProps(Propiedades props) {
		this.props = props;
	}

	public String getArchivoData() {
		return archivoData;
	}

	public void setArchivoData(String archivoData) {
		this.archivoData = archivoData;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public String getArchivo1() {
		return archivo1;
	}

	public void setArchivo1(String archivo1) {
		this.archivo1 = archivo1;
	}

	public String getArchivo2() {
		return archivo2;
	}

	public void setArchivo2(String archivo2) {
		this.archivo2 = archivo2;
	}

	public String getArchivo3() {
		return archivo3;
	}

	public void setArchivo3(String archivo3) {
		this.archivo3 = archivo3;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public int getNumArchSalida() {
		return numArchSalida;
	}

	public void setNumArchSalida(int numArchSalida) {
		this.numArchSalida = numArchSalida;
	}

	public String[] getDocumentos() {
		return documentos;
	}

	public void setDocumentos(String[] documentos) {
		this.documentos = documentos;
	}

	public String[] getNombres() {
		return nombres;
	}

	public void setNombres(String[] nombres) {
		this.nombres = nombres;
	}

	public int[] getSalarios() {
		return salarios;
	}

	public void setSalarios(int[] salarios) {
		this.salarios = salarios;
	}

	public int[][] getNomina1() {
		return nomina1;
	}

	public void setNomina1(int[][] nomina1) {
		this.nomina1 = nomina1;
	}

	public int[][] getNomina2() {
		return nomina2;
	}

	public void setNomina2(int[][] nomina2) {
		this.nomina2 = nomina2;
	}

	public int[][] getNomina3() {
		return nomina3;
	}

	public void setNomina3(int[][] nomina3) {
		this.nomina3 = nomina3;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public int getSizeN1() {
		return sizeN1;
	}

	public void setSizeN1(int sizeN1) {
		this.sizeN1 = sizeN1;
	}

	public int getSizeN2() {
		return sizeN2;
	}

	public void setSizeN2(int sizeN2) {
		this.sizeN2 = sizeN2;
	}

	public int getSizeN3() {
		return sizeN3;
	}

	public void setSizeN3(int sizeN3) {
		this.sizeN3 = sizeN3;
	}

	public int getRete1() {
		return rete1;
	}

	public void setRete1(int rete1) {
		this.rete1 = rete1;
	}

	public int getRete2() {
		return rete2;
	}

	public void setRete2(int rete2) {
		this.rete2 = rete2;
	}

	public int getRete3() {
		return rete3;
	}

	public void setRete3(int rete3) {
		this.rete3 = rete3;
	}
	
	
	
	
	
	
	
	
}
