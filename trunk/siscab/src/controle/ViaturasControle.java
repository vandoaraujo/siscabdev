package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Usuario;
import modelo.Viatura;

import org.apache.log4j.Logger;

import dao.ViaturaDao;

/**
 * Servlet implementation class ViaturasControle
 */
public class ViaturasControle extends HttpServlet {
    private static final long serialVersionUID = 1L;
    RequestDispatcher view;
    static Logger logger = Logger.getLogger(ViaturasControle.class);
    private List<Viatura> viaturas = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViaturasControle() {
	super();
	// TODO Auto-generated constructor stub
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

	Usuario usuario = (Usuario) getServletContext().getAttribute(
		"usuarioCorrente");

	if (!(usuario.getPerfil().getId() == 4)) {

	    request.setAttribute("descricaoServico", "Cadastro de Viatura");
	    request.setAttribute("perfil", "Controlador do COCB");
	    view = request.getRequestDispatcher("/acessoNegado.jsp");

	}
	else{

	viaturas = ViaturaDao.getInstance().listar();
	request.setAttribute("viaturas", viaturas);
	view = request.getRequestDispatcher("/viaturaMenu.jsp");
	
	}

	try {
	    view.forward(request, response);
	} catch (ServletException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
