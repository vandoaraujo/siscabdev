package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.TipoViatura;

import org.apache.log4j.Logger;

import dao.TipoViaturaDao;

/**
 * Servlet implementation class ConsultaViaturas
 */
public class ConsultaViaturas extends HttpServlet {
    private static final long serialVersionUID = 1L;
    RequestDispatcher view = null;

    static Logger logger = Logger.getLogger(ConsultaViaturas.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaViaturas() {
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
	List<TipoViatura> tipos = TipoViaturaDao.getInstance()
		.listarTodosTiposViaturas();
	HttpSession sessao = request.getSession();
	sessao.setAttribute("tiposViatura", tipos);
	view = request.getRequestDispatcher("/procurarViaturas.jsp");

	try {
	    view.forward(request, response);
	} catch (ServletException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

}
