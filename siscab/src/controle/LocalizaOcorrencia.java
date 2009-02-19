package controle;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Chamado;
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
	
	    //Verificar questao do numero do atendimento!	
		
		
		//Evento acionado com o botão de Procurar Ocorrencias próximas
		int operacaoARealizar = Integer.parseInt(request.getParameter("operacaoARealizar"));
		String municipio = request.getParameter("municipio");
		String bairro = request.getParameter("bairro");
		
		
		//Seto dois atributos importantes
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
		String nomeUsuario = request.getParameter("usuario");
		String nomeObmUsuario = request.getParameter("obmUsuario");
		
		String date = request.getParameter("dataRegistrada");
		DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");  
		java.sql.Date data = null;
		try {
			data = new java.sql.Date(fmt.parse(date).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		
		//long horaRegistrada = Long.parseLong(request.getParameter("horaRegistrada"));
		String origemChamado = request.getParameter("origem");
		String nomeSolicitante =  request.getParameter("nomeSolicitante");
		String telefone = request.getParameter("telefoneSolicitante");
		String aproxVitimas = request.getParameter("numAproximadoVitimas");
		if(aproxVitimas.equals("")) aproxVitimas = "0";
		int numAproximadoVitimas = Integer.parseInt(aproxVitimas);
		String infoComplementares = request.getParameter("infoComplementares");
		String tipoOcorrencia = request.getParameter("tipoOcorrencia");
	    String logradouro = request.getParameter("logradouro");
		String numComplemento = request.getParameter("numComplemento");
		String coordXAuxiliar = request.getParameter("coordX");
		if(coordXAuxiliar.equals("")) coordXAuxiliar = "0";
		float coordX = Float.parseFloat(coordXAuxiliar);
		String coordYAuxiliar = request.getParameter("coordY");
		if(coordYAuxiliar.equals("")) coordYAuxiliar = "0";
		float coordY = Float.parseFloat(coordYAuxiliar);		
		String obmReceberSolicitacao = request.getParameter("obmReceberSolicitacao");
		
	
		
			
		//Ver este campo --- JSP passa o valor 1
		int registroOcorrencia = Integer.parseInt(request.getParameter("registroOcorrencia"));
		

		
		//Criacao do Objeto Chamado com os campos da tela
		Chamado chamado = new Chamado();
		chamado.setHorainicio(data);
		//chamado.setHoratermino(horatermino);
		chamado.setId(numeroGeradoChamado);
		chamado.setInfocomplementares(infoComplementares);
		//chamado.setNaturezaChamado(naturezaChamado);
		chamado.setNomeSolicitante(nomeSolicitante);
		chamado.setNumaproxvitimas(numAproximadoVitimas);
		//Buscar no Banco a OBM
		OBM obmUsuario = OBMDao.getInstance().listarOBMNome(nomeObmUsuario);
		//OBM que recebeu aquele chamado, não o atendimento
		chamado.setObm(obmUsuario);
		chamado.setOrigem(origemChamado);
		chamado.setTelefoneSolicitante(telefone);
		
		//Cria objeto NaturezaChamados que será utilizada na próxima página
		NaturezaChamados n = new NaturezaChamados();
		ArrayList<String> nChamados =  n.getAr();
		
		//Repassa os atributos de Atendimentos, pois pode haver Ocorrencias Próximas
		request.setAttribute("tipoOcorrencia", tipoOcorrencia);
		request.setAttribute("logradouro", logradouro);
		request.setAttribute("numComplemento", numComplemento);
		request.setAttribute("coordY", coordY);
		request.setAttribute("coordX", coordX);
		request.setAttribute("obmSolicitacao", obmReceberSolicitacao);
		//parametros de chamado
		request.setAttribute("naturezaChamados", nChamados);
		request.setAttribute("objChamado", chamado);
		
		if(operacaoARealizar == 1){
			RequestDispatcher view = request.getRequestDispatcher("/MostraMapaLocalOcorrencia.jsp");
			view.forward(request, response);
		}

		
			
		
		
	}

}
