package dao;

import java.util.ArrayList;
import java.util.List;

import modelo.Viatura;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ViaturaDao {

    private static Session session;
    private static ViaturaDao instance = null;
    static Logger logger = Logger.getLogger(ViaturaDao.class);

    public static ViaturaDao getInstance() {
	instance = new ViaturaDao();
	return instance;
    }

    private Transaction t;

    private ViaturaDao() {
	session = HibernateUtil.getInstance().AbreUmaSession();
    }

    public void abreUmaSessionFluxosExcecao() {

	session = HibernateUtil.getInstance().AbreUmaSession();

    }

    public void atualizar(Viatura viatura) {

	Transaction t = session.beginTransaction();
	session.update(viatura);
	t.commit();
	session.flush();
	session.close();
	logger.info("Viatura Atualizada");

    }

    public void atualizarDiversasViaturas(Viatura viatura) {

	session.update(viatura);

    }

    public Viatura BuscaViaturaId(Integer id) {

	Viatura viatura = (Viatura) session.load(Viatura.class, id);
	return viatura;
    }

    public void deletar(Viatura viatura) {

	Transaction t = session.beginTransaction();
	session.delete(viatura);
	t.commit();
	session.flush();
	session.close();
	logger.info("DELETADO");
    }

    public void fechaSession() {
	session.close();
    }

    public void finalizaTransacao(Transaction tra) {

	tra.commit();
	session.flush();
	session.close();
	logger.info("Viaturas Atualizadas");
    }

    public Transaction iniciaTransacao() {
	t = session.beginTransaction();
	return t;

    }

    public List<Viatura> listar() {

	List<Viatura> l = new ArrayList();
	l = session.createQuery("from modelo.Viatura").list();
	return l;

    }

    public Viatura listarViaturasNumero(String numeroViatura) {

	Viatura via = (Viatura) session.createQuery(
		"from modelo.Viatura v where v.numero=:numero").setString(
		"numero", numeroViatura).uniqueResult();
	return via;

    }

    public List<Viatura> listaViaturasObm(int obmAtual) {

	List<Viatura> viaturasOBM = session.createQuery(
		"from modelo.Viatura v where v.obm_id=:obm").setInteger("obm",
		obmAtual).list();
	return viaturasOBM;
    }

    public List<Viatura> listaViaturasOBMStatusEmProntidao(int obmAtual) {

	List<Viatura> viaturasOBM = session
		.createQuery(
			"from modelo.Viatura v where v.obm_id=:obm and viatura_status = 'Em prontid�o'")
		.setInteger("obm", obmAtual).list();
	return viaturasOBM;
    }

    public List<Viatura> listaViaturasPorStatus(String status) {

	List<Viatura> viaturasPorStatus = session
		.createQuery(
			"from modelo.Viatura v where v.viatura_status=:status order by obm_id,viatura_status")
		.setString("status", status).list();
	return viaturasPorStatus;
    }

    public List<Viatura> listaViaturasPorTipo(int tipoViatura) {

	List<Viatura> viaturasPorTipo = session
		.createQuery(
			"from modelo.Viatura v where v.tipo_viatura=:tipoViatura order by v.tipo_viatura")
		.setInteger("tipoViatura", tipoViatura).list();
	return viaturasPorTipo;
    }

    public List<Viatura> listaViaturasPorTipoStatus(int tipoViatura,
	    String status) {

	List<Viatura> viaturasPorTipoStatus = session
		.createQuery(
			"from modelo.Viatura v where v.tipo_viatura=:tipoViatura and v.viatura_status=:status order by v.viatura_status,v.tipoViatura")
		.setInteger("tipoViatura", tipoViatura).setString("status",
			status).list();
	return viaturasPorTipoStatus;
    }

    public void salvar(Viatura viatura) {

	Transaction t = session.beginTransaction();
	session.save(viatura);
	t.commit();
	session.flush();
	session.close();

    }

}
