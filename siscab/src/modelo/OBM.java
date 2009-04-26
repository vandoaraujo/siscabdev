package modelo;

import javax.persistence.Column;
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
@Table(name = "obm")
public class OBM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    /*
     * @OneToMany(mappedBy="obm",fetch = FetchType.EAGER)
     * 
     * @Cascade(CascadeType.DELETE_ORPHAN) private Collection<Usuario> usuarios;
     */

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "municipio_id", insertable = true, updatable = true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private Municipio municipio;

    private String bairro;

    private String logradouro;

    private String numCompl;
    @Column(name = "coordx")
    private double coordX;
    @Column(name = "coordy")
    private double coordY;
    @Column(name = "status_obm")
    private int statusObm;

    public String getBairro() {
	return bairro;
    }

    public double getCoordX() {
	return coordX;
    }

    public double getCoordY() {
	return coordY;
    }

    public int getId() {
	return id;
    }

    /*
     * public Collection<Usuario> getUsuarios() { return usuarios; }
     * 
     * public void setUsuarios(Collection<Usuario> usuarios) { this.usuarios =
     * usuarios; }
     */

    public String getLogradouro() {
	return logradouro;
    }

    public Municipio getMunicipio() {
	return municipio;
    }

    public String getNome() {
	return nome;
    }

    public String getNumCompl() {
	return numCompl;
    }

    public int getStatus() {
	return statusObm;
    }

    public void setBairro(String bairro) {
	this.bairro = bairro;
    }

    public void setCoordX(double coordX) {
	this.coordX = coordX;
    }

    public void setCoordY(double coordY) {
	this.coordY = coordY;
    }

    public void setId(int id) {
	this.id = id;
    }

    public void setLogradouro(String logradouro) {
	this.logradouro = logradouro;
    }

    public void setMunicipio(Municipio municipio) {
	this.municipio = municipio;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public void setNumCompl(String numCompl) {
	this.numCompl = numCompl;
    }

    public void setStatus(int status) {
	this.statusObm = status;
    }

}
