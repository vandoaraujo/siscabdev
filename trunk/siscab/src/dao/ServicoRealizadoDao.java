package dao;

import java.util.List;

import modelo.Atendimento;
import modelo.ServicoRealizado;
import modelo.VitimaAtendida;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ServicoRealizadoDao {
	
	
	private static Session session;
	private static ServicoRealizadoDao instance = null;
	
	public static ServicoRealizadoDao getInstance(){
		instance = new ServicoRealizadoDao();
		return instance;
		
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
