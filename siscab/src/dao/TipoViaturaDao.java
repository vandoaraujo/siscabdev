package dao;

import java.util.List;

import modelo.TipoViatura;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TipoViaturaDao {

    private static Session session;
    private static TipoViaturaDao instance = null;
    static Logger logger = Logger.getLogger(TipoViaturaDao.class);

    public static TipoViaturaDao getInstance() {
	instance = new TipoViaturaDao();
	return instance;

    }

    private TipoViaturaDao() {
	session = HibernateUtil.getInstance().AbreUmaSession();
    }

    public void fechaSession() {
	session.close();
    }

    public TipoViatura listarTipoViatura(String nome) {

	TipoViatura tipoV = (TipoViatura) session
		.createQuery(
			"from modelo.TipoViatura o where o.tipoviatura_descricao=:nome")
		.setString("nome", nome).uniqueResult();
	return tipoV;

    }

    public List<TipoViatura> listarTodosTiposViaturas() {
	List<TipoViatura> l = session.createQuery("from modelo.TipoViatura")
		.list();
	return l;

    }

    public void salvar(TipoViatura municipio) {

	Transaction t = session.beginTransaction();
	session.save(municipio);
	t.commit();
	session.flush();
	session.close();

    }

}
