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

import modelo.Municipio;
import modelo.NaturezaChamados;
import modelo.OBM;
import dao.AtendimentosDao;
import dao.MunicipioDao;
import dao.OBMDao;

/**
 * Servlet implementation class LocalizaOcorrencia
 */
public class LocalizaOcorrencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocalizaOcorrencia() {
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
	
		
		//Evento acionado com o bot�o de Procurar Ocorrencias pr�ximas
		int operacaoARealizar = Integer.parseInt(request.getParameter("operacaoARealizar"));
		String municipio = request.getParameter("municipio");
		String bairro = request.getParameter("bairro");
		bairro.toLowerCase();
		
		System.out.println("################# Atributo vindo de Regitsrar Chamado  ---- Municipio");
		System.out.println(municipio);
		
		
		request.setAttribute("municipio", municipio);
		//Set dois atributos importantes
		request.setAttribute("bairro", bairro);
		
		if(operacaoARealizar == 2){
			
			
			Municipio mun = MunicipioDao.getInstance().listarMunicipioNome(municipio);
			List at = AtendimentosDao.getInstance().listarOcorrenciasProximas(mun.getId(), bairro);
			request.setAttribute("municipio", municipio);
			request.setAttribute("listaAtendimentosProximos", at);
		    RequestDispatcher view = request.getRequestDispatcher("/ProcurarOcorrenciasProximas.jsp");
			view.forward(request, response);
		}
		
		
		int numeroGeradoChamado = Integer.parseInt(request.getParameter("idChamado")); //cast
				
		//Este parametro n�o � usado para este caso de Uso...Seria apenas para log.
		String nomeUsuario = request.getParameter("usuario");
		
		//Esta OBm do objeto chamado ir� entrar no Caso de Uso transferir Atendimento como OBM default...
		String nomeObmUsuario = request.getParameter("obmUsuario");
		
		String origemChamado = request.getParameter("origem");
		String nomeSolicitante =  request.getParameter("nomeSolicitante");
		String telefone = request.getParameter("telefoneSolicitante");
		String aproxVitimas = request.getParameter("numAproximadoVitimas");
		if(aproxVitimas.equals("")) aproxVitimas = "0";
		int numAproximadoVitimas = Integer.parseInt(aproxVitimas);
		String infoComplementares = request.getParameter("infoComplementares");
				
		//Ver este campo --- JSP passa o valor 1
		int registroOcorrencia = Integer.parseInt(request.getParameter("registroOcorrencia"));
		
		//Cria objeto NaturezaChamados que ser� utilizada na pr�xima p�gina
		NaturezaChamados n = new NaturezaChamados();
		ArrayList<String> nChamados =  n.getAr();
		
		ArrayList<OBM> obms = (ArrayList<OBM>) OBMDao.getInstance().listarTodasOBMs();
						
	
	
		request.setAttribute("numeroChamado", numeroGeradoChamado);
		request.setAttribute("obms", obms);
		request.setAttribute("origemChamado", origemChamado);
		request.setAttribute("nomeSolicitante", nomeSolicitante);
		request.setAttribute("telefone", telefone);
		request.setAttribute("aproxVitimas", numAproximadoVitimas);
		request.setAttribute("naturezaChamados", nChamados);
		request.setAttribute("infoComplementares", infoComplementares);
		request.setAttribute("nomeObmUsuario", nomeObmUsuario);
			
		if(operacaoARealizar == 1){
			RequestDispatcher view = request.getRequestDispatcher("/MostraMapaLocalOcorrencia.jsp");
			view.forward(request, response);
		}	
		
	}

}