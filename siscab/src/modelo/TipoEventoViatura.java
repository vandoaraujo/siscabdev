package modelo;

import java.util.ArrayList;

public class TipoEventoViatura {

    private ArrayList<String> tiposEventos;

    public TipoEventoViatura() {

	tiposEventos = new ArrayList<String>();
	tiposEventos.add("Sa�da da OBM");
	tiposEventos.add("Chegada � Cena");
	tiposEventos.add("Sa�da da Cena");
	tiposEventos.add("Chegada ao Hospital");
	tiposEventos.add("Sa�da do Hospital");
	tiposEventos.add("Retorno � OBM");

    }

    public ArrayList<String> getTiposEventos() {
	return tiposEventos;
    }

    public void setTiposEventos(ArrayList<String> tiposEventos) {
	this.tiposEventos = tiposEventos;
    }

}
