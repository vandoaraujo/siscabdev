package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import modelo.Atendimento;
import dao.AtendimentoDao;

/**
 * Servlet implementation class EditaAtendimento
 */
public class EditaAtendimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	static Logger logger = Logger.getLogger(EditaAtendimento.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditaAtendimento() {
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
		
		logger.info(getServletName());
		int registro = (Integer.parseInt(request.getParameter("registro")));
		Atendimento atendimento = (Atendimento)AtendimentoDao.getInstance().BuscaAtendimentoAtendimentoNumero(registro);
		
		HttpSession sessao = request.getSession();
		sessao.setAttribute("atendimentoAtual", atendimento);
		RequestDispatcher view = request.getRequestDispatcher("/acompanharAtendimentoOpcoes.jsp");
		
	
		
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

