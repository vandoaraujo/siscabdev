package dao;

import java.util.List;

import modelo.Atendimentos;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class AtendimentosDao {

		private static Session session;
		// Singleton
		private static AtendimentosDao singleton = null;
		
		
		public static AtendimentosDao getInstance(){
			singleton = new AtendimentosDao();
			return singleton;
			
		}
		
		private AtendimentosDao(){
			session = HibernateUtil.getInstance().AbreUmaSession();
		}
		
		public void fechaSession(){
			session.close();
		}
			
		
		public void salvar(Atendimentos atendimento){
			
			Transaction t = session.beginTransaction();
			session.save(atendimento);
			t.commit();
			session.flush();
			session.close();
			
		}
			
		public void deletar(Atendimentos atendimento) {
			
			Transaction t = session.beginTransaction();
			session.delete(atendimento);
			t.commit();
			session.flush();
			session.close();
			System.out.println("DELETADO");
		}
		
		public void atualizar(Atendimentos atendimento) {
			
			Transaction t = session.beginTransaction();
			session.update(atendimento);
			t.commit();
			session.flush();
			session.close();
			System.out.println("ATUALIZADO");
			
		}


		public List<Atendimentos> listarTodosAtendimentos(){
			List<Atendimentos> atend = session.createQuery("from modelo.Atendimentos").list();
			return atend;
			
		}
		
		

		public Integer listaUltimoId(){
			
			Integer idMax = (Integer) session.createQuery("select max(id) from Atendimentos").uniqueResult();
			return idMax;
			
		}
		
	
		public List<Atendimentos> listarOcorrenciasProximas(int municipio, String bairro){
			
			List<Atendimentos> atendimentos = (List<Atendimentos>) session.createQuery("from modelo.Atendimentos a where a.municipio_id=:municipio and a.bairro=:bairroParametro and status_atendimento <> 'Finalizado'").setInteger("municipio", municipio).setString("bairroParametro", bairro).list();  
			return atendimentos;		
			
		}
		
		public Atendimentos BuscaAtendimentoId(Integer id) {
			
			Atendimentos atendimentos = (Atendimentos) session.load(Atendimentos.class, id);
			return atendimentos;
		}
		
		public Atendimentos BuscaAtendimentoAtendimentoNumero(Integer id) {
			
			Atendimentos atendimento = (Atendimentos) session.createQuery("from modelo.Atendimentos a where a.atendimento_numero=:atendimentoid").setInteger("atendimentoid", id).uniqueResult();  
			return atendimento;
		}
		
		
		public List<Atendimentos> listarAtendimentosNaoFinalizados(){
			
			List<Atendimentos> atendimentos = (List<Atendimentos>) session.createQuery("from modelo.Atendimentos a where a.status_atendimento <> 'Finalizado'").list();  
			return atendimentos;		
			
		}
		
		
		
}

