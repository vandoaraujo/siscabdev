package controle;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Chamado;
import modelo.NaturezaChamado;
import modelo.OBM;
import modelo.Usuario;
import dao.ChamadoDao;
import dao.NaturezaChamadoDao;
import dao.OBMDao;

/**
 * Servlet implementation class FinalizarChamadoIniciarAtendimento
 */
public class FinalizarChamadoIniciarAtendimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Date dataFim = null;
    private Date dataInicio = null;
    int identificadorChamado, numAproxVitimas = 0;
    String infoComplementares, nomeSolicitante, obmSolicitada, naturezaChamado, origemChamado, telefone = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalizarChamadoIniciarAtendimento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Parametros --- ver...
		String operacaoARealizar = request.getParameter("operacaoARealizar");
		String registroOcorrencia = request.getParameter("registroOcorrencia");
		nomeSolicitante = request.getParameter("nomeSolicitante");
		telefone = request.getParameter("telefone");
		String aproxVitimas = request.getParameter("numAproxVitimas");
		if(aproxVitimas.equals("")) aproxVitimas = "0";
		numAproxVitimas = Integer.parseInt(aproxVitimas);
		obmSolicitada = request.getParameter("obmSolicitada");
		infoComplementares = request.getParameter("infoComplementares");
		origemChamado =  request.getParameter("origemChamado");
		naturezaChamado = request.getParameter("naturezaChamado");
		identificadorChamado = Integer.parseInt(request.getParameter("numeroChamado"));
		String municipio = request.getParameter("municipio");
		String bairro = request.getParameter("bairro");
		String endereco = request.getParameter("endereco");
		String numero = request.getParameter("numero");
		
		getHoraFinalChamado();

		//Artificio para setar a hora Inicial do Chamado no guardada no Servlet RegistrarChamado
		dataInicio = Chamado.getDataHoraInicioChamado();
				
		salvarChamado();
					
		if(naturezaChamado.equals("Solicitação de socorro")){
			
			
			request.setAttribute("municipio", municipio);
			request.setAttribute("bairro", bairro);
			request.setAttribute("idNumeroAtendimento", identificadorChamado);
			request.setAttribute("obmAtendimento", obmSolicitada);
			request.setAttribute("endereco", endereco);
			request.setAttribute("numero", numero);
			RequestDispatcher view = request.getRequestDispatcher("/RegistrarAtendimento.jsp");
			view.forward(request, response);
		}
		else{
			
			RequestDispatcher view = request.getRequestDispatcher("/FinalizarChamado.jsp");
			view.forward(request, response);
			
		}
		
	
	}

	private void salvarChamado() {
		
		//Criacao do Objeto Chamado com os campos da tela
		Chamado chamado = new Chamado();
		chamado.setHorainicio(dataInicio);
		chamado.setHoratermino(dataFim);
		//chamado.setHoratermino(horatermino);
		chamado.setId(identificadorChamado);
		chamado.setInfocomplementares(infoComplementares);
		//chamado.setNaturezaChamado(naturezaChamado);
		chamado.setNomeSolicitante(nomeSolicitante);
		chamado.setNumaproxvitimas(numAproxVitimas);
		//Buscar no Banco a OBM
		OBM obmUsuario = OBMDao.getInstance().listarOBMNome(obmSolicitada);
		//OBM que recebeu aquele chamado, não o atendimento
		
		//Busca no banco a Natureza do chamado
		NaturezaChamado natureza = NaturezaChamadoDao.getInstance().listarTipoNaturezaNome(naturezaChamado);
		chamado.setObm(obmUsuario);
		chamado.setOrigem(origemChamado);
		chamado.setTelefoneSolicitante(telefone);
		chamado.setNaturezaChamado(natureza);
					
		ChamadoDao.getInstance().salvar(chamado);
		
	}

	private void getHoraFinalChamado() {
		
		GregorianCalendar calendar =  new GregorianCalendar();
		calendar.add(GregorianCalendar.MONTH, 0);
		calendar.add(GregorianCalendar.HOUR_OF_DAY, 0);
		calendar.add(GregorianCalendar.MINUTE, 0);
		DateFormat formata = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		String grava = formata.format(calendar.getTime());
		dataFim = new Date(formata.format(calendar.getTime()));
	}
	
	

}
