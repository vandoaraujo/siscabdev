package dao;

import java.util.List;

import modelo.OBM;
import modelo.SiscabException;

import org.apache.log4j.Logger;
import org.hibernate.ObjectDeletedException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import controle.Login;

public class OBMDao {

	private static Session session;
	private static OBMDao instance = null;
	static Logger logger = Logger.getLogger(OBMDao.class);
	
	public static OBMDao getInstance(){
		instance = new OBMDao();
		return instance;
		
	}
	
	private OBMDao(){
		session = HibernateUtil.getInstance().AbreUmaSession();
	}
	
	public void fechaSession(){
		session.close();
	}
	
	
	
	public void salvar(OBM area){
		
		Transaction t = session.beginTransaction();
		session.save(area);
		t.commit();
		session.flush();
		session.close();
		
	}
		
	public void deletar(OBM obm) throws SiscabException {
		try{
		Transaction t = session.beginTransaction();
		session.delete(obm);
		t.commit();
		session.flush();
		session.close();
		logger.info("DELETADO");
		}
		catch(ObjectDeletedException o){
			
			throw new SiscabException("N�o � possivel deletar esta OBM devido ela ter associacoes");
		}
	}
	
	public void atualizar(OBM obm) {
		
		Transaction t = session.beginTransaction();
		session.update(obm);
		t.commit();
		session.flush();
		session.close();
		logger.info("ATUALIZADO");
		
	}


	public List<OBM> listarTodasOBMs(){
		List<OBM> l = session.createQuery("from modelo.OBM").list();
		return l;
		
	}
	
	public List<OBM> listarTodasOBMsExcetoCOCB(){
		List<OBM> l = session.createQuery("from modelo.OBM where nome <> 'COCB'").list();
		return l;
		
	}
		
	public OBM listarOBMNome(String nome){
		
		OBM obm = (OBM) session.createQuery("from modelo.OBM o where o.nome=:nome").setString("nome", nome).uniqueResult();  
		return obm;		
		
	}
	
	public OBM BuscaOBMId(Integer id) {
		
		OBM obm = (OBM) session.load(OBM.class, id);
		return obm;
	}
	
	
}

