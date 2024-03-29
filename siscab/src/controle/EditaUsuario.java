package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Usuario;

import org.apache.log4j.Logger;

import dao.UsuarioDao;

/**
 * Servlet implementation class EditaUsuario
 */
public class EditaUsuario extends HttpServlet {

    private static final long serialVersionUID = 1L;
    static Logger logger = Logger.getLogger(EditaUsuario.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditaUsuario() {
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
	Integer id = Integer.parseInt(request.getParameter("registro"));
	Usuario usuario = UsuarioDao.getInstance().BuscaUsuarioId(id);
	request.setAttribute("usuario", usuario);
	RequestDispatcher view = request
		.getRequestDispatcher("EditaUsuario.jsp");
	try {
	    view.forward(request, response);
	} catch (ServletException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

}
