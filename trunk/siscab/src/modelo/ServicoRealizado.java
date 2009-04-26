package modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "servicos")
public class ServicoRealizado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tiposervico_id", insertable = true, updatable = true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private TipoServico tiposervico_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "atendimento_id", insertable = true, updatable = true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private Atendimento atendimento_id;

    public Atendimento getAtendimentos() {
	return atendimento_id;
    }

    public int getId() {
	return id;
    }

    public TipoServico getTiposervico() {
	return tiposervico_id;
    }

    public void setAtendimentos(Atendimento atendimentos) {
	this.atendimento_id = atendimentos;
    }

    public void setId(int id) {
	this.id = id;
    }

    public void setTiposervico(TipoServico tiposervico) {
	this.tiposervico_id = tiposervico;
    }

}
