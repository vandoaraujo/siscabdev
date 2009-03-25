package dao;

import java.util.List;

import modelo.TipoViatura;
import modelo.TipoOcorrencia;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TipoOcorrenciaDao {

	private static Session session;
	// Singleton
	private static TipoOcorrenciaDao singleton = null;
	
	
	public static TipoOcorrenciaDao getInstance(){
		singleton = new TipoOcorrenciaDao();
		return singleton;
		
	}
	
	private TipoOcorrenciaDao(){
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


	public List<TipoOcorrencia> listarTodosTiposOcorrencias(){
		List<TipoOcorrencia> l = session.createQuery("from modelo.TipoOcorrencia").list();
		return l;
		
	}
	
	public TipoOcorrencia listarTiposOcorrenciaNome(String nome){
		
		TipoOcorrencia tipoV = (TipoOcorrencia) session.createQuery("from modelo.TipoOcorrencia o where o.tipoocorrencia_descricao=:nome").setString("nome", nome).uniqueResult();  
		return tipoV;		
		
	}

	
}
