package dao;

import java.util.List;
import modelo.OBM;
import modelo.Usuario;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class OBMDao {

	private static Session session;
	// Singleton
	private static OBMDao singleton = null;
	
	
	public static OBMDao getInstance(){
		singleton = new OBMDao();
		return singleton;
		
	}
	
	private OBMDao(){
		session = HibernateUtil.getInstance().AbreUmaSession();
	}
	
	public void fechaSession(){
		session.close();
	}
	
	
	
	public void salvar(OBM area){
		
		Transaction t = session.beginTransaction();
		session.save(area);
		t.commit();
		session.flush();
		session.close();
		
	}
		
	public void deletar(OBM obm) {
		
		Transaction t = session.beginTransaction();
		session.delete(obm);
		t.commit();
		session.flush();
		session.close();
		System.out.println("DELETADO");
	}
	
	public void atualizar(OBM obm) {
		
		Transaction t = session.beginTransaction();
		session.update(obm);
		t.commit();
		session.flush();
		session.close();
		System.out.println("ATUALIZADO");
		
	}


	public List<OBM> listarTodasOBMs(){
		List<OBM> l = session.createQuery("from modelo.OBM").list();
		return l;
		
	}
		
	public OBM listarOBMNome(String nome){
		
		Transaction tx = session.beginTransaction();  
		OBM obm = (OBM) session.createQuery("from modelo.OBM o where o.nome=:nome").setString("nome", nome).uniqueResult();  
		tx.commit();
		return obm;		
		
	}
	
	public OBM BuscaOBMId(Integer id) {
		
		OBM obm = (OBM) session.load(OBM.class, id);
		return obm;
	}
	
	
}
