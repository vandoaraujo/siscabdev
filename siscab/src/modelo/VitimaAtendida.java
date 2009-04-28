package modelo;

import java.io.Serializable;

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
@Table(name = "vitimas")
public class VitimaAtendida implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    private int idade;

    private char sexo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "atendimento_id", insertable = true, updatable = true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private Atendimento atendimento_id;

    private int cor;
    @Column(name = "situacaofinal")
    private int vitima_situacao;

    private String hospitaldestino;

    public Atendimento getAtendimento() {
	return atendimento_id;
    }

    public int getCor() {
	return cor;
    }

    public String getHospitaldestino() {
	return hospitaldestino;
    }

    public int getId() {
	return id;
    }

    public int getIdade() {
	return idade;
    }

    public String getNome() {
	return nome;
    }

    public char getSexo() {
	return sexo;
    }

    public int getVitima_situacao() {
	return vitima_situacao;
    }

    public void setAtendimento(Atendimento atendimento) {
	this.atendimento_id = atendimento;
    }

    public void setCor(int cor) {
	this.cor = cor;
    }

    public void setHospitaldestino(String hospitaldestino) {
	this.hospitaldestino = hospitaldestino;
    }

    public void setId(int id) {
	this.id = id;
    }

    public void setIdade(int idade) {
	this.idade = idade;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public void setSexo(char sexo) {
	this.sexo = sexo;
    }

    public void setVitima_situacao(int vitima_situacao) {
	this.vitima_situacao = vitima_situacao;
    }

}
