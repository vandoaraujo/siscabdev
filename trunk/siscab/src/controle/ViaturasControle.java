package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import modelo.TipoViatura;
import modelo.Usuario;
import modelo.Viatura;
import dao.TipoViaturaDao;
import dao.ViaturaDao;

/**
 * Servlet implementation class ViaturasControle
 */
public class ViaturasControle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher view;
	static Logger logger = Logger.getLogger(ViaturasControle.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViaturasControle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info(getServletName());

		Usuario usuario = (Usuario) getServletContext().getAttribute("usuarioCorrente");
		
		if(!(usuario.getPerfil().getId() == 4)){
			
			request.setAttribute("descricaoServico", "Cadastro de Viatura");
			request.setAttribute("perfil", "Controlador do COCB");
			view = request.getRequestDispatcher("/acessoNegado.jsp");
			
		}
		
			view = request.getRequestDispatcher("/viaturaMenu.jsp");
					
		try {
			view.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
