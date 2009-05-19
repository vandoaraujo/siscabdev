package controle;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import modelo.OBM;
import modelo.PerfilUsuario;
import modelo.Usuario;
import dao.OBMDao;
import dao.PerfilUsuarioDao;
import dao.UsuarioDao;

/**
 * Servlet implementation class EfetivaAlteracaoDadosUsuario
 */
public class EfetivaAlteracaoDadosUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  static Logger logger = Logger.getLogger(EfetivaAlteracaoDadosUsuario.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EfetivaAlteracaoDadosUsuario() {
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
	
	    logger.info(getServletName());
	    
	    int registroUsuario = Integer.parseInt(request.getParameter("registro"));
	    String email = request.getParameter("email");
	    String perfil = request.getParameter("perfil");
	    String password = request.getParameter("senha");
	    String novaSenha = request.getParameter("novaSenha");
	    String repeticaoSenha = request.getParameter("repeticaoSenha");
	    
	    
	    PerfilUsuario p = PerfilUsuarioDao.getInstance().listarPerfilNome(perfil);
	    
	    Usuario usuario = UsuarioDao.getInstance().BuscaUsuarioId(registroUsuario);
	    
	   	    	    
	    if(usuario.getSenha().equals(password)){
		
		if(novaSenha.equals(repeticaoSenha)){
		    
		    
		    usuario.setEmail(email);
		    usuario.setId(registroUsuario);
		    usuario.setPerfil(p);
		    usuario.setSenha(repeticaoSenha);
		    
		    UsuarioDao.getInstance().atualizar(usuario);
		    despacha(request, response, "sucesso", usuario.getNomeGuerra());
		}
		else{
		    despacha(request, response, "novaSenhaErrada", usuario.getNomeGuerra());
		}
	    }
	    else{
		despacha(request, response, "senhaAtualErrada", usuario.getNomeGuerra());
	    }

	    	
	}
	
	private void despacha(HttpServletRequest request,
		    HttpServletResponse response, String acao, String nomeUsuario) {

		RequestDispatcher view;
		request.setAttribute("obm", nomeUsuario);
		if (acao.equals("sucesso")) {
		    request.setAttribute("mensagem", "Dados alterados no cadastro.");
		}
		else if(acao.equals("novaSenhaErrada")){
		    request.setAttribute("mensagem", "Os campos referentes a nova senha não conferem.");
		}
		else if(acao.equals("senhaAtualErrada")){
		    request.setAttribute("mensagem", "A senha anterior não foi digitada corretamente.");
		}
			
		view = request.getRequestDispatcher("dadosAlteradosUsuarioMensagem.jsp");

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


}
