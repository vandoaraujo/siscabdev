package modelo;

import java.util.ArrayList;

public class NaturezaChamados {
	
	private ArrayList<String> ar;
		
	public NaturezaChamados(){
		
		ar = new ArrayList<String>();
		ar.add("Solicita��o de socorro");
		ar.add("Trote");
		ar.add("Engano/Desist�ncia");
		ar.add("Queda de liga��o");
		ar.add("Orienta��o");
		ar.add("Informa��o sobre atendimento");
				
	}


	public ArrayList<String> getAr() {
		return ar;
	}
	
	public void setAr(ArrayList<String> ar) {
		this.ar = ar;
	}
	

	

}
