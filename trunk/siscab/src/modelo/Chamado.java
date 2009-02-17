package modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


public class Chamado {
	
	
	private String naturezaChamado;
	private String origem;
	private String nomeSolicitante;
	private String telefoneSolicitante;
	private int numAproxVitimas;
	private String infoComplementares;
	@OneToOne
	@JoinColumn(name="obm_id")
	private OBM obm;
	private Date horaTermino;
	private Date horainicio;
	
	public String getNaturezaChamado() {
		return naturezaChamado;
	}
	public void setNaturezaChamado(String naturezaChamado) {
		this.naturezaChamado = naturezaChamado;
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public String getNomeSolicitante() {
		return nomeSolicitante;
	}
	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}
	public String getTelefoneSolicitante() {
		return telefoneSolicitante;
	}
	public void setTelefoneSolicitante(String telefoneSolicitante) {
		this.telefoneSolicitante = telefoneSolicitante;
	}
	public int getNumAproxVitimas() {
		return numAproxVitimas;
	}
	public void setNumAproxVitimas(int numAproxVitimas) {
		this.numAproxVitimas = numAproxVitimas;
	}
	public String getInfoComplementares() {
		return infoComplementares;
	}
	public void setInfoComplementares(String infoComplementares) {
		this.infoComplementares = infoComplementares;
	}
	public OBM getObm() {
		return obm;
	}
	public void setObm(OBM obm) {
		this.obm = obm;
	}
	public Date getHoraTermino() {
		return horaTermino;
	}
	public void setHoraTermino(Date horaTermino) {
		this.horaTermino = horaTermino;
	}
	public Date getHorainicio() {
		return horainicio;
	}
	public void setHorainicio(Date horainicio) {
		this.horainicio = horainicio;
	}
	
	

}
