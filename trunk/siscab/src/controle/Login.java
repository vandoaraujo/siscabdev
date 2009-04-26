package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import dao.HibernateUtil;
import dao.UsuarioDao;

/**
 * Servlet implementation class ServletLogin
 */
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Usuario usu = null;
    HibernateUtil h = null;

    static Logger logger = Logger.getLogger(Login.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
	super();
	// logger.info("Inicializando");

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// Faz chamada ao Banco de Dados instanciando uma SessionFactory

	String registro = request.getParameter("numRegistro");
	String senha = request.getParameter("senha");

	logger.info(getServletName());

	boolean b = validaParametros(registro, senha, response, request);
	if (b == true) {

	    try {
		int numRegistro = Integer.parseInt(registro);
		usu = UsuarioDao.getInstance()
			.buscarUsuario(numRegistro, senha);
	    } catch (Exception n) {
		validaParametros(registro, senha, response, request);
	    }

	    getServletContext().setAttribute("usuarioCorrente", usu);
	    validaUsuario(request, usu, response);

	} else {

	    request.setAttribute("mensagem",
		    "É necessário preencher usuário e senha");
	    logger.info("dados incompletos.jsp");
	    RequestDispatcher view = request
		    .getRequestDispatcher("/dadosIncompletos.jsp");
	    view.forward(request, response);
	}

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
	super.init(config);
	// inicializa o sistema de logging com as configurações padrão.
	BasicConfigurator.configure();
	// Configura o Nivel que irá aparecer no Console
	logger.setLevel(Level.INFO);
	h = HibernateUtil.getInstance();

    }

    private boolean validaParametros(String registro, String senha,
	    HttpServletResponse response, HttpServletRequest request)
	    throws IOException {

	if (registro.equals("") || senha.equals("")) {

	    return false;
	}

	return true;

    }

    public void validaUsuario(HttpServletRequest req, Usuario usu,
	    HttpServletResponse response) throws ServletException, IOException {

	UsuarioDao.getInstance().fechaSession();

	if (usu == null) {
	    req.setAttribute("mensagem", "Login e/ou senha inválido!");
	    RequestDispatcher view = req.getRequestDispatcher("/home.jsp");
	    view.forward(req, response);

	}

	else if (!usu.getStatus().equals("ATIVO")) {

	    HttpSession session = req.getSession();
	    session.setAttribute("usuario", usu);
	    RequestDispatcher view = req
		    .getRequestDispatcher("/statusUsuarioInativo.jsp");
	    view.forward(req, response);

	}

	else {

	    HttpSession session = req.getSession();
	    req.setAttribute("session", session);
	    session.setAttribute("usuario", usu);
	    RequestDispatcher view = req
		    .getRequestDispatcher("/paginaPrincipal.jsp");
	    view.forward(req, response);
	}

    }

}
