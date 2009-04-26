package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "municipios")
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String municipio_nome;

    public int getId() {
	return id;
    }

    public String getMunicipio_nome() {
	return municipio_nome;
    }

    public void setId(int id) {
	this.id = id;
    }

    public void setMunicipio_nome(String municipio_nome) {
	this.municipio_nome = municipio_nome;
    }

}
