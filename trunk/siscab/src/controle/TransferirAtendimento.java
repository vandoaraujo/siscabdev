package controle;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChamadoDao;
import dao.MunicipioDao;
import dao.UsuarioDao;

import modelo.Chamado;
import modelo.Municipio;
import modelo.Usuario;

/**
 * Servlet implementation class TransferirAtendimento
 */
public class TransferirAtendimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher view;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferirAtendimento() {
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
		
		Usuario usuario = (Usuario) getServletContext().getAttribute("usuarioCorrente");
		
		if(!(usuario.getPerfil().getId() == 2) || !(usuario.getPerfil().getId() == 3) || !(usuario.getPerfil().getId() == 4)){
			
			request.setAttribute("descricaoServico", "Transferir Atendimento");
			request.setAttribute("perfil", "Atendente do COCB, Operador da OBM ou Controlador do COCB");
			view = request.getRequestDispatcher("/acessoNegado.jsp");
			
		}
		
		else if((usuario.getPerfil().getId() == 4)){
			
			request.setAttribute("perfil","controlador do COCB");				 			
			view = request.getRequestDispatcher("/pagina1transferirAtendimento.jsp");
		}
		
		else if((usuario.getPerfil().getId() == 3)){
			
			request.setAttribute("perfil","Operador da OBM");				 			
			view = request.getRequestDispatcher("/pagina1transferirAtendimento.jsp");
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


