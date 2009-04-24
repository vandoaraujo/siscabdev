package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="numregistro")
	private int numRegistro;
	@Column(name="nomeguerra")
	private String nomeGuerra;
	private String email;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "obm_id", insertable=true, updatable =true)
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private OBM obm;
	
	@OneToOne
	@JoinColumn(name="perfil_id")
	private PerfilUsuario perfil;
	private String senha;
	@Column(name="status_usuario")
	private String statusUsu;
	@Transient
	private String statusConexao;
		
	public PerfilUsuario getPerfil() {
		return perfil;
	}
	
	public void setPerfil(PerfilUsuario perfil){
		this.perfil=perfil;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumRegistro() {
		return numRegistro;
	}
	public void setNumRegistro(int numRegistro) {
		this.numRegistro = numRegistro;
	}
	public String getNomeGuerra() {
		return nomeGuerra;
	}
	public void setNomeGuerra(String nomeGuerra) {
		this.nomeGuerra = nomeGuerra;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public OBM getObm() {
		return obm;
	}
	public void setObm(OBM obm) {
		this.obm = obm;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getStatus() {
		return statusUsu;
	}
	public void setStatus(String status) {
		this.statusUsu = status;
	}
	public String getStatusConexao() {
		return statusConexao;
	}
	public void setStatusConexao(String statusConexao) {
		this.statusConexao = statusConexao;
	}
	
	
	

	


}
