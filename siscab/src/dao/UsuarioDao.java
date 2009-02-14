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
	
	
	public static UsuarioDao getInstance(){
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
	
	public Usuario buscarUsuario(String nomeGuerra, String senha){
		
		Transaction tx = session.beginTransaction();
		Usuario usu = (Usuario) session.createQuery("from modelo.Usuario u where u.nomeGuerra=:login and u.senha=:senha").setString("login", nomeGuerra).setString("senha", senha).uniqueResult();  
		return usu;
	}
	
	public Usuario listarUsuariosNome(String nome){
		
		Transaction tx = session.beginTransaction();  
		Usuario usu = (Usuario) session.createQuery("from modelo.Usuario u where u.nome=:nome").setString("nome", nome).uniqueResult();  
		tx.commit();
		
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

	

}


