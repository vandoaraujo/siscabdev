package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.OBM;
import modelo.TipoViatura;
import modelo.Viatura;

import org.apache.log4j.Logger;

import dao.OBMDao;
import dao.TipoViaturaDao;
import dao.ViaturaDao;

/**
 * Servlet implementation class AlteraStatusViatura
 */
public class AlteraStatusViatura extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String novoStatusViatura;
	private String obsViatura;
	private int registroViatura;
	static Logger logger = Logger.getLogger(AlteraStatusViatura.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlteraStatusViatura() {
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
		
	    	logger.info(getServletName().toString());
		novoStatusViatura = request.getParameter("novoStatus");
		obsViatura = request.getParameter("obsViatura");
		registroViatura = Integer.parseInt(request
			.getParameter("registroViatura"));

		alterar(request, response, registroViatura);

	}
	
	 private void alterar(HttpServletRequest request,
		    HttpServletResponse response, int registroViatura) {

		Viatura via = ViaturaDao.getInstance().BuscaViaturaId(registroViatura);
		// Viatura em atendimento não pode ser modificada
		if (via.getViatura_status().equals("Em atendimento")) {

		    despacha(request, response, "Em atendimento", via.getNumero());

		} else {

		    via.setViatura_obs(obsViatura);
		    via.setViatura_status(novoStatusViatura);
		    ViaturaDao.getInstance().atualizar(via);
		    despacha(request, response, "alterar", via.getNumero());
		}
	    }
	 
	    /*
	     * Recebe como parametro HttpRequest, response, a acao a ser executada e o
	     * nome do objeto
	     */
	    private void despacha(HttpServletRequest request,
		    HttpServletResponse response, String string, String numeroViatura) {

		RequestDispatcher view;
		request.setAttribute("numeroViatura", numeroViatura);
		if (string.equals("alterar")) {
		    request.setAttribute("mensagem", "Situação da viatura foi atualizada no cadastro.");
		}
		else if (string.equals("Em atendimento")) {
		    request
			    .setAttribute("mensagem",
				    "Situacao da viatura não pode ser alterada no momento, pois está em atendimento.");

		}
		
		view = request.getRequestDispatcher("/mensagemStatusViatura.jsp");

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
