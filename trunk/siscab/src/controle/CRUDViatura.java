package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.OBM;
import modelo.Usuario;
import modelo.Viatura;
import dao.OBMDao;
import dao.UsuarioDao;
import dao.ViaturaDao;

/**
 * Servlet implementation class CRUDViatura
 */
public class CRUDViatura extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private String numeroViatura; 
	private String obm;
	private String statusViatura;
	private String obsViatura;
	private int registroViatura;
	private String tipoViatura;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDViatura() {
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
		
		
		numeroViatura = request.getParameter("numero");
		obm = request.getParameter("obm");
		statusViatura = request.getParameter("status");
		obsViatura = request.getParameter("obsViatura");
		tipoViatura = request.getParameter("tipoViatura");
		registroViatura = Integer.parseInt(request.getParameter("registroViatura"));
		int operacao = Integer.parseInt(request.getParameter("operacaoARealizar"));
		
		
		if(operacao == 1){
				
		 	salvar(request,response);	
			
		}			
		else if(operacao == 2){
				
			alterar(request, response, registroViatura);
		}
		    
		else{
				
			deletar(request, response,registroViatura);
		}
		    
		    
						
		}
		
		

		protected void salvar (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
				
					
				OBM cobm = OBMDao.getInstance().listarOBMNome(obm);
					
				Viatura via = new Viatura();
				via.setNumero(numeroViatura);
				via.setObm(cobm);
				via.setTipo_viatura(tipoViatura);
				via.setViatura_obs(obsViatura);
				via.setViatura_status(statusViatura);
				
				ViaturaDao.getInstance().salvar(via);
				request.setAttribute("viatura", via);
				despacha(request, response,"salvar", via.getNumero());
				
		
				
			
		}
		
		/*
		 * Recebe como parametro HttpRequest, response, a acao a ser executada e o nome do objeto
		 */
		private void despacha(HttpServletRequest request,
				HttpServletResponse response, String string, String numeroViatura) {
			
				RequestDispatcher view;
				request.setAttribute("numeroViatura", numeroViatura);
				if(string.equals("salvar")){
					
					request.setAttribute("mensagem", "salva com sucesso!!");
					
				}
				
				else if(string.equals("alterar")){
					request.setAttribute("mensagem", "alterado com sucesso!!");

				}
				else{
					request.setAttribute("mensagem", "deletado com sucesso!!");
				}
				
				view = request.getRequestDispatcher("/mensagemViatura.jsp");
				
			
				
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
				HttpServletResponse response, int registroViatura) {
			
			Viatura via = ViaturaDao.getInstance().BuscaViaturaId(registroViatura);
			String numeroViatura = via.getNumero();
			ViaturaDao.getInstance().deletar(via);
			despacha(request, response, "deletar", numeroViatura);
		}

		private void alterar(HttpServletRequest request,
				HttpServletResponse response, int registroViatura) {
			
			Viatura via = ViaturaDao.getInstance().BuscaViaturaId(registroViatura);
			OBM obmAtual = OBMDao.getInstance().listarOBMNome(obm);
			via.setNumero(numeroViatura);
			via.setObm(obmAtual);
			via.setTipo_viatura(tipoViatura);
			via.setViatura_obs(obsViatura);
			via.setViatura_status(statusViatura);
			ViaturaDao.getInstance().atualizar(via);
			despacha(request, response, "alterar", via.getNumero());		
		}
	
}
