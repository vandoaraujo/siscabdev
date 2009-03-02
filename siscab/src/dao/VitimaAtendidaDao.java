package dao;

import java.util.List;

import modelo.Atendimentos;
import modelo.Usuario;
import modelo.VitimaAtendida;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class VitimaAtendidaDao {

	private static Session session;
	// Singleton
	private static VitimaAtendidaDao singleton = null;
	
	public static VitimaAtendidaDao getInstance(){
		//Verificar sessoes...
		//if(singleton == null)
		
		singleton = new VitimaAtendidaDao();
		return singleton;
		
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
		System.out.println("DELETADO");
	}
	
	public void atualizar(VitimaAtendida vitima) {
		
		Transaction t = session.beginTransaction();
		session.update(vitima);
		t.commit();
		session.flush();
		session.close();
		System.out.println("ATUALIZADO");
		
	}

	public List<VitimaAtendida> listar(){
		List<VitimaAtendida> l = session.createQuery("from modelo.VitimaAtendida").list();
		return l;
		
	}
	
	public List<VitimaAtendida> listaVitimasReferenteUmAtendimento(int atendimentoId){
		
		List<VitimaAtendida> vitimas = session.createQuery("from modelo.VitimaAtendida where atendimento=:atendimentoId").setInteger("atendimento", atendimentoId).list();
		return vitimas;
	}
	
	@Deprecated
	public VitimaAtendida buscarVitima(Atendimentos at){
		
		VitimaAtendida vitima = (VitimaAtendida) session.createQuery("atend from modelo.Vitima v, modelo.Atendimentos atend where atend.id = v.atendimento").uniqueResult();
		return vitima;
	}
	
	public Usuario listarUmaVitimaNome(String nome){
				
		Criteria c = session.createCriteria(VitimaAtendida.class);
		c.add(Restrictions.like("nome", nome));
		Usuario p = (Usuario) c.setMaxResults(1);
		return p;		
		
	}

		
	
}
