package modelo;

import java.util.ArrayList;

public class TipoEventoViatura {

    private ArrayList<String> tiposEventos;

    public TipoEventoViatura() {

	tiposEventos = new ArrayList<String>();
	tiposEventos.add("Saída da OBM");
	tiposEventos.add("Chegada à Cena");
	tiposEventos.add("Saída da Cena");
	tiposEventos.add("Chegada ao Hospital");
	tiposEventos.add("Saída do Hospital");
	tiposEventos.add("Retorno à OBM");

    }

    public ArrayList<String> getTiposEventos() {
	return tiposEventos;
    }

    public void setTiposEventos(ArrayList<String> tiposEventos) {
	this.tiposEventos = tiposEventos;
    }

}
