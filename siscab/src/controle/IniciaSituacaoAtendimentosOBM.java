package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class IniciaSituacaoAtendimentosOBM
 */
public class IniciaSituacaoAtendimentosOBM extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static Logger logger = Logger
	    .getLogger(IniciaSituacaoAtendimentosOBM.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IniciaSituacaoAtendimentosOBM() {
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
	RequestDispatcher view;
	view = request.getRequestDispatcher("/painelSituacaoAtendimentos.jsp");

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
