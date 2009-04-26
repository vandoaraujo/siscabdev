package modelo;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "atendimentos")
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "numero")
    private int atendimento_numero;

    @OneToOne
    @JoinColumn(name = "tipoocorrencia_id")
    private TipoOcorrencia tipoocorrencia;

    @OneToOne
    @JoinColumn(name = "chamado_id")
    private Chamado chamado_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "municipio_id", insertable = true, updatable = true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private Municipio municipio_id;

    private int modofechamento_id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movimentaviatura", schema = "siscab", joinColumns = @JoinColumn(name = "atendimento_id"), inverseJoinColumns = @JoinColumn(name = "viatura_id"))
    private Collection<Viatura> viaturas;

    private String bairro;

    private String logradouro;

    private int numcompl;

    private double coordx;

    private double coordy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "obm_id", insertable = true, updatable = true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private OBM obm_id;

    private String status_atendimento;

    /*
     * • Pendente; • Em andamento; • Finalizado.
     */

    public int getAtendimento_numero() {
	return atendimento_numero;
    }

    public String getBairro() {
	return bairro;
    }

    public Chamado getChamado_id() {
	return chamado_id;
    }

    public double getCoordx() {
	return coordx;
    }

    public double getCoordy() {
	return coordy;
    }

    public int getId() {
	return id;
    }

    public String getLogradouro() {
	return logradouro;
    }

    public int getModofechamento_id() {
	return modofechamento_id;
    }

    public Municipio getMunicipio_id() {
	return municipio_id;
    }

    public int getNumcompl() {
	return numcompl;
    }

    public OBM getObm_id() {
	return obm_id;
    }

    public String getStatus_atendimento() {
	return status_atendimento;
    }

    public TipoOcorrencia getTipoocorrencia() {
	return tipoocorrencia;
    }

    public Collection<Viatura> getViaturas() {
	return viaturas;
    }

    public void setAtendimento_numero(int atendimento_numero) {
	this.atendimento_numero = atendimento_numero;
    }

    public void setBairro(String bairro) {
	this.bairro = bairro;
    }

    public void setChamado_id(Chamado chamado_id) {
	this.chamado_id = chamado_id;
    }

    public void setCoordx(double coordx) {
	this.coordx = coordx;
    }

    public void setCoordy(double coordy) {
	this.coordy = coordy;
    }

    public void setId(int id) {
	this.id = id;
    }

    public void setLogradouro(String logradouro) {
	this.logradouro = logradouro;
    }

    public void setModofechamento_id(int modofechamento_id) {
	this.modofechamento_id = modofechamento_id;
    }

    public void setMunicipio_id(Municipio municipio_id) {
	this.municipio_id = municipio_id;
    }

    public void setNumcompl(int numcompl) {
	this.numcompl = numcompl;
    }

    public void setObm_id(OBM obm_id) {
	this.obm_id = obm_id;
    }

    public void setStatus_atendimento(String status_atendimento) {
	this.status_atendimento = status_atendimento;
    }

    public void setTipoocorrencia(TipoOcorrencia tipoocorrencia) {
	this.tipoocorrencia = tipoocorrencia;
    }

    public void setViaturas(Collection<Viatura> viaturas) {
	this.viaturas = viaturas;
    }

}
