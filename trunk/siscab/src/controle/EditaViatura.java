package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Viatura;

import org.apache.log4j.Logger;

import dao.ViaturaDao;

/**
 * Servlet implementation class EditaViatura
 */
public class EditaViatura extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static Logger logger = Logger.getLogger(EditaViatura.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditaViatura() {
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
	int registro = (Integer.parseInt(request.getParameter("registro")));
	Viatura via = ViaturaDao.getInstance().BuscaViaturaId(registro);
	request.setAttribute("viaturaAtual", via);
	RequestDispatcher view = request
		.getRequestDispatcher("/editaViatura.jsp");

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

}
