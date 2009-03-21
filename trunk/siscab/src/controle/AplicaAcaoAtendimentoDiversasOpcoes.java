package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Atendimentos;
import modelo.MovimentaViatura;
import modelo.ServicoRealizado;
import modelo.Usuario;
import modelo.Viatura;
import modelo.VitimaAtendida;
import dao.MovimentaViaturaDao;
import dao.ServicoRealizadoDao;
import dao.ViaturaDao;
import dao.VitimaAtendidaDao;

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
		else if(operacao == 7){
			
			finalizarChamado(request, response,registroAtendimento);
		}
		
	    
	    
					
	}
		
	//Implementar
	private void finalizarChamado(HttpServletRequest request,
			HttpServletResponse response, int registroAtendimento) {
		// TODO Auto-generated method stub
		
	}

	private void servicosExecutados(HttpServletRequest request,
			HttpServletResponse response, int registroAtendimento) {
		

		List <ServicoRealizado> servicos = ServicoRealizadoDao.getInstance().listaServicosReferenteUmAtendimento(at.getId());
				
		RequestDispatcher view;
		HttpSession sessao = request.getSession();
		sessao.setAttribute("servicos", servicos);
		sessao.setAttribute("registroAtendimento",at.getId());
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
		
		System.out.println("NUM constatado de vitimas" + at.getChamado_id().getNumaproxvitimas());

		List <VitimaAtendida> vitimas = VitimaAtendidaDao.getInstance().listaVitimasReferenteUmAtendimento(at.getId());
				
		RequestDispatcher view;
		HttpSession sessao = request.getSession();
		sessao.setAttribute("vitimas", vitimas);
		sessao.setAttribute("atendimentoAtual", at);
		view = request.getRequestDispatcher("/iniciarTelaVitimas.jsp");
		
	try {
		view.forward(request, response);
	} catch (ServletException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
		
	}

	private void viaturasEmpenhadas(HttpServletRequest request,
			HttpServletResponse response, int registroAtendimento) {

		//Usuario usuario = (Usuario) getServletContext().getAttribute("usuarioCorrente");

		List <Viatura> viaturas = new ArrayList();
		
		List <Viatura> tiposEventosViatura = MovimentaViaturaDao.getInstance().ListarViaturaNaoRepetidasEmAtendimento(at.getId());
	
							
		for(int i=0;i<tiposEventosViatura.size();i++){
			
			Integer viatura_id = tiposEventosViatura.get(i).getId();
			Viatura v = ViaturaDao.getInstance().BuscaViaturaId(viatura_id);
			viaturas.add(v);
			
		}
				
		HttpSession sessao = request.getSession();
		sessao.setAttribute("viaturas", viaturas);
		RequestDispatcher view;
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


