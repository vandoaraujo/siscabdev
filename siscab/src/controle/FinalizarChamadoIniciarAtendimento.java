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
import modelo.NaturezaChamados;
import modelo.OBM;
import modelo.Usuario;
import dao.ChamadoDao;
import dao.NaturezaChamadosDao;
import dao.OBMDao;

/**
 * Servlet implementation class FinalizarChamadoIniciarAtendimento
 */
public class FinalizarChamadoIniciarAtendimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
				
		String nomeSolicitante = request.getParameter("nomeSolicitante");
		String telefone = request.getParameter("telefone");
		String aproxVitimas = request.getParameter("numAproxVitimas");
		if(aproxVitimas.equals("")) aproxVitimas = "0";
		int numAproxVitimas = Integer.parseInt(aproxVitimas);
		String obmSolicitada = request.getParameter("obmSolicitada");
		String infoComplementares = request.getParameter("infoComplementares");
		String origemChamado =  request.getParameter("origemChamado");
		String naturezaChamado = request.getParameter("naturezaChamado");
		String id =  request.getParameter("numeroChamado");
		int identificadorChamado = Integer.parseInt(id);
		String municipio = request.getParameter("municipio");
		String bairro = request.getParameter("bairro");
		String endereco = request.getParameter("endereco");
		String numero = request.getParameter("numero");
		
			
		GregorianCalendar calendar =  new GregorianCalendar();
		calendar.add(GregorianCalendar.MONTH, 0);
		calendar.add(GregorianCalendar.HOUR_OF_DAY, 0);
		calendar.add(GregorianCalendar.MINUTE, 0);
		DateFormat formata = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		String grava = formata.format(calendar.getTime());
		Date dataFim = new Date(formata.format(calendar.getTime()));

		//Artificio para setar a hora Inicial do Chamado no guardada no Servlet RegistrarChamado
		Date dataInicio = Chamado.getHoraInicioChamado();
		
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
		NaturezaChamados natureza = NaturezaChamadosDao.getInstance().listarTipoNaturezaNome(naturezaChamado);
		chamado.setObm(obmUsuario);
		chamado.setOrigem(origemChamado);
		chamado.setTelefoneSolicitante(telefone);
		chamado.setNaturezaChamado(natureza);
					
		ChamadoDao.getInstance().salvar(chamado);
					
		System.out.println("############### SALVO COM SUCESSO!!!!!!!!! ####################");
		
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
	
	

}
