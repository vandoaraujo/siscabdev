package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Atendimentos;
import modelo.OBM;
import modelo.ServicoRealizado;
import modelo.TipoViatura;
import modelo.TiposServicos;
import modelo.Viatura;
import dao.AtendimentosDao;
import dao.OBMDao;
import dao.ServicoRealizadoDao;
import dao.TipoViaturaDao;
import dao.TiposServicosDao;
import dao.ViaturaDao;

/**
 * Servlet implementation class ControlaServico
 */
public class ControlaServico extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int registroAtendimento;
	private String tipoServico;
	private int registroServico;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlaServico() {
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
		

		tipoServico = request.getParameter("tipoServico");
		registroAtendimento = Integer.parseInt(request.getParameter("numeroAtendimento"));
		registroServico = Integer.parseInt(request.getParameter("registroServico"));
		int operacao = Integer.parseInt(request.getParameter("operacaoARealizar"));
		
		
		if(operacao == 1){
				
		 	salvar(request,response);	
			
		}			
		else if (operacao == 3){
				
			deletar(request, response,registroServico);
		}
		  
		    
						
		}
		
		

		protected void salvar (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
				
				Atendimentos at = AtendimentosDao.getInstance().BuscaAtendimentoId(registroAtendimento);
				
								
				TiposServicos tipoS = TiposServicosDao.getInstance().listarTipoServicoNome(tipoServico);
					
				ServicoRealizado servico = new ServicoRealizado();
				servico.setAtendimentos(at);
				servico.setTiposervico(tipoS);
				
				ServicoRealizadoDao.getInstance().salvar(servico);
				
				request.setAttribute("servico", servico);
				despacha(request, response,"salvar", servico.getTiposervico().getTiposervico_descricao());
				
		
				
			
		}
		
		/*
		 * Recebe como parametro HttpRequest, response, a acao a ser executada e o nome do objeto
		 */
		private void despacha(HttpServletRequest request,
				HttpServletResponse response, String string, String descricaoServico) {
			
				RequestDispatcher view;
				request.setAttribute("descricaoServico", descricaoServico);
				if(string.equals("salvar")){
					
					request.setAttribute("mensagem", "salva com sucesso!!");
					
				}
				else{
					request.setAttribute("mensagem", "deletado com sucesso!!");
				}
				
				view = request.getRequestDispatcher("/mensagemServico.jsp");
				
			
				
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
				HttpServletResponse response, int registroServico) {
			
			ServicoRealizado servico = ServicoRealizadoDao.getInstance().BuscaServicoId(registroServico);
			String nomeServico = servico.getTiposervico().getTiposervico_descricao();
			ServicoRealizadoDao.getInstance().deletar(servico);
			despacha(request, response, "deletar", nomeServico);
		}
}
