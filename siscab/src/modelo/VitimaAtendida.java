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
		private Atendimentos atendimento_id;
		
		private int cor;
		@Column(name="situacaofinal")
		private int vitima_situacao;
		
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
			return atendimento_id;
		}

		public void setAtendimento(Atendimentos atendimento) {
			this.atendimento_id = atendimento;
		}

		public int getCor() {
			return cor;
		}

		public void setCor(int cor) {
			this.cor = cor;
		}

		public int getVitima_situacao() {
			return vitima_situacao;
		}

		public void setVitima_situacao(int vitima_situacao) {
			this.vitima_situacao = vitima_situacao;
		}

		public String getHospitaldestino() {
			return hospitaldestino;
		}

		public void setHospitaldestino(String hospitaldestino) {
			this.hospitaldestino = hospitaldestino;
		}
		
}
		
	
		
