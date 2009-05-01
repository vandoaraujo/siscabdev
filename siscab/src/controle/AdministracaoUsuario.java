package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Usuario;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class AdministracaoUsuario
 */
public class AdministracaoUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;
    RequestDispatcher view;

    static Logger logger = Logger.getLogger(AdministracaoUsuario.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministracaoUsuario() {
	super();

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

	if (!(usuario.getPerfil().getId() == 1)) {

	    request.setAttribute("descricaoServico", "Cadastro de Usu�rios");
	    request.setAttribute("perfil", "Administrador");
	    view = request.getRequestDispatcher("/acessoNegado.jsp");
	} else {
	    view = request.getRequestDispatcher("/administracao_usuario.jsp");
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