package dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import modelo.Chamado;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ChamadoDao {

    private static Session session;
    private static ChamadoDao instance = null;
    static Logger logger = Logger.getLogger(ChamadoDao.class);

    public static ChamadoDao getInstance() {
	instance = new ChamadoDao();
	return instance;

    }

    private ChamadoDao() {
	session = HibernateUtil.getInstance().AbreUmaSession();
    }

    public void atualizar(Chamado chamado) {

	Transaction t = session.beginTransaction();
	session.update(chamado);
	t.commit();
	session.flush();
	session.close();
	logger.info("ATUALIZADO");

    }

    public Chamado BuscaChamadoId(Integer id) {

	Chamado chamado = (Chamado) session.load(Chamado.class, id);
	return chamado;
    }

    public void deletar(Chamado chamado) {

	Transaction t = session.beginTransaction();
	session.delete(chamado);
	t.commit();
	session.flush();
	session.close();
	logger.info("DELETADO");
    }

    public void fechaSession() {
	session.close();
    }

    public Chamado listarChamadoNomeSolicitante(String nomeSolicitante) {

	Transaction tx = session.beginTransaction();
	Chamado chamado = (Chamado) session.createQuery(
		"from modelo.Chamado o where o.nomesolicitante=:nome")
		.setString("nome", nomeSolicitante).uniqueResult();
	tx.commit();
	return chamado;

    }

    public List<Chamado> listarTodosChamados() {

	List<Chamado> chama = session.createQuery("from modelo.Chamado").list();
	return chama;

    }

    public Integer listaUltimoId() {

	Integer idMax = (Integer) session.createQuery(
		"select max(id) from Chamado").uniqueResult();
	return idMax;

    }

    public Iterator ParametrosResultadosRelatorioChamadosPorNatureza(
	    Date dataInicial, Date dataFinal) {

	Iterator chamadosPorNatureza = session
		.createQuery(
			"select n.naturezachamado_descricao, count (distinct c.id) from modelo.Chamado c, modelo.NaturezaChamado n "
				+ "where c.naturezachamado = n.id and c.horainicio >=:horaInicial and c.horainicio <=:horaFinal "
				+ "group by n.naturezachamado_descricao order by count(c.id) desc")
		.setDate("horaInicial", dataInicial).setDate("horaFinal",
			dataFinal).list().iterator();
	return chamadosPorNatureza;
    }

    public void salvar(Chamado chamado) {

	Transaction t = session.beginTransaction();
	session.save(chamado);
	t.commit();
	session.flush();
	session.close();

    }

}