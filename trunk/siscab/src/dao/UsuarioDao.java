package dao;

import java.util.List;

import modelo.Usuario;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UsuarioDao {

    private static Session session;
    private static UsuarioDao instance = null;
    private static Usuario usuarioLogado;
    static Logger logger = Logger.getLogger(UsuarioDao.class);

    public static UsuarioDao getInstance() {
	instance = new UsuarioDao();
	return instance;
    }

    public static Usuario getUsuarioLogado() {
	return usuarioLogado;
    }

    private UsuarioDao() {
	session = HibernateUtil.getInstance().AbreUmaSession();
    }

    public void atualizar(Usuario usuario) {

	Transaction t = session.beginTransaction();
	session.update(usuario);
	t.commit();
	session.flush();
	session.close();
	logger.info("ATUALIZADO");

    }

    public List<Usuario> buscarNumRegistroRepetido(int numRegistro) {

	List usu = session.createQuery(
		"from modelo.Usuario u where u.numRegistro=:login").setInteger(
		"login", numRegistro).list();
	return usu;
    }

    public Usuario buscarUsuario(int numRegistro, String senha) {

	Usuario usu = (Usuario) session
		.createQuery(
			"from modelo.Usuario u where u.numRegistro=:login and u.senha=:senha")
		.setInteger("login", numRegistro).setString("senha", senha)
		.uniqueResult();
	usuarioLogado = usu;
	return usu;
    }

    public List<Usuario> buscarUsuariosNumRegistro(Integer registro) {

	List<Usuario> usuario = session.createCriteria(Usuario.class).add(
		Restrictions.sqlRestriction("numRegistro like '" + registro
			+ "%'")).list();
	return usuario;
    }

    public List<Usuario> buscarUsuariosNumRegistroOBM(int numero, int id) {

	List<Usuario> usuario = session.createCriteria(Usuario.class).add(
		Restrictions.sqlRestriction("numRegistro like '" + numero
			+ "%'" + "and obm_id like '" + id + "%'")).list();
	return usuario;
    }

    public List<Usuario> buscarUsuariosOBM(int obm) {

	List usu = session
		.createQuery("from modelo.Usuario u where u.obm=:obm")
		.setInteger("obm", obm).list();
	return usu;
    }

    public Usuario BuscaUsuarioId(Integer id) {

	Usuario u = (Usuario) session.load(Usuario.class, id);
	return u;
    }

    public void deletar(Usuario usuario) {

	Transaction t = session.beginTransaction();
	session.delete(usuario);
	t.commit();
	session.flush();
	session.close();
	logger.info("DELETADO");
    }

    public void fechaSession() {
	session.close();
    }

    public List<Usuario> listar() {
	List<Usuario> l = session.createQuery("from modelo.Usuario").list();
	return l;
    }

    public Usuario listarUsuariosNome(String nome) {

	Usuario usu = (Usuario) session.createQuery(
		"from modelo.Usuario u where u.nome=:nome").setString("nome",
		nome).uniqueResult();
	return usu;

    }

    public void salvar(Usuario usuario) {

	Transaction t = session.beginTransaction();
	session.save(usuario);
	t.commit();
	session.flush();
	session.close();
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
	UsuarioDao.usuarioLogado = usuarioLogado;
    }
}
