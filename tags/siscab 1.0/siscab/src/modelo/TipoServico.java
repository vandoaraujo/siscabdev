package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tiposservicos")
public class TipoServico {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		private String tiposervico_descricao;


		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTiposervico_descricao() {
			return tiposervico_descricao;
		}

		public void setTiposervico_descricao(String tiposervico_descricao) {
			this.tiposervico_descricao = tiposervico_descricao;
		}

		
		
	
	
	
}
