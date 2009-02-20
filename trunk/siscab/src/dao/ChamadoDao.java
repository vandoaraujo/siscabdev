package dao;

import java.util.List;

import modelo.Chamado;
import modelo.OBM;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ChamadoDao {

	private static Session session;
	// Singleton
	private static ChamadoDao singleton = null;
	
	
	public static ChamadoDao getInstance(){
		singleton = new ChamadoDao();
		return singleton;
		
	}
	
	private ChamadoDao(){
		session = HibernateUtil.getInstance().AbreUmaSession();
	}
	
	public void fechaSession(){
		session.close();
	}
		
	
	public void salvar(Chamado chamado){
		
		Transaction t = session.beginTransaction();
		session.save(chamado);
		t.commit();
		session.flush();
		session.close();
		
	}
		
	public void deletar(Chamado chamado) {
		
		Transaction t = session.beginTransaction();
		session.delete(chamado);
		t.commit();
		session.flush();
		session.close();
		System.out.println("DELETADO");
	}
	
	public void atualizar(Chamado chamado) {
		
		Transaction t = session.beginTransaction();
		session.update(chamado);
		t.commit();
		session.flush();
		session.close();
		System.out.println("ATUALIZADO");
		
	}


	public List<Chamado> listarTodasOBMs(){
		List<Chamado> chama = session.createQuery("from modelo.Chamado").list();
		return chama;
		
	}
	
	
	//Otimizar este método
	public Integer listaUltimoId(){
		
		int maior = 0;
		List<Chamado> lista = (List<Chamado>)session.createQuery("from modelo.Chamado").list();
		for(int i = 0;i<lista.size();i++){
			Chamado ch = lista.get(i);
			maior = ch.getId();
		}
		return maior;
	}
		
	public Chamado listarChamadoNomeSolicitante(String nomeSolicitante){
		
		Transaction tx = session.beginTransaction();  
		Chamado chamado = (Chamado) session.createQuery("from modelo.Chamado o where o.nomesolicitante=:nome").setString("nome", nomeSolicitante).uniqueResult();  
		tx.commit();
		return chamado;		
		
	}
	
	public Chamado BuscaChamadoId(Integer id) {
		
		Chamado chamado = (Chamado) session.load(Chamado.class, id);
		return chamado;
	}
	
	
}


