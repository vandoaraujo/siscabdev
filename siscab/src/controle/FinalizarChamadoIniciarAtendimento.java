package controle;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Chamado;
import modelo.OBM;
import dao.ChamadoDao;
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
		String naturezaChamado = request.getParameter("naturezaChamados");
		String id =  request.getParameter("numeroChamado");
		int identificadorChamado = Integer.parseInt(id);
		
		String dataHora = request.getParameter("dataHora");
			
		DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy - HH:mm");  
		java.sql.Date data = null;
		try {
			data = new java.sql.Date(fmt.parse(dataHora).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}  
			
		//Criacao do Objeto Chamado com os campos da tela
		Chamado chamado = new Chamado();
		chamado.setHorainicio(data);
		//chamado.setHoratermino(horatermino);
		chamado.setId(identificadorChamado);
		chamado.setInfocomplementares(infoComplementares);
		//chamado.setNaturezaChamado(naturezaChamado);
		chamado.setNomeSolicitante(nomeSolicitante);
		chamado.setNumaproxvitimas(numAproxVitimas);
		//Buscar no Banco a OBM
		OBM obmUsuario = OBMDao.getInstance().listarOBMNome(obmSolicitada);
		//OBM que recebeu aquele chamado, não o atendimento
		chamado.setObm(obmUsuario);
		chamado.setOrigem(origemChamado);
		chamado.setTelefoneSolicitante(telefone);
		
		ChamadoDao.getInstance().salvar(chamado);
		
		request.setAttribute("idNumeroAtendimento", identificadorChamado);
		request.setAttribute("obmAtendimento", obmUsuario);
		
		System.out.println("############### SALVO COM SUCESSO!!!!!!!!! ####################");
		
		if(naturezaChamado.equals("Solicitação de socorro")){
			
			RequestDispatcher view = request.getRequestDispatcher("/RegistrarAtendimento.jsp");
			view.forward(request, response);
		}
		else{
			
			RequestDispatcher view = request.getRequestDispatcher("/RegistrarAtendimento.jsp");
			view.forward(request, response);
			
		}


	}
	
	

}
