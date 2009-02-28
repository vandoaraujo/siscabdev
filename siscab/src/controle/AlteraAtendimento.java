package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Atendimentos;
import modelo.Municipio;
import modelo.OBM;
import dao.AtendimentosDao;
import dao.MunicipioDao;
import dao.OBMDao;

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
	private float coordX;
	private float coordY;
	private String status;
	private String tipoOcorrencia;
	private int registroId = 0;
	
	private Atendimentos atendimento = null;
	
       
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
	
	   	//Parametros do JSP

		 registroId = Integer.parseInt(request.getParameter("registroAtendimento"));
   	 	 obmPresente = request.getParameter("obmAtendimento");
		 municipio = request.getParameter("municipio");
		 bairro = request.getParameter("bairro");
		 logradouro = request.getParameter("logradouro");
		 numComplemento = Integer.parseInt(request.getParameter("numComplemento"));
		 coordX = Float.parseFloat(request.getParameter("coordX"));
		 coordY = Float.parseFloat(request.getParameter("coordY"));
		 status= request.getParameter("status");
		 tipoOcorrencia = request.getParameter("tipoOcorrencia");
		 
		 atendimento = AtendimentosDao.getInstance().BuscaAtendimentoId(registroId);
		 
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
		AtendimentosDao.getInstance().deletar(atendimento);
		despacha(request, response, "deletar", atendimentoNumero);
	}

	private void alterar(HttpServletRequest request,
			HttpServletResponse response, int registro) {
						 					
		 Municipio municipioDao = MunicipioDao.getInstance().listarMunicipioNome(municipio);
		 
		 System.out.println(" RECEBE MUNICIPIO ---#########################");
		 System.out.println(municipioDao.toString());
		 System.out.println("#########################");
		 			
		OBM obm = OBMDao.getInstance().listarOBMNome(obmPresente);
		
		atendimento.setObm_id(obm);
		atendimento.setMunicipio_id(municipioDao);
		atendimento.setBairro(bairro);
		atendimento.setLogradouro(logradouro);
		atendimento.setNumcompl(numComplemento);
		atendimento.setCoordx(coordX);
		atendimento.setCoordy(coordY);
		atendimento.setStatus_atendimento(status);
		atendimento.setTipoocorrencia(tipoOcorrencia);
						
		AtendimentosDao.getInstance().atualizar(atendimento);
		request.setAttribute("atendimento", atendimento);
		despacha(request, response,"alterar", atendimento.getAtendimento_numero());
		
	}
}