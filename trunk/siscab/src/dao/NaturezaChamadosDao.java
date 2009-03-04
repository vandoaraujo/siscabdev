package dao;

import java.util.List;

import modelo.NaturezaChamados;
import modelo.TiposOcorrencia;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class NaturezaChamadosDao {
	
	private static Session session;
	// Singleton
	private static NaturezaChamadosDao singleton = null;
	
	
	public static NaturezaChamadosDao getInstance(){
		singleton = new NaturezaChamadosDao();
		return singleton;
		
	}
	
	private NaturezaChamadosDao(){
		session = HibernateUtil.getInstance().AbreUmaSession();
	}
	
	public void fechaSession(){
		session.close();
	}
	
	
	
	public void salvar(NaturezaChamadosDao natureza){
		
		Transaction t = session.beginTransaction();
		session.save(natureza);
		t.commit();
		session.flush();
		session.close();
		
	}


	public List<NaturezaChamados> listarTodasNaturezasChamado(){
		List<NaturezaChamados> l = session.createQuery("from modelo.NaturezaChamados").list();
		return l;
		
	}
	
	public NaturezaChamados listarTipoNaturezaNome(String nome){
		
		NaturezaChamados natureza = (NaturezaChamados) session.createQuery("from modelo.NaturezaChamados o where o.naturezachamado_descricao=:nome").setString("nome", nome).uniqueResult();  
		return natureza;		
		
	}

	

}
