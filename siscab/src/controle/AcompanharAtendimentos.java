package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Atendimentos;
import modelo.Usuario;
import dao.AtendimentosDao;

/**
 * Servlet implementation class AcompanharAtendimentos
 */
public class AcompanharAtendimentos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher view;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcompanharAtendimentos() {
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
			//Inicia Caso de Uso Acompanhar Atendimentos não finalizados
		
		
		Usuario usuario = (Usuario) getServletContext().getAttribute("usuarioCorrente");
		
		if(!(usuario.getPerfil().getId() == 3)){
			
			request.setAttribute("descricaoServico", "Registrar Chamado");
			request.setAttribute("perfil", "Operador da OBM");
			view = request.getRequestDispatcher("/acessoNegado.jsp");
			
		}
		
		else{
		
		List<Atendimentos> atendimentos = AtendimentosDao.getInstance().listarAtendimentosNaoFinalizados();
		System.out.println("Busco objetos" + atendimentos);
		
		request.setAttribute("atendimentos", atendimentos);
		view = request.getRequestDispatcher("/atendimentosNaoFinalizados.jsp");
		
		}
		
	
		
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
