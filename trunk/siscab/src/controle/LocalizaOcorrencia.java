package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Atendimento;
import modelo.Municipio;
import modelo.OBM;
import dao.AtendimentoDao;
import dao.MunicipioDao;
import dao.OBMDao;

/**
 * Servlet implementation class LocalizaOcorrencia
 */
public class LocalizaOcorrencia extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	String municipio = null;
	String bairro = null;
	String endereco = null;
	String numero = null;		
	
       
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
	
		this.request = request;
		this.response = response;
		
		//Evento acionado com o botão de Procurar Ocorrencias próximas
		int operacaoARealizar = Integer.parseInt(request.getParameter("operacaoARealizar"));
		municipio = request.getParameter("municipio");
		bairro = request.getParameter("bairro");
		endereco = request.getParameter("endereco");
		numero = request.getParameter("numero");		
		bairro.toLowerCase();
		
		request.setAttribute("municipio", municipio);
		//Set dois atributos importantes
		request.setAttribute("bairro", bairro);
		
		//Procura Ocorrencias Proximas conforme o evento que o botao acionou
		if(operacaoARealizar == 2){
			buscaChamadosProximos();
		}
		
		int numeroGeradoChamado = Integer.parseInt(request.getParameter("idChamado")); //cast
				
		//Este parametro não é usado para este caso de Uso...Seria apenas para log.
		String nomeUsuario = request.getParameter("usuario");
		
		//Esta Obm do objeto chamado irá entrar no Caso de Uso transferir Atendimento como OBM default...
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
		
		ArrayList<OBM> obms = (ArrayList<OBM>) OBMDao.getInstance().listarTodasOBMs();
		
		request.setAttribute("numeroChamado", numeroGeradoChamado);
		request.setAttribute("obms", obms);
		request.setAttribute("origemChamado", origemChamado);
		request.setAttribute("nomeSolicitante", nomeSolicitante);
		request.setAttribute("telefone", telefone);
		request.setAttribute("aproxVitimas", numAproximadoVitimas);
		request.setAttribute("infoComplementares", infoComplementares);
		request.setAttribute("nomeObmUsuario", nomeObmUsuario);
		request.setAttribute("endereco", endereco);
		request.setAttribute("numero", numero);
			
		if(operacaoARealizar == 1){
			RequestDispatcher view = request.getRequestDispatcher("/MostraMapaLocalOcorrencia.jsp");
			view.forward(request, response);
		}
		
		
	}
	
	private void buscaChamadosProximos() throws ServletException, IOException {
		
		Municipio mun = MunicipioDao.getInstance().listarMunicipioNome(municipio);
		List<Atendimento> at = AtendimentoDao.getInstance().listarOcorrenciasProximas(mun.getId(), bairro);
		request.setAttribute("municipio", municipio);
		request.setAttribute("listaAtendimentosProximos", at);
	    RequestDispatcher view = request.getRequestDispatcher("/ProcurarOcorrenciasProximas.jsp");
		view.forward(request, response);
		
		
	}

	
}
