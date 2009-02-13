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
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	@Column
	private int numRegistro;
	@Column
	private String nomeGuerra;
	@Column
	private String email;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "obm_id", insertable=true, updatable =true)
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private OBM obm;
	@Column
	private String perfil;
	@Column
	private String senha;
	@Transient
	private String statusUsu;
	@Transient
	private String statusConexao;
	
	
	/*public enum PerfilUsuario{
		ADMIN(1,"Administrador do Sistema"), ATENDENTE(2,"Atendente do COCB")
		,OPERADOR(3,"Operador da OBM"),CONTROLADOR(4,"Controlador da OBM"),
		COMANDANTE(5,"Comandante");
		
		private int id;
		private String nome;
		
		public String getNome(){return this.nome;}
		
		PerfilUsuario(int id, String nome)
		{
			this.id =id;
			this.nome=nome;
		}
		
	}
	
	
	private PerfilUsuario perfilEnum;
	public PerfilUsuario getPerfilEnum(){return this.perfilEnum;}
	public void setDay(PerfilUsuario perfilEnum) {this.perfilEnum = perfilEnum;}
	
	public void itera(){
		
		for(PerfilUsuario perfil : PerfilUsuario.values()){
			System.out.println(perfil.getNome());
			
		}
	}
	
	*/

	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
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
