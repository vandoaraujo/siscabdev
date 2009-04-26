package modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
@Table(name = "chamados")
public class Chamado {

    public static Date getDataHoraInicioChamado() {
	return data;

    }

    public static void setDataHoraInicioChamado() {

	GregorianCalendar calendar = new GregorianCalendar();
	calendar.add(Calendar.MONTH, 0);
	calendar.add(Calendar.HOUR_OF_DAY, 0);
	calendar.add(Calendar.MINUTE, 0);
	DateFormat formata = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	data = new Date(formata.format(calendar.getTime()));

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "naturezachamado_id")
    private NaturezaChamado naturezachamado;

    private String origem;

    private String nomesolicitante;

    private String telefoneSolicitante;

    private int numaproxvitimas;

    private String infocomplementares;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "obm_id", insertable = true, updatable = true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private OBM obm;

    private Date horatermino;

    private Date horainicio;
    static Date data;

    public Date getHorainicio() {
	return horainicio;
    }

    public Date getHoratermino() {
	return horatermino;
    }

    public int getId() {
	return id;
    }

    public String getInfocomplementares() {
	return infocomplementares;
    }

    public NaturezaChamado getNaturezaChamado() {
	return naturezachamado;
    }

    public String getNomeSolicitante() {
	return nomesolicitante;
    }

    public int getNumaproxvitimas() {
	return numaproxvitimas;
    }

    public OBM getObm() {
	return obm;
    }

    public String getOrigem() {
	return origem;
    }

    public String getTelefoneSolicitante() {
	return telefoneSolicitante;
    }

    public void setHorainicio(Date horainicio) {
	this.horainicio = horainicio;
    }

    public void setHoratermino(Date horatermino) {
	this.horatermino = horatermino;
    }

    public void setId(int id) {
	this.id = id;
    }

    public void setInfocomplementares(String infocomplementares) {
	this.infocomplementares = infocomplementares;
    }

    public void setNaturezaChamado(NaturezaChamado naturezaChamado) {
	this.naturezachamado = naturezaChamado;
    }

    public void setNomeSolicitante(String nomeSolicitante) {
	this.nomesolicitante = nomeSolicitante;
    }

    public void setNumaproxvitimas(int numaproxvitimas) {
	this.numaproxvitimas = numaproxvitimas;
    }

    public void setObm(OBM obm) {
	this.obm = obm;
    }

    public void setOrigem(String origem) {
	this.origem = origem;
    }

    public void setTelefoneSolicitante(String telefoneSolicitante) {
	this.telefoneSolicitante = telefoneSolicitante;
    }

}
