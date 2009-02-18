package dao;

import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UsuarioDao {
	
	private static Session session;
	// Singleton
	private static UsuarioDao singleton = null;
	
	private static Usuario usuarioLogado;
	
	
	public static UsuarioDao getInstance(){
		//Verificar sessoes...
		//if(singleton == null)
		
		singleton = new UsuarioDao();
		return singleton;
		
	}
	
	private UsuarioDao(){
		session = HibernateUtil.getInstance().AbreUmaSession();
	}
	
	public void fechaSession(){
		session.close();
	}
		
	public void salvar(Usuario usuario){
		
		Transaction t = session.beginTransaction();
		session.save(usuario);
		t.commit();
		session.flush();
		session.close();
		
		
	}
	
	public void deletar(Usuario usuario) {
		
		Transaction t = session.beginTransaction();
		session.delete(usuario);
		t.commit();
		session.flush();
		session.close();
		System.out.println("DELETADO");
	}
	
	public void atualizar(Usuario ator) {
		
		Transaction t = session.beginTransaction();
		session.update(ator);
		t.commit();
		session.flush();
		session.close();
		System.out.println("ATUALIZADO");
		
	}

	public List<Usuario> listar(){
		List<Usuario> l = session.createQuery("from modelo.Usuario").list();
		return l;
		
	}
	
	public Usuario buscarUsuario(int numRegistro, String senha){
		
		Usuario usu = (Usuario) session.createQuery("from modelo.Usuario u where u.numRegistro=:login and u.senha=:senha").setInteger("login", numRegistro).setString("senha", senha).uniqueResult();
		usuarioLogado=usu;
		return usu;
	}
	
	public Usuario listarUsuariosNome(String nome){
		
		Usuario usu = (Usuario) session.createQuery("from modelo.Usuario u where u.nome=:nome").setString("nome", nome).uniqueResult();  
		return usu;		
		
	}
	
	public Usuario listarUmUsuarioNome(String nome){
				
		Criteria c = session.createCriteria(Usuario.class);
		c.add(Restrictions.like("nome", nome));
		
		Usuario p = (Usuario) c.setMaxResults(1);
		return p;		
		
	}

	@Deprecated
	public List<Usuario> procurarUsuariosParametro(Integer registro, String nomeGuerra) {
	
		List<Usuario> usu = (List<Usuario>) session.createQuery("from modelo.Usuario u where u.nomeGuerra=:nome or u.numRegistro=:registro")
		.setString("nome", '%'+nomeGuerra+'%').setInteger("registro",'%'+registro+'%').list();  
		return usu;
	}
	@Deprecated
	public List<Usuario> procurarUsuariosParametro1(Integer registro, String nomeGuerra) {
		
		Criteria c = session.createCriteria(Usuario.class);
		c.add(Restrictions.or(Restrictions.like("nomeGuerra", '%'+ nomeGuerra + '%'),Restrictions.like("numRegistro", '%'+registro+'%')));
		
		List<Usuario> usu = (List<Usuario>)c.list();
		return usu;
		
	}
	
	public List<Usuario> procurarUsuariosParametro2(Integer registro, String nomeGuerra) {
					
		List<Usuario> usuario = session.createCriteria(Usuario.class).add(Restrictions.sqlRestriction("numRegistro like '" +registro+ "%'")).list();
		return usuario;
	}

	public Usuario BuscaUsuarioId(Integer id) {
		
		Usuario u = (Usuario) session.load(Usuario.class, id);
		return u;
	}

	public static Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	

}


