package dao;

import java.util.List;

import modelo.TipoViatura;
import modelo.TiposOcorrencia;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TiposOcorrenciaDao {

	private static Session session;
	// Singleton
	private static TiposOcorrenciaDao singleton = null;
	
	
	public static TiposOcorrenciaDao getInstance(){
		singleton = new TiposOcorrenciaDao();
		return singleton;
		
	}
	
	private TiposOcorrenciaDao(){
		session = HibernateUtil.getInstance().AbreUmaSession();
	}
	
	public void fechaSession(){
		session.close();
	}
	
	
	
	public void salvar(TipoViatura municipio){
		
		Transaction t = session.beginTransaction();
		session.save(municipio);
		t.commit();
		session.flush();
		session.close();
		
	}


	public List<TiposOcorrencia> listarTodosTiposOcorrencias(){
		List<TiposOcorrencia> l = session.createQuery("from modelo.TiposOcorrencia").list();
		return l;
		
	}
	
	public TiposOcorrencia listarTiposOcorrenciaNome(String nome){
		
		TiposOcorrencia tipoV = (TiposOcorrencia) session.createQuery("from modelo.TiposOcorrencia o where o.tipoocorrencia_descricao=:nome").setString("nome", nome).uniqueResult();  
		return tipoV;		
		
	}

	
}
