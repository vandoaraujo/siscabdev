package modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tiposocorrencias")
public class TipoOcorrencia implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tipoocorrencia_descricao;

    public int getId() {
	return id;
    }

    public String getTipoocorrencia_descricao() {
	return tipoocorrencia_descricao;
    }

    public void setId(int id) {
	this.id = id;
    }

    public void setTipoocorrencia_descricao(String tipoocorrencia_descricao) {
	this.tipoocorrencia_descricao = tipoocorrencia_descricao;
    }

}
