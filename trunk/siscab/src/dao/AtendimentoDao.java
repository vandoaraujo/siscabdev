package dao;

import java.util.List;

import modelo.Atendimento;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class AtendimentoDao {

		private static Session session;
		// Singleton
		private static AtendimentoDao singleton = null;
		
		
		public static AtendimentoDao getInstance(){
			singleton = new AtendimentoDao();
			return singleton;
			
		}
		
		private AtendimentoDao(){
			session = HibernateUtil.getInstance().AbreUmaSession();
		}
		
		public void fechaSession(){
			session.close();
		}
			
		
		public void salvar(Atendimento atendimento){
			
			Transaction t = session.beginTransaction();
			session.save(atendimento);
			t.commit();
			session.flush();
			session.close();
			
		}
			
		public void deletar(Atendimento atendimento) {
			
			Transaction t = session.beginTransaction();
			session.delete(atendimento);
			t.commit();
			session.flush();
			session.close();
			System.out.println("DELETADO");
		}
		
		public void atualizar(Atendimento atendimento) {
			
			Transaction t = session.beginTransaction();
			session.update(atendimento);
			t.commit();
			session.flush();
			session.close();
			System.out.println("ATUALIZADO");
			
		}


		public List<Atendimento> listarTodosAtendimentos(){
			List<Atendimento> atend = session.createQuery("from modelo.Atendimento").list();
			return atend;
			
		}
		
		

		public Integer listaUltimoId(){
			
			Integer idMax = (Integer) session.createQuery("select max(id) from Atendimento").uniqueResult();
			return idMax;
			
		}
		
	
		public List<Atendimento> listarOcorrenciasProximas(int municipio, String bairro){
			
			List<Atendimento> atendimentos = (List<Atendimento>) session.createQuery("from modelo.Atendimento a where a.municipio_id=:municipio and a.bairro=:bairroParametro and status_atendimento <> 'Finalizado'").setInteger("municipio", municipio).setString("bairroParametro", bairro).list();  
			return atendimentos;		
			
		}
		
		public Atendimento BuscaAtendimentoId(Integer id) {
			
			Atendimento atendimentos = (Atendimento) session.load(Atendimento.class, id);
			return atendimentos;
		}
		
		public Atendimento BuscaAtendimentoAtendimentoNumero(Integer id) {
			
			Atendimento atendimento = (Atendimento) session.createQuery("from modelo.Atendimento a where a.atendimento_numero=:atendimentoid").setInteger("atendimentoid", id).uniqueResult();  
			return atendimento;
		}
		
		public List<Atendimento> BuscaAtendimentosRedirecionadosAoCOCB(Integer id) {
			
			List<Atendimento> atendimentos = (List<Atendimento>) session.createQuery("from modelo.Atendimento a where a.obm_id=:idOBM and a.status_atendimento <> 'Finalizado'").setInteger("idOBM", id).list();  
			return atendimentos;
		}
		
		
		public List<Atendimento> listarAtendimentosNaoFinalizados(){
			
			List<Atendimento> atendimentos = (List<Atendimento>) session.createQuery("from modelo.Atendimento a where a.status_atendimento <> 'Finalizado'").list();  
			return atendimentos;		
			
		}
		
		
		
}

