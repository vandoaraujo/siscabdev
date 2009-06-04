package modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tiposviaturas")
public class TipoViatura implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tipoviatura_abreviacao;

    private String tipoviatura_descricao;

    public int getId() {
	return id;
    }

    public String getTipoviatura_abreviacao() {
	return tipoviatura_abreviacao;
    }

    public String getTipoviatura_descricao() {
	return tipoviatura_descricao;
    }

    public void setId(int id) {
	this.id = id;
    }

    public void setTipoviatura_abreviacao(String tipoviatura_abreviacao) {
	this.tipoviatura_abreviacao = tipoviatura_abreviacao;
    }

    public void setTipoviatura_descricao(String tipoviatura_descricao) {
	this.tipoviatura_descricao = tipoviatura_descricao;
    }

}
