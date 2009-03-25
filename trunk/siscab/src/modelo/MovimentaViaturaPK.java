package modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class MovimentaViaturaPK implements Serializable{
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="atendimento_id")
	private Atendimento atendimento_id;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "viatura_id")
	private Viatura viatura_id;


	public Atendimento getAtendimentos() {
		return atendimento_id;
	}


	public void setAtendimentos(Atendimento atendimentos) {
		this.atendimento_id = atendimentos;
	}


	public Viatura getViatura() {
		return viatura_id;
	}


	public void setViatura(Viatura viatura) {
		this.viatura_id = viatura;
	}
	
	

}
