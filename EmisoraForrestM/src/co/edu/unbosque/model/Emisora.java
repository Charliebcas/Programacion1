package co.edu.unbosque.model;

import java.util.ArrayList;

import co.edu.unbosque.model.persistence.Archivo;
import co.edu.unbosque.model.persistence.CaracteristicasEmisora;

public class Emisora {
	
	
	private Archivo archivo;
	private CaracteristicasEmisora caracteristicasEmisora;
	private ArrayList<PistaMusical> pistasMusicales;
	private ParillaMusical parillaDelDia;
	private String nombrEmisora;
	private String modoTransmision;
	private String tipoDeMusica;
	
	
	
	public Emisora() {
		
	}
	
	public void asignarInformacionEmisora(String nombre, String modo, String tipo) {
		
	}
	
	public void gestionarCaracteristicas() {
		
	}
	
	public void guardarPistaMusical() {
		
	}
	
	public boolean escribirArchivoEmisora() {
		return false;
		
	}
	
	public boolean escribirArchivoPistaMusical() {
		return false;
		
	}
	
	public boolean escribirArchivoParillaDelDia() {
		return false;
		
	}

	public Archivo getArchivo() {
		return archivo;
	}

	public void setArchivo(Archivo archivo) {
		this.archivo = archivo;
	}

	public CaracteristicasEmisora getCaracteristicasEmisora() {
		return caracteristicasEmisora;
	}

	public void setCaracteristicasEmisora(CaracteristicasEmisora caracteristicasEmisora) {
		this.caracteristicasEmisora = caracteristicasEmisora;
	}

	public ArrayList<PistaMusical> getPistasMusicales() {
		return pistasMusicales;
	}

	public void setPistasMusicales(ArrayList<PistaMusical> pistasMusicales) {
		this.pistasMusicales = pistasMusicales;
	}

	public ParillaMusical getParillaDelDia() {
		return parillaDelDia;
	}

	public void setParillaDelDia(ParillaMusical parillaDelDia) {
		this.parillaDelDia = parillaDelDia;
	}

	public String getNombrEmisora() {
		return nombrEmisora;
	}

	public void setNombrEmisora(String nombrEmisora) {
		this.nombrEmisora = nombrEmisora;
	}

	public String getModoTransmision() {
		return modoTransmision;
	}

	public void setModoTransmision(String modoTransmision) {
		this.modoTransmision = modoTransmision;
	}

	public String getTipoDeMusica() {
		return tipoDeMusica;
	}

	public void setTipoDeMusica(String tipoDeMusica) {
		this.tipoDeMusica = tipoDeMusica;
	}


}
