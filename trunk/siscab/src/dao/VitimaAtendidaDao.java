package dao;

import java.util.List;

import modelo.Atendimento;
import modelo.OBM;
import modelo.Usuario;
import modelo.VitimaAtendida;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import controle.Login;

public class VitimaAtendidaDao {

	private static Session session;
	private static VitimaAtendidaDao instance = null;
	static Logger logger = Logger.getLogger(VitimaAtendidaDao.class);

	
	public static VitimaAtendidaDao getInstance(){
		instance = new VitimaAtendidaDao();
		return instance;
		
	}
	
	private VitimaAtendidaDao(){
		session = HibernateUtil.getInstance().AbreUmaSession();
	}
	
	public void fechaSession(){
		session.close();
	}
		
	public void salvar(VitimaAtendida vitima){
		
		Transaction t = session.beginTransaction();
		session.save(vitima);
		t.commit();
		session.flush();
		session.close();
		
		
	}
	
	public void deletar(VitimaAtendida vitima) {
		
		Transaction t = session.beginTransaction();
		session.delete(vitima);
		t.commit();
		session.flush();
		session.close();
		logger.info("DELETADO");
	}
	
	public void atualizar(VitimaAtendida vitima) {
		
		Transaction t = session.beginTransaction();
		session.update(vitima);
		t.commit();
		session.flush();
		session.close();
		logger.info("ATUALIZADO");
		
	}

	public List<VitimaAtendida> listar(){
		List<VitimaAtendida> l = session.createQuery("from modelo.VitimaAtendida").list();
		return l;
		
	}
	
	public List<VitimaAtendida> listaVitimasReferenteUmAtendimento(int atendimentoId){
		
		List<VitimaAtendida> vitimas = session.createQuery("from modelo.VitimaAtendida v where v.atendimento_id=:atendimentoVitimas").setInteger("atendimentoVitimas", atendimentoId).list();
		return vitimas;
	}
	
	@Deprecated
	public VitimaAtendida buscarVitima(Atendimento at){
		
		VitimaAtendida vitima = (VitimaAtendida) session.createQuery("atend from modelo.Vitima v, modelo.Atendimentos atend where atend.id = v.atendimento").uniqueResult();
		return vitima;
	}
	
	public Usuario listarUmaVitimaNome(String nome){
				
		Criteria c = session.createCriteria(VitimaAtendida.class);
		c.add(Restrictions.like("nome", nome));
		Usuario p = (Usuario) c.setMaxResults(1);
		return p;		
		
	}
	
	public VitimaAtendida BuscaVitimaId(Integer id) {
		
		VitimaAtendida vitima = (VitimaAtendida) session.load(VitimaAtendida.class, id);
		return vitima;
	}

		
	
}
