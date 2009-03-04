package dao;

import java.util.List;

import modelo.TiposOcorrencia;
import modelo.TiposServicos;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TiposServicosDao {
	
	private static Session session;
	// Singleton
	private static TiposServicosDao singleton = null;
	
	
	public static TiposServicosDao getInstance(){
		singleton = new TiposServicosDao();
		return singleton;
		
	}
	
	private TiposServicosDao(){
		session = HibernateUtil.getInstance().AbreUmaSession();
	}
	
	public void fechaSession(){
		session.close();
	}
	
	
	
	public void salvar(TiposServicos tiposServicos){
		
		Transaction t = session.beginTransaction();
		session.save(tiposServicos);
		t.commit();
		session.flush();
		session.close();
		
	}


	public List<TiposServicos> listarTodosTiposServicos(){
		
		List<TiposServicos> l = session.createQuery("from modelo.TiposServicos").list();
		return l;
		
	}
	
	public TiposServicos listarTipoServicoNome(String nome){
		
		TiposServicos tipoS = (TiposServicos) session.createQuery("from modelo.TiposServicos o where o.tiposervico_descricao=:nome").setString("nome", nome).uniqueResult();  
		return tipoS;		
		
	}

	

}
