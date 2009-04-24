package dao;

import java.util.List;

import modelo.TipoViatura;
import modelo.TipoOcorrencia;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import controle.Login;

public class TipoOcorrenciaDao {

	private static Session session;
	private static TipoOcorrenciaDao instance = null;
	static Logger logger = Logger.getLogger(TipoOcorrenciaDao.class);

	public static TipoOcorrenciaDao getInstance(){
		instance = new TipoOcorrenciaDao();
		return instance;
		
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
