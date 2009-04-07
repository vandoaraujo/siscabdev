package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.VitimaAtendida;
import dao.VitimaAtendidaDao;

/**
 * Servlet implementation class EditaVitima
 */
public class EditaVitima extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditaVitima() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer id = Integer.parseInt(request.getParameter("registro"));
		
		String atendimentoAtual = request.getParameter("atendimentoAtual");
		
		VitimaAtendida vitima = VitimaAtendidaDao.getInstance().BuscaVitimaId(id); 
		
		request.setAttribute("vitima", vitima);
		request.setAttribute("atendimentoAtual", atendimentoAtual);
		
		RequestDispatcher view = request.getRequestDispatcher("editaVitima.jsp");
		try {
			view.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}
