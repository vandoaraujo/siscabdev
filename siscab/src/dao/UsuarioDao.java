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

	public List<Usuario> procurarUsuariosParametro(Integer registro, String nomeGuerra) {

		
		List<Usuario> usu = (List<Usuario>) session.createQuery("from modelo.Usuario u where u.nomeGuerra=:nome and u.numRegistro=:registro")
		.setString("nome", nomeGuerra).setInteger("registro", registro).list();  
	
		
		/*List<Usuario> usuarios =
		(List<Usuario>) session.createQuery("from modelo.Usuario u where u.nome=:nome1 and registro=:registro1").setString("nome1", nomeGuerra).setInteger("registro1", registro).list();  
			
		/*Criteria c = session.createCriteria(Usuario.class);
		c.add(Restrictions.like("nome", nomeGuerra));
		c.add(Restrictions.like("registro", registro));*/
		
		//List<Usuario> usuarios =(List<Usuario>) session.createQuery("Usuario u where u.nome = 'nome' or registro = 'registro'")
		//.setString("nome", "%" + nomeGuerra + "%").setString("registro", "%" + registro + "%");
		
		/*for(Usuario usuario :  (List<Usuario>) c.list()){
			   	usu.add(usuario);
			
		}*/
		
		/*c.setMaxResults(20);
		usu = c.list();*/
		
		return usu;
	}


}


