package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sun.misc.Compare;

import modelo.Atendimento;
import modelo.ModoFechamento;
import modelo.ServicoRealizado;
import modelo.Usuario;
import modelo.Viatura;
import modelo.VitimaAtendida;
import dao.ModoFechamentoDao;
import dao.MovimentaViaturaDao;
import dao.ServicoRealizadoDao;
import dao.ViaturaDao;
import dao.VitimaAtendidaDao;

/**
 * Servlet implementation class AplicaAcaoAtendimentoDiversasOpcoes
 */
public class AplicaAcaoAtendimentoDiversasOpcoes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Atendimento at = null;
       
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
			
		at = (Atendimento) request.getSession().getAttribute("atendimentoAtual"); 
		int registroAtendimento = 0;
	    int operacao = Integer.parseInt(request.getParameter("operacaoARealizar"));
    	registroAtendimento = Integer.parseInt(request.getParameter("registroAtendimento"));    	
    	
	    //operacao que  será realizada
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
		
	private void finalizarChamado(HttpServletRequest request,
			HttpServletResponse response, int registroAtendimento) {
		
		List<ModoFechamento> modo = ModoFechamentoDao.getInstance().listarTodosModosFechamento();
		
		RequestDispatcher view;
		HttpSession sessao = request.getSession();
		sessao.setAttribute("modos", modo);
		sessao.setAttribute("registroAtendimento",at.getId());
		sessao.setAttribute("numeroAtendimento", at.getAtendimento_numero());
		view = request.getRequestDispatcher("/informaModoFechamento.jsp");
		
	try {
		view.forward(request, response);
	} catch (ServletException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
		
	}
		
		
	private void servicosExecutados(HttpServletRequest request,
			HttpServletResponse response, int registroAtendimento) {
		

		List <ServicoRealizado> servicos = ServicoRealizadoDao.getInstance().listaServicosReferenteUmAtendimento(at.getId());
				
		RequestDispatcher view;
		HttpSession sessao = request.getSession();
		sessao.setAttribute("servicos", servicos);
		sessao.setAttribute("numeroAtendimento",at.getAtendimento_numero());
		sessao.setAttribute("registroAtendimento",at.getId());
		sessao.setAttribute("atendimentoAtual", at);
		view = request.getRequestDispatcher("/iniciarServico.jsp");
		
	try {
		view.forward(request, response);
	} catch (ServletException e) {
		e.printStackTrace();
	} catch (IOException e) {
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
		
		at = (Atendimento) request.getSession().getAttribute("atendimentoAtual"); 
		Usuario usuario = (Usuario) getServletContext().getAttribute("usuarioCorrente");
    	
		//Object objCoordX_Operador = null;
		//objCoordX_Operador = (Object)usuario.getObm().getCoordX();
		//Double CoordX_Operador = 0.0;
		//Double CoordY_Operador = 0.0;

		//Object objCoordX_Acidente = null;
		//objCoordX_Acidente = (Object)at.getCoordx();
		//Double CoordX_Acidente = 0.0;
		//Double CoordY_Acidente = 0.0;
		
    	//if (!objCoordX_Operador.equals(null)){    	
	    	request.setAttribute("CoordX_Operador", usuario.getObm().getCoordX());
	    	request.setAttribute("CoordY_Operador", usuario.getObm().getCoordX());    		    		    
    	//}
    	//else
    	//{
    	//	request.setAttribute("CoordX_Operador", CoordX_Operador);
	    //	request.setAttribute("CoordY_Operador", CoordY_Operador);  
    	//}
    	
    	//if (!objCoordX_Acidente.equals(null)){    	
    		request.setAttribute("CoordX_Acidente", at.getCoordx());
    		request.setAttribute("CoordY_Acidente", at.getCoordy());    		    		    
    	//}
    	//else
    	//{
    	//	request.setAttribute("CoordX_Acidente", CoordX_Acidente);
	    //	request.setAttribute("CoordY_Acidente", CoordY_Acidente);  
    	//}
		
    	request.setAttribute("NomeOrigem", usuario.getObm().getNome());
		request.setAttribute("EnderecoOrigem", usuario.getObm().getLogradouro());
		request.setAttribute("NumeroOrigem", usuario.getObm().getNumCompl());
		request.setAttribute("BairroOrigem", usuario.getObm().getBairro());
		request.setAttribute("MunicipioOrigem", usuario.getObm().getMunicipio().getMunicipio_nome());
					
		request.setAttribute("NumeroAtendimento", at.getAtendimento_numero());
		request.setAttribute("EnderecoDestino", at.getLogradouro());
		request.setAttribute("NumeroDestino", at.getNumcompl());
		request.setAttribute("BairroDestino", at.getBairro());
		request.setAttribute("MunicipioDestino", at.getMunicipio_id().getMunicipio_nome());		
		
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


