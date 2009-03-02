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
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="vitimas")
public class VitimaAtendida {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		private String nome;
		
		private int idade;
		
		private char sexo;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "atendimento_id", insertable=true, updatable =true)
		@Fetch(FetchMode.JOIN)
		@Cascade(CascadeType.SAVE_UPDATE)
		private Atendimentos atendimento;
		
		private String cor;
		
		private String vitima_situacao;
		
		private String hospitaldestino;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public int getIdade() {
			return idade;
		}

		public void setIdade(int idade) {
			this.idade = idade;
		}

		public char getSexo() {
			return sexo;
		}

		public void setSexo(char sexo) {
			this.sexo = sexo;
		}

		public Atendimentos getAtendimento() {
			return atendimento;
		}

		public void setAtendimento(Atendimentos atendimento) {
			this.atendimento = atendimento;
		}

		public String getCor() {
			return cor;
		}

		public void setCor(String cor) {
			this.cor = cor;
		}

		public String getVitima_situacao() {
			return vitima_situacao;
		}

		public void setVitima_situacao(String vitima_situacao) {
			this.vitima_situacao = vitima_situacao;
		}

		public String getHospitaldestino() {
			return hospitaldestino;
		}

		public void setHospitaldestino(String hospitaldestino) {
			this.hospitaldestino = hospitaldestino;
		}
		
}
		
	
		
