package dao;

import java.util.List;

import modelo.Usuario;
import modelo.Viatura;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class ViaturaDao {

	private static Session session;
	// Singleton
	private static ViaturaDao singleton = null;
	

	public static ViaturaDao getInstance(){
		//if(singleton == null)
		singleton = new ViaturaDao();
		return singleton;
		
	}
	
	private ViaturaDao(){
		session = HibernateUtil.getInstance().AbreUmaSession();
	}
	
	public void fechaSession(){
		session.close();
	}
		
	public void salvar(Viatura viatura){
		
		Transaction t = session.beginTransaction();
		session.save(viatura);
		t.commit();
		session.flush();
		session.close();
		
		
	}
	
	public void deletar(Viatura viatura) {
		
		Transaction t = session.beginTransaction();
		session.delete(viatura);
		t.commit();
		session.flush();
		session.close();
		System.out.println("DELETADO");
	}
	
	public void atualizar(Viatura viatura) {
		
		Transaction t = session.beginTransaction();
		session.update(viatura);
		t.commit();
		session.flush();
		session.close();
		System.out.println("ATUALIZADO");
		
	}

	public List<Viatura> listar(){
		
		List<Viatura> l = session.createQuery("from modelo.Viatura").list();
		return l;
		
	}
	public Viatura listarViaturasNumero(String numeroViatura){
		
		Viatura via = (Viatura) session.createQuery("from modelo.Viatura v where v.numero=:numero").setString("numero", numeroViatura).uniqueResult();  
		return via;		
		
	}
	
	public Viatura BuscaViaturaId(Integer id) {
		
		Viatura viatura = (Viatura) session.load(Viatura.class, id);
		return viatura;
	}

		
}
