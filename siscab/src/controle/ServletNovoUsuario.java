package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.OBM;

import dao.OBMDao;

/**
 * Servlet implementation class ServletNovoUsuario
 */
public class ServletNovoUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletNovoUsuario() {
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
			
		List<OBM> obms = OBMDao.getInstance().listarTodasOBMs();
		OBMDao.getInstance().fechaSession();
		
		request.setAttribute("obms", obms);
		
		RequestDispatcher view = request.getRequestDispatcher("paginas/administracao/novoUsuario.jsp");
		view.forward(request, response);
		
	}

}
