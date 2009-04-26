package modelo;

import java.io.Serializable;
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
@Table(name = "viaturas")
public class Viatura implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "tipoviatura_id")
    private TipoViatura tipo_viatura;
    @Column
    private String numero;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "obm_id", insertable = true, updatable = true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private OBM obm_id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movimentaviatura", schema = "siscab", joinColumns = @JoinColumn(name = "viatura_id"), inverseJoinColumns = @JoinColumn(name = "atendimento_id"))
    private Collection<Atendimento> atendimentos;
    @Column(name = "obs")
    private String viatura_obs;
    @Column(name = "status_viatura")
    private String viatura_status;

    public Collection<Atendimento> getAtendimentos() {
	return atendimentos;
    }

    public int getId() {
	return id;
    }

    public String getNumero() {
	return numero;
    }

    public OBM getObm() {
	return obm_id;
    }

    public TipoViatura getTipo_viatura() {
	return tipo_viatura;
    }

    public String getViatura_obs() {
	return viatura_obs;
    }

    public String getViatura_status() {
	return viatura_status;
    }

    public void setAtendimentos(Collection<Atendimento> atendimentos) {
	this.atendimentos = atendimentos;
    }

    public void setId(int id) {
	this.id = id;
    }

    public void setNumero(String numero) {
	this.numero = numero;
    }

    public void setObm(OBM obm) {
	this.obm_id = obm;
    }

    public void setTipo_viatura(TipoViatura tipo_viatura) {
	this.tipo_viatura = tipo_viatura;
    }

    public void setViatura_obs(String viatura_obs) {
	this.viatura_obs = viatura_obs;
    }

    public void setViatura_status(String viatura_status) {
	this.viatura_status = viatura_status;
    }

}
