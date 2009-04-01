package dao;

import java.util.List;

import modelo.TipoOcorrencia;
import modelo.TipoServico;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TipoServicoDao {
	
	private static Session session;
	private static TipoServicoDao instance = null;
		
	public static TipoServicoDao getInstance(){
		instance = new TipoServicoDao();
		return instance;
		
	}
	
	private TipoServicoDao(){
		session = HibernateUtil.getInstance().AbreUmaSession();
	}
	
	public void fechaSession(){
		session.close();
	}
	
	
	
	public void salvar(TipoServico tiposServicos){
		
		Transaction t = session.beginTransaction();
		session.save(tiposServicos);
		t.commit();
		session.flush();
		session.close();
		
	}


	public List<TipoServico> listarTodosTiposServicos(){
		
		List<TipoServico> l = session.createQuery("from modelo.TipoServico").list();
		return l;
		
	}
	
	public TipoServico listarTipoServicoNome(String nome){
		
		TipoServico tipoS = (TipoServico) session.createQuery("from modelo.TipoServico o where o.tiposervico_descricao=:nome").setString("nome", nome).uniqueResult();  
		return tipoS;		
		
	}

	

}
