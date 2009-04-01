package modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="chamados")
public class Chamado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name="naturezachamado_id")
	private NaturezaChamado naturezachamado;
	
	private String origem;
	
	private String nomesolicitante;
	
	private String telefoneSolicitante;
	
	private int numaproxvitimas;
	
	private String infocomplementares;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "obm_id", insertable=true, updatable =true)
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private OBM obm;
	
	private Date horatermino;
	
	private Date horainicio;
		
	static Date data;
	
	public NaturezaChamado getNaturezaChamado() {
		return naturezachamado;
	}
	public void setNaturezaChamado(NaturezaChamado naturezaChamado) {
		this.naturezachamado = naturezaChamado;
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public String getNomeSolicitante() {
		return nomesolicitante;
	}
	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomesolicitante = nomeSolicitante;
	}
	public String getTelefoneSolicitante() {
		return telefoneSolicitante;
	}
	public void setTelefoneSolicitante(String telefoneSolicitante) {
		this.telefoneSolicitante = telefoneSolicitante;
	}

	public OBM getObm() {
		return obm;
	}
	
	public void setObm(OBM obm) {
		this.obm = obm;
	}
	public Date getHorainicio() {
		return horainicio;
	}
	public void setHorainicio(Date horainicio) {
		this.horainicio = horainicio;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumaproxvitimas() {
		return numaproxvitimas;
	}
	public void setNumaproxvitimas(int numaproxvitimas) {
		this.numaproxvitimas = numaproxvitimas;
	}
	public String getInfocomplementares() {
		return infocomplementares;
	}
	public void setInfocomplementares(String infocomplementares) {
		this.infocomplementares = infocomplementares;
	}
	public Date getHoratermino() {
		return horatermino;
	}
	public void setHoratermino(Date horatermino) {
		this.horatermino = horatermino;
	}
	
	public static void retornaDataInicialChamado(){
		
		GregorianCalendar calendar =  new GregorianCalendar();
		calendar.add(GregorianCalendar.MONTH, 0);
		calendar.add(GregorianCalendar.HOUR_OF_DAY, 0);
		calendar.add(GregorianCalendar.MINUTE, 0);
		DateFormat formata = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		data = new Date(formata.format(calendar.getTime()));
		
	}
	
	public static Date getHoraInicioChamado(){
		return data;
		
	}
	
	

}
