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
	private Atendimentos atendimentos;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "viatura_id")
	private Viatura viatura;


	public Atendimentos getAtendimentos() {
		return atendimentos;
	}


	public void setAtendimentos(Atendimentos atendimentos) {
		this.atendimentos = atendimentos;
	}


	public Viatura getViatura() {
		return viatura;
	}


	public void setViatura(Viatura viatura) {
		this.viatura = viatura;
	}
	
	

}
