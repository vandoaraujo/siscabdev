package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.OBM;
import modelo.Usuario;

import dao.OBMDao;

/**
 * Servlet implementation class ObmControle
 */
public class ObmControle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher view;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObmControle() {
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
		
		Usuario usuario = (Usuario) getServletContext().getAttribute("usuarioCorrente");
		
		if(!(usuario.getPerfil().getId() == 1)){
			
			request.setAttribute("descricaoServico", "Cadastro de Obm");
			request.setAttribute("perfil", "Administrador");
			view = request.getRequestDispatcher("/acessoNegado.jsp");
			
		}
		
		else{
			
			List obms = (List<OBM>) OBMDao.getInstance().listarTodasOBMs();
			request.setAttribute("obms", obms);
			view = request.getRequestDispatcher("/listarOBMs.jsp");
						
			
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
