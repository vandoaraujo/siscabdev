package modelo;

import java.util.ArrayList;

public class TiposOcorrencia {

	private ArrayList<String> tipoOcorrencia;
	
	public TiposOcorrencia(){
		
		tipoOcorrencia = new ArrayList<String>();
		tipoOcorrencia.add("Colis�o de ve�culo");
		tipoOcorrencia.add("Atropelamento");
		tipoOcorrencia.add("Mal s�bito");
		tipoOcorrencia.add("Inc�ndio florestal");
		tipoOcorrencia.add("Orienta��o");
		tipoOcorrencia.add("Animal em situa��o de risco");
	
	}


	public ArrayList<String> getAr() {
		return tipoOcorrencia;
	}
	
	public void setAr(ArrayList<String> ar) {
		this.tipoOcorrencia = ar;
	}
	
	
}
