package controle;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Municipio;
import modelo.NaturezaChamados;
import modelo.OBM;
import modelo.TiposOcorrencia;
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
	
	    //Verificar questao do numero do atendimento!	
		
		
		//Evento acionado com o botão de Procurar Ocorrencias próximas
		int operacaoARealizar = Integer.parseInt(request.getParameter("operacaoARealizar"));
		String municipio = request.getParameter("municipio");
		String bairro = request.getParameter("bairro");
		
		
		//Set dois atributos importantes
		request.setAttribute("municipio", municipio);
		request.setAttribute("bairro", bairro);
		
		if(operacaoARealizar == 2){
			
			
			Municipio mun = MunicipioDao.getInstance().listarMunicipioNome(municipio);
			List at = AtendimentosDao.getInstance().listarOcorrenciasProximas(mun.getId(), bairro);
			request.setAttribute("listaAtendimentosProximos", at);
		    RequestDispatcher view = request.getRequestDispatcher("/ProcurarOcorrenciasProximas.jsp");
			view.forward(request, response);
		}
		
		
		int numeroGeradoChamado = Integer.parseInt(request.getParameter("idChamado")); //cast
				
		//Este parametro não é usado para este caso de Uso...Seria apenas para log.
		String nomeUsuario = request.getParameter("usuario");
		
		//Esta OBm do objeto chamado irá entrar no Caso de Uso transferir Atendimento como OBM default...
		String nomeObmUsuario = request.getParameter("obmUsuario");
		
	
		//Faz o parse de String em data		
		String date = request.getParameter("dataRegistrada");
		DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy - hh:mm");  
		Date data = null;
		try {
			data = new Date(fmt.parse(date).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		
		System.out.println("#########################");

		System.out.println("data" + data);
		
		System.out.println("#########################");

		
	
		String origemChamado = request.getParameter("origem");
		String nomeSolicitante =  request.getParameter("nomeSolicitante");
		String telefone = request.getParameter("telefoneSolicitante");
		String aproxVitimas = request.getParameter("numAproximadoVitimas");
		if(aproxVitimas.equals("")) aproxVitimas = "0";
		int numAproximadoVitimas = Integer.parseInt(aproxVitimas);
		String infoComplementares = request.getParameter("infoComplementares");
	
	    
		//########################################//
		//Atriibutos de Atendimento, vai para outro servlet
		/*String logradouro = request.getParameter("logradouro");
		String numComplemento = request.getParameter("numComplemento");
		String coordXAuxiliar = request.getParameter("coordX");
		if(coordXAuxiliar.equals("")) coordXAuxiliar = "0";
		float coordX = Float.parseFloat(coordXAuxiliar);
		String coordYAuxiliar = request.getParameter("coordY");
		if(coordYAuxiliar.equals("")) coordYAuxiliar = "0";
		float coordY = Float.parseFloat(coordYAuxiliar);		
		String obmReceberSolicitacao = request.getParameter("obmReceberSolicitacao");
	    String tipoOcorrencia = request.getParameter("tipoOcorrencia");
		
		*/
		
				
		//Ver este campo --- JSP passa o valor 1
		int registroOcorrencia = Integer.parseInt(request.getParameter("registroOcorrencia"));
		
		//Cria objeto NaturezaChamados que será utilizada na próxima página
		NaturezaChamados n = new NaturezaChamados();
		ArrayList<String> nChamados =  n.getAr();
		
		
		/*//aTRIBUTOS DO SERVLET QUE IRÁ TRATAR OS DADOS DE ATENDIMENTO
		//Repassa os atributos de Atendimentos, pois pode haver Ocorrencias Próximas
		request.setAttribute("tipoOcorrencia", tipoOcorrencia);
		request.setAttribute("logradouro", logradouro);
		request.setAttribute("numComplemento", numComplemento);
		request.setAttribute("coordY", coordY);
		request.setAttribute("coordX", coordX);
		request.setAttribute("obmSolicitacao", obmReceberSolicitacao);
		//parametros de chamado
		request.setAttribute("chamadoAtual", chamado);
		
		*/
		
		TiposOcorrencia tipo = new TiposOcorrencia();
		ArrayList<String> nTiposOcorrencia = tipo.getAr();
		request.setAttribute("tipoOcorrencia", nTiposOcorrencia);
		
		ArrayList<OBM> obms = (ArrayList<OBM>) OBMDao.getInstance().listarTodasOBMs();
		
					
		
		request.setAttribute("numeroChamado", numeroGeradoChamado);
		request.setAttribute("data", date);
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
