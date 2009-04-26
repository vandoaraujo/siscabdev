package dao;

import java.util.List;

import modelo.NaturezaChamado;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class NaturezaChamadoDao {

    private static Session session;
    private static NaturezaChamadoDao instance = null;
    static Logger logger = Logger.getLogger(NaturezaChamadoDao.class);

    public static NaturezaChamadoDao getInstance() {
	instance = new NaturezaChamadoDao();
	return instance;

    }

    private NaturezaChamadoDao() {
	session = HibernateUtil.getInstance().AbreUmaSession();
    }

    public void fechaSession() {
	session.close();
    }

    public NaturezaChamado listarTipoNaturezaNome(String nome) {

	NaturezaChamado natureza = (NaturezaChamado) session
		.createQuery(
			"from modelo.NaturezaChamado o where o.naturezachamado_descricao=:nome")
		.setString("nome", nome).uniqueResult();
	return natureza;

    }

    public List<NaturezaChamado> listarTodasNaturezasChamado() {
	List<NaturezaChamado> l = session.createQuery(
		"from modelo.NaturezaChamado").list();
	return l;

    }

    public void salvar(NaturezaChamadoDao natureza) {

	Transaction t = session.beginTransaction();
	session.save(natureza);
	t.commit();
	session.flush();
	session.close();

    }

}
