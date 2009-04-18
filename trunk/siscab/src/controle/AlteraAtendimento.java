package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import modelo.Atendimento;
import modelo.Municipio;
import modelo.OBM;
import modelo.TipoOcorrencia;
import modelo.Viatura;
import dao.AtendimentoDao;
import dao.MovimentaViaturaDao;
import dao.MunicipioDao;
import dao.OBMDao;
import dao.TipoOcorrenciaDao;
import dao.ViaturaDao;

/**
 * Servlet implementation class AlteraAtendimento
 */
public class AlteraAtendimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String obmPresente;
	private String municipio;
	private String bairro;
	private String logradouro;
	private int numComplemento;
	private double coordX;
	private double coordY;
	private String status;
	private String tipoOcorrencia;
	private int registroId = 0;
	List<Viatura> viaturas = null;
	private Atendimento atendimento = null;
	
	static Logger logger = Logger.getLogger(AlteraAtendimento.class);

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlteraAtendimento() {
        super();
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
	
		logger.info(getServletName());

		registroId = Integer.parseInt(request.getParameter("registroAtendimento"));
		obmPresente = request.getParameter("obmAtendimento");
		municipio = request.getParameter("municipio");
		bairro = request.getParameter("bairro");
		logradouro = request.getParameter("logradouro");
		numComplemento = Integer.parseInt(request.getParameter("numComplemento"));
		coordX = Double.parseDouble(request.getParameter("coordX"));
		coordY = Double.parseDouble(request.getParameter("coordY"));
		status= request.getParameter("status");
		tipoOcorrencia = request.getParameter("tipoOcorrencia");
		 
		atendimento = AtendimentoDao.getInstance().BuscaAtendimentoId(registroId);
		
		int operacao = Integer.parseInt(request.getParameter("operacaoARealizar"));
  	    //Controle de qual operacao será realizada
	    if(operacao == 2){
	    	
			alterar(request, response, registroId);
		}	    
		else{
			
			deletar(request, response,registroId);
		}
	    					
	}
	
	/*
	 * Recebe como parametro HttpRequest, response, a acao a ser executada e o nome do objeto
	 */
	private void despacha(HttpServletRequest request,
		HttpServletResponse response, String string, int numeroAtendimento) {
		
		RequestDispatcher view;
		request.setAttribute("numeroAtendimento", numeroAtendimento);
			
		if(string.equals("alterar")){
			request.setAttribute("mensagem", "alterado com sucesso!!");

		}
		else{
			request.setAttribute("mensagem", "deletado com sucesso!!");
		}
			
		view = request.getRequestDispatcher("/atendimentoMensagem.jsp");
			
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

	private void deletar(HttpServletRequest request,
			HttpServletResponse response, int registro) {
		
		int atendimentoNumero = atendimento.getAtendimento_numero();
		
		viaturas = MovimentaViaturaDao.getInstance().ListarViaturaNaoRepetidasEmAtendimento(atendimento.getId());
		
		ViaturaDao.getInstance().iniciaTransacao();
		
		trocaStatusViaturas();
		//Finaliza Transacao
		ViaturaDao.getInstance().finalizaTransacao();
		
		AtendimentoDao.getInstance().deletar(atendimento);
		despacha(request, response, "deletar", atendimentoNumero);
	}
	
	private void trocaStatusViaturas() {
		
		for(int i=0;i< viaturas.size(); i++){
			Viatura v = viaturas.get(i);
			v.setViatura_status("Em prontidão");
			ViaturaDao.getInstance().atualizarDiversasViaturas(v);
			logger.info(" ############ Atualizou " + " viatura!   ################");
		}
		
	}

	private void alterar(HttpServletRequest request,
			HttpServletResponse response, int registro) {
						 					
		Municipio municipioDao = MunicipioDao.getInstance().listarMunicipioNome(municipio);
		 			
		OBM obm = OBMDao.getInstance().listarOBMNome(obmPresente);
		
		TipoOcorrencia tipoO = TipoOcorrenciaDao.getInstance().listarTiposOcorrenciaNome(tipoOcorrencia);
		
		atendimento.setObm_id(obm);
		atendimento.setMunicipio_id(municipioDao);
		atendimento.setBairro(bairro);
		atendimento.setLogradouro(logradouro);
		atendimento.setNumcompl(numComplemento);
		atendimento.setCoordx(coordX);
		atendimento.setCoordy(coordY);
		atendimento.setStatus_atendimento(status);
		atendimento.setTipoocorrencia(tipoO);
						
		AtendimentoDao.getInstance().atualizar(atendimento);
		request.setAttribute("atendimento", atendimento);
		despacha(request, response,"alterar", atendimento.getAtendimento_numero());
		
	}
}