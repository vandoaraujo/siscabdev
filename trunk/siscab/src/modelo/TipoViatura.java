package modelo;

import java.util.ArrayList;

public class TipoViatura {
		
private ArrayList<String> tipoViatura;
	
	public TipoViatura(){
		
		tipoViatura = new ArrayList<String>();
		tipoViatura.add("Auto Bomba Tanque");
		tipoViatura.add("Auto Busca e Salvamento");
		tipoViatura.add("Auto Busca e Salvamento Leve");
		tipoViatura.add("Auto Socorro de Emerg�ncia (ambul�ncia)");
		tipoViatura.add("Auto Plataforma Mec�nica");
		tipoViatura.add("Auto Produtos Perigosos");
	
	}


	public ArrayList<String> getTiposViaturas() {
		return tipoViatura;
	}
	
	public void setAr(ArrayList<String> viatura) {
		this.tipoViatura = viatura;
	}
	
	
}

