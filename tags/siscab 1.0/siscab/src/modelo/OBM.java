package modelo;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="obm")
public class OBM {
			
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;

		private String nome;
		
		/*@OneToMany(mappedBy="obm",fetch = FetchType.EAGER)
		@Cascade(CascadeType.DELETE_ORPHAN)
		private Collection<Usuario> usuarios;
		*/
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "municipio_id", insertable=true, updatable =true)
		@Fetch(FetchMode.JOIN)
		@Cascade(CascadeType.SAVE_UPDATE)
		private Municipio municipio;
		
		private String bairro;
		
		private String logradouro;
		
		private String numCompl;
		@Column(name="coordx")
		private double coordX;
		@Column(name="coordy")
		private double coordY;
		@Column(name="status_obm")
		private int statusObm;
		
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

		/*public Collection<Usuario> getUsuarios() {
			return usuarios;
		}

		public void setUsuarios(Collection<Usuario> usuarios) {
			this.usuarios = usuarios;
		}*/

		public Municipio getMunicipio() {
			return municipio;
		}

		public void setMunicipio(Municipio municipio) {
			this.municipio = municipio;
		}

		public String getBairro() {
			return bairro;
		}

		public void setBairro(String bairro) {
			this.bairro = bairro;
		}

		public String getLogradouro() {
			return logradouro;
		}

		public void setLogradouro(String logradouro) {
			this.logradouro = logradouro;
		}

		public String getNumCompl() {
			return numCompl;
		}

		public void setNumCompl(String numCompl) {
			this.numCompl = numCompl;
		}

		public double getCoordX() {
			return coordX;
		}

		public void setCoordX(double coordX) {
			this.coordX = coordX;
		}

		public double getCoordY() {
			return coordY;
		}

		public void setCoordY(double coordY) {
			this.coordY = coordY;
		}

		public int getStatus() {
			return statusObm;
		}

		public void setStatus(int status) {
			this.statusObm = status;
		}

			
		
	}


