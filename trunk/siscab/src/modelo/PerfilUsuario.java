package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "perfis")
public class PerfilUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String perfil_descricao;

    public int getId() {
	return id;
    }

    public String getPerfil_descricao() {
	return perfil_descricao;
    }

    public void setId(int id) {
	this.id = id;
    }

    public void setPerfil_descricao(String perfil_descricao) {
	this.perfil_descricao = perfil_descricao;
    }

}
