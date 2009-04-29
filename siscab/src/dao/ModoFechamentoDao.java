package dao;

import java.util.List;

import modelo.ModoFechamento;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ModoFechamentoDao {

    private static Session session;
    private static ModoFechamentoDao instance = null;
    static Logger logger = Logger.getLogger(ModoFechamentoDao.class);

    public static ModoFechamentoDao getInstance() {
	instance = new ModoFechamentoDao();
	return instance;

    }

    private ModoFechamentoDao() {
	session = HibernateUtil.getInstance().AbreUmaSession();
    }

    public ModoFechamento BuscaChamadoId(Integer id) {

	ModoFechamento chamado = (ModoFechamento) session.load(
		ModoFechamento.class, id);
	return chamado;
    }

    public void fechaSession() {
	session.close();
    }

    public ModoFechamento listarModoFechamentoNome(String nome) {

	ModoFechamento natureza = (ModoFechamento) session.createQuery(
		"from modelo.ModoFechamento o where o.descricao=:nome")
		.setString("nome", nome).uniqueResult();
	return natureza;

    }

    public List<ModoFechamento> listarTodosModosFechamento() {
	List<ModoFechamento> l = (List<ModoFechamento>)session.createQuery(
		"from modelo.ModoFechamento").list();
	return l;

    }

    public void salvar(ModoFechamentoDao modoFechamento) {

	Transaction t = session.beginTransaction();
	session.save(modoFechamento);
	t.commit();
	session.flush();
	session.close();

    }

}
