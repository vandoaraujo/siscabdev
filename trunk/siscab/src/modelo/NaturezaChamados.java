package modelo;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="naturezaschamados")
public class NaturezaChamados {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String naturezachamado_descricao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaturezachamado_descricao() {
		return naturezachamado_descricao;
	}

	public void setNaturezachamado_descricao(String naturezachamado_descricao) {
		this.naturezachamado_descricao = naturezachamado_descricao;
	}
	
	
	

}
