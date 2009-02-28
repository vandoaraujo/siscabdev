package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Atendimentos;

/**
 * Servlet implementation class AplicaAcaoAtendimentoDiversasOpcoes
 */
public class AplicaAcaoAtendimentoDiversasOpcoes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Atendimentos at = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AplicaAcaoAtendimentoDiversasOpcoes() {
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
			
		at = (Atendimentos) request.getSession().getAttribute("atendimentoAtual"); 
			
		System.out.println("FUNCIONA" + at.getId());
		
		int registroAtendimento = 0;
	    int operacao = Integer.parseInt(request.getParameter("operacaoARealizar"));
    	registroAtendimento = Integer.parseInt(request.getParameter("registroAtendimento"));
	  
	    
	    //COntrole de qual operacao será realizada
	    
	    if(operacao ==2){
		
	    	editar(request,response, registroAtendimento);	
		
	    }			
		else if(operacao == 3){
			
			sugestaoRota(request, response, registroAtendimento);
		}
	    
		else if(operacao == 4){
			
			viaturasEmpenhadas(request, response,registroAtendimento);
		}
	    
		else if(operacao == 5){
			
			vitimas(request, response,registroAtendimento);
		}
		else if(operacao == 6){
			
			servicosExecutados(request, response,registroAtendimento);
		}
	    
	    
					
	}
		
	private void servicosExecutados(HttpServletRequest request,
			HttpServletResponse response, int registroAtendimento) {
		
		
		RequestDispatcher view;
		HttpSession sessao = request.getSession();
		sessao.setAttribute("atendimentoAtual", at);
		view = request.getRequestDispatcher("/iniciarServico.jsp");
		
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

	private void vitimas(HttpServletRequest request,
			HttpServletResponse response, int registroAtendimento) {
		
		
		RequestDispatcher view;
		HttpSession sessao = request.getSession();
		sessao.setAttribute("atendimentoAtual", at);
		view = request.getRequestDispatcher("/iniciarTelaVitimas.jsp");
		
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

	private void viaturasEmpenhadas(HttpServletRequest request,
			HttpServletResponse response, int registroAtendimento) {

		
		RequestDispatcher view;
		HttpSession sessao = request.getSession();
		sessao.setAttribute("atendimentoAtual", at);
		view = request.getRequestDispatcher("/iniciarViaturasEmpenhadas.jsp");
		
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

	private void sugestaoRota(HttpServletRequest request,
			HttpServletResponse response, int registroAtendimento) {
		
		RequestDispatcher view;
		HttpSession sessao = request.getSession();
		sessao.setAttribute("atendimentoAtual", at);
		view = request.getRequestDispatcher("/iniciarSugestaoRota.jsp");
		
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

	public void editar(HttpServletRequest request,
			HttpServletResponse response,  int registroAtendimento){
	
		
		RequestDispatcher view;
		HttpSession sessao = request.getSession();
		sessao.setAttribute("atendimentoAtual", at);
		view = request.getRequestDispatcher("/editaAtendimento.jsp");
		
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


