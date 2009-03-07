package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Atendimentos;
import modelo.MovimentaViatura;
import modelo.Viatura;
import dao.AtendimentosDao;
import dao.MovimentaViaturaDao;
import dao.ViaturaDao;

/**
 * Servlet implementation class NovaMovimentacaoViatura
 */
public class NovaMovimentacaoViatura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NovaMovimentacaoViatura() {
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
		
		
	    int registroViatura = (Integer.parseInt(request.getParameter("registro")));
	    int numeroAtendimento = (Integer.parseInt(request.getParameter("numeroAtendimento")));
	    
		Viatura via = (Viatura)ViaturaDao.getInstance().BuscaViaturaId(registroViatura);
		Atendimentos atendimento = (Atendimentos)AtendimentosDao.getInstance().BuscaAtendimentoId(numeroAtendimento);
		
		List<MovimentaViatura> tiposEventosViaturaAtendimento = MovimentaViaturaDao.getInstance().listaTiposEventosViaturaAtendimento(atendimento.getId(), via.getId());
		
		HttpSession sessao = request.getSession();
		sessao.setAttribute("tiposeventosViaturaAtendimento", tiposEventosViaturaAtendimento);
		sessao.setAttribute("viaturaAtual",via);
		sessao.setAttribute("atendimento", atendimento);
		RequestDispatcher view = request.getRequestDispatcher("/listaEventosViaturaAtendimento.jsp");
		
	
		
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


