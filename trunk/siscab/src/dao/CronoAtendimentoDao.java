package dao;

import java.util.List;

import modelo.CronoAtendimento;
import modelo.Usuario;
import modelo.VitimaAtendida;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import controle.Login;

public class CronoAtendimentoDao {
	
	private static Session session;
	private static CronoAtendimentoDao instance = null;
	static Logger logger = Logger.getLogger(CronoAtendimentoDao.class);
	
	public static CronoAtendimentoDao getInstance(){
		instance = new CronoAtendimentoDao();
		return instance;
		
	}
	
	private CronoAtendimentoDao(){
		session = HibernateUtil.getInstance().AbreUmaSession();
	}
	
	public void fechaSession(){
		session.close();
	}
		
	public void salvar(CronoAtendimento cronoAtendimento){
		
		Transaction t = session.beginTransaction();
		session.save(cronoAtendimento);
		t.commit();
		session.flush();
		session.close();
		
		
	}
	
	public void deletar(CronoAtendimento cronoAtendimento) {
		
		Transaction t = session.beginTransaction();
		session.delete(cronoAtendimento);
		t.commit();
		session.flush();
		session.close();
		logger.info("DELETADO");
	}
	
	public void atualizar(CronoAtendimento cronoAtendimento) {
		
		Transaction t = session.beginTransaction();
		session.update(cronoAtendimento);
		t.commit();
		session.flush();
		session.close();
		logger.info("ATUALIZADO");
		
	}

	public List<CronoAtendimento> listar(){
		List<CronoAtendimento> crono = session.createQuery("from modelo.CronoAtendimento").list();
		return crono;
		
	}
	
	public List<CronoAtendimento> listaCronologiasReferenteUmAtendimento(int atendimentoId){
		
		List<CronoAtendimento> cronoLista = session.createQuery("from modelo.CronoAtendimento c where c.atendimento_id=:atendimento").setInteger("atendimento", atendimentoId).list();
		return cronoLista;
	}
	
	public CronoAtendimento verificaCronologiaAtendimentoInicio(int atendimentoId){
		
		CronoAtendimento cronoInicio = (CronoAtendimento) session.createQuery("from modelo.CronoAtendimento c where c.atendimento_id=:atendimento and c.cronoatendimento_tipoevento = 'início'").setInteger("atendimento", atendimentoId).uniqueResult();
		session.close();
		return cronoInicio;
	}
	
	
	public Usuario listarUmaVitimaNome(String nome){
				
		Criteria c = session.createCriteria(VitimaAtendida.class);
		c.add(Restrictions.like("nome", nome));
		Usuario p = (Usuario) c.setMaxResults(1);
		return p;		
		
	}
	
	public CronoAtendimento BuscaVitimaId(Integer id) {
		
		CronoAtendimento crono = (CronoAtendimento) session.load(CronoAtendimento.class, id);
		return crono;
	}
}
