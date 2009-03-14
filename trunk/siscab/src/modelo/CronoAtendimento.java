package modelo;

import java.util.Date;

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
@Table(name="cronoatendimento")
public class CronoAtendimento {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		private String cronoatendimento_tipoevento;
		
		private Date cronoatendimento_horaevento;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "atendimento_id", insertable=true, updatable =true)
		@Fetch(FetchMode.JOIN)
		@Cascade(CascadeType.SAVE_UPDATE)
		private Atendimentos atendimento_id;
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		public String getCronoatendimento_tipoevento() {
			return cronoatendimento_tipoevento;
		}

		public void setCronoatendimento_tipoevento(String cronoatendimento_tipoevento) {
			this.cronoatendimento_tipoevento = cronoatendimento_tipoevento;
		}

		public Date getCronoatendimento_horaevento() {
			return cronoatendimento_horaevento;
		}

		public void setCronoatendimento_horaevento(Date cronoatendimento_horaevento) {
			this.cronoatendimento_horaevento = cronoatendimento_horaevento;
		}

		public Atendimentos getAtendimento_id() {
			return atendimento_id;
		}

		public void setAtendimento_id(Atendimentos atendimento_id) {
			this.atendimento_id = atendimento_id;
		}

		
}
		
	
		
