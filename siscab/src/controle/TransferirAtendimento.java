package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Atendimento;
import modelo.Usuario;
import dao.AtendimentoDao;

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
		
		if(!(usuario.getPerfil().getId() == 2) && !(usuario.getPerfil().getId() == 3) && !(usuario.getPerfil().getId() == 4)){
			
			request.setAttribute("descricaoServico", "Transferir Atendimento");
			request.setAttribute("perfil", "Atendente do COCB, Operador da OBM ou Controlador do COCB");
			view = request.getRequestDispatcher("/acessoNegado.jsp");
			
		}
		//Usuario é o controlador do COCB
		else if((usuario.getPerfil().getId() == 4)){
			
			
			//Passo a OBM do usuário. Se o usuário tem o perfil de controlador do COCB ou atendente do
			//COCB ele está cadastrado no COCB.
			List<Atendimento> at = AtendimentoDao.getInstance().BuscaAtendimentosRedirecionadosAoCOCB(usuario.getObm().getId());
			
			request.setAttribute("atendimentos", at );
			request.setAttribute("obm", "COCB");
			request.setAttribute("perfil","controlador do COCB");				 			
			view = request.getRequestDispatcher("/transferirAtendimentoPagina1.jsp");
		}
		
		else if((usuario.getPerfil().getId() == 3)){
			
			List<Atendimento> at = AtendimentoDao.getInstance().BuscaAtendimentosRedirecionadosAoCOCB(usuario.getObm().getId());
			
			request.setAttribute("atendimentos", at );
			request.setAttribute("obm", usuario.getObm().getNome());
			request.setAttribute("perfil","Operador da OBM");				 			
			view = request.getRequestDispatcher("/transferirAtendimentoPagina1.jsp");
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


