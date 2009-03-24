package dao;

import java.util.List;
import modelo.Municipio;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MunicipioDao {
	
	private static Session session;
	// Singleton
	private static MunicipioDao singleton = null;
	
	
	public static MunicipioDao getInstance(){
		singleton = new MunicipioDao();
		return singleton;
		
	}
	
	private MunicipioDao(){
		session = HibernateUtil.getInstance().AbreUmaSession();
	}
	
	public void fechaSession(){
		session.close();
	}
	
	
	
	public void salvar(Municipio municipio){
		
		Transaction t = session.beginTransaction();
		session.save(municipio);
		t.commit();
		session.flush();
		session.close();
		
	}


	public List<Municipio> listarTodosMunicipios(){
		List<Municipio> l = session.createQuery("from modelo.Municipio order by municipio_nome").list();
		return l;
		
	}
		
	public Municipio listarMunicipioNome(String nome){
		
		Transaction tx = session.beginTransaction();  
		Municipio municipio = (Municipio) session.createQuery("from modelo.Municipio m where m.municipio_nome=:municipio").setString("municipio", nome).uniqueResult();  
		tx.commit();
		return municipio;		
		
	}
	
	
}


