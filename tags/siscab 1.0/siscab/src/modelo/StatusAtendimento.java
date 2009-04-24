package modelo;

import java.util.ArrayList;

public class StatusAtendimento {
	
	private ArrayList<String> statusAtendimento;
	
	public StatusAtendimento(){
		
		statusAtendimento = new ArrayList<String>();
		statusAtendimento.add("Pendente");
		statusAtendimento.add("Em andamento");
		statusAtendimento.add("Finalizado");
	
	}


	public ArrayList<String> getStatus() {
		return statusAtendimento;
	}
	
	public void setAr(ArrayList<String> status) {
		this.statusAtendimento = status;
	}
}
