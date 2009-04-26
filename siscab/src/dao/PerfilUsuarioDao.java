package dao;

import java.util.List;

import modelo.PerfilUsuario;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PerfilUsuarioDao {

    private static Session session;
    private static PerfilUsuarioDao instance = null;
    static Logger logger = Logger.getLogger(PerfilUsuarioDao.class);

    public static PerfilUsuarioDao getInstance() {
	instance = new PerfilUsuarioDao();
	return instance;

    }

    private PerfilUsuarioDao() {
	session = HibernateUtil.getInstance().AbreUmaSession();
    }

    public void atualizar(PerfilUsuario perfil) {

	Transaction t = session.beginTransaction();
	session.update(perfil);
	t.commit();
	session.flush();
	session.close();
	logger.info("ATUALIZADO");

    }

    public PerfilUsuario BuscaPerfilId(Integer id) {

	PerfilUsuario perfil = (PerfilUsuario) session.load(
		PerfilUsuario.class, id);
	return perfil;
    }

    public void deletar(PerfilUsuario perfil) {

	Transaction t = session.beginTransaction();
	session.delete(perfil);
	t.commit();
	session.flush();
	session.close();
	logger.info("DELETADO");
    }

    public void fechaSession() {
	session.close();
    }

    public PerfilUsuario listarPerfilNome(String nome) {

	PerfilUsuario perfil = (PerfilUsuario) session.createQuery(
		"from modelo.PerfilUsuario p where p.perfil_descricao=:nome")
		.setString("nome", nome).uniqueResult();
	return perfil;

    }

    public List<PerfilUsuario> listarTodosPerfis() {
	List<PerfilUsuario> perfil = session.createQuery(
		"from modelo.PerfilUsuario").list();
	return perfil;

    }

    public void salvar(PerfilUsuario perfil) {

	Transaction t = session.beginTransaction();
	session.save(perfil);
	t.commit();
	session.flush();
	session.close();

    }

}
