package dao;

import java.util.List;

import modelo.Chamado;
import modelo.ModoFechamento;
import modelo.NaturezaChamado;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ModoFechamentoDao {
	
	
	private static Session session;
	private static ModoFechamentoDao instance = null;
	
	public static ModoFechamentoDao getInstance(){
		instance = new ModoFechamentoDao();
		return instance;
		
	}
	
	private ModoFechamentoDao(){
		session = HibernateUtil.getInstance().AbreUmaSession();
	}
	
	public void fechaSession(){
		session.close();
	}
	
	
	
	public void salvar(ModoFechamentoDao modoFechamento){
		
		Transaction t = session.beginTransaction();
		session.save(modoFechamento);
		t.commit();
		session.flush();
		session.close();
		
	}


	public List<ModoFechamento> listarTodosModosFechamento(){
		List<ModoFechamento> l = session.createQuery("from modelo.ModoFechamento").list();
		return l;
		
	}
	
	public ModoFechamento listarModoFechamentoNome(String nome){
		
		ModoFechamento natureza = (ModoFechamento) session.createQuery("from modelo.ModoFechamento o where o.descricao=:nome").setString("nome", nome).uniqueResult();  
		return natureza;		
		
	}
	
	public ModoFechamento BuscaChamadoId(Integer id) {
		
		ModoFechamento chamado = (ModoFechamento) session.load(ModoFechamento.class, id);
		return chamado;
	}

	

}
