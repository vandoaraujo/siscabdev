package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.OBM;
import modelo.SiscabException;
import modelo.Usuario;
import dao.OBMDao;
import dao.UsuarioDao;

/**
 * Servlet implementation class NovoUsuarioServlet
 */
public class NovoUsuarioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private int numRegistro; 
	private String nomeGuerra; 
	private String obm;
	private String perfil;
	private String email;
	private String senha; 
	private String status;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NovoUsuarioServlet() {
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
		

	    //<input type="hidden" name="registroUsuario" value =${usuario.numRegistro} > 
	    int registroUsuario = 0;
	    int operacao = Integer.parseInt(request.getParameter("operacaoARealizar"));
    	registroUsuario = Integer.parseInt(request.getParameter("registroUsuario"));
	  
	    
	    //Controle de qual operacao será realizada
	    
	    if(operacao == 1){
		
	    	try {
				salvar(request,response);
			} catch (SiscabException e) {
				e.printStackTrace();
			}	
		
	    }			
		else if(operacao == 2){
			
			alterar(request, response, registroUsuario);
		}
	    
		else{
			
			deletar(request, response,registroUsuario);
		}
	    
	    
					
	}
	
	

	protected void salvar (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SiscabException 
	{
			
					
			numRegistro = Integer.parseInt(request.getParameter("registro"));
			nomeGuerra = request.getParameter("nomeGuerra");
			obm = request.getParameter("obm");
			perfil = request.getParameter("perfil");
			email = request.getParameter("email");
			senha = request.getParameter("senha");
			status = request.getParameter("status");
			
			//Verifica Duplicidade de Pessoas já cadastradas no BD
			List<Usuario> usuarios = UsuarioDao.getInstance().listar();
			
			for(int it = 0;it<usuarios.size();it++){
				Usuario usu = usuarios.get(it);
				if(usu.getNomeGuerra().equals(nomeGuerra)){
					
					SiscabException siscab = new SiscabException("Usuário já cadastrado com este NomeGuerra!");
					response.sendRedirect("/siscabException.jsp");
				}
			}
					
			OBM cobm = OBMDao.getInstance().listarOBMNome(obm);
			
			OBMDao.getInstance().fechaSession();
					
			Usuario usu = new Usuario();
			usu.setNomeGuerra(nomeGuerra);
			usu.setNumRegistro(numRegistro);
			usu.setObm(cobm);
			usu.setPerfil(perfil);
			usu.setEmail(email);
			usu.setStatus(status);
			usu.setSenha(senha);
			
			UsuarioDao.getInstance().salvar(usu);
			request.setAttribute("usuario", usu);
			despacha(request, response,"salvar", usu.getNomeGuerra());
			
	
			
		
	}
	
	/*
	 * Recebe como parametro HttpRequest, response, a acao a ser executada e o nome do objeto
	 */
	private void despacha(HttpServletRequest request,
			HttpServletResponse response, String string, String nomeGuerra) {
		
			RequestDispatcher view;
			request.setAttribute("nomeUsuario", nomeGuerra);
			if(string.equals("salvar")){
				
				request.setAttribute("mensagem", "salvo com sucesso!!");
				
			}
			
			else if(string.equals("alterar")){
				request.setAttribute("mensagem", "alterado com sucesso!!");

			}
			else{
				request.setAttribute("mensagem", "deletado com sucesso!!");
			}
			
			view = request.getRequestDispatcher("paginas/administracao/usuarioSalvo.jsp");
			
		
			
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
		
		Usuario usu = UsuarioDao.getInstance().BuscaUsuarioId(registro);
		String nome = usu.getNomeGuerra();
		UsuarioDao.getInstance().deletar(usu);
		despacha(request, response, "deletar", nome);
		
		
		
		
	}

	private void alterar(HttpServletRequest request,
			HttpServletResponse response, int registro) {
		
		numRegistro = Integer.parseInt(request.getParameter("registro"));
		nomeGuerra = request.getParameter("nomeGuerra");
		obm = request.getParameter("obm");
		perfil = request.getParameter("perfil");
		email = request.getParameter("email");
		senha = request.getParameter("senha");
		status = request.getParameter("status");
		
		Usuario usu = UsuarioDao.getInstance().BuscaUsuarioId(registro);
		usu.setNumRegistro(numRegistro);
		usu.setNomeGuerra(nomeGuerra);
		OBM obmAtual = OBMDao.getInstance().listarOBMNome(obm);
		usu.setObm(obmAtual);
		usu.setPerfil(perfil);
		usu.setEmail(email);
		usu.setSenha(senha);
		usu.setStatus(status);
		UsuarioDao.getInstance().atualizar(usu);
		despacha(request, response, "alterar", usu.getNomeGuerra());		
	}
}
