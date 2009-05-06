package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.OBM;
import modelo.PerfilUsuario;
import modelo.SiscabException;
import modelo.Usuario;

import org.apache.log4j.Logger;

import dao.OBMDao;
import dao.PerfilUsuarioDao;
import dao.UsuarioDao;

/**
 * Servlet implementation class NovoUsuarioServlet
 */
public class NovoUsuarioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private int numRegistro;
    private String nomeGuerra;
    private String obm = null;
    private String perfil = null;
    private String email;
    private String senha;
    private String status;

    static Logger logger = Logger.getLogger(NovoUsuarioServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NovoUsuarioServlet() {
	super();
    }

    private void alterar(HttpServletRequest request,
	    HttpServletResponse response, int registro) {

	numRegistro = Integer.parseInt(request.getParameter("registro"));
	nomeGuerra = request.getParameter("nomeGuerra");
	obm = request.getParameter("obm");
	perfil = request.getParameter("perfil");
	email = request.getParameter("email");
	senha = request.getParameter("senha");
	status = request.getParameter("status");
	status.toUpperCase();

	logger.info("TEM QUE MODIFICAR O STATUS PARA MAIUSCULO " + status);

	Usuario usu = UsuarioDao.getInstance().BuscaUsuarioId(registro);
	usu.setNumRegistro(numRegistro);
	usu.setNomeGuerra(nomeGuerra);
	OBM obmAtual = OBMDao.getInstance().listarOBMNome(obm);
	usu.setObm(obmAtual);

	PerfilUsuario p = PerfilUsuarioDao.getInstance().listarPerfilNome(
		perfil);
	
	if (p.getId() == 3 && obmAtual.getId() == 1) {
	    
	    SiscabException siscab = new SiscabException(
	    "O Cocb não possui Operador de OBM!.");
	    RequestDispatcher view = request
	    .getRequestDispatcher("/siscabException.jsp");

	    	try {
		view.forward(request, response);
    		} catch (ServletException e) {
    		    // TODO Auto-generated catch block
    		    e.printStackTrace();
    		} catch (IOException e) {
    		    // TODO Auto-generated catch block
    		    e.printStackTrace();
    		}
	}
	else{

	usu.setPerfil(p);
	usu.setEmail(email);
	usu.setSenha(senha);
	usu.setStatus(status);
	UsuarioDao.getInstance().atualizar(usu);
	despacha(request, response, "alterar", usu.getNomeGuerra());
	
	}
    }

    private void deletar(HttpServletRequest request,
	    HttpServletResponse response, int registro) {

	Usuario usu = UsuarioDao.getInstance().BuscaUsuarioId(registro);
	String nome = usu.getNomeGuerra();
	UsuarioDao.getInstance().deletar(usu);
	despacha(request, response, "deletar", nome);
    }

    /*
     * Recebe como parametro HttpRequest, response, a acao a ser executada e o
     * nome do objeto
     */
    private void despacha(HttpServletRequest request,
	    HttpServletResponse response, String string, String nomeGuerra) {

	RequestDispatcher view;
	request.setAttribute("nomeUsuario", nomeGuerra);
	if (string.equals("salvar")) {

	    request.setAttribute("mensagem", "Usuário incluído no cadastro.");

	}

	else if (string.equals("alterar")) {
	    request.setAttribute("mensagem", "Dados do usuário foram alterados.");

	} else {
	    request.setAttribute("mensagem", "Usuário foi excluído do cadastro.");
	}

	view = request
		.getRequestDispatcher("paginas/administracao/usuarioSalvo.jsp");

	try {
	    view.forward(request, response);
	} catch (ServletException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	logger.info(getServletName());
	int registroUsuario = 0;
	int operacao = Integer.parseInt(request
		.getParameter("operacaoARealizar"));
	registroUsuario = Integer.parseInt(request
		.getParameter("registroUsuario"));

	// Ação a ser realizada
	if (operacao == 1) {

	    try {
		salvar(request, response);
	    } catch (SiscabException e) {
		e.printStackTrace();
	    }

	} else if (operacao == 2) {

	    alterar(request, response, registroUsuario);
	}

	else {

	    deletar(request, response, registroUsuario);
	}
    }

    protected void salvar(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException,
	    SiscabException {

	numRegistro = Integer.parseInt(request.getParameter("registro"));
	nomeGuerra = request.getParameter("nomeGuerra");
	obm = request.getParameter("obm");
	perfil = request.getParameter("perfil");
	email = request.getParameter("email");
	senha = request.getParameter("senha");
	status = request.getParameter("status");

	// Verifica Duplicidade de Pessoas já cadastradas no BD
	List<Usuario> usuarios = UsuarioDao.getInstance()
		.buscarNumRegistroRepetido(numRegistro);
	
	 PerfilUsuario p = PerfilUsuarioDao.getInstance().listarPerfilNome(
		    perfil);
	 
	 OBM cobm = OBMDao.getInstance().listarOBMNome(obm);

	if (!usuarios.isEmpty()) {

	    SiscabException siscab = new SiscabException(
		    "Usuário já cadastrado.");
	    // response.sendRedirect("siscabException.jsp");
	    RequestDispatcher view = request
		    .getRequestDispatcher("/siscabException.jsp");

	    try {
		view.forward(request, response);
	    } catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	} 
	
	else if (p.getId() == 3 && cobm.getId() == 1) {
	    
	    SiscabException siscab = new SiscabException(
	    "O Cocb não possui Operador de OBM!.");
	    RequestDispatcher view = request
	    .getRequestDispatcher("/siscabException.jsp");

	    	try {
		view.forward(request, response);
    		} catch (ServletException e) {
    		    // TODO Auto-generated catch block
    		    e.printStackTrace();
    		} catch (IOException e) {
    		    // TODO Auto-generated catch block
    		    e.printStackTrace();
    		}
	}
	else{
	    
	    OBMDao.getInstance().fechaSession();
	    Usuario usu = new Usuario();
	    usu.setNomeGuerra(nomeGuerra);
	    usu.setNumRegistro(numRegistro);
	    usu.setObm(cobm);
	    usu.setPerfil(p);
	    usu.setEmail(email);
	    usu.setStatus(status);
	    usu.setSenha(senha);

	    UsuarioDao.getInstance().salvar(usu);
	    request.setAttribute("usuario", usu);
	    despacha(request, response, "salvar", usu.getNomeGuerra());

	}
    }
}
