package modelo;

import java.util.ArrayList;

public class NaturezaChamados {
	
	private ArrayList<String> ar;
		
	public NaturezaChamados(){
		
		ar = new ArrayList<String>();
		ar.add("Solicitação de socorro");
		ar.add("Trote");
		ar.add("Engano/Desistência");
		ar.add("Queda de ligação");
		ar.add("Orientação");
		ar.add("Informação sobre atendimento");
				
	}


	public ArrayList<String> getAr() {
		return ar;
	}
	
	public void setAr(ArrayList<String> ar) {
		this.ar = ar;
	}
	

	

}
