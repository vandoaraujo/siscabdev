package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import modelo.OBM;
import modelo.PerfilUsuario;
import dao.OBMDao;
import dao.PerfilUsuarioDao;

/**
 * Servlet implementation class ServletNovoUsuario
 */
public class NovoUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int id;   
	static Logger logger = Logger.getLogger(NovoUsuario.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NovoUsuario() {
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
		List<OBM> obms = OBMDao.getInstance().listarTodasOBMs();
		List<PerfilUsuario> perfil = PerfilUsuarioDao.getInstance().listarTodosPerfis();
		request.setAttribute("perfil", perfil);
		request.setAttribute("obms", obms);
		RequestDispatcher view = request.getRequestDispatcher("paginas/administracao/novoUsuario.jsp");
		view.forward(request, response);
		
	}

}
