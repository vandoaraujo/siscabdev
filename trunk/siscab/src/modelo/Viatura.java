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
@Table(name="viaturas")
public class Viatura {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		@Column
		private String tipo_viatura;
		@Column
		private String numero;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "obm_id", insertable=true, updatable =true)
		@Fetch(FetchMode.JOIN)
		@Cascade(CascadeType.SAVE_UPDATE)
		private OBM obm;
		
		private String viatura_obs;
		
		
		// 
		private String viatura_status;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTipo_viatura() {
			return tipo_viatura;
		}

		public void setTipo_viatura(String tipo_viatura) {
			this.tipo_viatura = tipo_viatura;
		}

		public String getNumero() {
			return numero;
		}

		public void setNumero(String numero) {
			this.numero = numero;
		}

		public OBM getObm() {
			return obm;
		}

		public void setObm(OBM obm) {
			this.obm = obm;
		}

		public String getViatura_obs() {
			return viatura_obs;
		}

		public void setViatura_obs(String viatura_obs) {
			this.viatura_obs = viatura_obs;
		}

		public String getViatura_status() {
			return viatura_status;
		}

		public void setViatura_status(String viatura_status) {
			this.viatura_status = viatura_status;
		}
		
		
	

}
