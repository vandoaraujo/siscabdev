package dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import modelo.Atendimento;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import controle.Login;

public class AtendimentoDao {

		private static Session session;
		private static AtendimentoDao instance = null;
		static Logger logger = Logger.getLogger(AtendimentoDao.class);

		
		public static AtendimentoDao getInstance(){
			instance = new AtendimentoDao();
			return instance;
			
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
			logger.info("DELETADO");
		}
		
		public void atualizar(Atendimento atendimento) {
			
			Transaction t = session.beginTransaction();
			session.update(atendimento);
			t.commit();
			session.flush();
			session.close();
			logger.info("ATUALIZADO");
			
		}


		public List<Atendimento> listarTodosAtendimentos(){
			List<Atendimento> atend = session.createQuery("from modelo.Atendimento").list();
			return atend;
			
		}
		
		

		public Integer listaUltimoId(){
			
			Integer idMax = (Integer) session.createQuery("select max(id) from Atendimento").uniqueResult();
			return idMax;
		}
		
		public Iterator ParametrosResultadosRelatorio(Date dataInicial, Date dataFinal){
			
			Iterator modoFechamentosInteiros = session.createQuery("SELECT m.descricao,COUNT( distinct a.id) from modelo.Atendimento a, modelo.ModoFechamento m,modelo.CronoAtendimento c where m.id = a.modofechamento_id and" +
					"  c.cronoatendimento_horaevento >=:horaInicial and c.cronoatendimento_horaevento <=:horaFinal and c.atendimento_id = a.id and" +
						" a.status_atendimento = 'Finalizado' and c.cronoatendimento_tipoevento = 'finalização'" +
						"  group by m.descricao " +
						"order by count(a.id) desc").setDate("horaInicial",dataInicial).setDate("horaFinal",dataFinal).list().iterator();
			return modoFechamentosInteiros;	
		}
		
		
		public Iterator ParametrosResultadosRelatorioTipoOcorrencia(Date dataInicial, Date dataFinal){
			
			Iterator tipoOcorrenciasAtendimento = session.createQuery("SELECT toc.tipoocorrencia_descricao,COUNT( distinct a.id) from modelo.Atendimento a, modelo.TipoOcorrencia toc,modelo.CronoAtendimento c where toc.id = a.tipoocorrencia and" +
					"  c.cronoatendimento_horaevento >=:horaInicial and c.cronoatendimento_horaevento <=:horaFinal and c.atendimento_id = a.id" +
						" and c.cronoatendimento_tipoevento = 'início'" +
						"  group by toc.tipoocorrencia_descricao" +
						" order by count(a.id) desc").setDate("horaInicial",dataInicial).setDate("horaFinal",dataFinal).list().iterator();
			return tipoOcorrenciasAtendimento;
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
		
		public List<Atendimento> listarAtendimentosNaoFinalizadosOBM(int obmId){

			List<Atendimento> atendimentos = (List<Atendimento>) session.createQuery("from modelo.Atendimento a where a.obm_id=:idOBM and a.status_atendimento <> 'Finalizado'").setInteger("idOBM", obmId).list();  
			return atendimentos;
		}
		
		public List<Atendimento> listarAtendimentosOBM(int obmId){

			List<Atendimento> atendimentos = (List<Atendimento>) session.createQuery("from modelo.Atendimento a where a.obm_id=:idOBM").setInteger("idOBM", obmId).list();  
			return atendimentos;
		}
		
		
		
}

