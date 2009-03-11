package modelo;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movimentaviatura", schema="siscab")
public class MovimentaViatura {
	

	@EmbeddedId
	private MovimentaViaturaPK chaveComposta;
		
	private String movimentaviatura_tipoevento;
	
	private Date movimentaviatura_horaEvento;

	public String getMovimentaviatura_tipoevento() {
		return movimentaviatura_tipoevento;
	}

	public void setMovimentaviatura_tipoevento(String movimentaviatura_tipoevento) {
		this.movimentaviatura_tipoevento = movimentaviatura_tipoevento;
	}

	public Date getMovimentaviatura_horaEvento() {
		return movimentaviatura_horaEvento;
	}

	public void setMovimentaviatura_horaEvento(Date movimentaviatura_horaEvento) {
		this.movimentaviatura_horaEvento = movimentaviatura_horaEvento;
	}

	public MovimentaViaturaPK getChaveComposta() {
		return chaveComposta;
	}

	public void setChaveComposta(MovimentaViaturaPK chaveComposta) {
		this.chaveComposta = chaveComposta;
	}
	
	

}