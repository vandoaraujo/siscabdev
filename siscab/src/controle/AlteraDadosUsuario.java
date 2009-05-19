package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Usuario;
import dao.UsuarioDao;

/**
 * Servlet implementation class AlteraDadosUsuario
 */
public class AlteraDadosUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlteraDadosUsuario() {
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
		
	    int idUsuario = Integer.parseInt(request.getParameter("usuarioId"));
	    
	    Usuario usu = UsuarioDao.getInstance().BuscaUsuarioId(idUsuario);
	    
	    request.setAttribute("usuario", usu);
	    
	    RequestDispatcher view = request.getRequestDispatcher("dadosUsuarioEdicao.jsp");

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
