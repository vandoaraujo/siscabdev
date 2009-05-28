package modelo;

import java.io.Serializable;

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
@Table(name = "usuario")
public class Usuario implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "numregistro")
    private int numRegistro;
    @Column(name = "nomeguerra")
    private String nomeGuerra;
    private String email;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "obm_id", insertable = true, updatable = true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private OBM obm;

    @OneToOne
    @JoinColumn(name = "perfil_id")
    private PerfilUsuario perfil;
    private String senha;
    @Column(name = "status_usuario")
    private String statusUsu;
    @Transient
    private String statusConexao;

    public String getEmail() {
	return email;
    }

    public int getId() {
	return id;
    }

    public String getNomeGuerra() {
	return nomeGuerra;
    }

    public int getNumRegistro() {
	return numRegistro;
    }

    public OBM getObm() {
	return obm;
    }

    public PerfilUsuario getPerfil() {
	return perfil;
    }

    public String getSenha() {
	return senha;
    }

    public String getStatus() {
	return statusUsu;
    }

    public String getStatusConexao() {
	return statusConexao;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public void setId(int id) {
	this.id = id;
    }

    public void setNomeGuerra(String nomeGuerra) {
	this.nomeGuerra = nomeGuerra;
    }

    public void setNumRegistro(int numRegistro) {
	this.numRegistro = numRegistro;
    }

    public void setObm(OBM obm) {
	this.obm = obm;
    }

    public void setPerfil(PerfilUsuario perfil) {
	this.perfil = perfil;
    }

    public void setSenha(String senha) {
	this.senha = senha;
    }

    public void setStatus(String status) {
	this.statusUsu = status;
    }

    public void setStatusConexao(String statusConexao) {
	this.statusConexao = statusConexao;
    }

}
