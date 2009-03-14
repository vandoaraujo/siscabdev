package dao;

import java.util.List;

import modelo.Atendimentos;
import modelo.CronoAtendimento;
import modelo.Usuario;
import modelo.VitimaAtendida;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class CronoAtendimentoDao {
	
	private static Session session;
	// Singleton
	private static CronoAtendimentoDao singleton = null;
	
	public static CronoAtendimentoDao getInstance(){
		//Verificar sessoes...
		//if(singleton == null)
		
		singleton = new CronoAtendimentoDao();
		return singleton;
		
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
		System.out.println("DELETADO");
	}
	
	public void atualizar(CronoAtendimento cronoAtendimento) {
		
		Transaction t = session.beginTransaction();
		session.update(cronoAtendimento);
		t.commit();
		session.flush();
		session.close();
		System.out.println("ATUALIZADO");
		
	}

	public List<CronoAtendimento> listar(){
		List<CronoAtendimento> crono = session.createQuery("from modelo.CronoAtendimento").list();
		return crono;
		
	}
	
	public List<CronoAtendimento> listaCronologiasReferenteUmAtendimento(int atendimentoId){
		
		List<CronoAtendimento> cronoLista = session.createQuery("from modelo.CronoAtendimento c where c.atendimento_id=:atendimento").setInteger("atendimento", atendimentoId).list();
		return cronoLista;
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
