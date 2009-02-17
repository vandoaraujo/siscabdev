package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Municipio;
import modelo.OBM;
import dao.MunicipioDao;
import dao.OBMDao;

/**
 * Servlet implementation class EditaOBM
 */
public class EditaOBM extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditaOBM() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int registro = (Integer.parseInt(request.getParameter("registro")));
		OBM obm = (OBM)OBMDao.getInstance().BuscaOBMId(registro);
		List<Municipio> municipios = (List<Municipio>)MunicipioDao.getInstance().listarTodosMunicipios();
		request.setAttribute("municipios", municipios);
		request.setAttribute("obmAtual",obm );
		
		
		RequestDispatcher view = request.getRequestDispatcher("/editaOBM.jsp");
		
	
		
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
