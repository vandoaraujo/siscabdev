package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Chamado;
import modelo.Municipio;
import modelo.Usuario;
import modelo.Viatura;

import org.apache.log4j.Logger;

import dao.MunicipioDao;
import dao.UsuarioDao;
import dao.ViaturaDao;

/**
 * Servlet implementation class SituacaoViaturasOBM
 */
public class SituacaoViaturasOBM extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher view;
	static Logger logger = Logger.getLogger(SituacaoViaturasOBM.class);
	private List<Viatura> viaturas = null;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SituacaoViaturasOBM() {
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
		Usuario usuario = (Usuario) getServletContext().getAttribute(
			"usuarioCorrente");

		if ((!(usuario.getPerfil().getId() == 3))) {

		    request.setAttribute("descricaoServico", "Situação das viaturas");
		    request.setAttribute("perfil","Operador da OBM");
		    view = request.getRequestDispatcher("/acessoNegado.jsp");

		}

		else {
		    
		    viaturas = ViaturaDao.getInstance().listaViaturasObm(usuario.getObm().getId());
		    request.setAttribute("viaturas", viaturas);
		    view = request.getRequestDispatcher("/situacaoViaturas.jsp");

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
