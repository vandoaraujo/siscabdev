package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Viatura;
import dao.ViaturaDao;

/**
 * Servlet implementation class ViaturasControle
 */
public class ViaturasControle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		List viatura = (List<Viatura>) ViaturaDao.getInstance().listar();
		
		if (viatura.size() == 0){
			System.out.println("FUNCIONA!");
		}
		if (viatura.isEmpty()){
			System.out.println("FUNCIONA IF 2!");
		}
		
		if(viatura.contains(null)){
			
			System.out.println("Lista vazia");
		}
		request.setAttribute("viaturas", viatura);
		RequestDispatcher view = request.getRequestDispatcher("/listarViaturas.jsp");
		view.forward(request, response);
	}

}
