package modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="atendimentos")
public class Atendimentos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int atendimento_numero;
	
	@OneToOne
	@JoinColumn(name="tipoocorrencia_id")
	private TipoViatura tipoocorrencia;
	
	@OneToOne
	@JoinColumn(name="chamado_id")
	private Chamado chamado_id;
		
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "municipio_id", insertable=true, updatable =true)
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Municipio municipio_id;
	
	private String bairro;
	
	private String logradouro;
	
	private int numcompl;
	
	private float coordx;
	
	private float coordy;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "obm_id", insertable=true, updatable =true)
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private OBM obm_id;
	
	private String status_atendimento;
	
	/*•	Pendente;
	•	Em andamento;
	•	Finalizado.*/


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAtendimento_numero() {
		return atendimento_numero;
	}

	public void setAtendimento_numero(int atendimento_numero) {
		this.atendimento_numero = atendimento_numero;
	}

	public String getTipoocorrencia() {
		return tipoocorrencia;
	}

	public void setTipoocorrencia(String tipoocorrencia) {
		this.tipoocorrencia = tipoocorrencia;
	}

	public Chamado getChamado_id() {
		return chamado_id;
	}

	public void setChamado_id(Chamado chamado_id) {
		this.chamado_id = chamado_id;
	}

	public Municipio getMunicipio_id() {
		return municipio_id;
	}

	public void setMunicipio_id(Municipio municipio_id) {
		this.municipio_id = municipio_id;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumcompl() {
		return numcompl;
	}

	public void setNumcompl(int numcompl) {
		this.numcompl = numcompl;
	}

	public float getCoordx() {
		return coordx;
	}

	public void setCoordx(float coordx) {
		this.coordx = coordx;
	}

	public float getCoordy() {
		return coordy;
	}

	public void setCoordy(float coordy) {
		this.coordy = coordy;
	}

	public OBM getObm_id() {
		return obm_id;
	}

	public void setObm_id(OBM obm_id) {
		this.obm_id = obm_id;
	}

	public String getStatus_atendimento() {
		return status_atendimento;
	}

	public void setStatus_atendimento(String status_atendimento) {
		this.status_atendimento = status_atendimento;
	}
	
	
}
