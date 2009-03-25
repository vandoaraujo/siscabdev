package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Atendimento;
import modelo.OBM;
import dao.AtendimentoDao;
import dao.OBMDao;

/**
 * Servlet implementation class RepasseAtendimento
 */
public class RepasseAtendimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher view;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RepasseAtendimento() {
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
		
		int registro = Integer.parseInt(request.getParameter("registro"));
		int perfilUsuario = Integer.parseInt(request.getParameter("perfilUsuario"));
		
		Atendimento at = AtendimentoDao.getInstance().BuscaAtendimentoId(registro);
		
		System.out.println("IMPRIME PERFIL ATUAL USUARIO" + perfilUsuario);
				
		//1 caso - Controlador do COCB transfere atendimento para uma daterminada OBM
		if(perfilUsuario == 4){
			
			List<OBM> obm = OBMDao.getInstance().listarTodasOBMsExcetoCOCB();
			request.setAttribute("obm", obm);
						
		}
		//2 caso - Operador da OBM transfere atendimento para o COCB
		/*else{
			
			request.setAttribute("obm", "COCB");
			
		}*/
		
		request.setAttribute("atendimentos", at );
		request.setAttribute("perfil","Operador da OBM");				 			
		view = request.getRequestDispatcher("/transferirAtendimentoPagina2.jsp");
		
		try {
			view.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
		


}
