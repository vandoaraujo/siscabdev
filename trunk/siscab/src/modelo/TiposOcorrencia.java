package modelo;

import java.util.ArrayList;

public class TiposOcorrencia {

	private ArrayList<String> tipoOcorrencia;
	
	public TiposOcorrencia(){
		
		tipoOcorrencia = new ArrayList<String>();
		tipoOcorrencia.add("Colisão de veículo");
		tipoOcorrencia.add("Atropelamento");
		tipoOcorrencia.add("Mal súbito");
		tipoOcorrencia.add("Incêndio florestal");
		tipoOcorrencia.add("Orientação");
		tipoOcorrencia.add("Animal em situação de risco");
	
	}


	public ArrayList<String> getAr() {
		return tipoOcorrencia;
	}
	
	public void setAr(ArrayList<String> ar) {
		this.tipoOcorrencia = ar;
	}
	
	
}
