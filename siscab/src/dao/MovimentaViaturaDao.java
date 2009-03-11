package dao;

import java.util.List;

import modelo.Usuario;
import modelo.MovimentaViatura;
import modelo.Viatura;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class MovimentaViaturaDao {

		
	private static Session session;
	// Singleton
	private static MovimentaViaturaDao singleton = null;
	
	public static MovimentaViaturaDao getInstance(){
		//Verificar sessoes...
		//if(singleton == null)
		
		singleton = new MovimentaViaturaDao();
		return singleton;
		
	}
	
	private MovimentaViaturaDao(){
		session = HibernateUtil.getInstance().AbreUmaSession();
	}
	
	public void fechaSession(){
		session.close();
	}
		
	public void salvar(MovimentaViatura viaturasEmpenhadas){
		
		Transaction t = session.beginTransaction();
		session.save(viaturasEmpenhadas);
		t.commit();
		session.flush();
		session.close();
		
		
	}
	
	public void deletar(MovimentaViatura viaturasEmpenhadas) {
		
		Transaction t = session.beginTransaction();
		session.delete(viaturasEmpenhadas);
		t.commit();
		session.flush();
		session.close();
		System.out.println("DELETADO");
	}
	
	public void atualizar(MovimentaViatura viaturasEmpenhadas) {
		
		Transaction t = session.beginTransaction();
		session.update(viaturasEmpenhadas);
		t.commit();
		session.flush();
		session.close();
		System.out.println("ATUALIZADO");
		
	}

	public List<MovimentaViatura> listar(){
		List<MovimentaViatura> l = session.createQuery("from modelo.MovimentaViatura").list();
		return l;
		
	}
	
	
	public List<MovimentaViatura> listarViaturasTipoEvento(String tipoEvento){
		
		List<MovimentaViatura> historicoMovimentacoes = (List<MovimentaViatura>) session.createQuery("from modelo.MovimentaViatura m where m.movimentaviatura_tipoevento=:nome").setString("nome", tipoEvento).list();
		return historicoMovimentacoes;		
		
	}
	
	public int listarUltimoId(){
		
		Integer idMax =  (Integer) session.createQuery("select max(numRegistro) from MovimentaViatura").uniqueResult();
		return idMax;
		
	}
	
	public List<MovimentaViatura> listaTiposEventosViaturaAtendimento(int atendimentoAtual, int viaturaAtual){
		
		List<MovimentaViatura> tiposEventosViaturaAtendimento = session.createQuery("from modelo.MovimentaViatura v where v.atendimento_id=:atendimento_id and v.viatura_id=:viatura_id").setInteger("atendimento_id", atendimentoAtual).setInteger("viatura_id", viaturaAtual).list();
		return tiposEventosViaturaAtendimento;
	}
	
	public List<MovimentaViatura> listaViaturasDeUmAtendimento(int atendimentoAtual){
		
		List<MovimentaViatura> tiposEventosViaturaAtendimento = session.createQuery("from modelo.MovimentaViatura v where v.chaveComposta.atendimento_id=:atendimento_id").setInteger("atendimento_id", atendimentoAtual).list();
		return tiposEventosViaturaAtendimento;
	}


		
}
