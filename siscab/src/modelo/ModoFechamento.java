package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "modosfechamento")
public class ModoFechamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "modofechamento_descricao")
    private String descricao;

    public String getDescricao() {
	return descricao;
    }

    public int getId() {
	return id;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public void setId(int id) {
	this.id = id;
    }

}
