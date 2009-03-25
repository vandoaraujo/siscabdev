package dao;

import java.util.List;

import modelo.Atendimento;
import modelo.OBM;
import modelo.PerfilUsuario;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class PerfilUsuarioDao {
	
	
	private static Session session;
	// Singleton
	private static PerfilUsuarioDao singleton = null;
	
	
	public static PerfilUsuarioDao getInstance(){
		singleton = new PerfilUsuarioDao();
		return singleton;
		
	}
	
	private PerfilUsuarioDao(){
		session = HibernateUtil.getInstance().AbreUmaSession();
	}
	
	public void fechaSession(){
		session.close();
	}
		
	
	public void salvar(PerfilUsuario perfil){
		
		Transaction t = session.beginTransaction();
		session.save(perfil);
		t.commit();
		session.flush();
		session.close();
		
	}
		
	public void deletar(PerfilUsuario perfil) {
		
		Transaction t = session.beginTransaction();
		session.delete(perfil);
		t.commit();
		session.flush();
		session.close();
		System.out.println("DELETADO");
	}
	
	public void atualizar(PerfilUsuario perfil) {
		
		Transaction t = session.beginTransaction();
		session.update(perfil);
		t.commit();
		session.flush();
		session.close();
		System.out.println("ATUALIZADO");
		
	}


	public List<PerfilUsuario> listarTodosPerfis(){
		List<PerfilUsuario> perfil = session.createQuery("from modelo.PerfilUsuario").list();
		return perfil;
		
	}
	
	public PerfilUsuario listarPerfilNome(String nome){
		
		PerfilUsuario perfil = (PerfilUsuario) session.createQuery("from modelo.PerfilUsuario p where p.perfil_descricao=:nome").setString("nome", nome).uniqueResult();  
		return perfil;		
		
	}
		
	public PerfilUsuario BuscaPerfilId(Integer id) {
		
		PerfilUsuario perfil = (PerfilUsuario) session.load(PerfilUsuario.class, id);
		return perfil;
	}
		

}
