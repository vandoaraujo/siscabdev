package dao;

import java.util.List;

import modelo.Atendimentos;
import modelo.ServicoRealizado;
import modelo.VitimaAtendida;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ServicoRealizadoDao {
	
	
	private static Session session;
	// Singleton
	private static ServicoRealizadoDao singleton = null;
	
	public static ServicoRealizadoDao getInstance(){
		//Verificar sessoes...
		//if(singleton == null)
		
		singleton = new ServicoRealizadoDao();
		return singleton;
		
	}
	
	private ServicoRealizadoDao(){
		session = HibernateUtil.getInstance().AbreUmaSession();
	}
	
	public void fechaSession(){
		session.close();
	}
		
	public void salvar(ServicoRealizado servico){
		
		Transaction t = session.beginTransaction();
		session.save(servico);
		t.commit();
		session.flush();
		session.close();
		
		
	}
	
	public void deletar(ServicoRealizado servico) {
		
		Transaction t = session.beginTransaction();
		session.delete(servico);
		t.commit();
		session.flush();
		session.close();
		System.out.println("DELETADO");
	}
	
	public void atualizar(ServicoRealizado servico) {
		
		Transaction t = session.beginTransaction();
		session.update(servico);
		t.commit();
		session.flush();
		session.close();
		System.out.println("ATUALIZADO");
		
	}

	public ServicoRealizado BuscaServicoId(Integer id) {
		
		ServicoRealizado u = (ServicoRealizado) session.load(ServicoRealizado.class, id);
		return u;
	}
	
	public List<ServicoRealizado> listaServicosReferenteUmAtendimento(int atendimentoId){
		
		List<ServicoRealizado> servicos = session.createQuery("from modelo.ServicoRealizado s where s.atendimento_id=:servicosAtendimento").setInteger("servicosAtendimento", atendimentoId).list();
		return servicos;
	}


}